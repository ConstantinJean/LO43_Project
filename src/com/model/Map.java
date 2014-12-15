package com.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import com.model.hexagon.*;
import com.model.port.*;
import com.observer.*;

public class Map{
	
/********************** Attributes **********************/
	private ArrayList<Observer> observers;
	
	private ArrayList<Hexagon> hexagons;
	private ArrayList<Point> points;
	private ArrayList<Path> paths;
	
	private LayaboutMate layaboutmate;
	
/*********************** Methods ***********************/
	public Map(){
		//--- Initialisation of attributes ---
		observers = new ArrayList<Observer>();
		
		hexagons = new ArrayList<Hexagon>();
		points = new ArrayList<Point>();
		paths = new ArrayList<Path>();
		
		layaboutmate = new LayaboutMate();
		
		//--- placement and initialisation of hexagons ---
		int x, y;
		
		Stack<Integer> diceValue = new Stack<Integer>();
		diceValue.push(-1); //desert
		diceValue.push(2);
		for(int i=0 ; i<2 ; i++){
			diceValue.push(3);
			diceValue.push(4);
			diceValue.push(5);
			diceValue.push(6);
			diceValue.push(8);
			diceValue.push(9);
			diceValue.push(10);
			diceValue.push(11);
		}
		diceValue.push(12);
		
		Stack<Ressource> ressource = new Stack<Ressource>();
		for(int i=0 ; i<4 ; i++){
			ressource.add(Ressource.BEER);
			ressource.add(Ressource.COFFEE);
			ressource.add(Ressource.COURS);
		}
		for(int i=0 ; i<3 ; i++){
			ressource.add(Ressource.SLEEP);
			ressource.add(Ressource.FOOD);
		}
		
		Collections.shuffle(diceValue);
		Collections.shuffle(ressource);
		
		//placement of intern hexagons
		x=10;
		for(y=2 ; y<=10 ; y+=2){
			InternHexagon newHexa;
			int diceV = diceValue.pop();
			if(diceV != -1){
				Ressource ressourceV = ressource.pop();
				newHexa = new InternHexagon(x, y, diceV, ressourceV);
			}
			else{
				newHexa = new InternHexagon(x, y, -1, Ressource.NO_RESSOURCE);
				layaboutmate.setPos(newHexa);
			}
			hexagons.add(newHexa);
		}
		for(x=7 ; x<=13 ; x+=6){
			for(y=3 ; y<=9 ; y+=2){
				InternHexagon newHexa;
				int diceV = diceValue.pop();
				if(diceV != -1){
					Ressource ressourceV = ressource.pop();
					newHexa = new InternHexagon(x, y, diceV, ressourceV);
				}
				else{
					newHexa = new InternHexagon(x, y, -1, Ressource.NO_RESSOURCE);
					layaboutmate.setPos(newHexa);
				}
				hexagons.add(newHexa);
			}
		}
		for(x=4 ; x<=16 ; x+=12){
			for(y=4 ; y<=8 ; y+=2){
				InternHexagon newHexa;
				int diceV = diceValue.pop();
				if(diceV != -1){
					Ressource ressourceV = ressource.pop();
					newHexa = new InternHexagon(x, y, diceV, ressourceV);
				}
				else{
					newHexa = new InternHexagon(x, y, -1, Ressource.NO_RESSOURCE);
					layaboutmate.setPos(newHexa);
				}
				hexagons.add(newHexa);
			}
		}
		
		// placement of points
		for(y=0 ; y<=14 ; y++){
			for(x=0 ; x<=22 ; x++){
				boolean pointCreated = false;
				for(Hexagon h : hexagons){
					if(x == h.getX()-1 && y == h.getY()+1
						|| x == h.getX() && (y == h.getY() || y == h.getY()+2)
						|| x == h.getX()+2 && (y == h.getY() || y == h.getY()+2)
						|| x == h.getX()+3 && y == h.getY()+1
						){
						if(!pointCreated){
							Point newPoint = new Point(x, y);
							points.add(newPoint);
							h.addPoint(newPoint);
							pointCreated = true;
						}
						else{
							h.addPoint(points.get(points.size()-1));
						}
					}
				}
			}
		}
		
		// placement of paths
		for(Point p1 : points){
			int x1=p1.getX(), y1=p1.getY();
			for(Point p2 : points){
				int x2 = p2.getX(), y2 = p2.getY();
				if(x2==x1-2 && y2==y1
					|| x2==x1-1 && (y2==y1-1 || y2==y1+1)
					|| x2==x1+1 && (y2==y1-1 || y2==y1+1)
					|| x2==x1+2 && y2==y1
					){
						p1.addAdjPoint(p2);
						
					if(x1 < x2){
						paths.add(new Path(p1, p2));
					}
				}
			}
		}
		
		//placement of extern hexagons
		for(x=10, y=0 ; y<=2 ; x+=3, y+=1){
			hexagons.add(new ExternHexagon(x, y));
		}
		for(x=19, y=3 ; y<=7 ; y+=2){
			hexagons.add(new ExternHexagon(x, y));
		}
		for(x=19, y=9 ; y<=11 ; x-=3, y+=1){
			hexagons.add(new ExternHexagon(x, y));
		}
		for(x=10, y=12 ; y>=10 ; x-=3, y-=1){
			hexagons.add(new ExternHexagon(x, y));
		}
		for(x=1, y=9 ; y>=5 ; y-=2){
			hexagons.add(new ExternHexagon(x, y));
		}
		for(x=1, y=3 ; y>=1 ; x+= 3, y-=1){
			hexagons.add(new ExternHexagon(x, y));
		}
		
		// we add the points on the new extern hexagons
		for(Point p : points){
			for(Hexagon h : hexagons){
				if(h.getClass() == ExternHexagon.class){
					if(p.getX() == h.getX()-1 && p.getY() == h.getY()+1
						|| p.getX() == h.getX() && (p.getY() == h.getY() || p.getY() == h.getY()+2)
						|| p.getX() == h.getX()+2 && (p.getY() == h.getY() || p.getY() == h.getY()+2)
						|| p.getX() == h.getX()+3 && p.getY() == h.getY()+1
						){
						h.addPoint(p);
					}
				}
			}
		}
		
		//placement of ports
		
		Stack<Ressource> portTypeList = new Stack<Ressource>();
		portTypeList.push(Ressource.BEER);
		portTypeList.push(Ressource.SLEEP);
		portTypeList.push(Ressource.COFFEE);
		portTypeList.push(Ressource.COURS);
		portTypeList.push(Ressource.FOOD);
		for(int i=0 ; i<4 ; i++){
			portTypeList.push(Ressource.NO_RESSOURCE);
		}
		Collections.shuffle(portTypeList);
		
		for(Hexagon h : hexagons){
			if(h.getClass() == ExternHexagon.class && hexagons.indexOf(h)%2 == 0){
				Point p1, p2;
				Ressource portType = portTypeList.pop();
				if(h.getPoints().size()==2){
					p1=h.getPoints().get(0);
					p2=h.getPoints().get(1);
				}
				else{
					p1=h.getPoints().get(1);
					p2=h.getPoints().get(((int)(Math.random()*2))*2);
				}
				
				if(portType == Ressource.NO_RESSOURCE){
					((ExternHexagon)h).setPort(new GeneralPort(p1, p2));
				}
				else{
					((ExternHexagon)h).setPort(new SpecifiedPort(p1, p2, portType));
				}
				
			}
		}
	}
	
	
	
	
	
	/*getters and setters*/
	
	public ArrayList<Hexagon> getHexagones(){
		return hexagons;
	}
	public ArrayList<Point> getPoints(){
		return points;
	}
	public ArrayList<Path> getPaths(){
		return paths;
	}
	public LayaboutMate getLayaboutMate(){
		return layaboutmate;
	}
	
}
