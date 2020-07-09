package com.fracpracgdx;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.utils.Align;

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
	
	public void calibrateLocation() {
		if (this.location.horizontal == Alignment.CENTRE && this.location.vertical == Alignment.CENTRE) {
			System.out.println("centre align");
			this.area.setAlignment(1);
			System.out.println(this.area.getAlignment());
			System.out.println(Align.center);
		}
		else if (this.location.horizontal == Alignment.CENTRE && this.location.vertical == Alignment.TOP) {
			this.area.setAlignment(Align.top);
		}
		else if (this.location.horizontal == Alignment.CENTRE && this.location.vertical == Alignment.BOTTOM) {
			this.area.setAlignment(Align.bottom);
		}
		else if (this.location.horizontal == Alignment.LEFT && this.location.vertical == Alignment.CENTRE) {
			this.area.setAlignment(Align.left);
		}
		else if (this.location.horizontal == Alignment.LEFT && this.location.vertical == Alignment.TOP) {
			this.area.setAlignment(Align.topLeft);
		}
		else if (this.location.horizontal == Alignment.LEFT && this.location.vertical == Alignment.BOTTOM) { 
			this.area.setAlignment(Align.bottomLeft);
		}
		else if (this.location.horizontal == Alignment.RIGHT && this.location.vertical == Alignment.CENTRE) {
			this.area.setAlignment(Align.right);
		}
		else if (this.location.horizontal == Alignment.RIGHT && this.location.vertical == Alignment.TOP) {
			this.area.setAlignment(Align.topRight);
		}
		else if (this.location.horizontal == Alignment.RIGHT && this.location.vertical == Alignment.BOTTOM) { 
			this.area.setAlignment(Align.bottomRight);
		}
	}
	
	public void setText(String text) {
		this.area.setText(text);
	}
	
	public String getText() {
		return this.area.getText();
	}
}
