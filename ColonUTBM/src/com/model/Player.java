package com.model;

import java.awt.Color;
import java.util.ArrayList;

import com.model.card.RessourceCard;
import com.observer.*;

public class Player implements Observable{

/********************** Attributes **********************/
	private ArrayList<Observer> observers;
	
	private String name;
	
	private Color color;
	
	private int passageOrder;
	
	private ArrayList<UV> UVs;
	private ArrayList<UVplus> UVsPlus;
	private ArrayList<CC> CCs;
	
	
	private ArrayList<RessourceCard> ressourceCards;
	
	private int VictoryPoint;
	private int longestCursus;
	private int oldestElder;
	private boolean haveTheLongestCursus;
	private boolean haveTheOldestElder;
/*********************** Methods ***********************/
	public Player(String name, Color color){
		this.name=name;
		this.color = color;
		passageOrder=0;
		observers = new ArrayList<Observer>();
		
		ressourceCards = new ArrayList<RessourceCard>();
		UVs = new ArrayList<UV>();
		UVsPlus = new ArrayList<UVplus>();
		CCs = new ArrayList<CC>();
		
		VictoryPoint=0;
		longestCursus = 0;
		oldestElder = 0;
		haveTheLongestCursus = false;
		haveTheOldestElder = false;
	}
	
	
	
	public String getName(){
		return name;
	}
	public Color getColor(){
		return color;
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
	public int getRessourceCards(Ressource ressource){
		int nb = 0;
		for(RessourceCard rc : ressourceCards){
			if(rc.getType() == ressource){
				nb++;
			}
		}
		return nb;
	}
	public void addUV(UV UV){
		UVs.add(UV);
	}
	public void addCC(CC CC){
		CCs.add(CC);
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
	
	public void UpdateObserver(String msg) {
		for(Observer o : observers){
			o.update(msg);
		}
	}
	
	
	
}
