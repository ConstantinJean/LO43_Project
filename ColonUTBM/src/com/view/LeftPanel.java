package com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.controller.Controller;
import com.model.GameManager;
import com.model.Ressource;

public class LeftPanel extends JPanel{
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
	public LeftPanel(GameManager gm, Controller c){
		super();
		gameManager = gm;
		actionPanel = new ActionPanel(gm, c);
		
		
		this.layout = new BorderLayout();
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(X_SIZE, 0));// the 2nd argument doesn't change anything
		
		this.add(actionPanel, BorderLayout.SOUTH);
	}
	
	public void paintComponent(Graphics g){
		g.setColor(gameManager.getCurrentPlayer().getColor());
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.BLACK);
		g.drawString(gameManager.getCurrentPlayer().getName(), 10, 20);
		g.drawString("BEER : "+gameManager.getCurrentPlayer().getRessourceCards(Ressource.BEER), 10, 40);
		g.drawString("SLEEP : "+gameManager.getCurrentPlayer().getRessourceCards(Ressource.SLEEP), 10, 55);
		g.drawString("COFFEE : "+gameManager.getCurrentPlayer().getRessourceCards(Ressource.COFFEE), 10, 70);
		g.drawString("COURS : "+gameManager.getCurrentPlayer().getRessourceCards(Ressource.COURS), 10, 85);
		g.drawString("FOOD : "+gameManager.getCurrentPlayer().getRessourceCards(Ressource.FOOD), 10, 100);
	}
	
	
}
