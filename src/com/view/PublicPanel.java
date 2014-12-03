package com.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.model.Player;
import com.observer.Observer;

public class PublicPanel extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/********************** Attributes **********************/
	private ArrayList<Player> players;
	
	private ArrayList<PublicPlayerPanel> playerPanel;
	
	private GridLayout layout;
	
/*********************** Methods ***********************/
	public PublicPanel(ArrayList<Player> players){
		super();
		
		this.players = players;
		playerPanel = new ArrayList<PublicPlayerPanel>();
		layout = new GridLayout(players.size(), 1);
		this.setLayout(layout);
		layout.setVgap(5);
		
		for(Player p : players){
			PublicPlayerPanel newPan = new PublicPlayerPanel(p);
			add(newPan);
		}
	}
	
	
	public void paintComponent(Graphics g){
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
	}


	
	public void update() {
		this.repaint();
	}
	
	
}
