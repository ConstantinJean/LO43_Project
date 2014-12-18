package com.observer;

public interface Observable {
	public void addObserver(Observer obs);
	public void removeObservers();
	public void UpdateObserver(String msg);
}
