package Strategy;

import java.util.LinkedList;
import java.util.Queue;
import java.awt.Point;

import Main.Launcher;
import PathFinding.AStarPathFinder;
import PathFinding.Path;
import Shared.Ally;
import Shared.ObjectItem;
import Shared.*;

//Behavior to get to the ball
public class myGoToBall implements Behaviour {
	int maxDist = 0; //in pixels
	int maxCommands = 2;
	private ObjectItem[] objects;
	static  boolean die = false;
	Queue<Integer> moveCommands = new LinkedList<Integer>(); //move to robotcontroller?

	//Behavior should happen if the robot does not have the ball
	public boolean takeControl(ObjectItem[] o) {
		objects = o;
		return (objects[2].getCentre().y>100  && objects[2].getCentre().y<400 );
		//return(true);
	}

	public void suppress() {
	if (!die)
		System.out.println("die");
	//	Launcher.sendCommand(101010);
	die = true;	
		
	}

	//Find a path to the ball and go to it
	public void action() {
		
		System.out.println("Going to the ball");
			
		PathToCommands ptc = new PathToCommands();
		AStarPathFinder finder = new AStarPathFinder(5000);
		
		Point ball = new Point(objects[2].getCentre().x,objects[2].getCentre().y);
		
		

		int mod = 20;
		if (Constants.ourGoal.x>300) mod = -mod;
		Point vec = new Point (ball.x - Constants.oppGoal.x,ball.y - Constants.oppGoal.y); 



		int y2 = (int) (((double)(vec.x+mod)/(double)(vec.x))*vec.y);

				


		Path path1 = finder.findPath(objects[us].getCentre().x, objects[us].getCentre().y, Constants.oppGoal.x+mod, Constants.oppGoal.y+y2);

		for (int i = 0; i < path1.getLength(); i++){
			// System.out.println(path1.getX(i) + " " + path1.getY(i));
		}
		Launcher.sendCommand(101010);
		moveCommands = ptc.translate(path1, objects[us]);  
		while (!moveCommands.isEmpty()) {Launcher.sendCommand(moveCommands.remove());}
		Path path2 = finder.findPath(objects[us].getCentre().x, objects[us].getCentre().y, Constants.oppGoal.x,Constants.oppGoal.y);
		moveCommands = ptc.translate(path2, objects[us]);  
		while (!moveCommands.isEmpty()) {Launcher.sendCommand(moveCommands.remove());}


		while (!die)
		{
		if (die) System.out.println("died");
		System.out.println("dx"+Math.abs(MyArbitrator.objects[2].getCentre().y - ball.y));
			
		System.out.println("dy"+Math.abs(MyArbitrator.objects[2].getCentre().y - ball.y));
		if (Math.abs(MyArbitrator.objects[2].getCentre().x - ball.x)>20) {
		System.out.println("break");
		break;}
		if (Math.abs(MyArbitrator.objects[2].getCentre().y - ball.y)>20) {
		System.out.println("break");
		break;}
		}
		System.out.println("Exit Going to the ball");
	
		// Launcher.sendCommand(Constants.KICK);
	}
}
