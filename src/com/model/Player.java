package com.model;

import java.util.ArrayList;

import com.model.card.RessourceCard;
import com.observer.*;

public class Player implements Observable{

/********************** Attributes **********************/
	private ArrayList<Observer> observers;
	
	private String name;
	
	private int passageOrder;
	
//	private ArrayList<UV>
	
	private ArrayList<RessourceCard> ressourceCards;
	
	private int VictoryPoint;
	private int longestCursus;
	private int oldestElder;
	private boolean haveTheLongestCursus;
	private boolean haveTheOldestElder;
/*********************** Methods ***********************/
	public Player(String name){
		this.name=name;
		passageOrder=0;
		observers = new ArrayList<Observer>();
		
		ressourceCards = new ArrayList<RessourceCard>();
		
		VictoryPoint=0;
		longestCursus = 0;
		oldestElder = 0;
		haveTheLongestCursus = false;
		haveTheOldestElder = false;
	}
	
	
	
	public String getName(){
		return name;
	}
	public void setPassageOrder(int i){
		passageOrder = i;
	}
	public int getPassageOrder(){
		return passageOrder;
	}
	public ArrayList<RessourceCard> getRessourceCards(){
		return ressourceCards;
	}
	public int getVictoryPoint(){
		return VictoryPoint;
	}

	public int getLongestCursus() {
		return longestCursus;
	}

	public int getOldestElder() {
		return oldestElder;
	}

	public boolean haveTheLongestCursus() {
		return haveTheLongestCursus;
	}

	public boolean haveTheOldestElder() {
		return haveTheOldestElder;
	}

	
	
	/* observable methods */
	public void addObserver(Observer obs) {
		observers.add(obs);
		
	}

	public void removeObservers() {
		observers = new ArrayList<Observer>();
	}
	
	public void UpdateObserver() {
		for(Observer o : observers){
			o.update();
		}
	}
	
	
	
}
