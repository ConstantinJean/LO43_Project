package com.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;

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
	

	private CardLayout layout;

	/*********************** Methods ***********************/
	public GlobalPanel(GameManager gm, Controller c){
		super();
		menuPanel = new MenuPanel(gm, c);
		mainPanel = new MainPanel(gm, c);
		transitionPanel = new TransitionPanel(gm, c);
		
		layout = new CardLayout();
		
		this.setLayout(layout);
		
		this.add(menuPanel, "menuPanel");
		this.add(transitionPanel, "transitionPanel");
		this.add(mainPanel, "mainPanel");
		
		layout.show(this, "menuPanel");
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	public void showMainPanel(){
		layout.show(this, "mainPanel");
	}
	
	public void showTransitionPanel(){
		layout.show(this, "transitionPanel");
	}
	
	
	public LogPanel getLogPanel(){
		return mainPanel.getLogPanel();
	}

}
