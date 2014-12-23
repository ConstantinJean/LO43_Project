package com.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.controller.Controller;
import com.model.GameManager;
import com.model.Player;
import com.model.Ressource;

public class ShopPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/********************** Attributes **********************/
	private GameManager gameManager;
	
	private JButton UV;
	private JButton UVplus;
	private JButton CC;
	private JButton DevCard;
		
	/*********************** Methods ***********************/
	public ShopPanel(GameManager gm, Controller c){
		super();
		gameManager = gm;
		
		setLayout(null);
		
		UV = new JButton("UV");
		UV.addActionListener(c);
		UV.setActionCommand("BUY_UV");
		UV.setEnabled(false);
		add(UV);
		UV.setBounds(50, 100, 100, 30);
		
		UVplus = new JButton("UV**");
		UVplus.addActionListener(c);
		UVplus.setActionCommand("BUY_UV_PLUS");
		UVplus.setEnabled(false);
		add(UVplus);
		UVplus.setBounds(50, 150, 100, 30);
		
		CC = new JButton("CC");
		CC.addActionListener(c);
		CC.setActionCommand("BUY_CC");
		CC.setEnabled(false);
		add(CC);
		CC.setBounds(50, 200, 100, 30);
		
		DevCard = new JButton("Card");
		DevCard.addActionListener(c);
		DevCard.setActionCommand("BUY_DEV_CARD");
		DevCard.setEnabled(false);
		add(DevCard);
		DevCard.setBounds(50, 250, 100, 30);
		
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.BLACK);
		Player cp = gameManager.getCurrentPlayer();
		
		// le bouton UV :
		if(cp.getRessourceCards(Ressource.BEER) >= 1
				&& cp.getRessourceCards(Ressource.FOOD) >= 1
				&& cp.getRessourceCards(Ressource.COURS) >= 1
				&& cp.getRessourceCards(Ressource.COFFEE) >= 1)
			UV.setEnabled(true);
		else
			UV.setEnabled(false);
		g.drawString("1 Beer + 1 Food + 1 Cours + 1 Coffee", 160, 120);
		
		// le bouton UV**
		if(cp.getRessourceCards(Ressource.COURS) >= 2
				&& cp.getRessourceCards(Ressource.SLEEP) >= 3)
			UVplus.setEnabled(true);
		else
			UVplus.setEnabled(false);
		g.drawString("2 Cours + 3 Sleep", 160, 170);
		
		// le bouton CC
		if(cp.getRessourceCards(Ressource.BEER) >= 1
				&& cp.getRessourceCards(Ressource.FOOD) >= 1)
			CC.setEnabled(true);
		else
			CC.setEnabled(false);
		g.drawString("1 Beer + 1 Food", 160, 220);
		
		// le bouton development card
		if(cp.getRessourceCards(Ressource.COURS) >= 1
				&& cp.getRessourceCards(Ressource.COFFEE) >= 1
				&& cp.getRessourceCards(Ressource.SLEEP) >= 1)
			DevCard.setEnabled(true);
		else
			DevCard.setEnabled(false);
		g.drawString("1 Cours + 1 Coffe + 1 Sleep", 160, 270);
	}
	
}
