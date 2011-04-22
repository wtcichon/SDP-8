package Simulator;
import java.awt.Point;
import java.util.*;

import Shared.Ally;
import Shared.ObjectItem;

public class SimToStrat {
/* translate state of world received from sim into same format as returned by vision
 * 
 * 
 */
	public static ObjectItem[] translate(String in){
		ObjectItem[] items = new ObjectItem[3];
		for (int i = 0; i < items.length; i++){
			items[i] = new ObjectItem();
		}
		int yellowx = 0;
		int yellowy = 0;
		float yellowAngle = 0;
		int bluex = 0;
		int bluey = 0;
		float blueAngle = 0;
		int ballx = 0;
		int bally = 0;
		int futureBallx = 0;
		int futureBally = 0;
		StringTokenizer st = new StringTokenizer(in,"|");

		String token = (String)st.nextToken();
		
	
		yellowx = (int) Ally.convertCmToPx(Integer.valueOf(token));
	

		token = (String)st.nextToken();

		yellowy = (int) Ally.convertCmToPx(Integer.valueOf(token));
		items[0].setPosition(yellowx-1, yellowy-1);
		items[0].setSize(yellowx+1, yellowy+1);

		token = (String)st.nextToken();
		items[0].setAngle(Float.valueOf(token));

		token = (String)st.nextToken();
		bluex = (int) Ally.convertCmToPx(Integer.valueOf(token));

		token = (String)st.nextToken();
		bluey = (int) Ally.convertCmToPx(Integer.valueOf(token));

		;

		items[1].setPosition(bluex-1, bluey-1);
		items[1].setSize(bluex+1, bluey+1);

		token = (String)st.nextToken();
		items[1].setAngle(Float.valueOf(token));

		token = (String)st.nextToken();
		ballx = (int) Ally.convertCmToPx(Integer.valueOf(token));

		token = (String)st.nextToken();
		bally = (int) Ally.convertCmToPx(Integer.valueOf(token));

		items[2].setPosition(ballx-1, bally-1);
		items[2].setSize(ballx+1, bally+1);
		
		token = (String)st.nextToken();
		futureBallx = (int) Ally.convertCmToPx(Integer.valueOf(token));
		
		token = (String)st.nextToken();
		futureBally = (int) Ally.convertCmToPx(Integer.valueOf(token));
		
		Point futureBall = new Point(futureBallx, futureBally);
		items[2].setAngle(futureBall);

		return items;
	}
	/*
	public static void main(String args[]){
String in = "10|20|90.0|30|40|180.0|50|60|25|55";

ObjectItem[] items = translate(in);
System.out.println("Blue coordinates " + items[0].getPosition().x + "," + items[0].getPosition().y + " angle " + items[0].getAngle());
System.out.println("Yellow coordinates " + items[1].getPosition().x + "," + items[1].getPosition().y + " angle " + items[1].getAngle());
System.out.println("Ball coordinates " + items[2].getPosition().x + "," + items[2].getPosition().y);
System.out.println("Future Ball coordinates " + items[2].getCircle().x + "," + items[2].getCircle().y);

	}
*/
}
