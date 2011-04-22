package Main;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import lejos.pc.comm.*;
/**
 * Class which sits on the PC partner to the robot.
 * Gets Bluetooth connection up and running with the NXT brick and
 * coordinates with the strategy module to collect commands and
 * send them onto the NXT brick
 *
 * @author SDP Team 8
 * @version 0.2
 */

public class RobotController extends Thread {
	
	DataInputStream dis;
	DataOutputStream dos;
	NXTComm nxtComm;
	NXTInfo nxtInfo;
	public static int state;
	String name = "ELIMIN8";
	String address = "00:16:53:0A:5C:22";
	//String address = "00:16:53:08:e0:69";
	
	/**
	 * Sets up connection
	 */
	public RobotController() {
		
		try {
			// If there is a problem with this, we can change 'NXTCommFactory.BLUETOOTH' to '2'.
			nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
		} catch (NXTCommException e) {
			System.out.println(e);
		}
		
		// If there is a problem with this, we can change 'NXTCommFactory.BLUETOOTH' to '2'.
		nxtInfo = new NXTInfo(NXTCommFactory.BLUETOOTH, name, address);
		
		try {
			nxtComm.open(nxtInfo);
		} catch (NXTCommException e) {
			System.out.println(e);
		}
		
		InputStream is = nxtComm.getInputStream();
		OutputStream os = nxtComm.getOutputStream();
		
		dis = new DataInputStream(is);
		dos = new DataOutputStream(os);
			
	}
	
	
//*** BT READER - THREADED	
//reads robot states sended back by robot
	public void run() {
		
		System.out.println("Scanner On ");		

		while (true) {
		//read
		try
		{
		state = dis.readInt();
		} catch (IOException error) {
			continue;
		}		
		//if state = 3 -> robot is turning   
		if (state == 3) {Launcher.isForward = false; Launcher.angles.clear();}
			else Launcher.isForward = true;
		
		}
	}

	/**
	 * Sends command to Robot (i.e. Robot.nxj)
	 *
	 * @param number, the opcode to be sent to the NXT brick
	 */
	public void sendCommand(int number) {
			
		try {
			dos.writeInt(number);
			dos.flush();
		} catch (IOException error) {
			System.out.println("Error in sending command to robot: " + error);
		}	
	}
	
	/**
	 * Closes all connections down to avoid problems/exceptions
	 */
	public void killConn() {
		try {
			dis.close();
			dos.close();
			nxtComm.close();
		} catch (Exception error) {
			System.out.println("Error in closing connection: " + error);
		}
	}
	
	/**
	 * Takes in command from Strategy module and
	 * translates it to an op code before sending it onto robot
	 *
	 * @param text, the command (in text) sent in from the Strategy module
	 */
	 public void rcvTranslate(String text) {
	 	String command = text;
	 	String firstFour = command.substring(0,4);
	 	String sign = command.substring(5,6);
	 	String value = command.substring(6);
	 	if (command.length() == 9) {
	 	//	System.out.println("First four = " + firstFour);
	 	//	System.out.println("Sign = " + sign);
	 	//	System.out.println("Value = " + value);
	 		int op = 0;
	 		if (firstFour.equals("init")) {
	 			op = 111111;
	 		}
	 		if (firstFour.equals("shut")) {
	 			op = 111666;
	 		}
	 		if (firstFour.equals("tune")) {
	 			op = 166000;
	 		}
	 		if (firstFour.equals("kick")) {
	 			op = 124000;
	 		}
	 		if (firstFour.equals("stop")) {
	 			op = 199999;
	 		}
	 		if (firstFour.equals("forw")) {
	 			firstFour = "121";
	 			op = Integer.parseInt(firstFour + value);
	 		}
	 		if (firstFour.equals("reve")) {
	 			firstFour = "122";
	 			op = Integer.parseInt(firstFour + value);
	 		}
	 		if (firstFour.equals("turn")) {
	 			if (sign.equals("-")) {
	 				firstFour = "223";
	 			} else {
	 				firstFour = "123";
	 			}
	 			op = Integer.parseInt(firstFour + value);
	 		}
	 	//	System.out.println("Op = " + op);
	 		this.sendCommand(op);
	 	} else {
	 		System.out.println("Command received in incorrect format - ignored");
	 	}
	 }
	
	/**
	 * This will take commands from the control panel
	 * Not important (in my opinion for milestone 2)
	 * I don't think that messages from the control pannel 
	 * are for the robot - they should feed into the 
	 * Strategy module so that the end, colour and other
	 * instructions can be used in the decision making.
	 */
	public void fromController(String text) {
		
	}
	
	//use this main for tests robot and BT stream 
	/* 
		public static void main(String[] args) throws InterruptedException {
		RobotController rc = new RobotController();
		//rc.rcvTranslate("kick,+000");
		Thread.sleep(5000);
		rc.rcvTranslate("forw,+100");
	//	rc.rcvTranslate("tune,+000");
		Thread.sleep(5000);
		rc.rcvTranslate("turn,+360");
		//rc.rcvTranslate("forw,+050");
	//	rc.rcvTranslate("kick,+066");
		//rc.rcvTranslate("stop,+222");
		//rc.rcvTranslate("turn,+112");
		//rc.rcvTranslate("kick,+000");
		//rc.rcvTranslate("reve,+066");
		//rc.rcvTranslate("tunry,+3334");
	//	System.out.println("Message sent and now waiting 2 seconds before killing");
		//Thread.sleep(2000);
		rc.killConn();
	}
*/
}
