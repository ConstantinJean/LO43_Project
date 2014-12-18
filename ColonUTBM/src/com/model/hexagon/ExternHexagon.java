package com.model.hexagon;

import com.model.port.Port;

public class ExternHexagon extends Hexagon {

	

/********************** Attributes **********************/
	private Port port;
/*********************** Methods ***********************/
	public ExternHexagon(int x, int y) {
		super(x, y);
		port = null;
	}
	
	public void setPort(Port port){
		this.port = port;
	}
	public Port getPort(){
		return port;
	}
	
}
