package com.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.view.MapPanel;

public class Controller implements MouseListener, ActionListener, MouseMotionListener {

	
	
	/********************** Attributes **********************/
		
		
		
	/*********************** Methods ***********************/
	public void actionPerformed(ActionEvent e) {
		
	}
	public void mousePressed(MouseEvent e) {
		if(e.getSource().getClass() == MapPanel.class){
			MapPanel mp = (MapPanel) e.getSource();
		}
		
	}
	
	public void mouseMoved(MouseEvent e) {
		if(e.getSource().getClass() == MapPanel.class){
			MapPanel mp = (MapPanel) e.getSource();
			mp.mouseOver(e.getPoint());
		}
		
	}
	
	
	
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	public void mouseDragged(MouseEvent e) {}
	
}
