package Simulator;
import java.awt.Point;
import java.awt.geom.*;
import java.util.LinkedList;
import java.util.Queue;


public class SimRobot{
	
	//variables
	
	private double speed;      //robot speed pixels per time unit
	private double rotSpeed;   //rotation speed degrees/time unit 
	private double points[][]; //description on bottom
	private int inch= 5;
	int lastCommand = 0;
	int command =0;
	double distance= 0;
	private Queue<Integer[]> commands = new LinkedList<Integer[]>();
	public boolean wantsToKick = false;
	

	
	//Getting the vector of directions used for moving the ball
	
	public Point getDirectionOfThisRobot()
	{
		
		//lastCommand = 1;
		Point p = new Point();
//		if (lastCommand == Commands.FORWARD)
		if (lastCommand == 1)
		p = new Point((int) Math.round(points[1][0] - points[0][0]),
				(int) Math.round(points[1][1]- points[0][1]));
		if (lastCommand == 2)
			p = new Point((int) Math.round(points[0][0] - points[1][0]),
					(int) Math.round(points[0][1]- points[1][1]));
		if (lastCommand == 4)
			p = new Point((int) Math.round(points[0][0] - points[3][0]),
					(int) Math.round(points[0][1]- points[3][1]));
		if (lastCommand == 3)
				p = new Point((int) Math.round(points[2][0] - points[1][0]),
						(int) Math.round(points[2][1]- points[1][1]));
			//System.out.println(lastCommand);
		if (lastCommand == 5)
				p = new Point((int) Math.round(points[1][0] - points[0][0]),
						(int) Math.round(points[1][1]- points[0][1]));	
		return p;
	}
	
	// get direction of the robot for kicking
	public Point getDirectionOfThisRobotKick()
	{
		
		Point p = new Point();
//		if (lastCommand == Commands.FORWARD)
		p = new Point((int) Math.round(points[1][0] - points[0][0]),
				(int) Math.round(points[1][1]- points[0][1]));
		return p;
	}
	
	// setting the points
	public SimRobot(double spd,double rot,double p[][])
	{
		speed = spd;
		rotSpeed = rot;
		points = new double[5][2];
		for (int i=0;i<5;i++)
			for (int j=0;j<2;j++)
				points[i][j] =p[i][j];
		
		
	}
	
	public int[][] getPoints()
	{
		int p[][] = new int[5][2];
		for (int i=0;i<5;i++)
		{
			for (int j=0;j<2;j++)
				p[i][j] = (int) Math.round(points[i][j]);
	
			
			}
		return p;
	}

	// getting the 4 corners of the robot
	public Point[] giveMeARobot()
	{
		//System.out.print("robot");
		Point robot[] = {
				new Point((int) Math.round(points[0][0]),(int) Math.round(points[0][1])),
				new Point((int) Math.round(points[1][0]),(int) Math.round(points[1][1])),
				new Point((int) Math.round(points[3][0]),(int) Math.round(points[3][1])),
				new Point((int) Math.round(points[2][0]),(int) Math.round(points[2][1])),
						};
		
		return robot;
		
	}
	
	
	public double getAngle()
	{
		double a;
		double x = points[1][0] - points[0][0];
		double y = points[1][1] - points[0][1];
				
		a = Math.toDegrees(Math.acos(y/Math.sqrt(x*x+y*y)));
		if (x<0) return -a;
		return a;
	}
	
	public Point getCentre()
	{
		
		return new Point((int) Math.round(points[4][0])/2,
				(int) Math.round(points[4][1]/2));
	}
	
	// getting the front area of the robot and setting a shooting area for the kick
	public Point[] giveMeFrontRobot()
	{
		double cos,x,y;
		int kick = 10;
	    cos = (points[1][0]-points[0][0])

	            /Math.sqrt(

	                            Math.pow((points[1][0]-points[0][0]), 2)+Math.pow((points[1][1]-points[0][1]), 2)

	                            ); 

	    x = cos * kick;

	    y = Math.sqrt(kick*kick - x*x);

		//System.out.print("front part");
		Point robotfront[] = {
					new Point((int) Math.round(points[1][0]),
							(int) Math.round(points[1][1])),
							new Point((int) Math.round(points[1][0]+x),
									(int) Math.round(points[1][1]+y)),
									
									new Point((int) Math.round(points[3][0]+x),
											(int) Math.round(points[3][1]+y)),
											
							new Point((int) Math.round(points[3][0]),
									(int) Math.round(points[3][1]))
												};
		
		return robotfront;
		
		
	}

