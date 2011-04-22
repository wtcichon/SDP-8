package Simulator;
 import java.awt.Point;



public class Ball 
{

// Coordinates
	public int x,y;
	Point d; //direction where ball is heading
	Double r; // Radious 
	private int inch= 5; //??
	int moves; //number of moves//

	//size of pitch
	Point pitchtopleft = new Point(0,0);
	Point pitchtopright = new Point(97*inch,0);
	Point pitchdownleft = new Point(0,47*inch );
	Point pitchdownright = new Point(97*inch,47*inch);
	
	Point pitch[] = {pitchtopleft,pitchtopright,pitchdownright,pitchdownleft}; //pitch points
	public Ball()
	{
	d = new Point();
	moves = 10;
	}
	
	 void stop()
	 {
		 moves =0;
	 }
	 
	//calculating the future position of the ball
	Point futureBall()
	{
		
		return new Point(x+ (d.x/8)*moves,y+(d.y/8)*moves);
	}
	
	
	void moveBall()
	{
		//if(!Collision.isPointIn(pitch, futureBall()))
		{
			//reflection/checking is the ball outside the pitch
		if (x < 0 || x > 97*inch){
			d.x*=(-1);
	//		moves = 10;
		}
		if (y < 0 || y > 47*inch){
			d.y*=(-1);
		//	moves = 10;
		}
		
		}
		if (moves ==0) return;
		// move distance.x/8
		x+= (d.x/8)*moves;
		y+= (d.y/8)*moves;
		moves--;
		
		
	}
	void kickBall(Point dir,int str)
	{
		d=dir;
		moves = str;
	//		x+=(d.x);
	//	y+= (d.y);
	}
}