package com.fracpracgdx;

public class GUILocation {
	public Alignment horizontal;
	public Alignment vertical;
	
	public GUILocation (Alignment horizontal) {
		this.horizontal = horizontal;
	}
	
	public GUILocation (Alignment horizontal, Alignment vertical) {
		this.horizontal = horizontal;
		this.vertical = vertical;
	}
	
	public GUILocation(GUILocation other) {
		this.vertical = other.vertical;
		this.horizontal = other.horizontal;
	}
}
