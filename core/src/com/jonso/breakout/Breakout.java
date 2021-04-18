package com.jonso.breakout;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Handles initializing the Title Screen.
 * Contains the SpriteBatch as well as the default font to draw with.
 * @author Jonathan So
 */
public class Breakout extends Game {

	public SpriteBatch batch;
	public BitmapFont font;
	
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new TitleScreen(this));
	}

	public void render () {
		super.render();
	}

	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
