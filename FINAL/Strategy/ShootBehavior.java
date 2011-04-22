package Strategy;

import java.util.LinkedList;
import java.util.Queue;

import Main.Launcher;
import PathFinding.AStarPathFinder;
import PathFinding.Path;
import Shared.Constants;
import Shared.ObjectItem;

public class ShootBehavior implements Behaviour{

	private	ObjectItem[] objects;
	Queue<Integer> moveCommands = new LinkedList<Integer>(); 

	public boolean takeControl(ObjectItem o[]) {
		objects = o;
		int botx = objects[us].getCentre().x;
		int boty = objects[us].getCentre().y;
		int ballx = objects[2].getCentre().x;
		int bally = objects[2].getCentre().y;
		if(
				//robot has ball i.e. ball is in front with robot facing it
				//1. ball is in front of robot 
				//boty < bally + 20 
				Math.abs(boty-bally)<20
				//&& boty > bally -20  //  bally
				//2. robot is facing ball
				&& objects[us].getAngle() > 45  //  -135 < angle < -45   
				&& objects[us].getAngle() < 135  //
				//3. robot isnt already charging goal i.e. is attacking from side
				&& boty > 200
				&& boty < 350
				//4. bot is in front of ball but also less than 100 in front
				// change for kicking range?
				&& botx > ballx
				&& botx < ballx+75
				//5. check robot is in shooting range
				&& botx > 500)
		{
			return true;	
		}
		return false;
	}


	public void action() {
		System.out.println("Shooting");
		if(objects[us].getAngle()>(0-90)){
			//makes new path to face (+1,-1) i.e. goal
			PathToCommands ptc = new PathToCommands();
			AStarPathFinder finder = new AStarPathFinder(500);
			Path path1 = finder.findPath(objects[us].getCentre().x, objects[us].getCentre().y,
					objects[us].getCentre().x-1, objects[us].getCentre().y-1);
			for (int i = 0; i < path1.getLength(); i++){
				//  	 System.out.println(path1.getX(i) + " " + path1.getY(i));
			}
			moveCommands = ptc.translate(path1, objects[us]);  
			Integer data;// = (String) moveCommands.peek();
			int i = 0;
			while ((data = (Integer) moveCommands.poll()) != null) {
				if (i < 1) {
					//		System.out.println("command " + data);
					Launcher.sendCommand(data);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						System.out.println(e.getMessage());
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				i++;
			}
			//    System.out.println("FIRE!!!");
			Launcher.sendCommand(Constants.KICK);
			/*try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

			//Launcher.sendCommand(Constants.FORWARD + 20);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Launcher.sendCommand(Constants.STOP);
			//	Launcher.rcvTranslate("stop,+000");
			// Launcher.rcvTranslate("play,+000");
		}
		if(objects[us].getAngle()<(0-90)){
			//makes new path to face (+1,-1) i.e. goal
			PathToCommands ptc = new PathToCommands();
			AStarPathFinder finder = new AStarPathFinder(500);
			Path path1 = finder.findPath(objects[us].getCentre().x, objects[us].getCentre().y,
					objects[us].getCentre().x-1, objects[us].getCentre().y+1);
			for (int i = 0; i < path1.getLength(); i++){
				System.out.println(path1.getX(i) + " " + path1.getY(i));
			}
			moveCommands = ptc.translate(path1, objects[us]);  
			Integer data;// = (String) moveCommands.peek();
			int i = 0;
			while ((data = (Integer) moveCommands.poll()) != null) {
				if (i < 1) {
					System.out.println("command " + data);
					Launcher.sendCommand(data);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				i++;
			}
			//    System.out.println("FIRE!!!");
			Launcher.sendCommand(Constants.KICK);
			/*try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/

			//Launcher.sendCommand(Constants.FORWARD + 20);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Launcher.sendCommand(Constants.STOP);
			//	Launcher.rcvTranslate("stop,+000");
			// Launcher.rcvTranslate("play,+000");
		}

	}

	public void suppress(){
	}
}
