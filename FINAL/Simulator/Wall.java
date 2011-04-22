package Simulator;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class Wall extends JComponent{

	public void paintComponent ( Graphics g )
	{
		super.paintComponent(g);
		this.setBackground(Color.green);
		g.setColor(Color.blue);
		g.fillRect(100,0,80,55);
		
	}
}
