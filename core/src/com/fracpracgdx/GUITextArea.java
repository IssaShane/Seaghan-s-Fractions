package com.fracpracgdx;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;

public class GUITextArea extends GUIElement {
	TextArea area;
	
	public GUITextArea(String text, Skin skin, String font) {
		this.area = new TextArea(text, skin, font);
	}
	public GUITextArea(String text, Skin skin, String font, Posn pos) {
		this.area = new TextArea(text, skin, font);
		this.setPos(pos);
	}

	@Override
	protected void addToStage(Stage stage) {
		stage.addActor(this.area);
	}
	
	@Override
	public void setPos(Posn pos) {
		this.pos = pos;
		this.area.setX(this.pos.x);
		this.area.setY(this.pos.y);
		this.area.setWidth(this.pos.w);
		this.area.setHeight(this.pos.h);
	}
	
	public void setText(String text) {
		this.area.setText(text);
	}
	
	public String getText() {
		return this.area.getText();
	}
}
