package com.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class TransitionPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/********************** Attributes **********************/
		

	/*********************** Methods ***********************/
	
	public TransitionPanel(){
		super(new CardLayout());
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g.setColor(Color.WHITE);
		g.drawString("NEXT PLAYER", this.getWidth()/2, this.getHeight()/2);
	}
	
	
}
