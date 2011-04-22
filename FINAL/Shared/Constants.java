package Shared;
import java.awt.Point;


public class Constants {
	/*111 111 = Initiialise

	121 XXX = Forward xxx units

	122 XXX = Reverse xxx units

	123 XXX = Turn xxx degrees (clockwise)

	223 XXX = Turn xxx degrees (counter clockwise)  

	124 000 = Kick

	133 001 = Our end is left

	133 002 = Our end is right

	144 001 = Our colour is blue

	144 002 = Our colour is yellow

	155 001 = Penalty to us

	155 002 = Penalty to them

	199 999 = Stop 
	  */
	public static int set_our_colour =-1;  // needs to be 0 for blue plate and 1 for yellow plate (or 2 for ball :) )
	public static int set_opp_colour = -1;
	public static int set_side = 0;	// needs to be -1 for left and 1 for right side of the pitch
	public static Point ourGoal;
	public static Point oppGoal;
	
	
	public static final int FORWARD = 121000;
	public static final int BACKWARD = 122000;
	public static final int TURN = 123000;
	public static final int TURN_BACK = 223000;
	public static final int KICK = 124000;
	public static final int SHUT = 199999;
	public static final int STOP = 654321;
	public static final Point LEFT_GOAL = new Point(24,244);
	public static final int MOVE_SPEED = 777000;
	public static final int TURN_SPEED = 888000;
	public static final int CLEAR = 101010;
}
