package com.model.port;

import com.model.Point;
import com.model.Ressource;

public class SpecifiedPort extends Port{

/********************** Attributes **********************/
	Ressource ressourceType;
/*********************** Methods ***********************/
	public SpecifiedPort(Point p1, Point p2, Ressource ressourceType){
		super(p1, p2);
		this.ressourceType = ressourceType;
		cost = 2;
	}
	
	public Ressource getRessourceType(){
		return ressourceType;
	}
}
