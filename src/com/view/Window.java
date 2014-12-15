package com.view;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.controller.Controller;
import com.model.*;
import com.observer.Observer;

public class Window extends JFrame implements Observer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/********************** Attributes **********************/
	
	private GlobalPanel globalPanel;
	
	
/*********************** Methods ***********************/
	public Window(GameManager gm, Controller c){
		this.setTitle("Colon UTBM");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.setSize(800, 500);
		this.setMinimumSize(new Dimension(700, 400));
		
		globalPanel = new GlobalPanel(gm, c);
		
		this.setContentPane(globalPanel);
		
		
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void showTransitionPanel(boolean b){
		globalPanel.showTransitionPanel(b);
	}

	public void update() {
		this.repaint();
	}
	
}
