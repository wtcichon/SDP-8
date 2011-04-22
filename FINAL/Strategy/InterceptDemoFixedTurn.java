package Strategy;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

import Main.Launcher;
import Shared.Ally;
import Shared.Constants;
import Shared.ObjectItem;
import Shared.StraightLine;

public class InterceptDemoFixedTurn implements Behaviour{
	//needs to:
	//1. set up inital condition to detect ball movement (ball passes
	//point on x at far end, needs to be moving straight line) 
	//2. figure out virtual ball to aim for 
	//3. Find angle to turn to to intercept (i.e. angle to virtual ball)
	// use angle averaging until catch is made begin move
	
	
	//while angle to virtual ball is greater than heading, turn anticlockwise
	//while angle to virtual ball is less than heading, turn clockwise]
	//when ball crosses threshold initiate forward move
	
	
	private ObjectItem[] objects;
	Queue<Integer> moveCommands = new LinkedList<Integer>();
	public boolean takeControl(ObjectItem[] o) {
		System.out.println("Checking test behaviour");
		objects = o;
		// we are yellow
		// 0 = blue
		// 1 = yellow
		// 2 = ball
		
		//when ball passes the x=500 mark on the pitch the behaviour takes control
		//i.e. when the ball is rolled from the far end to the robot
		//return (objects[2].getCentre().getX() < 500);
		return true;	
	}

	public void action() {
		System.out.println("Intercept Demo Initiated\n");
		//Launcher.sendCommand(Constants.CLEAR);
		//get vector of ball
		System.out.println("Ball : "+objects[2].getCentre().x+","+objects[2].getCentre().y);	
		System.out.println("FuBall : "+objects[2].getCircle().x+","+objects[2].getCircle().y);
		Point vector = Ally.vectorFromTo(objects[2].getCircle(), objects[2].getCentre());
		//multiply by 5 to make larger, increase if not working
		Point aimfor = new Point(vector.x*5,vector.y*5);
		//add to initial position of ball to get where we're aiming
		aimfor = Ally.addVectors(aimfor, objects[2].getCentre());
		//turn dependant on wether projected ball is above or below
		
		if(aimfor.y > objects[us].getCentre().y){
			Launcher.sendCommand(Constants.TURN_BACK + 90);
		}else{
			Launcher.sendCommand(Constants.TURN + 90);
		}
		/*
		System.out.println("I'm going "+aimfor.y+ " steps");
		//forward move to be in line with projected point
		Launcher.sendCommand(Constants.FORWARD + (int)Ally.convertPxToCm(Math.abs(objects[us].getCentre().y - aimfor.y)));
		
		Launcher.sendCommand(520000);
		while(objects[us].getCentre().y > aimfor.y){
			objects = Launcher.findObjects();
		}
		System.out.println("out of loop");
		Launcher.sendCommand(521000);
		*/
		Launcher.sendCommand(Constants.FORWARD + (int)Ally.convertPxToCm(310));
	
	}
	
	public void suppress() {   
		   moveCommands.clear();
	}
}