	public void addToQueue(Integer[] i){
		commands.add(i);
	}
	
	public void clearQueue(){
		commands.clear();
	}
	
	public boolean hasElements(){
		return (commands.size() > 0); 
	}
	
	//setting robot command 
	
	public void setCommand(int RobotsNewCommand){command = RobotsNewCommand;}
	
	// setting the robots distance
	public void setDist(int d){distance = d;}
	
	// setting robot movement and distance
	public void Robotmoving(){
		
		if (distance <= 0) {
			if (!commands.isEmpty()){
				Integer[] nextCommand = commands.remove();
				command = nextCommand[0];
				distance = nextCommand[1];
			} else {
				return;
			}
		}
		if (command == 1){
			
        	forward();
		distance = distance - speed;}
        	else if (command == 2){
            	backward();
		distance = distance - speed;}
        	else if (command == 3){
            	rotateAntiClockWise();
		distance = distance - rotSpeed;}
        	else if (command == 4){
                rotateClockWise();
		distance = distance - rotSpeed;}
        	else if (command == 5){
        		if (wantsToKick == false){
        			wantsToKick  = true;
        		}
        		
        	}
	}
	
	//FORWARD
public void forward()
{
	//double x = distance;
	double cos,x,y;
	//if(distance >0 ){
    cos = (points[1][0]-points[0][0])

            /Math.sqrt(

                            Math.pow((points[1][0]-points[0][0]), 2)+Math.pow((points[1][1]-points[0][1]), 2)

                            ); 
    
    x = cos * speed;

    y = Math.sqrt(speed*speed - x*x);
    
    
    double[] futureLeftFrontPoint = new double[2];
    double[] futureRightFrontPoint = new double[2];
    
    futureLeftFrontPoint[0] = points[1][0] + x;
    futureRightFrontPoint[0] = points[3][0] + x;
    
    if (points[1][1] < points[0][1])  {

        futureLeftFrontPoint[1] = points[1][1] - y;
        futureRightFrontPoint[1] = points[3][1] - y;}
    
   else{
    	futureLeftFrontPoint[1] = points[1][1] + y;
    	futureRightFrontPoint[1] = points[3][1] + y;
    	}
    
    
    if (futureLeftFrontPoint[0] > 0 && futureRightFrontPoint[0] > 0 && futureLeftFrontPoint[0] < 97*inch &&  futureRightFrontPoint[0] < 97*inch && futureLeftFrontPoint[1] > 0 && futureRightFrontPoint[1] > 0 &&
    		futureLeftFrontPoint[1] <47*inch && futureRightFrontPoint[1] < 47*inch){
    	for (int i=0;i<5;i++)

        {
    		lastCommand = 1;//take is outside of loop
                points[i][0] += x;

                if (points[1][1] < points[0][1])
                          points[i][1] -= y;       //point [0] is below point[1] then you moving up so substracting y 

                  else                                       // otherwise you are moving down so you adding it

                        points[i][1] += y;       

        }	
    }else {command = 2; distance =20;}
    
	}
    
//}
// BACKWARDS
public void backward()
{
    double cos,x,y;

    cos = (points[1][0]-points[0][0])

            /Math.sqrt(

                            Math.pow((points[1][0]-points[0][0]), 2)+Math.pow((points[1][1]-points[0][1]), 2)

                            ); 

    

    x = cos * speed;

    y = Math.sqrt(speed*speed - x*x);
    
    double[] futureLeftBackPoint = new double[2];
    double[] futureRightBackPoint = new double[2];
    
    futureLeftBackPoint[0] = points[0][0] - x;
    futureRightBackPoint[0] = points[2][0] - x;
    
    
    if (points[1][1] < points[0][1])  {

        futureLeftBackPoint[1] = points[0][1] + y;
        futureRightBackPoint[1] = points[2][1] + y;}
    
   else{
    	futureLeftBackPoint[1] = points[0][1] - y;
    	futureRightBackPoint[1] = points[2][1] - y;
    	}
    if (futureLeftBackPoint[0] > 0 && futureRightBackPoint[0] > 0 && futureLeftBackPoint[0] < 97*inch &&  futureRightBackPoint[0] < 97*inch && futureLeftBackPoint[1] > 0 && futureRightBackPoint[1] > 0 &&
    		futureLeftBackPoint[1] < 47*inch && futureRightBackPoint[1] < 47*inch){
    
    	for (int i=0;i<5;i++)

    {
    		lastCommand = 2; //takie it 
            points[i][0] -= x;

            if (points[1][1] < points[0][1])

                    points[i][1] += y;        //similar to previous case

                    else

                    points[i][1] -= y;

    }       

    }else {command = 1; distance =20;}
}
	
