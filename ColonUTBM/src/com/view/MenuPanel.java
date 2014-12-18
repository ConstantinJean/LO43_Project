package com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		playButton = new JButton("PLAY");
		this.add(playButton);
		playButton.setActionCommand("PLAY");
		playButton.addActionListener(c);
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
}
