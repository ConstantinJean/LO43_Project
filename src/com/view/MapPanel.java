package com.view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.controller.Controller;
import com.model.*;
import com.model.hexagon.*;
import com.model.port.*;
import com.observer.Observer;

public class MapPanel extends JPanel  implements Observer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/********************** Attributes **********************/
	private Map map;
	
	private BufferedImage stickman;
	
	
	private static final double POINT_HIT_BOX = 0.3;
	private static final double PATH_HIT_BOX = 0.5;
	private static final double HEXAGON_HIT_BOX = 1.0;

/*********************** Methods ***********************/
	public MapPanel(Map map, Controller controller){
		super();
		this.addMouseListener(controller);
		this.setMinimumSize(new Dimension(100, 100));
		
		this.map = map;
		
		stickman = null;
		try{
			stickman = ImageIO.read(new File("stickman.jpg"));
		} catch(IOException e){
			System.out.println("stickman exception");
		}
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		// for each hexagon
		for(Hexagon h : map.getHexagones()){
			
			// xTab and yTab contain the 6 vertices of the hexagon
			int xh = h.getX(), yh = h.getY();
			int xTab[] = {xh, xh+2, xh+3, xh+2, xh, xh-1};
			int yTab[] = {yh, yh, yh+1, yh+2, yh+2, yh+1};
			// we convert all of these vertex
			for(int i=0 ; i<6 ; i++){
				xTab[i] = convX(xTab[i]);
				yTab[i] = convY(yTab[i]);
			}
			//change the color in function of the produced ressources
			if(h.getClass() == InternHexagon.class){
				switch(((InternHexagon) h).getRessource()){
				case BEER:
					g.setColor(new Color(255, 204, 102));
					break;
				case SLEEP:
					g.setColor(new Color(255, 255, 255));
					break;
				case COFFEE:
					g.setColor(new Color(64, 28, 4));
					break;
				case COURS:
					g.setColor(new Color(19, 17, 84));
					break;
				case FOOD:
					g.setColor(new Color(213, 200, 0));
					break;
				case NO_RESSOURCE:
					g.setColor(new Color(200, 200, 200));
					break;
				}
				g.fillPolygon(xTab, yTab, 6);
			}
			else{
				g.setColor(new Color(150, 150, 150));
				g.fillPolygon(xTab, yTab, 6);
				
				// draw the port
				Port p = ((ExternHexagon)h).getPort();
				if(p!=null){
					g.setColor(Color.RED);
					g.fillOval((convX(h.getX())*2+convX(h.getX()+1))/3
							, (convY(h.getY())*2+convY(h.getY()+1))/3
							, convX(h.getX()+1)-convX(h.getX())
							, convY(h.getY()+1)-convY(h.getY()));
					
					g.drawLine((convX(h.getX())+convX(h.getX()+2))/2
							, (convY(h.getY())+convY(h.getY()+2))/2
							, convX(((ExternHexagon)h).getPort().getPoints().get(0).getX())
							, convY(((ExternHexagon)h).getPort().getPoints().get(0).getY()));
					g.drawLine((convX(h.getX())+convX(h.getX()+2))/2
							, (convY(h.getY())+convY(h.getY()+2))/2
							, convX(((ExternHexagon)h).getPort().getPoints().get(1).getX())
							, convY(((ExternHexagon)h).getPort().getPoints().get(1).getY()));
					if(p.getClass() == SpecifiedPort.class){
						Ressource r = ((SpecifiedPort)p).getRessourceType();
						switch(r){
						case BEER:
							g.setColor(new Color(255, 204, 102));
							break;
						case SLEEP:
							g.setColor(new Color(255, 255, 255));
							break;
						case COFFEE:
							g.setColor(new Color(64, 28, 4));
							break;
						case COURS:
							g.setColor(new Color(14, 12, 64));
							break;
						case FOOD:
							g.setColor(new Color(213, 200, 0));
							break;
						case NO_RESSOURCE:
							g.setColor(new Color(200, 200, 200));
							break;
						}
						g.fillOval((convX(h.getX())*2+convX(h.getX()+1))/3 +5
								, (convY(h.getY())*2+convY(h.getY()+1))/3 +5
								, convX(h.getX()+1)-convX(h.getX()) -10
								, convY(h.getY()+1)-convY(h.getY()) -10);
					}
				}
			}
			
			g.setColor(Color.RED);
			//display the index of the hexagon
			g.drawString(Integer.toString(map.getHexagones().indexOf(h)), xTab[0], yTab[5]);
			//display the adjacent points number
//			g.drawString(Integer.toString(h.getPoints().size()), xTab[0]+20, yTab[5]);
			
			//display the dice value
			if(h.getClass() == com.model.hexagon.InternHexagon.class
					&& ((InternHexagon)h).getDiceValue() != -1){
				g.drawString(Integer.toString(((InternHexagon) h).getDiceValue()), xTab[1], yTab[5]);
			}
		}
		
		
		// for each point
		for(Point p : map.getPoints()){
			g.setColor(Color.BLACK);
			g.fillOval(convX(p.getX())-2, convY(p.getY())-2, 5, 5);
			
			g.setColor(Color.BLUE);
			g.drawString(Integer.toString(map.getPoints().indexOf(p)), convX(p.getX()), convY(p.getY())-5);
		}
		
		// for each path
		for(Path p : map.getPaths()){
			//display the path
			g.setColor(Color.BLACK);
			g.drawLine(convX(p.getEnd().get(0).getX())
					, convY(p.getEnd().get(0).getY())
					, convX(p.getEnd().get(1).getX())
					, convY(p.getEnd().get(1).getY()));
			
			//display the index of the path
			g.setColor(Color.GREEN);
			g.drawString(Integer.toString(map.getPaths().indexOf(p))
					,(convX(p.getEnd().get(0).getX())+convX(p.getEnd().get(1).getX()))/2
					,(convY(p.getEnd().get(0).getY())+convY(p.getEnd().get(1).getY()))/2);
		}
		
		// draw the layabout mate
		g.setColor(Color.BLACK);
		int xPos = map.getLayaboutMate().getPos().getX();
		int yPos = map.getLayaboutMate().getPos().getY();
		g.fillOval((convX(xPos)*2+convX(xPos+1))/3
				, (convY(yPos)*2+convY(yPos+1))/3
				, convX(xPos+1)-convX(xPos)
				, convY(yPos+1)-convY(yPos));
		
		
	}
	
	
	private int convX(int x){
		return (x+1)*getWidth()/24;
	}
	private int convY(int y){
		return (y+1)*getHeight()/16;
	}

	public void update() {
		this.repaint();
	}
	public Object getObjectAt(java.awt.Point point){
		int xPos = point.x - getX() + PrivatePanel.X_SIZE;
		int yPos = point.y - getY();
		
		double caseW = getWidth()/24.0;
		double caseH = getHeight()/16.0;
		
		if(xPos > caseW && xPos < getWidth()-caseH
				&& yPos > caseH && yPos < getHeight()-caseW){
		
			xPos -= (int)caseW;
			yPos -= (int)caseH;
			
			//we look for the nearest point
			int nearPX = Math.round((float)(xPos)/(float)caseW);
			int nearPY = Math.round((float)(yPos)/(float)caseH);
			double nearPointDistance = Math.sqrt(Math.pow(nearPX-xPos/caseW, 2) + Math.pow(nearPY-yPos/caseH, 2));
			// between 0 and 0.5
//			System.out.println(nearPDistance);
			
//			System.out.println(nearPX + " " + nearPY);
			if(nearPointDistance<POINT_HIT_BOX){
				for(Point po : map.getPoints()){
					if(po.getX() == nearPX && po.getY() == nearPY){
							System.out.println("point " + map.getPoints().indexOf(po));
							return po;
					}
				}
			}
			
			//else we look for the nearest path
			for(Path pa : map.getPaths()){
				double pathCenterX = (pa.getEnd().get(0).getX() + pa.getEnd().get(1).getX())/2.0;
				double pathCenterY = (pa.getEnd().get(0).getY() + pa.getEnd().get(1).getY())/2.0;
				double pathDistance = Math.sqrt(Math.pow(pathCenterX-xPos/caseW, 2) + Math.pow(pathCenterY-yPos/caseH, 2));
				if(pathDistance<PATH_HIT_BOX){
					System.out.println("path " + map.getPaths().indexOf(pa));
					return pa;
				}
			}
			
			// else we look for the nearest hexagon
			for(Hexagon h : map.getHexagones()){
				double hexagonCenterX = h.getX()+1;
				double haxagonCenterY = h.getY()+1;
				double pathDistance = Math.sqrt(Math.pow(hexagonCenterX-xPos/caseW, 2) + Math.pow(haxagonCenterY-yPos/caseH, 2));
				if(pathDistance<HEXAGON_HIT_BOX){
					System.out.println("hexagon " + map.getHexagones().indexOf(h));
					return h;
				}
			}
			
			
			
		
		}
		
		
		
		return null;
	}
	
	
	
}
