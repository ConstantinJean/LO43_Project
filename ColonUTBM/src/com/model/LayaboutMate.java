package com.model;

import com.model.hexagon.InternHexagon;

public class LayaboutMate {

/********************** Attributes **********************/
	private InternHexagon pos;
/*********************** Methods ***********************/
	public LayaboutMate(){
	}
	
	
	// --- setters and getters ---
	public void setPos(InternHexagon pos){
		this.pos = pos;
	}
	public InternHexagon getPos(){
		return pos;
	}
}
