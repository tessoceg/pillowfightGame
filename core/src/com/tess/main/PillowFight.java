package com.tess.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.tess.main.Screens.PlayScreen;

public class PillowFight extends Game {
	public static final int V_WIDTH = 800;
	public static final int V_HEIGHT = 400;
	public SpriteBatch batch;

	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
	}
	public void render () {
		//logic
		super.render();
	}
	public void dispose () {

		//disposes the object while the game isn't running to save memory
	}
}
