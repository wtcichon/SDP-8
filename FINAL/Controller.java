import Main.*;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Shared.Constants;
import Strategy.DecisionMaker;
/*
 *  Controller.java - Configures and launch system
 */

public class Controller implements ActionListener {
	
	JFrame frame;
	JPanel top;
	JPanel middle;
	JPanel bottom;
	JPanel bttop;
	JPanel btmid;
	
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JButton button5;
	JButton button6;
	JButton button7;
	JButton button8;
	JButton button9;
	JButton button10;
	
	JLabel label1;
	JLabel label2;
	JLabel label3;
	JLabel label4;
	JLabel label5;
	JLabel label6;
	JLabel label7;
	JLabel label8;
	JLabel label9;
	JLabel label10;
	JLabel label11;
	
	/* Dummy co-ordinates until we get a link from the Vision code 
	 * (working on assumption, which will be wrong, that pitch is
	 * (0,0) bottom left to (1000,500) top right, centre of goals
	 * are (0,250) and (1000,250) and so on 
	 */
	int ballX = 250;
	int ballY = 129;
	int usX = 55;
	int usY = 90;
	int themX = 800;
	int themY = 430;
	String ourSide = "Right";
	
	String colour = "";
	private int situation = 0;
	private int set_mode = 0;

	
	String mode;
	
	
	public Controller() {
	//	Creates interface
		
		frame = new JFrame("Team Elimin8");
		top = new JPanel();
		middle = new JPanel();
		bottom = new JPanel();
		
		button1 = new JButton("Our Colour = Blue");
		button1.addActionListener(this);
		button1.setActionCommand("blue");
		
		button2 = new JButton("Our Colour = Yellow");
		button2.addActionListener(this);
		button2.setActionCommand("yell");
		
		button3 = new JButton("Our Side = left");
		button3.addActionListener(this);
		button3.setActionCommand("left");
		
		button4 = new JButton("Our Side = right");
		button4.addActionListener(this);
		button4.setActionCommand("righ");
		
		button5 = new JButton("Set up for match");
		button5.addActionListener(this);
		button5.setActionCommand("init");
		
		button6 = new JButton("Penalty - us");
		button6.addActionListener(this);
		button6.setActionCommand("penu");
		
		button7 = new JButton("Penalty - them");
		button7.addActionListener(this);
		button7.setActionCommand("pent");
		
		button8 = new JButton("Stop play");
		button8.addActionListener(this);
		button8.setActionCommand("stop");
		
		button9 = new JButton("Start play");
		button9.addActionListener(this);
		button9.setActionCommand("strt");
	
		button10 = new JButton("Start sim");
		button10.addActionListener(this);
		button10.setActionCommand("simm");
		
	
		
		label1 = new JLabel("Ball co-ordinates: ");
		label2 = new JLabel("(" + ballX + ", " + ballY + ")       ");
		
		label3 = new JLabel("Robot co-ordinates: ");
		label4 = new JLabel("(" + usX + ", " + usY + ")       ");
		
		label5 = new JLabel("Opposition co-ordinates: ");
		label6 = new JLabel("(" + themX + ", " + themY + ")       ");
		
		label7 = new JLabel("Side: ");
		label8 = new JLabel(ourSide + "			");
		
		label9 = new JLabel("Strip: ");
		label10 = new JLabel(colour + "			");
		label11 = new JLabel("Start sim");
	
		
		
		top.add(button1);
		top.add(button2);
		top.add(button3);
		top.add(button4);
		
	
		
		middle.add(button5);
		middle.add(button6);
		middle.add(button7);
		middle.add(button8);
		middle.add(button9);
		middle.add(button10);

		bottom.add(label1, BorderLayout.NORTH);
		bottom.add(label2, BorderLayout.CENTER);
		bottom.add(label3, BorderLayout.SOUTH);
		bottom.add(label4);
		bottom.add(label5);
		bottom.add(label6);
		bottom.add(label7);
		bottom.add(label8);
		bottom.add(label9);
		bottom.add(label10);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(top, BorderLayout.NORTH);
		frame.add(middle, BorderLayout.CENTER);
		frame.add(bottom, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
	
		
	}
	
	public static void main(String[] args) {
		Controller controller = new Controller();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		if ("blue".equals(e.getActionCommand())) {
			//set our colour as blue and opponent as yellow
			colour = "Blue";
			label10.setText(colour + "			");
			Constants.set_our_colour = 0;
			Constants.set_opp_colour = 1;
		}
		
		if ("yell".equals(e.getActionCommand())) {
			//set our colour as yellow and opponent as blue
			colour = "Yellow";
			label10.setText(colour + "			");
			Constants.set_our_colour = 1;
			Constants.set_opp_colour = 0;
		}
		
		if ("left".equals(e.getActionCommand())) {
			//set our colour as left
			ourSide = "Left";
			label8.setText(ourSide + "			");
			Constants.set_side = -1;
			Constants.ourGoal = new Point(25,240);
			Constants.oppGoal = new Point(615,240);
		}
		
		if ("righ".equals(e.getActionCommand())) {
			//set our colour as right
			ourSide = "Right";
			label8.setText(ourSide + "			");
			Constants.set_side = 1;
			Constants.ourGoal = new Point(615,240); //values need to be checked properly
			Constants.oppGoal = new Point(25,240);
		}
		
		if ("init".equals(e.getActionCommand())) {
			//set game mode as match
			ballX = 555;
			label2.setText("(" + ballX + ", " + ballY + ")       ");
			situation = 0;
		}
		
		if ("penu".equals(e.getActionCommand())) {
			//set game mode as penalty -> we are attackers
			ballX = 666;
			label2.setText("(" + ballX + ", " + ballY + ")       ");
			situation = 1;
		}
		
		if ("pent".equals(e.getActionCommand())) {
			//set game mode as penalty -> we are defenders
			situation = 2;
			ballX = 777;
			label2.setText("(" + ballX + ", " + ballY + ")       ");
		}
		
		if ("stop".equals(e.getActionCommand())) {
			//unimplemented yet
			ballX = 888;
			label2.setText("(" + ballX + ", " + ballY + ")       ");
		}
		
		if ("strt".equals(e.getActionCommand())) {
			//launch game
			ballX = 999;
			label2.setText("(" + ballX + ", " + ballY + ")       ");
			Launcher.setUp(set_mode);
			if(situation == 0){
				DecisionMaker.launchMatch();
			} else if(situation ==1){
				DecisionMaker.launchPenaltyUs();
			} else if(situation ==2){
				DecisionMaker.launchPenaltyThem();
			}
		}
		
		if ("simm".equals(e.getActionCommand())) {
			//turns on sim
			set_mode = 1;
		}

	}
}
