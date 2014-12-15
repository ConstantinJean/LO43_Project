package com.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LogPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/********************** Attributes **********************/
	private JLabel label;
/*********************** Methods ***********************/
	public LogPanel(){
		super();
		JLabel label = new JLabel();
		label.setText("welcome");
		this.add(label);
//		text = new String("welcome sljdbhvsljdbhv SDLJVHBSDhvb sldfhvbqdlfhvb");
	}
	public void paintComponent(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
}
