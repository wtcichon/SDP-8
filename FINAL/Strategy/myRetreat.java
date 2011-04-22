package Strategy;

import java.util.LinkedList;
import java.util.Queue;

import Main.Launcher;
import PathFinding.AStarPathFinder;
import PathFinding.Path;
import Shared.Ally;
import Shared.ObjectItem;
import Shared.*;


//runs if are in front of ball
public class myRetreat implements Behaviour {
	int maxDist = 0; //in pixels
	private ObjectItem[] objects;
	static  boolean die = false;
	Queue<Integer> moveCommands = new LinkedList<Integer>(); //move to robotcontroller?

	//Behavior should happen if the robot does not have the ball
	public boolean takeControl(ObjectItem[] o) {
		objects = o;

/*
Constants.ourGoal = new Point(615,240); //values need to be checked properly
Constants.oppGoal = new Point(25,240);
*/
		int ball2G = 	Math.abs(objects[2].getCentre().x - Constants.ourGoal.x);
		int us2G = 	Math.abs(objects[us].getCentre().x - Constants.ourGoal.x);
		return (ball2G<us2G);
		//return(true);
	}

	public void suppress() {
		die = true;
	}

	//Find a path to the ball and go to it
	public void action() {
			
			
		die = false;
		PathToCommands ptc = new PathToCommands();
		AStarPathFinder finder = new AStarPathFinder(5000);
		System.out.println("retreat");

		//System.out.println("Distance to ball " + Ally.distance(objects[1].getCircle(), objects[2].getCentre()));
		//System.out.println("Coords of BLUE centre  ("+objects[0].getCentre().x+","+objects[0].getCentre().y+")  angle: " + objects[0].getAngle());
		//System.out.println("Coords of BLUE front  ("+objects[0].getCircle().x+","+objects[0].getCircle().y+")");
		//System.out.println("Is Blocked: " + finder.blocked(objects[0].getCentre().x,objects[0].getCentre().y)); 
		//System.out.println("Coords of YELL centre  ("+objects[1].getCentre().x+","+objects[1].getCentre().y+")  angle: " + objects[1].getAngle());
		//System.out.println("Coords of YELL front  ("+objects[1].getCircle().x+","+objects[1].getCircle().y+")");

		//System.out.println("Is Blocked: " + finder.blocked(objects[1].getCentre().x,objects[1].getCentre().y)); 
		// System.out.println("Coords of BALL  ("+objects[2].getCentre().x+","+objects[2].getCentre().y+")");
		//System.out.println("Is Blocked: " + finder.blocked(objects[2].getCentre().x,objects[2].getCentre().y)); 	  

		//finder.addOpponent(objects[0].getCentre().x, objects[0].getCentre().y);
		//finder.addBallShield(objects[2].getCentre().x, objects[2].getCentre().y);
		//finder.addPitchBoundary();

		//assuming we are yellow
		// finder.addBlock(objects[1].getCentre().x,objects[1].getCentre().y);
		//-50 on x to put in front of ball
		int mod = 50;
		if  (Constants.ourGoal.x<300)
			mod = -mod;

int x1  =objects[us].getCentre().x;
int y1 = objects[us].getCentre().y;
int x2 = Constants.ourGoal.x-mod ;
int y2 = objects[2].getCentre().y;
System.out.println(" from "+x1+","+y1+" to "+x2+","+y2);
Path path1 = new Path();
path1 = finder.findPath(x1, y1,x2, y2);


		if (path1 != null)
		moveCommands = ptc.translate(path1, objects[us]);  
		else 
		System.out.println("path is null");
		//     String firstCommand = (String) moveCommands.poll();
		//    System.out.println(firstCommand);
		//   Launcher.rcvTranslate(firstCommand);
		Integer data;// = (String) moveCommands.peek();
		int i = 0;
		Launcher.sendCommand(101010);
		while (!moveCommands.isEmpty()) {
		Launcher.sendCommand(moveCommands.remove()
		);
			}

		while (!die) 
		{
		System.out.println(" Action ball "+ MyArbitrator.objects[2].getCentre().x+","+MyArbitrator.objects[2].getCentre().y);
		int ball2G = 	Math.abs(MyArbitrator.objects[2].getCentre().x - Constants.ourGoal.x);
		if (ball2G<50) break;
		int us2G = Math.abs(MyArbitrator.objects[us].getCentre().x - Constants.ourGoal.x);
		if  (ball2G>us2G) break;
		}
		}
		// Launcher.sendCommand(Constants.KICK);
	}

