package com.model;

import java.util.ArrayList;

public class Point {

/********************** Attributes **********************/
	private int x, y;
	
	private ArrayList<Point> adjPoints;
	
	private UV UV;
	
/*********************** Methods ***********************/
	public Point(int x, int y){
		this.x = x;
		this.y = y;
		adjPoints = new ArrayList<Point>();
		UV = null;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public UV getUV(){
		return UV;
	}
	public void setUV(UV UV){
		this.UV = UV;
	}
	
	public void addAdjPoint(Point p){
		adjPoints.add(p);
	}
	
	public ArrayList<Point> getAdjPoints(){
		return adjPoints;
	}
	
}
