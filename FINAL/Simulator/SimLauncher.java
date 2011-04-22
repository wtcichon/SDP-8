package Simulator;
import java.io.IOException;

import Shared.ObjectItem;


/*
 * Static class allowing to launch the different modules
 * needed for vision and control of the robot.
 */

public class SimLauncher
{

	static int  cnt = 0;	
	static Server test;
	public static void setUp()
	{
		test = new Server();
	
		try {
			test.create();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	//	System.out.println("Set up vision");
	
	
		//	rc = new RobotController();
	}

	public static ObjectItem[] findObjects()
	{
		String s = new String();
		
			try {
				s = test.rec();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				s =  "10|20|90.0|30|40|180.0|50|60|";
			}
		System.out.println("rec " + s);
		return SimToStrat.translate(s);
		//convert streing to objectItem
	//	cnt++;
	//	System.out.println("Vision called "+cnt+" times");
		
		
	}
	
	public static void rcvTranslate(String text){
		//rc.rcvTranslate(text);
	}
	
	public static void sendCommand(int command) throws IOException{
		System.out.println("sys "+command);
		test.send(command);
		//rc.sendCommand(command);
	}
}
