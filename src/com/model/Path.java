package com.model;

import java.util.ArrayList;

public class Path {

/********************** Attributes **********************/
	private ArrayList<Point> end;
	
	private CC CC;
	
/*********************** Methods ***********************/	
	public Path(Point p1, Point p2){
		end = new ArrayList<Point>();
		end.add(p1);
		end.add(p2);
	}
	
	public ArrayList<Point> getEnd(){
		return end;
	}
	
	public CC getCC(){
		return CC;
	}
	public void setCC(CC CC){
		this.CC = CC;
	}
}
