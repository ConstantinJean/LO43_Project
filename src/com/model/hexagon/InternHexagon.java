package com.model.hexagon;

import com.model.Ressource;

public class InternHexagon extends Hexagon{

	
/********************** Attributes **********************/
	private int diceValue;
	private Ressource ressource;
/*********************** Methods ***********************/
	
	public InternHexagon(int x, int y, int diceValue, Ressource ressource) {
		super(x, y);
		this.diceValue = diceValue;
		this.ressource = ressource;
	}
	
	public Ressource getRessource(){
		return ressource;
	}
	public int getDiceValue(){
		return diceValue;
	}

}
