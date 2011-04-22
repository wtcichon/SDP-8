package Strategy;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

import Main.Launcher;
import PathFinding.AStarPathFinder;
import PathFinding.Path;
import Shared.Constants;
import Shared.ObjectItem;

public class ForwardChargeBehavior implements Behaviour{

	int maxCommands = 1;
	private	ObjectItem[] objects;
	Queue<Integer> moveCommands = new LinkedList<Integer>(); //move to robotcontroller?
	static  boolean die = false;
	public boolean takeControl(ObjectItem o[]) {
		objects = o;

		if(  objects[us].getCentre().y < objects[2].getCentre().y + 30
				&& objects[us].getCentre().y > objects[2].getCentre().y - 30//50's were 30
				//Math.abs(objects[1].getCentre().y - objects[0].getCentre().y) > 40		
				&& objects[us].getCentre().x > objects[2].getCentre().x
				&& objects[us].getCentre().x < objects[2].getCentre().x + 120
				&& objects[us].getAngle() < (0-85)
				&& objects[us].getAngle() > (0-95)){//120 was 150
			//				&& objects[1].getCentre().x < objects[0].getCentre().x){
			//System.out.println("Charge !!!");
			return true;	
		}
		return false;
	}

	public void action() {
		System.out.println("Charging forward");

		//makes new path to +1 on the x axis to turn and face the goal
		PathToCommands ptc = new PathToCommands();
		// System.out.println("1"); 
		// System.out.println("2");
		AStarPathFinder finder = new AStarPathFinder(500);

		Path path1 = finder.findPath(objects[us].getCentre().x, objects[us].getCentre().y,
				objects[us].getCentre().x-1, objects[us].getCentre().y);
		//for (int i = 0; i < path1.getLength(); i++){
		// System.out.println(path1.getX(i) + " " + path1.getY(i));
		//}
		moveCommands = ptc.translate(path1, objects[us]);  
		Integer data;// = (String) moveCommands.peek();
		int i = 0;
		while (!moveCommands.isEmpty()) {Launcher.sendCommand(moveCommands.remove());}

		Point goal = new Point(oppGoal.x + (40)*ourSide, objects[us].getCentre().y); //x used to be 60
		//Launcher.sendCommand(Constants.FORWARD+ (int)Ally.convertPxToCm(Ally.distance(objects[1].getCentre(), goal)));
		Launcher.sendCommand(Constants.FORWARD+40);////THIS HAS BEEN SWAPPED FOR THE LINE ABOVE - 22nd 10.50pm
	
		int cnt = 0;
		while (!die)
		{
			if (cnt == 0)
				Launcher.sendCommand(Constants.KICK);
			cnt = (cnt+1)%50;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}
	//Launcher.sendCommand(Constants.STOP);
	//	Launcher.rcvTranslate("stop,+000");
	// Launcher.rcvTranslate("play,+000");

	@Override
	public void suppress() {
		die = true;	
		
	}
}