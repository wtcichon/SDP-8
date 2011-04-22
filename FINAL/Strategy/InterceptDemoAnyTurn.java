package Strategy;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

import Main.Launcher;
import Shared.Ally;
import Shared.Constants;
import Shared.ObjectItem;

public class InterceptDemoAnyTurn implements Behaviour{
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
		objects = o;
		// we are yellow
		// 0 = blue
		// 1 = yellow
		// 2 = ball
		
		//when ball passes the x=500 mark on the pitch the behaviour takes control
		//i.e. when the ball is rolled from the far end to the robot
		return (objects[2].getCentre().getX() < 500);
	}

	public void action() {
		System.out.println("Intercept Demo Initiated/n");
		Launcher.sendCommand(Constants.CLEAR);

		//get vector of ball
		Point vector = Ally.vectorFromTo(objects[2].getCircle(), objects[2].getCentre());
		//multiply by 5 to make larger, increase if not working
		Point aimfor = new Point(vector.x*5,vector.y*5);
		//add to initial position of ball to get where we're aiming
		aimfor = Ally.addVectors(aimfor, objects[2].getCentre());
		//vector from bot position of projected ball on the same x position so either 
		//it is above or below the robots position depending wether the projected ball
		//is
		Point abovebelow = new Point(objects[us].getCentre().x,aimfor.y);
		Point botballvector = Ally.vectorFromTo(objects[us].getCentre(), abovebelow);
		//get angle of botballvector
		double botballangle = Ally.vectorToAngle(botballvector);
		//get angle from heading to botballvector 
		botballangle = objects[us].getAngle() - botballangle;
		if(botballangle < 0){
		Launcher.sendCommand(Constants.TURN_BACK + (int)Math.abs(botballangle));
		}else{
			Launcher.sendCommand(Constants.TURN + (int)Math.abs(botballangle));
		}
		//forward move to be in line with projected point
		//can remove waits when queue is implemented
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Launcher.sendCommand(Constants.FORWARD + (int)Ally.convertPxToCm(Math.abs(objects[us].getCentre().y - aimfor.y)));
	}
	
	public void suppress() {   
		   moveCommands.clear();
	}
}
