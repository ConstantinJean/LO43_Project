package com.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.controller.Controller;
import com.model.GameManager;

public class MenuPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/********************** Attributes **********************/
	private JButton playButton;
	/*********************** Methods ***********************/
	
	public MenuPanel(GameManager gm, Controller c){
		super();
		setLayout(null);
		playButton = new JButton("PLAY");
		this.add(playButton);
		playButton.setActionCommand("PLAY");
		playButton.addActionListener(c);
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, getWidth(), getHeight());
		playButton.setBounds(getWidth()/2-40, getHeight()/2-15, 80, 30);
	}
}
