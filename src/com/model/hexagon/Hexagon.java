package com.model.hexagon;

import java.util.ArrayList;

import com.model.Point;

public abstract class Hexagon {

/********************** Attributes **********************/
	protected int x, y;
	
	protected ArrayList<Point> points;
	
	
	
/*********************** Methods ***********************/
	public Hexagon(int x, int y){
		this.x=x;
		this.y=y;
		this.points = new ArrayList<Point>();
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void addPoint(Point p){
		points.add(p);
	}
	public ArrayList<Point> getPoints(){
		return points;
	}
	
}
