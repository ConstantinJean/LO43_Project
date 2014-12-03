package com.model.port;

import java.util.ArrayList;

import com.model.*;

public abstract class Port {

/********************** Attributes **********************/
	ArrayList<Point> points;
	
	int cost;
/*********************** Methods ***********************/
	public Port(Point p1, Point p2){
		points = new ArrayList<Point>();
		points.add(p1);
		points.add(p2);
	}
	
	public ArrayList<Point> getPoints(){
		return points;
	}
}
