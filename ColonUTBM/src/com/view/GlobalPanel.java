package com.view;

import java.awt.CardLayout;

import javax.swing.JPanel;

import com.controller.Controller;
import com.model.GameManager;

public class GlobalPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/********************** Attributes **********************/
	
	private MenuPanel menuPanel;
	private MainPanel mainPanel;
	private TransitionPanel transitionPanel;
	private PlayerTradePanel playerTradePanel;
	

	private CardLayout layout;

	/*********************** Methods ***********************/
	public GlobalPanel(GameManager gm, Controller c){
		super();
		menuPanel = new MenuPanel(gm, c);
		mainPanel = new MainPanel(gm, c);
		transitionPanel = new TransitionPanel(gm, c);
		playerTradePanel = new PlayerTradePanel(gm);
		
		layout = new CardLayout();
		
		this.setLayout(layout);
		
		this.add(menuPanel, "menuPanel");
		this.add(transitionPanel, "transitionPanel");
		this.add(mainPanel, "mainPanel");
		this.add(playerTradePanel, "playerTradePanel");
		
		layout.show(this, "menuPanel");
	}
	
	public void showTransitionPanel(){
		layout.show(this, "transitionPanel");
	}
	public void showMainPanel(){
		layout.show(this, "mainPanel");
	}
	
	public void showPlayerTradePanel(){
		layout.show(this, "playerTradePanel");
	}
	
	public MainPanel getMainPanel(){
		return mainPanel;
	}

}
