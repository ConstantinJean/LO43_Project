package com.view;


import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.controller.Controller;
import com.model.*;

public class Window extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/********************** Attributes **********************/
	
	private MapPanel mapPanel;
	private PublicPanel publicInfoPanel;
	private PrivatePanel privatePanel;
	
	BorderLayout layout;
	
/*********************** Methods ***********************/
	public Window(GameManager gm, Controller c){
		this.setTitle("Colon UTBM");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.setSize(800, 500);
		this.setMinimumSize(new Dimension(700, 300));
		
		mapPanel = new MapPanel(gm.getMap(), c);
		publicInfoPanel = new PublicPanel(gm.getPlayers());
		privatePanel = new PrivatePanel(gm.getCurrentPlayer());
		
		layout = new BorderLayout();
		
		this.add(mapPanel, BorderLayout.CENTER);
		this.add(publicInfoPanel, BorderLayout.EAST);
		this.add(privatePanel, BorderLayout.WEST);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	
	public MapPanel getMapPanel(){
		return mapPanel;
	}

}
