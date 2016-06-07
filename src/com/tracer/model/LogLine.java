package com.tracer.model;

import java.util.Map;
import java.util.HashMap;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class LogLine {
	
	private static int counter = 0;
	private int serialNo;

	private int lineId;
	private Map<Integer, LogLine> children = new HashMap<Integer, LogLine>();
	LogLine parentLine;

	// Need an attribute to track the heirarchy level
	private int heirarchyLevel;
	private Rectangle r;
	
	LogLine(LogLine parentLine) {
		this.serialNo = counter++;
		this.lineId = this.serialNo;
		this.parentLine = parentLine;
	}

	public int getSerialNo() {
		return serialNo;
	}

	public Rectangle getR() {
		return r;
	}
	
	public Map<Integer, LogLine> getChildren() {
		return children;
	}
	
	public void setChildren(Map<Integer, LogLine> children) {
		this.children = children;
	}
	
	public int getNumChildren() {
		return this.children.size();
	}

}
