package Strategy;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import PathFinding.Path;
import Shared.Ally;
import Shared.Constants;
import Shared.ObjectItem;


public class PathToCommands {
	
	//assuming (0,0) is bottom left
	
	// Given a path and an initial robot location (with the angle),
	// finds the list of commands the robot has to execute to follow
	// the path.
	
	//121 XXX = Forward xxx units
	//123 XXX = Turn xxx degrees (clockwise)

	
	public Queue<Integer> translate(Path path, ObjectItem currentLoc){
		ArrayList<Integer> commands = new ArrayList<Integer>();
		
		Queue<Integer> q = new LinkedList<Integer>();
		double currentAngle = currentLoc.getAngle();
		int f = 1;
		//int add = 0;
		
		//if currentLoc.getAngle() == 720 i don't know angle !!! 
		
		
		//For every node in the path
		for (int i = 0; i < path.getLength()-1; i++){
			
			int currentX = path.getX(i);
			int currentY = path.getY(i);
			int nextX = path.getStep(i+1).getX();
			int nextY = path.getStep(i+1).getY();
			
			double turnAngle = - currentAngle;
			//if (turnAngle >0){
			//	turnAngle = - currentAngle;
			//} else {
			//	turnAngle = currentAngle;
			//}
			int diffX = nextX - currentX;
			int diffY = nextY - currentY;
			
			// Depending on which of the 8 surrounding squares the robot
			// has to move to, computes the required angle to do so
			if(diffX > 0 && diffY == 0){
				//move right				
				turnAngle += 90;
				
				//System.out.println("Case 1: move right");
			} else if (diffX > 0 && diffY > 0){
				//move top right
				turnAngle += 45;
				//System.out.println("Case 2: move top right");
			} else if (diffX==0 && diffY > 0){
				//move top 
				turnAngle += 0;
			//	System.out.println("Case 3: move top");
			} else if (diffX < 0 && diffY > 0){
				//move top left
				turnAngle -= 45;
			//	System.out.println("Case 4: move top left");
			} else if (diffX < 0 && diffY==0){
				//move left
				turnAngle -= 90;
			//	System.out.println("Case 5: move left");
			} else if (diffX < 0 && diffY < 0){
				//move bottom left
				turnAngle -= 135;
			//	System.out.println("Case 6: move bottom left");
			} else if (diffX ==0 && diffY < 0){
				//move bottom 
				turnAngle += 180;
			//	System.out.println("Case 7: move bottom");
			} else if (diffX > 0 && diffY < 0){
				//move bottom right
			//	System.out.println("Case 8: move bottom right");
				turnAngle += 135;
			} else {
			//	System.out.println("in no case!");
			}
			//System.out.println(turnAngle);
			// Get the smallest angle possible
			if(turnAngle > 180){
				turnAngle -= 360;
			} else if (turnAngle < -180){
				turnAngle += 360;
			}
			if (turnAngle != 0){
				if(turnAngle > 10){
					commands.add((Integer) (Constants.TURN + (int) turnAngle));
					//System.out.println("turn " + turnAngle + "   " + (Integer) (Constants.TURN + (int) turnAngle));

				} else if(turnAngle < -10){
					commands.add((Integer) (Constants.TURN_BACK + (int) Math.abs(turnAngle)));
					//System.out.println("turn " + turnAngle + "   " + (Integer) (Constants.TURN_BACK + (int) Math.abs(turnAngle)));

				}
				
			
				f = 1;
			} else{
				f++;
			}
			//add = (int) Math.floor((f/10)*1.5);
			//if ((int)Ally.convertPxToCm(f) > 2){
			//if((int)Ally.convertPxToCm(f) > 10){
			commands.add((Integer) (Constants.FORWARD + (int)Ally.convertPxToCm(f)));
			//}else
			//	if ((int)Ally.convertPxToCm(f) > 0)
				//{
		//	commands.add((Integer) (Constants.FORWARD + 10)); //+add//+add
		//	}
			//System.out.println("forw,+" + Ally.convertPxToCm(f)+add);
			//System.out.println("currentAngle " + currentAngle);
			currentAngle += turnAngle;
			//System.out.println("angle after turn " + currentAngle);
			}
		//System.out.println("size array " + commands.size());
		
		/*for (int i = 0; i < commands.size(); i++){
			System.out.println(commands.get(i));
		}*/
		
		// Transform the arraylist into the needed queue
		// Only keep the last forward command before a turn
		for (int i = 0; i < commands.size(); i++){
				if(commands.get(i)/1000 == 123 || commands.get(i)/1000 ==223){
					
					if(i != 0){	
						q.add(commands.get(i-1));
					}
					q.add(commands.get(i));
				}
		}
		if (commands.size()>0)
			q.add(commands.get(commands.size()-1));
		
		return q;
	}
}
