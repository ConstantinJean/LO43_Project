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
	
	
/*********************** Methods ***********************/
	public Window(GameManager gm, Controller c){
		this.setTitle("Colon UTBM");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.setSize(1000, 700);
		this.setMinimumSize(new Dimension(700, 400));
		
		globalPanel = new GlobalPanel(gm, c);
		logPanel = globalPanel.getLogPanel();
		
		this.setContentPane(globalPanel);
		
		
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void showTransitionPanel(){
		globalPanel.showTransitionPanel();
	}
	private void showMainPanel(){
		globalPanel.showMainPanel();
	}

	public void update(String msg) {
		
		if(msg.equals("trans")){
			showTransitionPanel();
		}
		else{
			showMainPanel();
		
			if(!logPanel.getText().equals(msg)){
				logPanel.setText(msg);
			}
			System.out.println(logPanel.getText());
		}
		
		this.repaint();
	}
	
}
