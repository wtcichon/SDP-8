import lejos.robotics.navigation.Pilot;
import lejos.robotics.navigation.SimpleNavigator;
import java.util.ArrayList;
import java.util.Queue;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;

//class contains static wrapers for different objects
//thos allows different processes to use same objects, and share data between them

class PilotWrapper
{
static public Pilot pt;
static public SimpleNavigator pilot; 
static public ArrayList<Integer> commands;
static public int state;
static public BTConnection conn;

}
