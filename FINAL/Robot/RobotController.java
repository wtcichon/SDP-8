

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTInfo;

/**
 * Class which sits on the PC partner to the robot.
 * Gets Bluetooth connection up and running with the NXT brick and
 * coordinates with the strategy module to collect commands and
 * send them onto the NXT brick
 *
 * @author SDP Team 8
 * @version 0.2
 */

public class RobotController extends Thread  {
	
	DataInputStream dis;
	DataOutputStream dos;
	NXTComm nxtComm;
	NXTInfo nxtInfo;
	static int state = 0;
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
			System.out.println("constructor");
			System.out.println(e);
		}
		
		// If there is a problem with this, we can change 'NXTCommFactory.BLUETOOTH' to '2'.
		nxtInfo = new NXTInfo(NXTCommFactory.BLUETOOTH, name, address);
		
		try {
			nxtComm.open(nxtInfo);
		} catch (NXTCommException e) {
			System.out.println("constructor");
			System.out.println(e);
		}
		
		InputStream is = nxtComm.getInputStream();
		OutputStream os = nxtComm.getOutputStream();
		
		dis = new DataInputStream(is);
		dos = new DataOutputStream(os);
			
	}
	 
//*** BT READER - THREADED	

	public void run() {
		
		System.out.println("Scanner On ");		
		while (true) {
		//read
		try {
			
			state = dis.readInt();
		} catch (IOException error) {
			continue;
		}			
		System.out.println("robot state : " + state);
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
	 		System.out.println("Op = " + op);
	 		this.sendCommand(op);
	 	} else {

	 		System.out.println(text + "Command received in incorrect format - ignored");
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
	
	public static void main(String[] args) throws InterruptedException {
	RobotController rc = new RobotController();

	rc.start();
	for(int i = 0;i<4;i++)
	{
	rc.rcvTranslate("forw,+010");
	Thread.sleep(50);
	rc.rcvTranslate("turn,+360");
	Thread.sleep(50);
	}

	Thread.sleep(1000);			
	System.out.println(state);


		rc.sendCommand(199999);		
			
		rc.killConn();
	}

}
