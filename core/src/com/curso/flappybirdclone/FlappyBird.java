package com.curso.flappybirdclone;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class FlappyBird extends ApplicationAdapter {

	private SpriteBatch batch;
	private Texture[] birds;
	private Texture background;
	private Texture pipeDown;
	private Texture pipeUp;

	//crash
	private Circle birdCircle;
	private Rectangle pipeUpRectangle;
	private Rectangle pipeDownRectangle;

	//draw the score
	private BitmapFont font;
	private int score = 0;
	private boolean scoredPoint;

	//configuration attributes
	private int deviceWidth;
	private int deviceHeight;

	//Status 0, game not start
	private int gameStatus = 0;

	private float variable = 0;

	private float velocityFall = 0;
	private float verticalStartingPosition;

	//configuration pipes
	private float pipeMovingPositionHorizontal;
	private float pipeMovingPositionVertical;
	private float roomBtwPipes;
	private Random numberRandom;
	private float heightPipesRandom;;

	private float deltaTime;

	@Override
	public void create () {
		batch = new SpriteBatch();
		birds = new Texture[3];
		birds[0] = new Texture("passaro1.png");
		birds[1] = new Texture("passaro2.png");
		birds[2] = new Texture("passaro3.png");

		background = new Texture("fundo.png");

		pipeDown = new Texture("cano_baixo_maior.png");
		pipeUp = new Texture("cano_topo_maior.png");

		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(6);

		birdCircle = new Circle();

		deviceWidth = Gdx.graphics.getWidth();
		deviceHeight = Gdx.graphics.getHeight();
		verticalStartingPosition = deviceHeight/2;
		pipeMovingPositionHorizontal = deviceWidth;
		pipeMovingPositionVertical = deviceWidth;
		roomBtwPipes = 300;
		numberRandom = new Random();


	}

	@Override
	public void render () {

		deltaTime = Gdx.graphics.getDeltaTime();
		variable += deltaTime *8;
		if(variable>2) variable = 0;

		//condition to start game
		if(gameStatus == 0){

			if(Gdx.input.justTouched()){
				gameStatus = 1;
			}

		} else{

			pipeMovingPositionHorizontal -= deltaTime *250;
			velocityFall ++;

			//Bird
			if(Gdx.input.justTouched()){
				velocityFall = - 15;
			}
			if(verticalStartingPosition > 0 || velocityFall < 0){
				verticalStartingPosition = verticalStartingPosition - velocityFall;
			}

			//checks if the pipe has left the screen
			if(pipeMovingPositionHorizontal < -pipeUp.getWidth()){
				pipeMovingPositionHorizontal = deviceWidth;
				heightPipesRandom = numberRandom.nextInt(400) - 200;
				scoredPoint = false;
			}

			//check score
			if(pipeMovingPositionHorizontal < 120){
				if(!scoredPoint){
					score++;
					scoredPoint = true;
				}
			}
		}

		batch.begin();

		batch.draw(background, 0,0,deviceWidth,deviceHeight);
		batch.draw(pipeUp, pipeMovingPositionHorizontal,deviceHeight / 2 + roomBtwPipes/2 + heightPipesRandom);
		batch.draw(pipeDown, pipeMovingPositionHorizontal,deviceHeight / 2 - pipeDown.getHeight() - roomBtwPipes/2 + heightPipesRandom);
		batch.draw(birds[(int)variable], 120, verticalStartingPosition);
		font.draw(batch, String.valueOf(score), deviceWidth/2, deviceHeight - 150);

		batch.end();

		//creating shapes
		birdCircle.set(120 + birds[0].getWidth()/2, verticalStartingPosition + birds[0].getHeight()/2, birds[0].getWidth()/2);
		pipeDownRectangle = new Rectangle(pipeMovingPositionHorizontal,
				deviceHeight / 2 - pipeDown.getHeight() - roomBtwPipes/2 + heightPipesRandom,
				pipeDown.getWidth(),
				pipeDown.getHeight());
		pipeUpRectangle = new Rectangle(pipeMovingPositionHorizontal,
				deviceHeight / 2 + roomBtwPipes/2 + heightPipesRandom,
				pipeUp.getWidth(),
				pipeUp.getHeight());

		//test crash
		if(Intersector.overlaps(birdCircle, pipeDownRectangle) || Intersector.overlaps(birdCircle, pipeUpRectangle)){
			Gdx.app.log("Crash", "Crash detect");
		}

	}
	
	@Override
	public void dispose () {
		//batch.dispose();
		//img.dispose();
	}
}
