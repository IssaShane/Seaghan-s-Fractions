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

public class FracPracGDX extends ApplicationAdapter {
	Stage stage;
	Skin skin;
	GUITextArea problemView;
	Problem problem;
	FractionInputField fracField;
	GUIContainer buttons;
	GUIContainer textFields;
	GUIContainer view;
	GUIContainer difficultyView;
	GUIContainer boundView;
	NumberInputField upperBound;
	NumberInputField lowerBound;
	GUIContainer totalView;
	
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
		try {
			problem.setLowerBound(lowerBound.getContent().num);
			problem.setUpperBound(upperBound.getContent().denom);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("caught");
			problem.setLowerBound(1);
			problem.setUpperBound(5);
		}
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
			problemView.setText("You are correct. Good job!");
		} else problemView.setText("You are incorrct\nCorrect solution: " + problem.evaluate() + "\nTry again!");
	}
	
	@Override
	public void create () {
		view = new GUIContainerColumn(new GUILocation(Alignment.CENTRE, Alignment.CENTRE));
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		skin = new Skin(Gdx.files.internal("uiskin.json"));
	  final NumberInputField numField = new NumberInputField(new Posn(10, 255, 200, 30), "uiskin.json");
	  fracField = new FractionInputField(new Posn(10, 290, 200, 30), "uiskin.json");
	  //final TextLabel inputLabel = new TextLabel("hello this is a label", skin, new Posn(10, 340, 200, 30));
	  fracField.setText("Write your solution here");
	  numField.setText("NUM");
	  
	  // button
	  GUIButton returnButton = new GUIButton("Check Solution", skin, "default", new Posn(230,330,120,30));
	  //returnButton.setSize(100, 30);
	  //returnButton.setPosition(230, 220);
	  returnButton.addListener(new InputListener() {
		  @Override 
		  public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
			  System.out.println("return pressed");
			  checkResult();
			  return true;
		  }
	  });
	  
	  // Problem View
	  problemView = new GUITextArea("", skin, "default", new Posn(10, 10, 200, 100));
	  problem = new Problem();
	  problemView.setText(problem.toString());
	  
	  // reload problem button
	  GUIButton reloadButton = new GUIButton("Next Problem", skin, "default", new Posn(330,220,120,30));
	  reloadButton.addListener(new InputListener() { 
	  	@Override
	  	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
	  		System.out.println("reload");
	  		reloadProblemView();
	  		return true;
	  	}
	  });
	  
	  buttons = new GUIContainerRow(new GUILocation(Alignment.CENTRE, Alignment.BOTTOM));
	  buttons.setPos(new Posn(0,0,640,30));
	  buttons.addElement(reloadButton);
	  buttons.addElement(returnButton);
	  buttons.calibrateLocations();
	  
	  // Difficulty View
	  difficultyView = new GUIContainerColumn(new GUILocation(Alignment.CENTRE, Alignment.CENTRE));
	  TextLabel diffViewLabel = new TextLabel("Difficulty", skin);
	  diffViewLabel.setPos(new Posn(0,200,50,30));
	  GUIButton diff1 = new GUIButton("1", skin, "default");
	  diff1.setPos(new Posn(0,0,30,30));
	  GUIButton diff2 = new GUIButton("2", skin, "default");
	  diff2.setPos(new Posn(0,0,30,30));
	  GUIButton diff3 = new GUIButton("3", skin, "default");
	  diff3.setPos(new Posn(0,0,30,30));
	  difficultyView.addElement(diffViewLabel);
	  difficultyView.addElement(diff1);
	  difficultyView.addElement(diff2);
	  difficultyView.addElement(diff3);
	  difficultyView.setPos(new Posn(0,0,210,480));
	  difficultyView.calibrateLocations();
	  
	  view.addElement(problemView);
	  view.addElement(fracField);
	  view.addElement(buttons);
	  view.setPos(new Posn(210,0,220,480));
	  view.calibrateLocations();
	  
	  boundView = new GUIContainerColumn(new GUILocation(Alignment.CENTRE, Alignment.CENTRE));
	  upperBound = new NumberInputField(new Posn(0,0,100,30),"uiskin.json");
	  upperBound.setText("upper bound");
	  lowerBound = new NumberInputField(new Posn(0,0,100,30),"uiskin.json");
	  lowerBound.setText("lower bound");
	  TextLabel boundViewLabel = new TextLabel("Upper & Lower Bounds", skin);
	  boundViewLabel.setPos(new Posn(0,0,200,100));
	  boundView.addElement(boundViewLabel);
	  boundView.addElement(upperBound);
	  boundView.addElement(lowerBound);
	  boundView.setPos(new Posn(0,0,200,480));
	  boundView.calibrateLocations();
	  
	  totalView = new GUIContainerRow(new GUILocation(Alignment.CENTRE, Alignment.CENTRE));
	  totalView.setPos(new Posn(0,0,640,480));
	  totalView.addElement(difficultyView);
	  totalView.addElement(view);
	  totalView.addElement(boundView);
	  totalView.calibrateLocations();
	  
	  // add all actors to stage
	  totalView.addToStage(stage);
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
