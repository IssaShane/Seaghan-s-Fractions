package com.fracpracgdx;

import com.badlogic.gdx.scenes.scene2d.Stage;

enum Alignment {
	CENTRE,
	LEFT,
	RIGHT,
	TOP,
	BOTTOM
}

public abstract class GUIElement {
	protected Posn pos;
	protected GUILocation location;
	protected int padding_left = 3, padding_right = 3, padding_top = 3, padding_bottom = 3;
	
	public GUIElement() {
		this.pos = new Posn(0,0,0,0);
		this.location = new GUILocation(Alignment.LEFT, Alignment.BOTTOM);
	}
	
	// setPos(pos) alters the current position to pos
	// Effects: mutates data
	// Efficiency: O(1)
	public void setPos(Posn pos) {
		this.pos = new Posn(pos);
	}
	
	// getPos() gives the current position
	public Posn getPos() { return this.pos; }
	
	// setLocation(location) alters the alignment of the element to location
	// Efficiency: O(1)
	// Effects: mutates data
	public void setLocation(GUILocation location) {
		this.location = new GUILocation(location);
	}
	
	// getLocation() gives the current alignment
	public GUILocation getLocation() { return this.location; }
	
	abstract protected void addToStage(Stage stage);
	
	public void setPaddingLeft(int p) { this.padding_left = p; }
	public void setPaddingRight(int p) { this.padding_right = p; }
	public void setPaddingTop(int p) { this.padding_top = p; }
	public void setPaddingBottom(int p) { this.padding_bottom = p; }
	public int getPaddingLeft() { return this.padding_left; }
	public int getPaddingRight() { return this.padding_right; }
	public int getPaddingTop() { return this.padding_top; }
	public int getPaddingBottom() { return this.padding_bottom; }
}
