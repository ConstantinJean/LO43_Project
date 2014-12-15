package com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.model.GameManager;
import com.model.Player;
import com.observer.Observer;

public class LeftPanel extends JPanel implements Observer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/********************** Attributes **********************/
	public static final int X_SIZE = 150;
	
	private BorderLayout layout;
	
	private ActionPanel actionPanel;
	
	private GameManager gameManager;

/*********************** Methods ***********************/
	public LeftPanel(GameManager gm){
		super();
		gameManager = gm;
		actionPanel = new ActionPanel(gameManager);
		
		
		this.layout = new BorderLayout();
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(X_SIZE, 0));// the 2nd argument doesn't change anything
		
		this.add(actionPanel, BorderLayout.SOUTH);
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.RED);
		g.drawString(gameManager.getCurrentPlayer().getName(), 10, 20);
	}
	
	
	public void update() {
		
	}
	
}
