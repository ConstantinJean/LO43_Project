package com.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.controller.Controller;
import com.model.GameManager;

public class TransitionPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/********************** Attributes **********************/
	private GameManager gameManager;

	/*********************** Methods ***********************/
	
	public TransitionPanel(GameManager gm, Controller c){
		super(new CardLayout());
		gameManager = gm;
		this.addMouseListener(c);
	}
	
	public void paintComponent(Graphics g){
		g.setColor(gameManager.getCurrentPlayer().getColor());
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, getWidth()/30));
		g.drawString(gameManager.getCurrentPlayer().getName(), this.getWidth()/2-getWidth()/10, this.getHeight()/2);
	}
	
	
}
