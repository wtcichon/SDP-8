package Simulator;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import java.util.Queue;

import javax.swing.JFrame;

/*
 *  Simulators GUI
 */

public class GUILauncher extends JFrame implements MouseListener,KeyListener{
    
	private int  inch=5;
//	private  Thread clock;
	
	private int command = 0; //currently executed command
	int xpos; //
	int ypos; //
	boolean mouseEntered;
	boolean pitchClick;
	
	int stratcode1 = 111;
	int stratcode2 = 111;
	String robotpoints;
	int[] val = new int[4];
	int code = 0;
	
	
    /**
     * Creates a new instance of Java2DFrame
     */
    // Defining the 4 points on the pitch
	Point pitchtopleft = new Point(0,0);
	Point pitchtopright = new Point(97*inch,0);
	Point pitchdownleft = new Point(0,47*inch);
	Point pitchdownright = new Point(97*inch,47*inch);
	
	Point pitch[] = {pitchtopleft,pitchtopright,pitchdownright,pitchdownleft};
	
	// creating the 2 robots
	static SimRobot yellow, blue;
	static Ball red;
	Graphics gr;
	private Client client;
	
	
	public GUILauncher(String s )   {  
	    super(s); 
	  //Panel p =new Panel();
	  //Label l1 = new Label ("Key Listener!" ) ;
	  //p.add(l1);  
	  //add(p);
    	
	  addKeyListener ( this ) ; 
	  setSize ( 488,244 );
	  setVisible(true);
	  addMouseListener(this);

	   addWindowListener(new WindowAdapter(){
	   public void windowClosing(WindowEvent e){
	  System.exit(0);
	     }
	  });
	  }  
    
	

