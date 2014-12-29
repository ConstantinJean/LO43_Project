package com.view;

import java.awt.Dimension;

import javax.swing.JTextArea;

public class LogPanel extends JTextArea{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/*********************** Methods ***********************/
	public LogPanel(){
		super();
		setPreferredSize(new Dimension(50, 150));
		setEditable(false);
		setLineWrap(true);
		setWrapStyleWord(true);
	}
}
