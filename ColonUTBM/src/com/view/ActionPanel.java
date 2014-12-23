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
	public static final int Y_SIZE = 150;
	
	private GameManager gameManager;
	
	private JButton nextTurn;
	private JButton buy;
	private JButton tradePlayer;
	private JButton tradeBankPort;
		
		
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
		
		tradePlayer = new JButton("Trade with a player");
		tradePlayer.addActionListener(c);
		tradePlayer.setActionCommand("TRADE_PLAYER");
		tradePlayer.setEnabled(false);
		
		tradeBankPort = new JButton("Trade with the bank");
		tradeBankPort.addActionListener(c);
		tradeBankPort.setActionCommand("TRADE_BANK_PORT");
		tradeBankPort.setEnabled(false);
		
		this.setPreferredSize(new Dimension(0, Y_SIZE));
		
		this.add(nextTurn);
		this.add(buy);
		this.add(tradePlayer);
		this.add(tradeBankPort);
		
		
	}
	
	public void paintComponent(Graphics g){
		nextTurn.setEnabled(gameManager.canEndTurn() && !gameManager.isBuying());
		buy.setEnabled(gameManager.canEndTurn() && !gameManager.isBuying());
		
		
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
}
