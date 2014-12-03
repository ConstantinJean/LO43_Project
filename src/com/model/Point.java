package com.model;

import java.util.ArrayList;

public class Point {

/********************** Attributes **********************/
	private int x, y;
	
	private ArrayList<Point> adjPoints;
	
/*********************** Methods ***********************/
	public Point(int x, int y){
		this.x = x;
		this.y = y;
		adjPoints = new ArrayList<Point>();
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void addAdjPoint(Point p){
		adjPoints.add(p);
	}
	
	public ArrayList<Point> getAdjPoints(){
		return adjPoints;
	}
	
}
