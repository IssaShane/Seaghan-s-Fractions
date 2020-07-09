package com.fracpracgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;

public class TextLabel extends GUIElement {
	Label label;
	Label.LabelStyle style;
	
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
		this.style = this.label.getStyle();
	}
	
	@Override
	public void addToStage(Stage stage) {
		stage.addActor(this.label);
	}
	
	@Override
	public void setPos(Posn pos) {
		this.pos = new Posn(pos);
		this.label.setX(this.pos.x);
		this.label.setY(this.pos.y);
		this.label.setWidth(this.pos.w);
		this.label.setHeight(this.pos.h);
	}
	
	public void setText(String content) {
		this.label.setText(content);
	}
	
	public String getText() { return this.label.getText().toString(); }
	
	public void calibrateLocation() {
		if (this.location.horizontal == Alignment.CENTRE && this.location.vertical == Alignment.CENTRE) {
			this.label.setAlignment(Align.center);
		}
		else if (this.location.horizontal == Alignment.CENTRE && this.location.vertical == Alignment.TOP) {
			this.label.setAlignment(Align.top);
		}
		else if (this.location.horizontal == Alignment.CENTRE && this.location.vertical == Alignment.BOTTOM) {
			this.label.setAlignment(Align.bottom);
		}
		else if (this.location.horizontal == Alignment.LEFT && this.location.vertical == Alignment.CENTRE) {
			this.label.setAlignment(Align.left);
		}
		else if (this.location.horizontal == Alignment.LEFT && this.location.vertical == Alignment.TOP) {
			this.label.setAlignment(Align.topLeft);
		}
		else if (this.location.horizontal == Alignment.LEFT && this.location.vertical == Alignment.BOTTOM) { 
			this.label.setAlignment(Align.bottomLeft);
		}
		else if (this.location.horizontal == Alignment.RIGHT && this.location.vertical == Alignment.CENTRE) {
			this.label.setAlignment(Align.right);
		}
		else if (this.location.horizontal == Alignment.RIGHT && this.location.vertical == Alignment.TOP) {
			this.label.setAlignment(Align.topRight);
		}
		else if (this.location.horizontal == Alignment.RIGHT && this.location.vertical == Alignment.BOTTOM) { 
			this.label.setAlignment(Align.bottomRight);
		}
	}
	
	public void setFontSize(int size) {
		// TODO: Finish implementing so that this will alter the font size of the label
	}
}
