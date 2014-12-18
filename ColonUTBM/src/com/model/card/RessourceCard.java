package com.model.card;

import com.model.Ressource;

public class RessourceCard {

/********************** Attributes **********************/
	private Ressource type;
/*********************** Methods ***********************/
	public RessourceCard(Ressource ressourceType){
		this.type = ressourceType;
	}
	
	public Ressource getType(){
		return type;
	}
}