 //cut --- different implementation for the rotation
	//public void r2()
	//{
	//	int sp = 10;
	//	double theta = Math.PI/(180/sp);
	//	double sin = Math.sin(theta);
	//	double cos = Math.cos(theta);
	//	double x,y;
	//	System.out.println("angle");
	//	for (int i=0;i<4;i++)
	//	{
	//	//extract point
	//	 x = points[i][0]-points[4][0];
	//	 y = points[i][1]-points[4][1];
		//rotate
	//	 double xn = (x*cos-y*sin);
	//	 double yn = (y*cos+x*sin);
	//	 double delta = x*x - 4*(y*y-y*yn);
	//	 if (delta<0) System.out.println("crap");
	//	 else
	//	 {
	//		 delta = Math.sqrt(delta);
			 
	//		 System.out.println("sin 1 =  " +( ((-1)*x -delta)/(2*y) )  );
	//		 System.out.println("sin 2 =  " +( ((-1)*x +delta)/(2*y) )  );
	//		 System.out.println("sin th = " + Math.sin(theta));
	//	 }
		//set up new point
	//	points[i][0] = points[4][0]+xn;
	//	points[i][1] = points[4][1]+yn;
	//	}

	//	a+=sp;
	//}
	

//	int a=0;
//	public int getA()
//	{
//	return a;
//	}
	//cut ---	

// CLOCKWISE ROTATION

public void rotateClockWise()
{

	double theta = Math.PI/(180/rotSpeed);
	//double[] futureRightFrontPoint = new double[2];
	//if (points[1][1] < points[0][1])  {

        
      //  futureRightFrontPoint[1] = ;
    
   //else{
    	
    //	futureRightFrontPoint[1] = points[3][1] + y;
    //	}
		
	double futurepoints[][] = new double[4][2];
    boolean valid = true;
	for (int i=0;i<4;i++)
	{
		Point2D original = new Point2D.Double(points[i][0], points[i][1]);
		AffineTransform at = AffineTransform.getRotateInstance(theta, points[4][0], points[4][1]);
		Point2D rotated = at.transform(original, null);
		if( (rotated.getX() <0 )|| (rotated.getX() > 97*inch) ||( rotated.getY() < 0 )|| (rotated.getY() > 47*inch) ){
			valid = false;
			command =2; distance =20;
		}
		futurepoints[i][0] = rotated.getX();
		futurepoints[i][1] = rotated.getY();
		//points[i][0] = rotated.getX();
		//points[i][1] = rotated.getY();
	}
	if (valid == true){
		for (int i =0; i<4;i++){
			points[i][0] = futurepoints[i][0];
			points[i][1] = futurepoints[i][1];
			lastCommand = 3;
		}
	}
	

	
}
// ANTICLOCK WISE ROTATION
	
	public void rotateAntiClockWise()
	{
	double theta = -Math.PI/(180/rotSpeed);
	double futurepoints[][] = new double[4][2];
    boolean valid = true;
    
	for (int i=0;i<4;i++)
		{
			Point2D original = new Point2D.Double(points[i][0], points[i][1]);
			AffineTransform at = AffineTransform.getRotateInstance(theta, points[4][0], points[4][1]);
			Point2D rotated = at.transform(original, null);
			if( (rotated.getX() <0 )|| (rotated.getX() > 97*inch) ||( rotated.getY() < 0 )|| (rotated.getY() > 47*inch) ){
				valid = false;
				command =2; distance =20;
				
			}
			futurepoints[i][0] = rotated.getX();
			futurepoints[i][1] = rotated.getY();
			//points[i][0] = rotated.getX();
			//points[i][1] = rotated.getY();
			//lastCommand = 4;
		}
	if (valid == true){
		for (int i =0; i<4;i++){
			points[i][0] = futurepoints[i][0];
			points[i][1] = futurepoints[i][1];
			lastCommand = 4;
		}}
	}
	
	
	
	
	}


///robot
/*
 *     p[0]                                    p[1]
 * 		=========================================
 *      |                                       |
 *      |                                       |
 *      |              direction                |
 *      |      *---------->                     |
 *      |     p[4]                              |
 *      |                                       |
 *      |                                       |
 * 		=========================================
 *     p[2]                                      p[3]
 * 
 *  p[4] is centre of mass
 * 
*/