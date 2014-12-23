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
	
	private LogPanel logPanel;
	
	private GameManager gameManager;
	private Controller controller;
/*********************** Methods ***********************/
	public Window(GameManager gm, Controller c){
		super();
		
		gameManager = gm;
		controller = c;
		
		this.setTitle("Colon UTBM");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setSize(1000, 700);
		this.setMinimumSize(new Dimension(700, 400));
		
		globalPanel = new GlobalPanel(gm, c);
		logPanel = globalPanel.getMainPanel().getLogPanel();
		
		this.setContentPane(globalPanel);
		
		
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void showTransitionPanel(boolean b){
		globalPanel.showTransitionPanel(b);
	}
	private void showShop(){
		new ShopWindow(gameManager, controller);
	}

	public void update(String msg) {
		
		if(msg.equals("trans")){
			showTransitionPanel(true);
		}
		else if(msg.equals("shop")){
			showShop();
		}
		else{
			showTransitionPanel(false);
		
			if(!logPanel.getText().equals(msg)){
				logPanel.setText(msg);
			}
			System.out.println(logPanel.getText());
		}
		
		this.repaint();
	}
	
}
