package com.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.controller.Controller;
import com.model.GameManager;

public class ActionPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/********************** Attributes **********************/
	public static final int Y_SIZE = 120;
	
	private GameManager gameManager;
	
	private JButton nextTurn;
	private JButton buy;
	private JButton trade;
		
		
	/*********************** Methods ***********************/
	public ActionPanel(GameManager gm, Controller c){
		super();
		gameManager = gm;
		
		
		nextTurn = new JButton("Next turn");
		nextTurn.addActionListener(c);
		nextTurn.setActionCommand("NEXT_TURN");
		nextTurn.setEnabled(false);
		
		buy = new JButton("Buy");
		buy.addActionListener(c);
		buy.setActionCommand("BUY");
		buy.setEnabled(false);
		
		trade = new JButton("Trade");
		trade.addActionListener(c);
		trade.setActionCommand("TRADE");
		trade.setEnabled(false);
		
		this.setPreferredSize(new Dimension(0, Y_SIZE));
		
		this.add(nextTurn);
		this.add(buy);
		this.add(trade);
		
		
	}
	
	public void paintComponent(Graphics g){
		nextTurn.setEnabled(gameManager.canEndTurn() && !gameManager.isBuying());
		buy.setEnabled(gameManager.canEndTurn() && !gameManager.isBuying());
		trade.setEnabled(gameManager.canEndTurn() && !gameManager.isBuying());
		
		
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
}
