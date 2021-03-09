package com.curso.flappybirdclone;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class FlappyBird extends ApplicationAdapter {

	private SpriteBatch batch;
	private Texture[] birds;
	private Texture background;
	private Texture pipeDown;
	private Texture pipeUp;

	//configuration attributes
	private int deviceWidth;
	private int deviceHeight;

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
		batch.begin();

		deltaTime = Gdx.graphics.getDeltaTime();

		variable += deltaTime *5;
		pipeMovingPositionHorizontal -= deltaTime *200;
		velocityFall ++;

		if(variable>2) variable = 0;

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
		}


		batch.draw(background, 0,0,deviceWidth,deviceHeight);
		batch.draw(pipeUp, pipeMovingPositionHorizontal,deviceHeight / 2 + roomBtwPipes/2 + heightPipesRandom);
		batch.draw(pipeDown, pipeMovingPositionHorizontal,deviceHeight / 2 - pipeDown.getHeight() - roomBtwPipes/2 + heightPipesRandom);
		batch.draw(birds[(int)variable], 120, verticalStartingPosition);

		batch.end();


	}
	
	@Override
	public void dispose () {
		//batch.dispose();
		//img.dispose();
	}
}
