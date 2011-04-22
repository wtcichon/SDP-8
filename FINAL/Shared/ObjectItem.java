package Shared;
import java.awt.Point;

/*	Class used to holding objects identified by inVision
 * 
 */

public class ObjectItem 
{
Point position, //upper left corner of object
		size;   //bottom right corner of object

Point originalSize; 	//original size of object
Double Angle;			//direction where robot is faced
Point centreOfCircle; 	//centre of orientation point (used for finding angle)

//ToDo : position prediction
Point prevPos; 
Point futPos;


public ObjectItem()
	{
		centreOfCircle = new Point(-1,-1);
		position = new Point(); //holds position where rectangle with robot starts
		size = new Point();		//holds position where rectangle with robot ends
								//but returns size of rectangle
	
		
		position.x = 999;  
		position.y = 999; 		
		size.x = 0;		
		size.y = 0;
		Angle = 720.;
	
	}


public void setPosition(int x, int y)
{
	position.x = x;
	position.y = y;
}


public void setAngle(double a)
{
	Angle = a;
	
}

public Point getCircle()
{
return centreOfCircle;	
}





public void setAngle(Point p)
{

	centreOfCircle.x = p.x;
	centreOfCircle.y = p.y;
	int x= p.x - getCentre().x;
	int y = p.y - getCentre().y;
	
	Angle = Math.toDegrees(Math.acos( 
	y/Math.sqrt(x*x+y*y)
		));
	 if (x<0) Angle = -Angle;
}

public double getAngle()
{
return Angle;	
}

public Point getPosition()
{
return (position);	
}

public void setSize(int x, int y)
{
	size.x = x;
	size.y = y;
}

public Point getSize()
{
	Point s = new Point();
	s.x = size.x - position.x;
	s.y = size.y - position.y;

return s;	
}


public Point getRealSize()
{
	Point s = new Point();
	s.x = size.x;
	s.y = size.y;

return s;	
}


public void SearchNewPos(int x, int y)
{
	if (x < position.x ) position.x = x;
	if (y < position.y ) position.y = y;
}

public void SearchNewSize(int x, int y)
{
	if (x > size.x ) size.x = x;
	if (y > size.y ) size.y = y;
}

public Point getCentre()
{
Point c = new Point();
c.x =  (position.x + size.x )/2;
c.y =  (position.y + size.y )/2;
return c;
}


}