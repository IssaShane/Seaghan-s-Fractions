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
	SpriteBatch batch;
	Skin skin;
	TextLabel problemView;
	Problem problem;
	FractionInputField fracField;
	GUIContainer buttons;
	GUIContainer textFields;
	GUIContainer view;
	GUIContainer difficultyView;
	GUIContainer boundView;
	GUIContainer tutorialView;
	GUIContainer outerView;
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
	
	public void setDifficulty(int difficulty) {
		this.problem.setDifficulty(difficulty);
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
		boolean correct;
		try {
			Fraction cont = fracField.getContent();
			System.out.println("content taken");
			correct = problem.checkSoln(cont);
		} catch (NumberFormatException e) {
			correct = false;
		}
		
		System.out.println("evaluated");
		if (correct) {
			problemView.setText("You are correct. Good job!");
		} else problemView.setText("You are incorrct\nCorrect solution: " + problem.evaluate() + "\nTry again!");
	}
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		view = new GUIContainerColumn(new GUILocation(Alignment.CENTRE, Alignment.CENTRE));
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		skin = new Skin(Gdx.files.internal("uiskin.json"));
	  final NumberInputField numField = new NumberInputField(new Posn(10, 255, 200, 30), "uiskin.json");
	  fracField = new FractionInputField(new Posn(10, 290, 200, 30), "uiskin.json");
	  fracField.setText("Write your solution here");
	  numField.setText("NUM");
	  
	  // button
	  GUIButton returnButton = new GUIButton("Check Solution", skin, "default", new Posn(230,330,120,30));
	  returnButton.addListener(new InputListener() {
		  @Override 
		  public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
			  checkResult();
			  return true;
		  }
	  });
	  
	  // Problem View
	  problemView = new TextLabel("", skin, new Posn(10, 10, 200, 100));
	  problem = new Problem();
	  problemView.setText(problem.toString());
	  problemView.setLocation(new GUILocation(Alignment.CENTRE, Alignment.CENTRE));
	  problemView.calibrateLocation();
	  
	  // Tutorial View
	  tutorialView = new GUIContainerColumn(new GUILocation(Alignment.LEFT, Alignment.CENTRE));
	  tutorialView.setPos(new Posn(0,0,640,60));
	  tutorialView.addElement(new TextLabel("1. Both fractions will have the same denominator", skin, new Posn(0,0,200,20)));
	  tutorialView.addElement(new TextLabel("2. One fraction's denominator will be a multiple of the other", skin, new Posn(0,0,200,20)));
	  tutorialView.addElement(new TextLabel("3. Fractions could have completely different denominators", skin, new Posn(0,0,200,20)));
	  tutorialView.setPaddingBottom(100);
	  tutorialView.setPaddingLeft(60);
	  tutorialView.calibrateLocations();
	  
	  // reload problem button
	  GUIButton reloadButton = new GUIButton("Next Problem", skin, "default", new Posn(330,220,120,30));
	  reloadButton.addListener(new InputListener() { 
	  	@Override
	  	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
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
	  diff1.addListener(new InputListener() {
		  @Override
		  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
			  setDifficulty(1);
			  return true;
		  }
	  });
	  GUIButton diff2 = new GUIButton("2", skin, "default");
	  diff2.setPos(new Posn(0,0,30,30));
	  diff2.addListener(new InputListener() {
		  @Override
		  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
			  setDifficulty(2);
			  return true;
		  }
	  });
	  GUIButton diff3 = new GUIButton("3", skin, "default");
	  diff3.setPos(new Posn(0,0,30,30));
	  diff3.addListener(new InputListener() {
		  @Override
		  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
			  setDifficulty(3);
			  return true;
		  }
	  });
	  difficultyView.addElement(diffViewLabel);
	  difficultyView.addElement(diff1);
	  difficultyView.addElement(diff2);
	  difficultyView.addElement(diff3);
	  difficultyView.setPos(new Posn(0,0,210,380));
	  difficultyView.calibrateLocations();
	  
	  view.addElement(problemView);
	  view.addElement(fracField);
	  view.addElement(buttons);
	  view.setPos(new Posn(210,0,220,480));
	  view.calibrateLocations();
	  
	  
	  boundView = new GUIContainerColumn(new GUILocation(Alignment.CENTRE, Alignment.CENTRE));
	  upperBound = new NumberInputField(new Posn(0,0,120,30),"uiskin.json");
	  upperBound.setText("upper bound");
	  lowerBound = new NumberInputField(new Posn(0,0,120,30),"uiskin.json");
	  lowerBound.setText("lower bound");
	  TextLabel boundViewLabel = new TextLabel("Upper & Lower Bounds", skin);
	  boundViewLabel.setPos(new Posn(0,0,200,100));
	  boundView.addElement(boundViewLabel);
	  boundView.addElement(upperBound);
	  boundView.addElement(lowerBound);
	  boundView.setPos(new Posn(0,0,200,380));
	  boundView.calibrateLocations();
	  
	  totalView = new GUIContainerRow(new GUILocation(Alignment.CENTRE, Alignment.CENTRE));
	  totalView.setPos(new Posn(0,0,640,380));
	  totalView.addElement(difficultyView);
	  totalView.addElement(view);
	  totalView.addElement(boundView);
	  totalView.calibrateLocations();
	  
	  outerView = new GUIContainerColumn(new GUILocation(Alignment.CENTRE, Alignment.BOTTOM));
	  outerView.setPos(new Posn(0,0,640,480));
	  outerView.addElement(totalView);
	  outerView.addElement(tutorialView);
	  outerView.calibrateLocations();
	  
	  // add all actors to stage
	  outerView.addToStage(stage);
	}

	@Override
	public void render () {
		drawRGB(82,82,82);
		stage.draw();
		stage.act();
	}
	
	@Override
	public void dispose () {
	}
	
	public void drawRGB(int r, int g, int b) {
		float R = r, B = b, G = g;
		R /= 255;
		G /= 255;
		B /= 255;
		Gdx.gl.glClearColor(R, G, B, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
}
