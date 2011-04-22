
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import lejos.nxt.LCD;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;
import lejos.robotics.navigation.*;
import lejos.nxt.*;
/*
this class is used for sending back data to PC
*/

class BTSender extends Thread
{
private static DataOutputStream dataOutStr;

static public void create() 
//crates connection
{
dataOutStr = PilotWrapper.conn.openDataOutputStream();
if (dataOutStr == null) {
LCD.clear();
LCD.drawString("NoDataStream", 0, 0);
try{
Thread.sleep(5000);		
} catch (Exception e) {}
}

}


static public void send()
{
//static launcher, creates instance of class and then sends data as independent process
BTSender send = new BTSender();
send.start();
} 

public void run() 
//actual place where data are send

{

try{
dataOutStr.writeInt(PilotWrapper.state);
dataOutStr.flush();
LCD.drawString("send", 0, 2);
LCD.drawInt(PilotWrapper.state, 0, 3);

} catch (Exception e)
{
LCD.drawString("error in sending", 0, 5);
}

try{
Thread.sleep(5000);		
} catch (Exception e) {}

}




}
