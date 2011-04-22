package Main;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Shared.Ally;
import Shared.Constants;
import Shared.ObjectItem;
import Simulator.Server;
import Simulator.SimToStrat;
import Vision.InVision;

/*
 * Static class allowing to launch the different modules
 * needed for vision and control of the robot.
 * This class is also bridge between strategy and vision, 
 * and between strategy and robot controller.
 *
 */

public class Launcher{

	//used in real mode
	static InVision v;
	static RobotController rc;
	
	//used in sim mode
	static Server test;	
	
	static int  cnt = 0;	
	static int mode = 0; //0 - reality, 1 - simulator
	
	static Boolean isForward = false;
	
	//for angle avg
	static public ArrayList<Double> angles;
	static public ObjectItem[] mem = null;
	
	//for prediction
	static public Point prevBall;
	
	
	
	public static void setUp() //set up inVison and RC
	{
		angles = new  ArrayList<Double>();
		v = new InVision();		
		rc = new RobotController();
	}

	
	public static void setUp(int x){
		mode = x;

		if (mode == 0) {setUp(); return;} //run set up for real mode
		
		//run setup for sim
		test = new Server();
		try {
			test.create();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
	}

	
	
	
	public static ObjectItem[] findObjects(){

		//reduce vision calls for prediction
		//if (cnt%3 !=0 ) return mem;
			
		if (mode == 0 ) //find object vision
		{
		cnt++;
		
		ObjectItem o[] = v.findObjects();
		
		mem = o;
		Point ball = o[2].getCentre();
		Point v = Ally.vectorFromTo(prevBall, ball);
		prevBall = o[2].getCentre();
		o[2].setAngle(Ally.addVectors(ball, v));
		
		//angle avg - if robot is not turning
		if (isForward)
			{
			angles.add(o[Constants.set_our_colour].getAngle());
			double avg = 0;
			for (int i =0; i< angles.size();i++)
				avg += angles.get(i);
			avg /= angles.size();
			o[Constants.set_our_colour].setAngle(avg);
			}
		
		return o;	
		}

		//find object simulator
		String s = new String();
		
		try {
			s = test.rec();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			//if error then received dummy value
			s =  "10|20|90.0|30|40|180.0|50|60|";
		}

	ObjectItem o[] =SimToStrat.translate(s);
	
	return o;
		
	}

	public static void rcvTranslate(String text){
		//unused now
		rc.rcvTranslate(text);
	}

	public static void sendCommand(int command){
		System.out.println("Sending  " + command);
		if (mode == 0) //sending command to robot
			{			
			rc.sendCommand(command);
			return;
			}
		 //sending command to sim
		try {
			test.send(command);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
}
