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
	
	private Player player;
	
/*********************** Methods ***********************/
	
	public PublicPlayerPanel(Player player){
		super();
		
		this.player = player;
	}
	
	public void paintComponent(Graphics g){
		g.setColor(player.getColor());
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.BLACK);
		g.drawString(player.getName(), 2, 12);
		g.drawString("VP : " + player.getVictoryPoint(), 2, 24);
	}
}
