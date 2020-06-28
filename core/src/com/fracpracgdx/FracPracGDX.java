package com.fracpracgdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.LibVectorWrapper.BashWrapper;

public class FracPracGDX extends ApplicationAdapter {
	Stage stage;
	Skin skin;
	TextArea problemView;
	Problem problem;
	FractionInputField fracField;
	GUIContainer buttons;
	GUIContainer textFields;
	
	public Posn centrePos(Posn currentPos) {
		Posn retval = new Posn(0,0,0,0);
		retval.w = currentPos.w;
		retval.h = currentPos.h;
		int winw = Gdx.graphics.getWidth();
		int winh = Gdx.graphics.getHeight();
		retval.x = (winw/2) - (retval.w/2);
		retval.y = (winh/2) - (retval.h/2);
		return retval;
	}
	
	public void reloadProblemView() {
		problem.reload(0);
		problemView.setText(problem.toString());
	}
	
	public void checkResult() {
		System.out.println("checkResult");
		Fraction cont = fracField.getContent();
		System.out.println("content taken");
		boolean correct = problem.checkSoln(cont);
		System.out.println("evaluated");
		if (problem.checkSoln(fracField.getContent())) {
			problemView.setText("YOU ARE CORRECT");
		} else problemView.setText("YOU ARE INCORRECT\nCORRECT SOLN: " + problem.evaluate());
	}
	
	@Override
	public void create () {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		skin = new Skin(Gdx.files.internal("uiskin.json"));
	  final NumberInputField numField = new NumberInputField(new Posn(10, 255, 200, 30), "uiskin.json");
	  fracField = new FractionInputField(new Posn(10, 290, 200, 30), "uiskin.json");
	  //final TextLabel inputLabel = new TextLabel("hello this is a label", skin, new Posn(10, 340, 200, 30));
	  textFields = new GUIContainerColumn(new GUILocation(Alignment.CENTRE, Alignment.CENTRE));
	  textFields.setPos(new Posn(0,0,640,240));
	  fracField.setText("Frac");
	  numField.setText("NUM");
	  
	  // button
	  Button returnButton = new TextButton("Return", skin, "default");
	  returnButton.setSize(100, 30);
	  returnButton.setPosition(230, 220);
	  returnButton.addListener(new InputListener() {
		  @Override 
		  public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
			  System.out.println("return pressed");
			  checkResult();
			  return true;
		  }
	  });
	  
	  // Problem View
	  problemView = new TextArea("", skin);
	  
	  problemView.setX(10);
	  problemView.setY(10);
	  problemView.setWidth(200);
	  problemView.setHeight(100);
	  Posn centre = centrePos(new Posn(0,0,(int)problemView.getWidth(),(int)problemView.getHeight()));
	  problemView.setX(centre.x);
	  problemView.setY(centre.y);
	  problemView.setWidth(centre.w);
	  problemView.setHeight(centre.h);
	  problem = new Problem();
	  problemView.setText(problem.toString());
	  
	  // reload problem button
	  Button reloadButton = new TextButton("Reload", skin, "default");
	  reloadButton.setSize(100, 30);
	  reloadButton.setPosition(330, 220);
	  reloadButton.addListener(new InputListener() { 
	  	@Override
	  	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
	  		System.out.println("reload");
	  		reloadProblemView();
	  		return true;
	  	}
	  });
	  
	  textFields.addElement(fracField);
	  textFields.addElement(numField);
	  textFields.calibrateLocations();
	  
	  // add all actors to stage
	  //fracField.addToStage(stage);
	  stage.addActor(returnButton);
	  //numField.addToStage(stage);
	  stage.addActor(problemView);
	  stage.addActor(reloadButton);
	  textFields.addToStage(stage);
	}

	@Override
	public void render () {
		stage.draw();
		stage.act();
	}
	
	@Override
	public void dispose () {
	}
}
