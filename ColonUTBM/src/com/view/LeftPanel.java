package com.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import com.controller.Controller;
import com.model.GameManager;

public class LeftPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/********************** Attributes **********************/
	public static final int X_SIZE = 150;
	
	private BorderLayout layout;
	
	private ActionPanel actionPanel;
	private PrivateInfoPanel privateInfoPanel;

/*********************** Methods ***********************/
	public LeftPanel(GameManager gm, Controller c){
		super();
		actionPanel = new ActionPanel(gm, c);
		privateInfoPanel = new PrivateInfoPanel(gm, c);
		
		this.layout = new BorderLayout();
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(X_SIZE, 0));// the 2nd argument doesn't change anything
		
		this.add(privateInfoPanel, BorderLayout.CENTER);
		this.add(actionPanel, BorderLayout.SOUTH);
	}
	
	
}
