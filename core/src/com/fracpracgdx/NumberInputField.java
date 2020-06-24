package com.fracpracgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class NumberInputField extends GUIElement {
	protected Fraction content;
	protected TextField field;
	protected Skin skin;
	
	public NumberInputField(Posn pos) {
		this.pos = pos;
		this.init();
	}
	
	public NumberInputField(Posn pos, String skinfilename) {
		this.pos = pos;
		this.skin = new Skin(Gdx.files.internal(skinfilename));
		this.init();
	}
	
	@Override
	protected void addToStage(Stage stage) {
		stage.addActor(this.field);
	}
	
	protected void init() {
		this.field = new TextField("", skin);
		this.field.setX(this.pos.x);
		this.field.setY(this.pos.y);
		// check for proper width
		if (this.pos.w != 0 && this.pos.h != 0) {
			this.field.setWidth(this.pos.w);
			this.field.setHeight(this.pos.h);
		}
		else {
			this.field.setWidth(200);
			this.field.setWidth(30);
		}
	}
	
	public Fraction getContent() { 
		this.content = new Fraction(Integer.parseInt(this.field.getText()));
		return this.content; }
	
	public String getContentAsString() {
		this.content = this.getContent();
		if (this.content.denom == 1) return Integer.toString(this.content.num);
		else return Integer.toString(this.content.num) + "/" + Integer.toString(this.content.denom);
	}
}
