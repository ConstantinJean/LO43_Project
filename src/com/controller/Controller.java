package com.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.view.MapPanel;

public class Controller implements MouseListener, ActionListener {

	

/********************** Attributes **********************/
	
	
	
/*********************** Methods ***********************/
	
	public void mouseClicked(MouseEvent arg0) {
		
	}

	public void mouseEntered(MouseEvent arg0) {
		
	}

	public void mouseExited(MouseEvent arg0) {
		
	}

	public void mousePressed(MouseEvent arg0) {
		MapPanel mp = (MapPanel) arg0.getSource();
		mp.getObjectAt(arg0.getPoint());
	}

	public void mouseReleased(MouseEvent arg0) {
		
	}

	public void actionPerformed(ActionEvent arg0) {
		
	}
}
