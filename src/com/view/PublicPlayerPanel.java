package com.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.model.Player;

public class PublicPlayerPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/********************** Attributes **********************/
	public static final int X_SIZE = 150;
	
	private Player player;
	
/*********************** Methods ***********************/
	
	public PublicPlayerPanel(Player player){
		super();
		
		this.player = player;
		this.setPreferredSize(new Dimension(X_SIZE, 0));// the 2nd argument doesn't change anything
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.BLACK);
		g.drawString(player.getName(), 2, 12);
		g.drawString("VP : " + player.getVictoryPoint(), 2, 24);
	}
}
