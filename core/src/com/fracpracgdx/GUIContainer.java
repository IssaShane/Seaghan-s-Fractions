package com.fracpracgdx;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GUIContainer extends GUIElement {
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
	
	public void calibrateLocations() {}
		
	@Override
	protected void addToStage(Stage stage) {
		for (GUIElement elem : contents) {
			elem.addToStage(stage);
		}
	}
	
	public void addElement(GUIElement elem) {
		this.contents.add(elem);
	}
	
	
}
