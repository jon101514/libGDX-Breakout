package com.jonso.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

/** Screen for the main game, which does a lot of heavy lifting.
 * Takes after both Kevin Glass' (CodeAndCode) Space Invaders tutorial as
 * well as the official libGDX tutorial for the "Drop" game.
 * @author Jonathan So
 */
public class GameScreen implements Screen {
    final Breakout game;

    private OrthographicCamera camera;

    // ASSETS
    // Textures
    private Texture paddleTX;
    private Texture ballTX;
    private Texture brickTX;

    // ENTITIES
    private ArrayList<Entity> entities;
    private ArrayList<Brick> bricks; // Subset of entities; just the bricks
    private ArrayList<Entity> removeList; // entities to remove
    private Paddle paddle;
    private Ball ball;

    // SCREEN
    private int WIDTH = 224;
    private int HEIGHT = 256;
    private int X_OFFSET = 16;

    // GAME PROPS
    private int paddleSpeed = 256;
    private final int COLUMNS = 8;
    private final int ROWS = 5;

    /**
     * Load assets, create entities with assets, and start up the game.
     * @param game The Breakout object, passed from TitleScreen.
     */
    public GameScreen(final Breakout game) {
        this.game = game;
        // LOAD ASSETS
        // Load texture assets
        paddleTX = new Texture(Gdx.files.internal("paddle.png"));
        ballTX = new Texture(Gdx.files.internal("ball.png"));
        brickTX = new Texture(Gdx.files.internal("brick.png"));

        // CREATE + SETUP CAMERA
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);

        // CREATE ENTITIES
        entities = new ArrayList<Entity>();
        bricks = new ArrayList<Brick>();
        removeList = new ArrayList<Entity>();
        // Create the bricks
        makeBricks();
        paddle = new Paddle(paddleTX, WIDTH / 2, HEIGHT / 8);
        entities.add(paddle);
        ball = new Ball(ballTX, WIDTH / 3, HEIGHT / 5);
        entities.add(ball);
    }

    /**
     * The game loop will:
     * 1. Clear the screen and update the camera.
     * 2. Get rid of any garbage (e.g. broken bricks).
     * 3. Draw all entities.
     * 4. Process input.
     * 5. Handle collisions.
     * @param delta Time from the previous update to now.
     */
    @Override
    public void render(float delta) {
        // Clear screen and update camera
        ScreenUtils.clear(0, 0, 0, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        // Clear entities we don't want to use
        entities.removeAll(removeList);
        bricks.removeAll(removeList);
        removeList.clear();

        // If bricks is empty, make more.
        if (bricks.isEmpty()) { makeBricks(); }

        // Draw every entity
        game.batch.begin();
        for (Entity ntt : entities) {
            ntt.move(delta);
            game.batch.draw(ntt.getSprite(), ntt.getX(), ntt.getY());
        }
        game.batch.end();

        // Process input
        processInput();

        // Handle collisions: ball to every other entity.
        // Ball-to-paddle
        if (ball.checkCollision(paddle)) {
            ball.collide(paddle);
        }
        // Ball-to-brick
        for (Brick brick : bricks) {
            if (ball.checkCollision(brick)) {
                ball.collide(brick);
                removeList.add(brick);
            }
        }
    }

    /**
     * Take user input; arrow keys move, space serves, and Esc quits.
     */
    public void processInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            paddle.setXVelocity(-paddleSpeed);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            paddle.setXVelocity(paddleSpeed);
        } else if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            ball.serve();
        } else if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            System.exit(0);
        } else {
            paddle.setXVelocity(0);
        }
    }

    /** Create a grid of bricks to break;
     * called both when the game starts and when
     * the player has broken all bricks.
     */
    private void makeBricks() {
        for (int i = 0; i < COLUMNS; i++) {
            for (int j = 0; j < ROWS; j++) {
                Brick brick = new Brick(brickTX,
                        X_OFFSET + brickTX.getWidth() * i,
                        brickTX.getHeight() * j + (HEIGHT * 5 / 8));
                bricks.add(brick);
                entities.add(brick);
            }
        }
    }

    /** Free all of our assets. */
    @Override
    public void dispose() {
        paddleTX.dispose();
        ballTX.dispose();
        brickTX.dispose();
    }

    /** REQUIRED SCREEN METHODS */

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
    public void show() {

    }

    @Override
    public void hide() {

    }
}
