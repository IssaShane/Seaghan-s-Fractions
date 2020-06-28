package com.fracpracgdx;

import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class GUIButton extends GUIElement {
	protected TextButton button;
	
	public GUIButton(String text, Skin skin, String font) {
		super();
		this.button = new TextButton(text, skin, font);
	}
	
	public GUIButton(String text, Skin skin, String font, Posn pos) {
		super();
		this.button = new TextButton(text, skin, font);
		this.setPos(pos);
	}
	
	@Override
	protected void addToStage(Stage stage) {
		stage.addActor(this.button);
	}
	
	@Override
	public void setPos(Posn pos) {
		this.pos = pos;
		this.button.setX(this.pos.x);
		this.button.setY(this.pos.y);
		this.button.setWidth(this.pos.w);
		this.button.setHeight(this.pos.h);
	}
	
	public void addListener(EventListener list) {
		this.button.addListener(list);
	}
}
