	

import java.io.*;

import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;
import lejos.robotics.navigation.*;
import lejos.nxt.*;


/*
 * Class to reside on the robot.  Will open up a connection
 * with the PC, using 'RobotController' on the other side.
 * 
 * @author SDP Team 8
 * @version 0.2
 */
public class RobotThread extends Thread {


//global setting	
	public int usX;
	public int usY;
	public int usAngle;
	public int themX;
	public int themY;
	public int ballX;
	public int ballY;
	
	public int ourGoalX;
	public int theirGoalX;
	
	public String startingSide = "left";
	public String stripColour = "blue";
	
	public int nextInt =0;
	public int moveSpeed = 50;
	public int turnSpeed = 100;
	public boolean runApp = false;
	
	public RobotThread() {

	
	
		
	
		// We need to refine these measurements.
		//creating pilot and navigator
		PilotWrapper.pt = new TachoPilot(8.16f, 13.5f, Motor.C, Motor.B);	
		PilotWrapper.pilot = new SimpleNavigator (PilotWrapper.pt);		
		Motor.A.setSpeed(900);
		Motor.A.setPower(100);
		
		//pilot.setMoveSpeed(100);
		PilotWrapper.pilot.setTurnSpeed(100);	
	}

//main process loop
	public void run() {
		int cnt = 0;
		runApp = true;
		LCD.clear();
		LCD.drawString("Making conn...", 0, 2);

		while (runApp) {
		this.move();
		}
	}

