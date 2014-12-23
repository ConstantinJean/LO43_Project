package com.view;

import javax.swing.JFrame;

import com.controller.Controller;
import com.model.GameManager;

public class ShopWindow  extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/********************** Attributes **********************/
	private ShopPanel shopPanel;
		
	/*********************** Methods ***********************/
	public ShopWindow(GameManager gm, Controller c){
		super();
		
		setTitle("Shop");
		setSize(700, 500);
		setResizable(false);
		
		shopPanel = new ShopPanel(gm, c);
		
		this.setLayout(null);
		
		this.setContentPane(shopPanel);
		
		this.addWindowListener(c);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
}
