package com.curso.flappybirdclone;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBird extends ApplicationAdapter {

	SpriteBatch batch;
	Texture bird;
	private Texture background;

	//configuration attributes
	private int deviceWidth;
	private int deviceHeight;
	private int moving = 0;

	@Override
	public void create () {
		batch = new SpriteBatch();
		bird = new Texture("passaro1.png");
		background = new Texture("fundo.png");
		deviceWidth = Gdx.graphics.getWidth();
		deviceHeight = Gdx.graphics.getHeight();


	}

	@Override
	public void render () {
		//Gdx.gl.glClearColor(1, 0, 0, 1);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		moving++;

		batch.draw(background, 0,0,deviceWidth,deviceHeight);
		batch.draw(bird, moving, 700);
		batch.end();


	}
	
	@Override
	public void dispose () {
		//batch.dispose();
		//img.dispose();
	}
}
