package com.fracpracgdx;

public class Posn {
	public int x, y, w, h = 0;
	
	public Posn(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public Posn(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Posn(Posn other) {
		this.x = other.x;
		this.y = other.y;
		this.w = other.w;
		this.h = other.h;
	}
}