	public void move() {
//if there is no command
		if (PilotWrapper.commands.size()==0)		
		{
//do nothing
		return;	}
		else
		{
//else get command
		nextInt = PilotWrapper.commands.get(0); PilotWrapper.commands.remove(0);}
		boolean msgCorrectLength = false;			
		int op = nextInt/1000; //.substring(0,3);
		int valint = nextInt%1000; //Integer.parseInt(value);
//check is format correct
		if (nextInt > 99999 ) {
			msgCorrectLength = true;
		} else {
			System.out.print("All message not rcvd");
			msgCorrectLength = false;
			commsDown();
		}
	//if correct, then execute it	
		if (msgCorrectLength) {
			if (nextInt == 111111 ) {
				initRobot();
			else if (op == 121 ) {
				forward(-valint);
			}
			else if (op == 123) {
				turn(valint);
			}
			else if (op == 223) {
				turn(-valint);
			}
			else if (op == 122) {

				reverse(-valint);
			}
			else if ( nextInt == 124000) {

				kick();
			}
			else if (nextInt == 133001) {
			}
			else if (nextInt == 133002) {
			}
			else if (nextInt == 144001) {
			}
			else if (nextInt == 144002 ) {
			}
			else if (nextInt == 111666 ) {
				runApp = false;
			}
			else if (nextInt == 654321 ) {
				stopRobot();
			}
			else if (nextInt == 199999 ) {
			}
			else if (nextInt == 130208 ) {
				giveMax();
			}
			else if (op == 777 ) {
				setMovtSpeed(valint);
			}
			else if (op == 888 ) {
				setTurnSpeed(valint);
			}
				
		} else {

		LCD.drawString("KILL", 0, 3);
		}
	}
		
		
	
	/**
	 * Method to set up the start of a half.  Will be called
	 * from a 'control panel' on the main PC before each half.
	 * All coordinates are assumptions based on a pitch length
	 * of 1000 px and width of 500px, bottom left as (0,0) and
	 * top right as (1000,500).  This will need changed once we
	 * have proper resolution from vision team.
	 */
	 public void initRobot () {
		usY = 250;
		themY = 250;
		ballX = 500;
		ballY = 250;
		if (startingSide.equals("left")) {
			usX = 41;
			usAngle = 0;
			themX = 959;
			ourGoalX = 0;
			theirGoalX = 1000;
			//sets initial position of the robot for position tracking
			PilotWrapper.pilot.setPosition(41, 250, 0);
		}
		else {
			usX = 959;
			usAngle = 180;
			themX = 41;
			ourGoalX = 1000;
			theirGoalX = 0;
			//sets initial position of the robot for position tracking
			PilotWrapper.pilot.setPosition(969, 250, 180);
		}
		LCD.clear();
		LCD.drawString("init processed.", 0, 3);
		//Thread.sleep(1000);
	}
	
	/**
	 * Method to execute when 'kick' instruction is received
	 */
	public void kick() {
	//ToDo : run as separate process	
		Motor.A.backward();
		try {
			Thread.sleep(130);
		} catch (Exception e) {
			System.out.println(e);
		}
		Motor.A.forward();
		try {
			Thread.sleep(130);
		} catch (Exception e) {
			System.out.println(e);
		}
		LCD.clear();
		LCD.drawString("kick", 0, 3);
	
	}
	 
	/**
	 * Method to execute when a 'turn xx' instruction is received
	 */
	public void turn(int degree) {
		PilotWrapper.state = 3;
		BTSender.send();
		PilotWrapper.pilot.rotate(degree);
	
	}
	  
	/**
	 * Method to execute when the 'forward x' instruction is received
	 */
	public void forward(int measure) {
		PilotWrapper.state = 1;
		BTSender.send();
		PilotWrapper.pilot.travel(measure);

	}
	   
	/**
	 * Method to execute when the 'reverse x' instruction is received
	 */
	public void reverse(int measure) {
		PilotWrapper.state = 2;
		BTSender.send();
		PilotWrapper.pilot.travel(-measure);
	}
	   
	public void setSide(String side) {
		LCD.clear();
	   	LCD.drawString("Prev side: " + startingSide, 0, 3); 
	   	startingSide = side;
	   	LCD.clear();
	   	LCD.drawString("Set side now: " + startingSide, 0, 3);
	}
	
	public void setStrip(String colour)  {
		LCD.clear();
	   	LCD.drawString("Prev colour: ", 0, 3);
	   	LCD.drawString(stripColour, 0, 4); 
	   	//Thread.sleep(1000);
	   	stripColour = colour;
	   	LCD.clear();
	   	LCD.drawString("Set colour now: ", 0, 3);
	   	LCD.drawString(stripColour, 0, 4);
	   	//Thread.sleep(1000);
	}
	
	public void stopRobot() {
		PilotWrapper.state = 0;
		BTSender.send();
	
		PilotWrapper.pilot.stop();
		}
	   
	public void kill()  {
		PilotWrapper.pilot.stop();
		runApp = false;
		LCD.clear();
		System.exit(0);
	}
	
	/**
	 * This is a dummy and will be updated to define
	 * default behaviour in BT breakdown eventually
	 */
	public void commsDown() {
		stopRobot();
	}
	
	/**
	 * Setter for moveSpeed
	 */
	public void setMovtSpeed(int measure) {
		moveSpeed = measure;
	 	PilotWrapper.pilot.setMoveSpeed(moveSpeed);
	}
	 
	/**
	 * Setter for turnSpeed
	 */
	public void setTurnSpeed(int measure) {
		turnSpeed = measure;
	  	PilotWrapper.pilot.setTurnSpeed(turnSpeed);
	}
	  
	/**
	 * Getter for moveSpeed
	 */
	public int getMoveSpeed() {
		return moveSpeed;
	}
	
	/**
	 * Getter for turnSpeed
	 */
	public int getTurnSpeed() {
		return turnSpeed;
	}
	
	public void giveMax() {
		float mms = PilotWrapper.pt.getMoveMaxSpeed();
		float tms = PilotWrapper.pt.getTurnMaxSpeed();
		float cms = PilotWrapper.pt.getMoveSpeed();
		float cts = PilotWrapper.pt.getTurnSpeed();
		LCD.clear();
		LCD.drawString("mms: " + mms, 0, 1);
		LCD.drawString("tms: " + tms, 0, 2);
		LCD.drawString("cms: " + cms, 0, 3);
		LCD.drawString("cts: " + cts, 0, 4);
	}
	   	
}

