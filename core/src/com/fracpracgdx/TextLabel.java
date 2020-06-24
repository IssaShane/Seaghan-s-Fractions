package com.fracpracgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class TextLabel extends GUIElement {
	Label label;
	
	public TextLabel (String content, Skin skin) {
		this.label = new Label(content, skin);
		init();
	}
	
	public TextLabel (String content, String skin) {
		this.label = new Label(content, new Skin(Gdx.files.internal(skin)));
		init();
	}
	
	public TextLabel (String content, Skin skin, Posn pos) {
		this.label = new Label (content, skin);
		this.pos = pos;
		init();
	}
	
	public TextLabel (String content, String skin, Posn pos) {
		this.label = new Label (content, new Skin(Gdx.files.internal(skin)));
		this.pos = pos;
		init();
	}
	
	protected void init() {
		this.label.setX(this.pos.x);
		this.label.setY(this.pos.y);
		if (this.pos.w != 0 && this.pos.h != 0) {
			this.label.setWidth(this.pos.w);
			this.label.setHeight(this.pos.h);
		}
		else {
			this.label.setWidth(200);
			this.label.setHeight(30);
		}
	}
	
	@Override
	public void addToStage(Stage stage) {
		stage.addActor(this.label);
	}
	
	public void setText(String content) {
		this.label.setText(content);
	}
	
	public String getText() { return this.label.getText().toString(); }
	
	
}
