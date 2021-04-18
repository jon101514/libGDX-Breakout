package com.jonso.breakout;

import com.badlogic.gdx.graphics.Texture;

/**
 * The ball is an Entity which handles movement, reflection,
 * and bounding itself.
 * @author Jonathan So
 */
public class Ball extends Entity {

    private int initX, initY;
    private int speed = 160;

    /**
     * Constructor for a ball which takes in a:
     * @param sprite Texture to display.
     * @param x Init x position.
     * @param y Init y position.
     */
    public Ball(Texture sprite, int x, int y) {
        super(sprite, x, y);
        initX = x;
        initY = y;
    }

    /**
     * Serves the ball downward and right to hit the paddle.
     */
    public void serve() {
        if (vx == 0 && vy == 0) {
            vx = speed;
            vy = -speed;
        }
    }

    /**
     * Move the ball in its direction, and make sure it's bounded.
     * @param delta Time that's passed from the previous frame to now.
     */
    public void move(float delta) {
        super.move(delta);
        checkBounds();
    }

    /**
     * Bound the ball within the screen.
     */
    private void checkBounds() {
        if (x < 0) { // L. Wall
            x = 0;
            vx *= -1;
        } else if (x > 224 - width) { // R. Wall
            x = 224 - width;
            vx *= -1;
        }
        if (y < -height) { // D. Wall... and below
            reset();
        } else if (y > 256 - height) { // U. Wall
            y = 256 - height;
            vy *= -1;
        }
    }

    /**
     * Reset the ball to its initial position and zero velocity.
     */
    private void reset() {
        this.x = initX;
        this.y = initY;
        this.vx = 0;
        this.vy = 0;
    }

    /**
     * Reflect the ball off an entity.
     * @param other The other entity we're reflecting off of.
     */
    public void collide(Entity other) {
        // Update direction with a vector from us to other
        if (Math.abs(other.getCX() - this.getCX()) > (width / 2)) {
            vx *= -1;
        }
        if (Math.abs(other.getCY() - this.getCY()) > (height / 2)) {
            vy *= -1;
        }
        if (other instanceof Paddle) { // Make sure it only goes up hitting paddle
            vy = Math.abs(vy);
        }

    }
}
