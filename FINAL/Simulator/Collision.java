package Simulator;


import java.awt.Point;

//DETECTING COLISION
//CHECKING IS A POINT IN GIVEN AREA

public class Collision {

static	public boolean isPointIn(Point p[], Point s )
/* checks is point s in rectangle given by set of points p
 *  
 * if angle between point s and all points p is less than 90 degrees
 * if is true for all points it means s is inside box given by p
 * 
 */
{
	       for (int i = 0; i<4;i++)

	       {

	               if ( Math.abs(getAngle(subPoint(p[(i+1)%4],p[i]),subPoint(s,p[i])))>90)
	            	   return false;
	            	/* use for testing purposes 
	               else
	            	   System.out.println("collision in "+p[i].x+","+p[i].y+" to "
	            			  +p[(i+1)%4].x+","+p[(i+1)%4].y+
	            			  " with angle " + getAngle(subPoint(p[(i+1)%4],p[i]),subPoint(s,p[i]))); 
	            	*/
	       }

	return true;

	}

	       

static	public double getAngle(Point a, Point b) 

	{

	       if ((a.x==b.x)&&(a.y==b.y))

	       return 2;

	       if ((a.x+a.y == 0 )||(b.x+b.y ==0))

	       return 3;

	       double cross = a.x*b.x+a.y*b.y;

	       double lenA = Math.sqrt(a.x*a.x+a.y*a.y);

	       double lenB = Math.sqrt(b.x*b.x+b.y*b.y);

	       return Math.toDegrees(Math.acos(cross/(lenA*lenB)));

	}


	public static Point subPoint(Point a, Point b) 

	{

	       return new Point(a.x-b.x,a.y-b.y);

	}
}
