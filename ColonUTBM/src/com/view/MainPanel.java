package com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.controller.Controller;
import com.model.GameManager;

public class MainPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/********************** Attributes **********************/
	
		private MapPanel mapPanel;
		private RightPanel publicInfoPanel;
		private LeftPanel privatePanel;
		
		private BorderLayout layout;

	/*********************** Methods ***********************/
	public MainPanel(GameManager gm, Controller c){
		super();
		
		mapPanel = new MapPanel(gm.getMap(), c);
		publicInfoPanel = new RightPanel(gm.getPlayers());
		privatePanel = new LeftPanel(gm, c);
		
		layout = new BorderLayout();
		setLayout(layout);
		
		this.add(mapPanel, BorderLayout.CENTER);
		this.add(publicInfoPanel, BorderLayout.EAST);
		this.add(privatePanel, BorderLayout.WEST);
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	
	public LogPanel getLogPanel(){
		return publicInfoPanel.getLogPanel();
	}
	
	public MapPanel getMapPanel(){
		return mapPanel;
	}
}
