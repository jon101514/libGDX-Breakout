package com.jonso.breakout;

import com.badlogic.gdx.graphics.Texture;

/**
 * Paddle that can move horizontally and bound itself to the screen.
 * @author Jonathan So
 */
public class Paddle extends Entity {

    /**
     * Make a Paddle with a Texture and position.
     * @param sprite Texture.
     * @param x Initial X position.
     * @param y Initial Y position.
     */
    public Paddle(Texture sprite, int x, int y) {
        super(sprite, x, y);
    }

    /**
     * Move the paddle laterally, then keep itself bounded.
     * @param vx Velocity in x-direction.
     */
    public void setXVelocity(int vx) {
        this.vx = vx;
        checkBounds();
    }

    /**
     * Check if the paddle is out of bounds and adjust position accordingly.
     */
    private void checkBounds() {
        if (x < 0) {
            x = 0;
        } else if (x > 224 - width) {
            x = 224 - width;
        }
    }
}
