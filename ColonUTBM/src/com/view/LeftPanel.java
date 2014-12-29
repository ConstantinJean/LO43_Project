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
		
		g.setColor(new Color(ColorConstants.BeerR, ColorConstants.BeerG, ColorConstants.BeerB));
		g.fillRect(10, 30, 10, 10);
		g.setColor(Color.BLACK);
		g.drawString("BEER : "+gameManager.getCurrentPlayer().getRessourceCards(Ressource.BEER), 25, 40);
		
		g.setColor(new Color(ColorConstants.SleepR, ColorConstants.SleepG, ColorConstants.SleepB));
		g.fillRect(10, 45, 10, 10);
		g.setColor(Color.BLACK);
		g.drawString("SLEEP : "+gameManager.getCurrentPlayer().getRessourceCards(Ressource.SLEEP), 25, 55);
		
		g.setColor(new Color(ColorConstants.CoffeeR, ColorConstants.CoffeeG, ColorConstants.CoffeeB));
		g.fillRect(10, 60, 10, 10);
		g.setColor(Color.BLACK);
		g.drawString("COFFEE : "+gameManager.getCurrentPlayer().getRessourceCards(Ressource.COFFEE), 25, 70);
		
		g.setColor(new Color(ColorConstants.CoursR, ColorConstants.CoursG, ColorConstants.CoursB));
		g.fillRect(10, 75, 10, 10);
		g.setColor(Color.BLACK);
		g.drawString("COURS : "+gameManager.getCurrentPlayer().getRessourceCards(Ressource.COURS), 25, 85);
		
		g.setColor(new Color(ColorConstants.FoodR, ColorConstants.FoodG, ColorConstants.FoodB));
		g.fillRect(10, 90, 10, 10);
		g.setColor(Color.BLACK);
		g.drawString("FOOD : "+gameManager.getCurrentPlayer().getRessourceCards(Ressource.FOOD), 25, 100);
	}
	
	
}
