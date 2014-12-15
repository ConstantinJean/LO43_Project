package com.model;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.controller.Controller;
import com.observer.Observable;
import com.observer.Observer;
import com.view.Window;

public class GameManager implements Observable{
	
/********************** Attributes **********************/
	private ArrayList<Observer> observers;
	
	private Map map;
	private ArrayList<Player> players;
	private Player currentPlayer;
	
	private int currentTurn;
	private boolean devCardPlayed;
	private int diceValue;
	
	private boolean placementPhase;
	
	
	
/*********************** Methods ***********************/
	
	// ---- Main ----
	public static void main(String[] args) {
		GameManager gm = new GameManager(4);
		Controller c = new Controller();
		Window w = new Window(gm, c);
		
		gm.addObserver(w);
		
		gm.startGame();
		
//		while(true){
//			try {
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			gm.nextTurn();
//		}
	}
	
	// ---- Contructor ----
	public GameManager(int nbPlayer){
		observers = new ArrayList<Observer>();
		
		map = new Map();
		players = new ArrayList<Player>();
		
		for(int i=0 ; i<nbPlayer ; i++){
			players.add(new Player("Player "+ (i+1)));
		}
		
		
		currentTurn = 0;
		currentPlayer = players.get(0);
		devCardPlayed = false;
		diceValue = 0;
		placementPhase = true;
		
	}
	
	// ---- function to start the game ----
	public void startGame(){
		
		int rand = (int)(Math.random()*players.size());
		int order=0;
		currentPlayer = players.get(rand);
		// Initialization of the passage order of all the player
		players.get(rand).setPassageOrder(rand);
		for(int i=rand ; i<players.size() ; i++){
			players.get(i).setPassageOrder(order);
			order++;
		}
		for(int i=0 ; i<rand ; i++){
			players.get(i).setPassageOrder(order);
			order++;
		}
		
//		JOptionPane.showMessageDialog(null, "the first player is "+currentPlayer.getName()+"\n"
//				+"clic on a point to place an UV");
		
		
		
	}
	
	public void nextTurn(){
		
		currentTurn++;
		JOptionPane.showMessageDialog(null, "turn "+currentTurn);
		diceValue = (int)(Math.random()*6+1) + (int)(Math.random()*6+1);
	}
	
	
	
	
	
	public Map getMap(){
		return map;
	}
	public ArrayList<Player> getPlayers(){
		return players;
	}
	public Player getCurrentPlayer(){
		return currentPlayer;
	}

	
	
	public void addObserver(Observer obs) {
		observers.add(obs);
	}
	public void removeObservers() {
		observers = new ArrayList<Observer>();
	}
	public void UpdateObserver() {
		for(Observer obs : observers){
			obs.update();
		}
	}

}
