package com.model.port;

import com.model.Point;


public class GeneralPort extends Port {

/********************** Attributes **********************/
/*********************** Methods ***********************/
	public GeneralPort(Point p1, Point p2){
		super(p1, p2);
		cost = 3;
	}
}
