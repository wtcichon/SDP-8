package Strategy;

import java.util.LinkedList;
import java.util.Queue;

import Main.Launcher;
import Shared.ObjectItem;


public class TurnToAngle implements Behaviour{
	private ObjectItem[] objects;
	Queue<Integer> moveCommands = new LinkedList<Integer>();
	//Behavior should happen if the robot reaches required distance behind ball
	// which will be the length of the 'directional shield' i.e. if the bot is behind
	// the far end of the directional shield on the ball it will drop down the
	// y axis into place to attack
	public void setVision(ObjectItem[] o)
	{
		objects = o;
		return;
	}
	public boolean takeControl(ObjectItem[] o) {
		objects = o;
		// we are yellow
		// 0 = blue
		// 1 = yellow
		// 2 = ball
		//;length of shield prong set 30 past ball
		return (true);
		
	//return (Ally.distance(objects[us].getCentre(), objects[2].getCentre()) > maxDist);
   }
	public void suppress() {   
		   moveCommands.clear();
	}
   public void action() {
	   double angle; 
	   angle = 90;
	   double leway = 10;
	   System.out.println("Live Movement: Aligning With Angle: " + angle);
	   Launcher.sendCommand(420000);
	   double angleo = objects[us].getAngle();
	   /*
	   while(angleo > (angle + leway) || angleo < angle){
		   angleo = objects[us].getAngle();
		   System.out.println("Angle: " + angleo);
	   }*/

	   while(angleo > (angle + leway) || angleo < angle){
		   angleo = objects[us].getAngle();
		   System.out.println("Angle: " + angleo);
	   }
	   Launcher.sendCommand(422000);
	   try {
		System.out.println(angleo);
		Thread.sleep(1000);
		System.out.println(objects[us].getAngle());
		Thread.sleep(10000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   //using pathfinder just to standardise the behavior codes
	   //a bit even though it's a simple movement
	   //create new path finder and path to command objects  
	   
}
}
