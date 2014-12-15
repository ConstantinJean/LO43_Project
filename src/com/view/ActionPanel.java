package com.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.model.GameManager;

public class ActionPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/********************** Attributes **********************/
	public static final int Y_SIZE = 150;
	
	private GameManager GameManager;
	
	private JButton nextTurn;
		
		
	/*********************** Methods ***********************/
	public ActionPanel(GameManager gm){
		super();
		GameManager = gm;
		
		nextTurn = new JButton("Next turn");
		
		this.setPreferredSize(new Dimension(0, Y_SIZE));
		
		this.add(nextTurn);
		
		
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
}
