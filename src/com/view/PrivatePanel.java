package com.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.model.Player;
import com.observer.Observer;

public class PrivatePanel extends JPanel implements Observer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/********************** Attributes **********************/
	public static final int X_SIZE = 150;
	
	private Player player;

/*********************** Methods ***********************/
	public PrivatePanel(Player player){
		super();
		this.player = player;
		this.setPreferredSize(new Dimension(X_SIZE, 0));// the 2nd argument doesn't change anything
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.RED);
		g.drawString(player.getName(), 10, 20);
	}
	
	
	public void update() {
		
	}
	
}