    public void paint(Graphics g) {
    	
    	try {
			checkForNewCommands();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NullPointerException e2){
			
		}
    	
       g.clearRect(0, 0, 488, 244);
        System.out.println("TICK");
    	
        //draw pitch
    	
        g.setColor(Color.green);
       	g.fillRect(0, 0, 97*inch, 47*inch);
       
       	//wall
    	
       	//System.out.println("wall");
    	g.setColor(Color.red);
    	g.drawLine(0,0,97*inch,0);// top horizontal line
    	g.drawLine(0,47*inch ,97*inch,47*inch); // bottom horizontal line
    	g.drawLine(0, 0,0,47*inch); // left vertical line
    	g.drawLine(0+97*inch, 0,97*inch,47*inch); //right vertical line
  
    	//clear
     	drawRobot(g, yellow, Color.GREEN);
     	drawRobot(g, blue, Color.GREEN);
     		
     	drawBall(g, red, Color.GREEN);
     
  
     		  
     	red.moveBall();
     	// ball cooridnates 
     	
     	Point b = new Point( red.x + 4, red.y +4);//centre of the ball
     	Point b1 = new Point(red.x,red.y);// coord of the ball
     	Point b2 = new Point( red.x + 4, red.y +8); // bottom
     	Point b3 = new Point( red.x + 4, red.y);// top
     	Point b4 = new Point( red.x, red.y + 4);//left
     	Point b5 = new Point( red.x + 8, red.y +4);// right
     	
       	// get points for the blue robot
        Point r[] =  blue.giveMeARobot();
        Point r1[] = blue.giveMeFrontRobot();
        
        // get points for the yellow robot
        Point q[] =  yellow.giveMeARobot();
        Point q1[] = yellow.giveMeFrontRobot();
        
        
        
       //checking does the yellow robot have the ball inside of it 
        if (Collision.isPointIn(q,b)||Collision.isPointIn(q,b2)||Collision.isPointIn(q,b3)||Collision.isPointIn(q,b4)||Collision.isPointIn(q,b5))
         {
        	// Moves ball
        	 red.kickBall(yellow.getDirectionOfThisRobot(),1);
        	 
         }
        // checking does the blue robot have the ball inside of it   
        if (Collision.isPointIn(r,b)||Collision.isPointIn(r,b2)||Collision.isPointIn(r,b3)||Collision.isPointIn(r,b4)||Collision.isPointIn(r,b5))
             {
            	// Moves ball
            	 red.kickBall(blue.getDirectionOfThisRobot(),1);
            	 
             }
    	//robots movement
 
        	if (command == 1)
        	{
        	//set distance 
        	//set command
        		blue.setDist(40);
        		blue.setCommand(1);
        	}
        	else
        	if (command == 2){
            	blue.setDist(40);
            	blue.setCommand(2);}
        	else
        	if (command == 3){
        		blue.setDist(40);
        		blue.setCommand(3);}
        	else	
        	if (command == 4){
        		blue.setDist(40);
    			blue.setCommand(4);}
        	else
        	
        	if (command == 5){
        		if (Collision.isPointIn(r1,b))
                {
                  	 red.kickBall(blue.getDirectionOfThisRobotKick(),10);
                  	 
                   }
        		else command =1;
        	}
        	command = 0;	
               
       	 blue.Robotmoving();

        if (yellow.wantsToKick && Collision.isPointIn(q1, b)){
              	 red.kickBall(yellow.getDirectionOfThisRobotKick(),10);
              	 yellow.distance = 0;
              	 yellow.wantsToKick = false;
        }
        yellow.Robotmoving();
       
        //draw
        
        drawRobot(g, yellow, Color.YELLOW);
        drawRobot(g, blue, Color.BLUE);
     //	if (pitchClick)
        
        drawFutureBall(g, red, Color.DARK_GRAY);
        System.out.print("future ball: "+ red.futureBall().x + red.futureBall().y);
        drawBall(g, red, Color.RED);
        System.out.println("ball: " + red.x + red.y);
           
        
        try {
        	Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			retWorld();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		repaint();
    }
    
   
    	 
    public static void main(String args[]) throws IOException{
          		GUILauncher a = new GUILauncher( "Key Listener Tester");
        		a.setBackground(Color.green);
        		a.setBounds(0, 0, 488, 244);
            	a.setVisible(true);
//            	System.out.println("main");
            	double p[][] = {{50,122},{90,122},{50,158},{90,158},{51,140}};
            	yellow = new SimRobot(5,10,p);
            	double q[][] = {{200,100},{240,100},{200,136},{240,136},{210,118}};
            	blue = new SimRobot(5,10,q);
            	red = new Ball();
            	red.x = 100;
            	red.y = 100;
            	
            //	red.kickBall(new Point(16,0), 20);
            	a.establishConnection();
            	a.retWorld();
            	
    
    }
    
  

    private void checkForNewCommands() throws IOException{
   //gets and executes command from queue 	
    	if (client.commandsWaiting()){
    		code = client.rec();
    	}
    	else {
    		if(code != 520000){
    			return;
    		}
    	}
    		//code = client.rec();
    		Integer[] newCommand = new Integer[2];
    		int code1 = (code / 1000);
    		 if (code1 == 121){
    			 newCommand[0] = 1;
    			 newCommand[1] = (code % 1000)*2;
    		 } else if (code1 == 122){
    			 newCommand[0] = 2;
    			 newCommand[1] = (code % 1000)*2;
    		 } else if (code1 == 123){
    			 newCommand[0] = 3;
    			 newCommand[1] = (code % 1000);
    		 } else if (code1 == 223){
    			 newCommand[0] = 4;
    			 newCommand[1] = (code % 1000);
    		 } else if (code1 == 124){
    			 newCommand[0] = 5;
    			 newCommand[1] = 10;
    		 } else if (code1 == 520) {
    			 newCommand[0] = 1;
    			 newCommand[1] = 1;
    		 } else if (code1 == 521){
    			 newCommand[0] = 1;
    			 newCommand[1] = 0;
    		 } else if (code1 == 101){
    			 yellow.clearQueue();
    			 return;
    		 }
    		 yellow.addToQueue(newCommand);
    		 
    } 
    
    private void establishConnection() throws IOException{
   	//creates connection with stategy
    client = new Client();
   	client.establish();
    }
    
    
    
    public void retWorld() throws IOException{
//sending state of the world
    	
    	Point robotYellow = (yellow.getCentre());
    	Point robotBlue = blue.getCentre();
    	double yellowAngle = yellow.getAngle();
    	double blueAngle = blue.getAngle();
    	StringBuffer result = new StringBuffer();
    
    	
    		result.append(robotBlue.x);
    		result.append("|");
    		result.append(robotBlue.y);
    		result.append("|");
    		result.append(blueAngle);
    		result.append("|");
    		result.append(robotYellow.x);
    		result.append("|");
    		result.append(robotYellow.y);
    		result.append("|");
    		result.append(yellowAngle);
    		result.append("|");
    		
    		result.append((red.x+4)/2);
    		result.append("|");
    		result.append((red.y+4)/2);
    		result.append("|");
    		result.append((red.futureBall().x + 4)/2);
    		result.append("|");
    		result.append((red.futureBall().y + 4)/2);
    		System.out.println(result);
    		client.send(result.toString());
    }
    
    
    
    public void drawRobot(Graphics g,SimRobot r, Color c)
   //draw robot r in colour c
    {
    	
    	int[][] p = r.getPoints();
        g.setColor(c);
        g.drawLine(p[0][0],p[0][1],p[1][0],p[1][1]);
        g.drawLine(p[3][0],p[3][1],p[1][0],p[1][1]);
        g.drawLine(p[2][0],p[2][1],p[3][0],p[3][1]);
        g.drawLine(p[0][0],p[0][1],p[2][0],p[2][1]);
        g.drawLine(p[4][0],p[4][1],p[1][0],p[1][1]);
        g.drawLine(p[4][0],p[4][1],p[3][0],p[3][1]);
       
    }


    public void drawBall(Graphics g, Ball b, Color c)
    //draw ball b in colour c
    {
    	g.setColor(c);
    	g.fillOval(b.x, b.y, 8, 8);
    }
    
    public void drawFutureBall(Graphics g, Ball b, Color c){
//draw future ball    	
    	g.setColor(c);
    	g.fillOval(b.futureBall().x, b.futureBall().y, 8, 8);
    }
    
	
    @Override
	public void keyPressed(KeyEvent arg) {
		// TODO Auto-generated method stub
    }


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		//command = 0;
	}

	
	@Override
	public void keyTyped(KeyEvent arg0){
		// TODO Auto-generated method stub
		//System.out.println("key = "+arg0.getKeyChar());
    	if (arg0.getKeyChar()=='w') command =1;
		else
		if (arg0.getKeyChar()=='s') command =2;
		
		else
			if (arg0.getKeyChar()=='a') command =3;
		
		else
			if (arg0.getKeyChar()=='d') command =4;
		else 
			if (arg0.getKeyChar()=='q') command =5;
		
			
		else command =0;
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		  xpos = e.getX();
		  ypos = e.getY();
		  if (xpos > 0 && xpos < 97*inch && ypos >0 && 
				    ypos <47*inch )  pitchClick = true;
				  // if it was not then pitchClick is false;
		  else 
				   pitchClick = false;
				  //show the results of the click
		 // repaint();
		  red.x=xpos;
		  red.y=ypos;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
    
    
 }


