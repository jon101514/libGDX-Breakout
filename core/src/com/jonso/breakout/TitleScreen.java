package com.jonso.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * A basic Title Screen for the game.
 *
 * @author Jonathan So
 */
public class TitleScreen implements Screen {

    final Breakout game;
    OrthographicCamera camera;

    public TitleScreen(final Breakout game) {
        this.game = game;
        // Camera setup
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 224, 256);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Breakout in libGDX", 64, 150);
        game.font.draw(game.batch, "By Jonathan So", 72, 100);
        game.batch.end();

        if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    /** REQUIRED SCREEN METHODS */

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
