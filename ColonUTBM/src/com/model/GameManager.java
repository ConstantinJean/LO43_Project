package com.model;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import com.controller.Controller;
import com.observer.Observable;
import com.observer.Observer;
import com.view.ColorConstants;
import com.view.Window;

public class GameManager implements Observable{
	
/********************** Attributes **********************/
	private ArrayList<Observer> observers;
	
	private Map map;
	private ArrayList<Player> players;
	private int nbPlayer;
	
	private int currentTurn;
	private boolean devCardPlayed;
	private int diceValue;
	
	private Player currentPlayer;
	private boolean placementPhase;
	private boolean placingUV;
	private boolean placingUVplus;
	private boolean placingCC;
	private boolean rollingDice;
	private boolean movingLayaboutMate;
	private boolean canEndTurn;
	private Object lastObjectPlaced;
	
	
	
/*********************** Methods ***********************/
	
	// ---- Main ----
	public static void main(String[] args) {
		GameManager gm = new GameManager(4);
		Controller c = new Controller(gm);
		Window w = new Window(gm, c);
		
		gm.addObserver(w);
		
	}
	
	// ---- Contructor ----
	public GameManager(int nbPlayer){
		observers = new ArrayList<Observer>();
		
		map = new Map();
		players = new ArrayList<Player>();
		this.nbPlayer = nbPlayer;
		
		for(int i=0 ; i<nbPlayer ; i++){
			switch(i){
			case 0:
				players.add(new Player("Player "+ (i+1), new Color(ColorConstants.p1R, ColorConstants.p1G, ColorConstants.p1B)));
				break;
			case 1:
				players.add(new Player("Player "+ (i+1), new Color(ColorConstants.p2R, ColorConstants.p2G, ColorConstants.p2B)));
				break;
			case 2:
				players.add(new Player("Player "+ (i+1), new Color(ColorConstants.p3R, ColorConstants.p3G, ColorConstants.p3B)));
				break;
			case 3:
				players.add(new Player("Player "+ (i+1), new Color(ColorConstants.p4R, ColorConstants.p4G, ColorConstants.p4B)));
				break;
			default :
				players.add(new Player("Player "+ (i+1), Color.WHITE));
				break;
			}
			
			
			
		}
		
		
		currentTurn = 0;
		currentPlayer = players.get(0);
		devCardPlayed = false;
		diceValue = 0;
		placementPhase = true;
		placingUV = false;
		placingUVplus = false;
		placingCC = false;
		rollingDice = false;
		movingLayaboutMate = false;
		canEndTurn = false;
		lastObjectPlaced = null;
		
	}
	
	// ---- function to start the game ----
	public void startGame(){
		
		currentTurn = 1;
		
		int rand = (int)(Math.random()*nbPlayer);
		int order=0;
		currentPlayer = players.get(rand);
		// Initialization of the passage order of all the player
		players.get(rand).setPassageOrder(rand);
		for(int i=rand ; i<nbPlayer ; i++){
			players.get(i).setPassageOrder(order);
			order++;
		}
		for(int i=0 ; i<rand ; i++){
			players.get(i).setPassageOrder(order);
			order++;
		}
		
		UpdateObserver("the first player is "+ currentPlayer.getName()+ ", clic on a point to place an UV");
		placingUV = true;
		
	}
	
	
	public void next(){
		
		if(placementPhase){
			if(placingUV){
				placingUV = false;
				
				if(currentTurn>nbPlayer){
					map.giveInitialRessources((UV)lastObjectPlaced);
				}
				
				placingCC = true;
				UpdateObserver("now clic on a path to place a CC");
			}
			else if(placingCC){
				placingCC = false;
				
				if(currentTurn<nbPlayer*2){
					if(currentTurn < nbPlayer){
						nextPlayer();
					}
					else if(currentTurn > nbPlayer){
						precedentPlayer();
					}
					currentTurn++;
					
					placingUV = true;
					UpdateObserver(currentPlayer.getName() + ", clic on a point to place an UV");
				}
				else{
					placementPhase = false;
					UpdateObserver("trans");
					currentTurn ++;
				}
			}
		}
		else{// if not placement phase
			if(rollingDice){
				rollingDice = false;
				int d1 = (int)(Math.random()*6+1), d2 = (int)(Math.random()*6+1);
				diceValue = d1+d2;
				if(diceValue != 7){
					map.produceRessources(diceValue);
					UpdateObserver("Dice value : "+diceValue+" ("+d1+"+"+d2+")");
					canEndTurn = true;
				}
				else{
					movingLayaboutMate = true;
					UpdateObserver("Dice value : "+diceValue+" ("+d1+"+"+d2+"), click on an hexagon to move the layaboute mate");
				}
			}
			else if(canEndTurn){
				canEndTurn = false;
				nextPlayer();
				UpdateObserver("trans");
				
			}
			else if(movingLayaboutMate){
				movingLayaboutMate = false;
				
				ArrayList<Object> targets = new ArrayList<Object>();
				for(Point po : map.getLayaboutMate().getPos().getPoints()){
					if(po.getUV() != null){
						if(!targets.contains(po.getUV().getPlayer()))
							targets.add(po.getUV().getPlayer().getName());
					}
				}
				String target = (String)JOptionPane.showInputDialog(null
						, "chose your target"
						, "target"
						, JOptionPane.PLAIN_MESSAGE
						, null
						, targets.toArray()
						, "name");
				System.out.println(target);
			}
			else{
				canEndTurn = false;
				UpdateObserver(currentPlayer.getName() + ", click to roll dice");
				rollingDice = true;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	private void nextPlayer(){
		if(currentPlayer == players.get(nbPlayer-1)){
			currentPlayer = players.get(0);
		}
		else{
			currentPlayer = players.get(players.indexOf(currentPlayer)+1);
		}
	}
	private void precedentPlayer(){
		if(players.indexOf(currentPlayer)==0){
			currentPlayer = players.get(nbPlayer-1);
		}
		else{
			currentPlayer = players.get(players.indexOf(currentPlayer)-1);
		}
	}
	
	
	public Map getMap(){
		return map;
	}
	public ArrayList<Player> getPlayers(){
		return players;
	}
	public int getNbPlayer(){
		return nbPlayer;
	}
	public Player getCurrentPlayer(){
		return currentPlayer;
	}
	public int getCurrentTurn() {
		return currentTurn;
	}
	public boolean isPlacementPhase() {
		return placementPhase;
	}
	public boolean isPlacingUV() {
		return placingUV;
	}
	public boolean isPlacingUVplus() {
		return placingUVplus;
	}
	public boolean isPlacingCC() {
		return placingCC;
	}
	public boolean isRollingDice(){
		return rollingDice;
	}
	public boolean isMovingLayaboutMate(){
		return movingLayaboutMate;
	}
	public boolean canEndTurn(){
		return canEndTurn;
	}
	public Object getLastObjectPlaced() {
		return lastObjectPlaced;
	}
	public void setLastObjectPlaced(Object lastObjectPlaced) {
		this.lastObjectPlaced = lastObjectPlaced;
	}

	
	
	
	public void addObserver(Observer obs) {
		observers.add(obs);
	}
	public void removeObservers() {
		observers = new ArrayList<Observer>();
	}
	public void UpdateObserver(String msg) {
		for(Observer obs : observers){
			obs.update(msg);
		}
	}

}
