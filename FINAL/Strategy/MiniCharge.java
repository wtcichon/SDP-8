package Strategy;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

import Main.Launcher;
import PathFinding.AStarPathFinder;
import PathFinding.Path;
import Shared.Constants;
import Shared.ObjectItem;
public class MiniCharge implements Behaviour{
private ObjectItem[] objects;
static  boolean die = false;
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
return (objects[us].getCentre().x > objects[2].getCentre().x + 50
&& objects[us].getCentre().y < objects[2].getCentre().y + 40
&& objects[us].getCentre().y > objects[2].getCentre().y - 40
);
//return (Ally.distance(objects[us].getCentre(), objects[2].getCentre()) > maxDist);
}
public void suppress() {
	die = true;	
}
public void action() {
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
objects[us].getCentre().x-1, objects[us].getCentre().y);
//path1.setFirstN(100);
moveCommands = ptc.translate(path1, objects[us]);
Integer data;// = (String) moveCommands.peek();
while ((data = (Integer) moveCommands.poll()) != null) {
try {
Thread.sleep(2000);
} catch (InterruptedException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
System.out.println("command " + data);

Launcher.sendCommand(data);
}
//set path to a point directly below current position in line with ball
// i.e. from robot (x,y) to robot's x coord, balls y coord
path1 = finder.findPath(objects[us].getCentre().x, objects[us].getCentre().y,
objects[us].getCentre().x-50, objects[us].getCentre().y);
//path1.setFirstN(100);
moveCommands = ptc.translate(path1, objects[us]);

while (!moveCommands.isEmpty()) {Launcher.sendCommand(moveCommands.remove());}

while (!die)
{
	
	
}


}
}