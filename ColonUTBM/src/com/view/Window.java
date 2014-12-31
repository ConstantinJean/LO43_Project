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
	
	private ShopWindow shopWindow;
/*********************** Methods ***********************/
	public Window(GameManager gm, Controller c){
		super();
		
		shopWindow = null;
		
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

	public void update(String msg) {
		
		if(msg.equals("trans")){
			globalPanel.showTransitionPanel();
		}
		else if(msg.equals("shop")){
			shopWindow = new ShopWindow(gameManager, controller);
		}
		else if(msg.equals("trade")){
			globalPanel.showTradePanel();
		}
		else{
			globalPanel.showMainPanel();
			
			if(shopWindow != null){
				shopWindow.dispose();
			}
		
			if(!logPanel.getText().equals(msg)){
				logPanel.setText(msg);
			}
			System.out.println(logPanel.getText());
		}
		
		this.repaint();
	}
	
}
