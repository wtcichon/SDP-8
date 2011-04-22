package Strategy;

import java.util.LinkedList;
import java.util.Queue;

import Main.Launcher;
import PathFinding.AStarPathFinder;
import PathFinding.Path;
import Shared.ObjectItem;

public class ContinuousDrop implements Behaviour{
	private ObjectItem[] objects;
	Queue<Integer> moveCommands = new LinkedList<Integer>();
	//Behavior should happen if the robot reaches required distance behind ball
	// which will be the length of the 'directional shield' i.e. if the bot is behind
	// the far end of the directional shield on the ball it will drop down the
	// y axis into place to attack

	public boolean takeControl(ObjectItem[] o) {
		objects = o;
		// we are yellow
		// 0 = blue
		// 1 = yellow
		// 2 = ball
		//;length of shield prong set 30 past ball
		return (objects[us].getCentre().getX() > objects[2].getCentre().getX() + 100
				&& objects[us].getCentre().getY() > objects[2].getCentre().getY());
		//return (Ally.distance(objects[us].getCentre(), objects[2].getCentre()) > maxDist);
	}

	public void action() {
		System.out.println("LOL DROP");
		
		
		if(objects[us].getCentre().y > objects[2].getCentre().y){
		//using pathfinder just to standardise the behavior codes
		//a bit even though it's a simple movement
		//create new path finder and path to command objects  
		AStarPathFinder finder = new AStarPathFinder(500);
		// block to add blocks 
		//finder.addOpponent(objects[0].getCentre().x, objects[0].getCentre().y);
		//finder.addBallShield(objects[2].getCentre().x, objects[2].getCentre().y);
		finder.addPitchBoundary();
		PathToCommands ptc = new PathToCommands();
		//set path to a point directly below current position in line with ball
		// i.e. from robot (x,y) to robot's x coord, balls y coord
		Path path1 = finder.findPath(objects[us].getCentre().x, objects[us].getCentre().y,
				objects[us].getCentre().x, objects[us].getCentre().y-1);
		//path1.setFirstN(100);
		moveCommands = ptc.translate(path1, objects[us]);  
		Integer data;// = (String) moveCommands.peek();
		while ((data = (Integer) moveCommands.poll()) != null) {
			System.out.println("command " + data);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Launcher.sendCommand(data);
		}
		
		
		/*
		   double angle; 
		   angle = -90;
		   double leway = 10;
		   System.out.println("Live Movement: Aligning With Angle: " + angle);
		   Launcher.sendCommand(420000);
		   double angleo = objects[us].getAngle();
		   /*
		   while(angleo > (angle + leway) || angleo < angle){
			   angleo = objects[us].getAngle();
			   System.out.println("Angle: " + angleo);
		   }

		   while(angleo > (angle + leway) || angleo < angle){
			   angleo = objects[us].getAngle();
			   System.out.println("Angle: " + angleo);
		   }
		   Launcher.sendCommand(422000);
		*/
		Launcher.sendCommand(520000);
		while(objects[us].getCentre().y > objects[2].getCentre().y){
			objects = Launcher.findObjects();
		}
		System.out.println("out of loop");
		Launcher.sendCommand(521000);
		}else{
			//using pathfinder just to standardise the behavior codes
			//a bit even though it's a simple movement
			//create new path finder and path to command objects  
			AStarPathFinder finder = new AStarPathFinder(500);
			// block to add blocks 
			//finder.addOpponent(objects[0].getCentre().x, objects[0].getCentre().y);
			//finder.addBallShield(objects[2].getCentre().x, objects[2].getCentre().y);
			finder.addPitchBoundary();
			PathToCommands ptc = new PathToCommands();
			//set path to a point directly below current position in line with ball
			// i.e. from robot (x,y) to robot's x coord, balls y coord
			Path path1 = finder.findPath(objects[us].getCentre().x, objects[us].getCentre().y,
					objects[us].getCentre().x, objects[us].getCentre().y+1);
			//path1.setFirstN(100);
			moveCommands = ptc.translate(path1, objects[us]);  
			Integer data;// = (String) moveCommands.peek();
			while ((data = (Integer) moveCommands.poll()) != null) {
				System.out.println("command " + data);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Launcher.sendCommand(data);
			}
			
			
			/*
			   double angle; 
			   angle = -90;
			   double leway = 10;
			   System.out.println("Live Movement: Aligning With Angle: " + angle);
			   Launcher.sendCommand(420000);
			   double angleo = objects[us].getAngle();
			   /*
			   while(angleo > (angle + leway) || angleo < angle){
				   angleo = objects[us].getAngle();
				   System.out.println("Angle: " + angleo);
			   }

			   while(angleo > (angle + leway) || angleo < angle){
				   angleo = objects[us].getAngle();
				   System.out.println("Angle: " + angleo);
			   }
			   Launcher.sendCommand(422000);
			*/
			Launcher.sendCommand(520000);
			while(objects[us].getCentre().y < objects[2].getCentre().y){
				objects = MyArbitrator.objects;
			}
			System.out.println("out of loop");
			Launcher.sendCommand(521000);
		}
		
	}
	
	public void suppress() {   
		   moveCommands.clear();
	}
}

