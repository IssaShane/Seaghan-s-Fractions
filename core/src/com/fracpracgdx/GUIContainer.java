package com.fracpracgdx;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class GUIContainer extends GUIElement {
	protected ArrayList<GUIElement> contents;
	
	public GUIContainer() {
		super();
		this.location = new GUILocation(Alignment.LEFT, Alignment.BOTTOM);
		this.contents = new ArrayList<GUIElement>();
	}
	
	public GUIContainer(GUILocation location) {
		super();
		this.location = new GUILocation(location);
		this.contents = new ArrayList<GUIElement>();
	}
	
	// calibrateLocations() alters the locations of each element of the container
	//   in accordinance with the alignment
	// Effects: mutates data
	// Efficiency: O(n)
	abstract public void calibrateLocations();
		
	// addToStage(stage) explained in GUIElement.java
	@Override
	protected void addToStage(Stage stage) {
		for (GUIElement elem : contents) {
			elem.addToStage(stage);
		}
	}
	
	// addElement(elem) adds elem to the container
	// Effects: mutates data
	// Efficiency: O(1)
	public void addElement(GUIElement elem) {
		this.contents.add(elem);
	}
	
	
}
