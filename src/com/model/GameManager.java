package com.model;

import java.util.ArrayList;

import com.controller.Controller;
import com.view.Window;

public class GameManager {
	
/********************** Attributes **********************/
	private Map map;
	private ArrayList<Player> players;
	private Player currentPlayer;
	
	private int currentTurn;
	private boolean devCardPlayed;
	private int diceValue;
	
	
	
/*********************** Methods ***********************/
	public GameManager(int nbPlayer){
		
		map = new Map();
		players = new ArrayList<Player>();
		
		for(int i=0 ; i<nbPlayer ; i++){
			players.add(new Player("Player "+ (i+1)));
		}
		
		
		currentTurn = 0;
		currentPlayer = players.get(0);
		devCardPlayed = false;
		diceValue = 0;
		
	}
	
	
	
	
	public static void main(String[] args) {
		GameManager gm = new GameManager(4);
		Controller c = new Controller();
		Window w = new Window(gm, c);
		
		gm.getMap().addObserver(w.getMapPanel());
		gm.getMap().UpdateObserver();
		
		
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

}
