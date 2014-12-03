package com.model;

import java.util.ArrayList;

public class Path {

/********************** Attributes **********************/
	private ArrayList<Point> end;
	
/*********************** Methods ***********************/	
	public Path(Point p1, Point p2){
		end = new ArrayList<Point>();
		end.add(p1);
		end.add(p2);
	}
	
	public ArrayList<Point> getEnd(){
		return end;
	}
}
