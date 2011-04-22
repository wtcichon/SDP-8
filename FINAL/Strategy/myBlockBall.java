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
public class myBlockBall implements Behaviour {
	int maxDist = 0; //in pixels
	int maxCommands = 2;
	private ObjectItem[] objects;
	static  boolean die = false;
	Queue<Integer> moveCommands = new LinkedList<Integer>(); //move to robotcontroller?

	//Behavior should happen if the robot does not have the ball
	public boolean takeControl(ObjectItem[] o) {
		objects = o;
		return true;
	}

	public void suppress() {
	if (!die)
		System.out.println("die");
	//	Launcher.sendCommand(101010);
	die = true;	
		
	}

	//Find a path to the ball and go to it
	public void action() {
		
		System.out.println("blocking ball");
			
		PathToCommands ptc = new PathToCommands();
		AStarPathFinder finder = new AStarPathFinder(5000);
		
		int x = objects[2].getCircle().x - objects[2].getCentre().x;
		int y = objects[2].getCircle().y - objects[2].getCentre().y;
		int X = objects[us].getCentre().x - objects[2].getCentre().x;
		
		int Y = objects[2].getCentre().y + ((int) y*X/x);
		
		Point dest = new Point(objects[us].getCentre().x,Y);
		

				


		Path path1 = finder.findPath(objects[us].getCentre().x, objects[us].getCentre().y, dest.x, dest.y);

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
			
		x = MyArbitrator.objects[2].getCircle().x - MyArbitrator.objects[2].getCentre().x;
		y = MyArbitrator.objects[2].getCircle().y - MyArbitrator.objects[2].getCentre().y;
		X = MyArbitrator.objects[us].getCentre().x -MyArbitrator.objects[2].getCentre().x;
		Y = MyArbitrator.objects[2].getCentre().y + ((int) y*X/x);
		
		Point newDest = new Point(objects[us].getCentre().x,Y);
		
		if (Ally.distance(dest,newDest)> 20)		
		break;
		}
		
		System.out.println("Exit Block");
	
		// Launcher.sendCommand(Constants.KICK);
	}
}
