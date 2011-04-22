package Strategy;

import java.util.LinkedList;
import java.util.Queue;

import Main.Launcher;
import PathFinding.AStarPathFinder;
import PathFinding.Path;
import Shared.Ally;
import Shared.ObjectItem;


//Behavior to get to the ball
public class GoToBall implements Behaviour {
	int maxDist = 0; //in pixels
	int maxCommands = 2;
	private ObjectItem[] objects;
	
	Queue<Integer> moveCommands = new LinkedList<Integer>(); //move to robotcontroller?

	//Behavior should happen if the robot does not have the ball
	public boolean takeControl(ObjectItem[] o) {
		objects = o;
		return (Ally.distance(objects[us].getCentre(), objects[2].getCentre()) > maxDist);
		//return(true);
	}

	public void suppress() {
		moveCommands.clear();
	}

	//Find a path to the ball and go to it
	public void action() {
		
		System.out.println("Going to the ball");
		PathToCommands ptc = new PathToCommands();
		AStarPathFinder finder = new AStarPathFinder(500);
		
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
		Path path1 = finder.findPath(objects[us].getCentre().x, objects[us].getCentre().y,
				objects[2].getCentre().x/*+(100*ourSide)*/, objects[2].getCentre().y);/////Changed 75 --> 100 so when going to the ball from very close by it doesn't knock it
		//System.out.printmillis("old path = " + path1.getLength());
		//  if(path1.getLength() >100){
		// path1.setFirstN(100);
		//  }
		//System.out.print("new path = " + path1.getLength());
		// if (path1.getLength() == 0)
		//   objects[1].setAngle(0);
		for (int i = 0; i < path1.getLength(); i++){
			// System.out.println(path1.getX(i) + " " + path1.getY(i));
		}
		moveCommands = ptc.translate(path1, objects[us]);  
		//   System.out.println("number of commands " + moveCommands.size());
		//     String firstCommand = (String) moveCommands.poll();
		//    System.out.println(firstCommand);
		//   Launcher.rcvTranslate(firstCommand);
		Integer data;// = (String) moveCommands.peek();
		int i = 0;
		while ((data = (Integer) moveCommands.poll()) != null) {
			if (i < maxCommands ) {
				//	System.out.println("command " + data);
				Launcher.sendCommand(data);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			i++;
		}
		// Launcher.sendCommand(Constants.KICK);
	}
}
