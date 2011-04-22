package Strategy;

	
import java.util.LinkedList;
import java.util.Queue;

import Main.Launcher;
import PathFinding.AStarPathFinder;
import PathFinding.Path;
import Shared.ObjectItem;


	public class MoveToFrontBall implements Behaviour {
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
			//return (objects[us].getCentre().getX() < objects[2].getCentre().getX());
			return (objects[us].getCentre().x < objects[2].getCentre().x);
		//return (Ally.distance(objects[us].getCentre(), objects[2].getCentre()) > maxDist);
	   }
		public void suppress() {   
			   moveCommands.clear();
		}
	   public void action() {
		   System.out.println("MoveToFrontBall");
		   //checks if room below ball to work around else 
		    System.out.println("Coords of BLUE centre  ("+objects[0].getCentre().x+","+objects[0].getCentre().y+")  angle: " + objects[0].getAngle());
			  //System.out.println("Coords of BLUE front  ("+objects[0].getCircle().x+","+objects[0].getCircle().y+")");
		 
			  
			  //System.out.println("Is Blocked: " + finder.blocked(objects[0].getCentre().x,objects[0].getCentre().y)); 
			  System.out.println("Coords of YELL centre  ("+objects[us].getCentre().x+","+objects[us].getCentre().y+")  angle: " + objects[us].getAngle());
			  //System.out.println("Coords of YELL front  ("+objects[us].getCircle().x+","+objects[us].getCircle().y+")");

			  //System.out.println("Is Blocked: " + finder.blocked(objects[us].getCentre().x,objects[us].getCentre().y)); 
			  System.out.println("Coords of BALL  ("+objects[2].getCentre().x+","+objects[2].getCentre().y+")");
			  //System.out.println("Is Blocked: " + finder.blocked(objects[2].getCentre().x,objects[2].getCentre().y)); 	  
			  
		   if(objects[2].getCentre().y > 240){
			   //go around bottom of ball
			   AStarPathFinder finder = new AStarPathFinder(500);
			   // block to add blocks 
			   //finder.addOpponent(objects[0].getCentre().x, objects[0].getCentre().y);
			   finder.addBallShield(objects[2].getCentre().x, objects[2].getCentre().y);
			   finder.addPitchBoundary();
				  PathToCommands ptc = new PathToCommands();
				  //set path to a point directly below current position in line with ball
				  // i.e. from robot (x,y) to robot's x coord, balls y coord
				  //int sbotx = objects[us].getCentre().x;
				  //int sballx = objects[2].getCentre().x;
				  /*
				  Path path1 = finder.findPath(objects[us].getCentre().x, objects[us].getCentre().y,
						  objects[2].getCentre().x - 100, objects[2].getCentre().y);
				  //path1.setFirstN(100);
				 // Path path2 = finder.findPath(sbotx, 360,
				//		  sballx + 100, 360);
				 // for(int i = 0; i < path2.getLength(); i++){
				//	  path1.appendStep(path2.getX(i),path2.getY(i));
				 // }
				  //Path path3 = finder.findPath(sballx + 100, 360,
					//	  sballx + 100, objects[2].getCentre().y);
				 // for(int i = 0; i < path3.getLength(); i++){
				//	  path1.appendStep(path3.getX(i),path3.getY(i));
				 // }
				  moveCommands = ptc.translate(path1, objects[us]);  
					 Integer data;// = (String) moveCommands.peek();
					// int i =0;
					 while ((data = (Integer) moveCommands.poll()) != null) {
				//		 if (i< 4){
						 System.out.println("command " + data);
						 Launcher.sendCommand(data);
						 try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					//	 }
						// i++;
						 
						 
						 */
				  Path path1 = finder.findPath(objects[us].getCentre().x, objects[us].getCentre().y,
						  objects[2].getCentre().x, objects[2].getCentre().y - 100);
				  moveCommands = ptc.translate(path1, objects[us]);  
					 Integer data;// = (String) moveCommands.peek();
					 while ((data = (Integer) moveCommands.poll()) != null) {
						 System.out.println("command " + data);
						 Launcher.sendCommand(data);
						 try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		   }
					 /*
					  path1 = finder.findPath(objects[us].getCentre().x, objects[us].getCentre().y,
							  objects[2].getCentre().x+150, objects[us].getCentre().y);
					  moveCommands = ptc.translate(path1, objects[us]);  
						 while ((data = (Integer) moveCommands.poll()) != null) {
							 System.out.println("command " + data);
							 Launcher.sendCommand(data);
							 try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			   }*/  //two of the same thing above?
		   
		   }else{
			   //go around bottom of ball
			   AStarPathFinder finder = new AStarPathFinder(500);
			   // block to add blocks 
			   //finder.addOpponent(objects[0].getCentre().x, objects[0].getCentre().y);
			   finder.addBallShield(objects[2].getCentre().x, objects[2].getCentre().y);
			   finder.addPitchBoundary();
				  PathToCommands ptc = new PathToCommands();
				  //set path to a point directly below current position in line with ball
				  // i.e. from robot (x,y) to robot's x coord, balls y coord
				  //int sbotx = objects[us].getCentre().x;
				  //int sballx = objects[2].getCentre().x;
				  
				  //added to set point away from ball so doesnt ram thru
				  Path path1 = finder.findPath(objects[us].getCentre().x, objects[us].getCentre().y,
						  objects[2].getCentre().x, objects[2].getCentre().y + 100);
				  moveCommands = ptc.translate(path1, objects[us]);  
					 Integer data;// = (String) moveCommands.peek();
					 while ((data = (Integer) moveCommands.poll()) != null) {
						 System.out.println("command " + data);
						 Launcher.sendCommand(data);
						 try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		   }
			/*		  path1 = finder.findPath(objects[us].getCentre().x, objects[us].getCentre().y,
							  objects[2].getCentre().x+150, objects[us].getCentre().y);
					  moveCommands = ptc.translate(path1, objects[us]);  
						 while ((data = (Integer) moveCommands.poll()) != null) {
							 System.out.println("command " + data);
							 Launcher.sendCommand(data);
							 try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			   }*/
				  //Path path1 = finder.findPath(sbotx, objects[us].getCentre().y,
					//	  sbotx, 360);
				  //path1.setFirstN(100);
				  //Path path2 = finder.findPath(sbotx, 360,
				//		  sballx + 100, 360);
				 // for(int i = 0; i < path2.getLength(); i++){
				//	  path1.appendStep(path2.getX(i),path2.getY(i));
				//  }
				 // Path path3 = finder.findPath(sballx + 100, 360,
				//		  sballx + 100, objects[2].getCentre().y);
				//  for(int i = 0; i < path3.getLength(); i++){
				//	  path1.appendStep(path3.getX(i),path3.getY(i));
				//  }
				/*	 while ((data = (Integer) moveCommands.poll()) != null) {
						 System.out.println("command " + data);
						 Launcher.sendCommand(data);
						 try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		   }*/
		   }

	}
}
