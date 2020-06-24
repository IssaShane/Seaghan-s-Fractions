package com.fracpracgdx;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class GUIContainer extends GUIElement {
	private ArrayList<GUIElement> contents;
	
	public GUIContainer() {
		this.location = new GUILocation(Alignment.LEFT, Alignment.BOTTOM);
	}
	
	public GUIContainer(GUILocation location) {
		this.location = location;
	}
	
	private void init() {
		// determine horizontal position of elements
		if (this.location.horizontal == Alignment.LEFT) {
			Posn newpos_ = contents.get(0).getPos();
			newpos_.x = this.pos.x + contents.get(0).getPaddingLeft();
			contents.get(0).setPos(newpos_);
			for (int i = 1; i < contents.size(); i++) {
				Posn newpos = contents.get(i).getPos();
				newpos.x = this.pos.x + contents.get(i).getPaddingLeft() + contents.get(i-1).getPos().x + 
						contents.get(i-1).getPos().w + contents.get(i-1).getPaddingRight();
				contents.get(i).setPos(newpos);
			}
		}
		else if (this.location.horizontal == Alignment.RIGHT) { 
			// TODO: Fix bc I got lazy
			Posn newpos_ = contents.get(0).getPos();
			newpos_.x = this.pos.x + this.pos.w - contents.get(0).getPaddingRight() - newpos_.w;
			contents.get(0).setPos(newpos_);
			for (int i = 1; i < contents.size(); i++) {
				Posn newpos = contents.get(i).getPos();
				newpos.x = this.pos.x + this.pos.w;
				contents.get(i).setPos(newpos);
			}
		}
	}
		
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
