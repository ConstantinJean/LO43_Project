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
			gm.buyCC();
			break;
		case "BUY_UV":
			gm.buyUV();
			break;
		case "BUY_UV_PLUS":
			gm.buyUVplus();
			break;
		case "BUY_DEV_CARD":
			gm.buyDevCard();
			break;
		}
		
	}
	
	public void mousePressed(MouseEvent e) {
		// on vérifie que le joueur a cliqué sur la mapPanel
		if(e.getSource().getClass() == MapPanel.class){
			MapPanel mp = (MapPanel) e.getSource();
			
			// si le joueur est en phase de placement d'UV
			if(gm.isPlacingUV()){
				// on vérifie que le curseur est au dessus d'un point
				if(mp.getUnderMouseObject() != null
						&& mp.getUnderMouseObject().getClass() == Point.class){
					Point po = (Point)mp.getUnderMouseObject();
					// on vérifie que le point ne contient pas d'UV
					if(po.getUV() == null){
						
						boolean placable = false;
						// si on se trouve dans la phase de placement, on peux placer l'UV au milieu de nul part
						if(gm.isPlacementPhase()){
							placable = true;
						}
						// sinon il faut etre relié a un CC
						else{
							placable = false;
							for(Path adjPa : gm.getMap().getPaths()){
								if(adjPa.getCC() != null && adjPa.getCC().getPlayer() == gm.getCurrentPlayer()){
									for(Point endPo : adjPa.getEnd()){
										if(endPo == po){
											placable = true;
										}
									}
								}
							}
						}
						
						// si il a une UV adjacente a ce point on ne pas poser l'UV
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
							gm.UpdateObserver("You can't place an UV here");
						}
					}
					else{
						gm.UpdateObserver("This point contains an UV");
					}
				}
			}
			// si le joeur est en train de placer une UV**
			else if(gm.isPlacingUVplus()){
				// on vérifie que le curseur est au dessus d'un point
				if(mp.getUnderMouseObject() != null
						&& mp.getUnderMouseObject().getClass() == Point.class){
					Point po = (Point)mp.getUnderMouseObject();
					// on vérifie que le point contient une UV
					if(po.getUV() != null){
						UV odlUV = po.getUV();
						if(odlUV.getPlayer() == gm.getCurrentPlayer()){
						
							UVplus newUVplus = new UVplus(gm.getCurrentPlayer());
							po.setUV(newUVplus);
							gm.getCurrentPlayer().addUVplus(newUVplus);
							gm.getCurrentPlayer().removeUV(odlUV);
							gm.setLastObjectPlaced(newUVplus);
							
							gm.nextAction();
						}
						else{
							gm.UpdateObserver("Your not the owner of this UV");
						}
					}
					else{
						gm.UpdateObserver("This point doesn't contain an UV");
					}
				}
			}
			// si le joueur est en train de placer un CC
			else if(gm.isPlacingCC()){
				// on vérifie que le curseur est au dessus d'un path
				if(mp.getUnderMouseObject() != null && mp.getUnderMouseObject().getClass() == Path.class){
					Path pa = (Path)mp.getUnderMouseObject();
					// on vérifie que le path ne contient pas de CC
					if(pa.getCC() == null){
						
						// si on se trouve en phase de placement
						if(gm.isPlacementPhase()){
							// on vérifie que le path est adjacent a l'UV que l'on a posé juste avant
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
								gm.UpdateObserver("You have to place your CC near your UV");
							}
						}
						// si on ne se trouve pas en phase de placement
						else{
							// on vérifie que le path est relié a une UV, une UV** ou un CC du même joueur
							boolean placable = false;
							for(Point po : pa.getEnd()){
								// si le path est relié a une UV ou UV** du joueur
								if(po.getUV() != null && po.getUV().getPlayer() == gm.getCurrentPlayer()){
									placable = true;
								}
								
								// si le path est relié a un CC du joueur
								if(!placable){
									for(Path adjPa : gm.getMap().getPaths()){
										if(adjPa.getCC() != null && adjPa.getCC().getPlayer() == gm.getCurrentPlayer()){
											for(Point endPo : adjPa.getEnd()){
												if(endPo == po){
													placable = true;
												}
											}
										}
									}
								}
							}
							
							if(placable){
								CC newCC = new CC(gm.getCurrentPlayer());
								pa.setCC(newCC);
								gm.getCurrentPlayer().addCC(newCC);
								gm.nextAction();
							}
							else{
								gm.UpdateObserver("Your CC should be near an UV or a CC");
							}
							
						}
					}
					else
						gm.UpdateObserver("This path contains a CC");
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
