package Strategy;

import java.awt.Point;


import Shared.Constants;
import Shared.ObjectItem;

//Modified lejos behavior interface
public interface Behaviour {

	public int us = Constants.set_our_colour; 
	public int them = Constants.set_opp_colour;
	public int ourSide = Constants.set_side; 
	public Point ourGoal = Constants.ourGoal;
	public Point oppGoal = Constants.oppGoal;
	public boolean takeControl(ObjectItem o[]);
	public void action();
	public void suppress();
}
