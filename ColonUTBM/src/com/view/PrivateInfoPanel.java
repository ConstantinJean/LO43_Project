package com.view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.controller.Controller;
import com.model.GameManager;
import com.model.Ressource;
import com.model.card.DevelopmentCard;
import com.model.card.DiscoveryCard;
import com.model.card.ElderCard;
import com.model.card.MonopolyCard;
import com.model.card.VictoryPointCard;

public class PrivateInfoPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/********************** Attributes **********************/
	
	private GameManager gameManager;
	private Controller controller;

	/*********************** Methods ***********************/
	public PrivateInfoPanel(GameManager gm, Controller c){
		super();
		
		gameManager = gm;
	}
	

	public void paintComponent(Graphics g){
		g.setColor(gameManager.getCurrentPlayer().getColor());
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.BLACK);
		g.drawString(gameManager.getCurrentPlayer().getName(), 10, 20);
		
		// les ressources :
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
	
		
		// les cartes de developpement
		ArrayList<DevelopmentCard> devCards = gameManager.getCurrentPlayer().getDevelopmentCards();
		this.removeAll();
		for(int i=0 ; i<devCards.size() ; i++){
			
			String name = "";
			if(devCards.get(i).getClass() == ElderCard.class){
				name = "Elder";
			}
			else if(devCards.get(i).getClass() == DiscoveryCard.class){
				name = "Discovery";
			}
			else if(devCards.get(i).getClass() == MonopolyCard.class){
				name = "Monopoly";			
			}
			else if(devCards.get(i).getClass() == VictoryPointCard.class){
				name = "V Point";
			}
			else{
				name = "Building CC";
			}
			
			
			JButton newCard = new JButton(name);
			newCard.setVisible(true);
			newCard.setBounds(10, 120+((this.getHeight()-120)/devCards.size())*i, 130, (this.getHeight()-120)/devCards.size()-3);
			newCard.addActionListener(controller);
			
			if(devCards.get(i).getClass() == ElderCard.class){
				newCard.setActionCommand("ELDER_CARD");
			}
			else if(devCards.get(i).getClass() == DiscoveryCard.class){
				newCard.setActionCommand("DISCOVERY_CARD");
			}
			else if(devCards.get(i).getClass() == MonopolyCard.class){
				newCard.setActionCommand("MONOPOLY_CARD");
			}
			else if(devCards.get(i).getClass() == VictoryPointCard.class){
				newCard.setEnabled(false);
			}
			else{
				newCard.setActionCommand("BUILDING_CC_CARD");
			}
			
			this.add(newCard);
		}
	
	
	
	
	}
	
}
