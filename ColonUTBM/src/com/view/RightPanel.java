package com.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.model.Player;

public class RightPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/********************** Attributes **********************/
	public static final int X_SIZE = 150;
	
	private LogPanel logPanel;
	
	private GridLayout layout;
	
/*********************** Methods ***********************/
	public RightPanel(ArrayList<Player> players){
		super();

		logPanel = new LogPanel();
		layout = new GridLayout(players.size()+1, 1);
		this.setLayout(layout);
		layout.setVgap(5);
		
		add(logPanel);
		for(Player p : players){
			PublicPlayerPanel newPan = new PublicPlayerPanel(p);
			add(newPan);
		}
		

		this.setPreferredSize(new Dimension(X_SIZE, 100));// the 2nd argument doesn't change anything
		
	}
	
	
	public void paintComponent(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
	}
	
	public LogPanel getLogPanel(){
		return logPanel;
	}


}
