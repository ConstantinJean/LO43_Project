package com.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class LogPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/********************** Attributes **********************/
	private String text;
/*********************** Methods ***********************/
	public LogPanel(){
		super();
		text = "welcome";
	}
	public void paintComponent(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		g.drawString(text, 0, 15);
	}
	
	public String getText(){
		return text;
	}
	
	public void setText(String msg){
		text = msg;
	}
}
