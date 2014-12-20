package com.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import com.model.card.RessourceCard;
import com.model.hexagon.*;
import com.model.port.*;

public class Map{
	
/********************** Attributes **********************/
	
	private ArrayList<Hexagon> hexagons;
	private ArrayList<Point> points;
	private ArrayList<Path> paths;
	
	private LayaboutMate layaboutmate;
	
/*********************** Methods ***********************/
	public Map(){
		// initialisation des attributs
		hexagons = new ArrayList<Hexagon>();
		points = new ArrayList<Point>();
		paths = new ArrayList<Path>();
		
		layaboutmate = new LayaboutMate();
		
		//création et placement des hexagons :
		
		int x, y;
		// on crée un stack qui contient les différentes valeurs de dés possibles
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
		
		// on fait de meme pour les types de ressource
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
		
		// on mélange ces stack
		Collections.shuffle(diceValue);
		Collections.shuffle(ressource);
		
		
		// puis on place les hexagones internes en se servant dans ces stacks
		InternHexagon newHexa;
		int diceV = 0;
		Ressource ressourceV;
		
		// placement des 5 hexagones de la colone centrale
		x=10;
		for(y=2 ; y<=10 ; y+=2){
			diceV = diceValue.pop();
			if(diceV != -1){
				ressourceV = ressource.pop();
				newHexa = new InternHexagon(x, y, diceV, ressourceV);
			}
			else{
				newHexa = new InternHexagon(x, y, -1, Ressource.NO_RESSOURCE);
				layaboutmate.setPos(newHexa);
			}
			hexagons.add(newHexa);
		}
		// placement des 2 colones de 4 hexagones
		for(x=7 ; x<=13 ; x+=6){
			for(y=3 ; y<=9 ; y+=2){
				diceV = diceValue.pop();
				if(diceV != -1){
					ressourceV = ressource.pop();
					newHexa = new InternHexagon(x, y, diceV, ressourceV);
				}
				else{
					newHexa = new InternHexagon(x, y, -1, Ressource.NO_RESSOURCE);
					layaboutmate.setPos(newHexa);
				}
				hexagons.add(newHexa);
			}
		}
		// placement des 2 dernières colones de 3 hexagones
		for(x=4 ; x<=16 ; x+=12){
			for(y=4 ; y<=8 ; y+=2){
				diceV = diceValue.pop();
				if(diceV != -1){
					ressourceV = ressource.pop();
					newHexa = new InternHexagon(x, y, diceV, ressourceV);
				}
				else{
					newHexa = new InternHexagon(x, y, -1, Ressource.NO_RESSOURCE);
					layaboutmate.setPos(newHexa);
				}
				hexagons.add(newHexa);
			}
		}
		
		// placement des points :
		// on parcours toutes les coordonnées de la map
		for(y=0 ; y<=14 ; y++){
			for(x=0 ; x<=22 ; x++){
				boolean pointCreated = false;
				// et on parcours tous les exagones crées (les hexagones internes)
				for(Hexagon h : hexagons){
					// on ajoute un point si la coordonné est situé sur le sommet d'un hexagone
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
		
		// Création des path :
		// on parcours tous les points crées
		for(Point p1 : points){
			int x1=p1.getX(), y1=p1.getY();
			// puis, pour chacun de ces points on parcours encore tous les point
			for(Point p2 : points){
				int x2 = p2.getX(), y2 = p2.getY();
				// on regarde si les 2 points sont adjacent du point de vu des hexagones
				if(x2==x1-2 && y2==y1
					|| x2==x1-1 && (y2==y1-1 || y2==y1+1)
					|| x2==x1+1 && (y2==y1-1 || y2==y1+1)
					|| x2==x1+2 && y2==y1
					){
					
					p1.addAdjPoint(p2);
					
					// on crée le path selement si x1<x2 pour éviter de créer tous les points en double
					if(x1 < x2){
						paths.add(new Path(p1, p2));
					}
				}
			}
		}
		
		// placement des hexagones externes:
		// on les places par groupe de 3, en tournant dans le sens des aiguilles d'une montre
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
		
		// on ajoute les points adjacents aux hexagones externes
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
		
		// placement des port
		// on utilise encore la methode du stack que l'on mélange
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
		
		// on prend 1 hexagone extern sur 2
		for(Hexagon h : hexagons){
			if(h.getClass() == ExternHexagon.class && hexagons.indexOf(h)%2 == 0){
				// le port et relié a 2 points
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
	
	// cette methode permer a la map de produire des ressources pour une valeur de dés donnée
	public void produceRessources(int diceValue){
		for(Hexagon he : hexagons){
			if(he.getClass() == InternHexagon.class){
				if(((InternHexagon)he).getDiceValue() == diceValue)
					((InternHexagon)he).produceRessources();
			}
		}
	}
	
	// cette methode est utilisée lors du deuxième tour la phase de placement
	// elle permet de donner une ressource de chaque hexagone adgacent a une UV
	public void giveInitialRessources(UV uv){
		for(Hexagon he : hexagons){
			if(he.getClass() == InternHexagon.class){
				for(Point po : he.getPoints()){
					if(po.getUV() == uv){
						RessourceCard newCard = new RessourceCard(((InternHexagon)he).getRessource());
						uv.getPlayer().getRessourceCards().add(newCard);
					}
				}
			}
		}
	}
	
	// ----getters and setters ----
	
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
