package com.model.hexagon;

import com.model.Point;
import com.model.Ressource;
import com.model.UV;
import com.model.UVplus;
import com.model.card.RessourceCard;

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
	
	public void produceRessources(){
		for(Point po : points){
			if(po.getUV() != null){
				po.getUV().getPlayer().getRessourceCards().add(new RessourceCard(ressource));
				if(po.getUV().getClass() == UVplus.class){
					po.getUV().getPlayer().getRessourceCards().add(new RessourceCard(ressource));
				}
			}
		}
	}
	
	
	public Ressource getRessource(){
		return ressource;
	}
	public int getDiceValue(){
		return diceValue;
	}

}
