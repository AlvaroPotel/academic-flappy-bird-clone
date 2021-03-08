package com.curso.flappybirdclone;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBird extends ApplicationAdapter {

	private SpriteBatch batch;
	private Texture[] birds;
	private Texture background;

	//configuration attributes
	private int deviceWidth;
	private int deviceHeight;

	private float variable = 0;

	private float velocityFall = 0;
	private float verticalStartingPosition;

	@Override
	public void create () {
		batch = new SpriteBatch();
		birds = new Texture[3];
		birds[0] = new Texture("passaro1.png");
		birds[1] = new Texture("passaro2.png");
		birds[2] = new Texture("passaro3.png");
		background = new Texture("fundo.png");

		deviceWidth = Gdx.graphics.getWidth();
		deviceHeight = Gdx.graphics.getHeight();
		verticalStartingPosition = deviceHeight/2;


	}

	@Override
	public void render () {
		batch.begin();

		variable += Gdx.graphics.getDeltaTime()*5;
		velocityFall ++;

		if(variable>2) variable = 0;

		if(verticalStartingPosition > 0){
			verticalStartingPosition = verticalStartingPosition - velocityFall;
		}


		batch.draw(background, 0,0,deviceWidth,deviceHeight);
		batch.draw(birds[(int)variable], 30, verticalStartingPosition);
		batch.end();


	}
	
	@Override
	public void dispose () {
		//batch.dispose();
		//img.dispose();
	}
}
