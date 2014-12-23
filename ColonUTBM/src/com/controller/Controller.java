package com.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.model.*;
import com.model.hexagon.InternHexagon;
import com.view.MapPanel;
import com.view.TransitionPanel;

public class Controller implements MouseListener, ActionListener, MouseMotionListener, WindowListener {

	
	
	/********************** Attributes **********************/
	private GameManager gm;
		
		
	/*********************** Methods ***********************/
	public Controller (GameManager gameManager){
		gm = gameManager;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case "PLAY":
			gm.startGame();
			break;
		case "NEXT_TURN":
			gm.nextTurn();
			break;
		case "BUY":
			gm.buy();
			break;
		case "BUY_CC":
			break;
		}
		
		
	}
	public void mousePressed(MouseEvent e) {
		if(e.getSource().getClass() == MapPanel.class){
			MapPanel mp = (MapPanel) e.getSource();
			
			if(gm.isPlacingUV()){
				if(mp.getUnderMouseObject() != null
						&& mp.getUnderMouseObject().getClass() == Point.class){
					Point po = (Point)mp.getUnderMouseObject();
					if(po.getUV() == null){
						boolean placable = true;
						for(Point adjPoint : po.getAdjPoints()){
							if(adjPoint.getUV() != null){
								placable = false;
							}
						}
						if(placable){
							UV newUV = new UV(gm.getCurrentPlayer());
							po.setUV(newUV);
							gm.getCurrentPlayer().addUV(newUV);
							gm.setLastObjectPlaced(newUV);
							
							gm.nextAction();
						}
						else{
							gm.UpdateObserver("you can't place an UV near an other UV");
						}
					}
				}
			}
			else if(gm.isPlacingCC()){
				if(mp.getUnderMouseObject() != null && mp.getUnderMouseObject().getClass() == Path.class){
					Path pa = (Path)mp.getUnderMouseObject();
					if(pa.getCC() == null){
						if(gm.getLastObjectPlaced().getClass() == UV.class){
							boolean placable = false;
							
							for(Point po : pa.getEnd()){
								if(po.getUV() == gm.getLastObjectPlaced()){
									placable = true;
								}
							}
							if(placable){
								CC newCC = new CC(gm.getCurrentPlayer());
								pa.setCC(newCC);
								gm.getCurrentPlayer().addCC(newCC);
								gm.nextAction();
							}
							else{
								gm.UpdateObserver("you have to place your CC near your UV");
							}
						}
					}
					
				}
			}
			else if(gm.isMovingLayaboutMate()){
				if(mp.getUnderMouseObject() != null 
						&& mp.getUnderMouseObject().getClass() == InternHexagon.class){
					InternHexagon he = (InternHexagon)mp.getUnderMouseObject();
					LayaboutMate lm = gm.getMap().getLayaboutMate();
					if(lm.getPos() != he){
						boolean atLeastOneOpponant = false;
						for(Point po : he.getPoints()){
							if(po.getUV()!=null && po.getUV().getPlayer() != gm.getCurrentPlayer())
								atLeastOneOpponant = true;
						}
						if(atLeastOneOpponant){
							lm.setPos(he);
							gm.nextAction();
						}
						else{
							gm.UpdateObserver("the layaboute mate should be near an opponant");
						}
					}
				}
			}
			else if(gm.isRollingDice()){
				gm.nextAction();
			}
			
			
			
		}
		else if(e.getSource().getClass() == TransitionPanel.class){
			gm.nextAction();
		}
		
	}
	
	public void mouseMoved(MouseEvent e) {
		if(e.getSource().getClass() == MapPanel.class){
			MapPanel mp = (MapPanel) e.getSource();
			mp.mouseOver(e.getPoint());
		}
		
	}
	
	// fonction appelée quand on ferme le shop;
	public void windowClosing(WindowEvent arg0) {
		gm.shopClosed();
	}
	
	
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	public void mouseDragged(MouseEvent e) {}
	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}
	
}
