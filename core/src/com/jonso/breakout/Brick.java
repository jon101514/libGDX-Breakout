package com.jonso.breakout;

import com.badlogic.gdx.graphics.Texture;

/**
 * Basic Brick entity with a sprite and position.
 * @author Jonathan So
 */
public class Brick extends Entity{
    /**
     * Make a brick with a:
     * @param sprite Sprite to display.
     * @param x Initial X position.
     * @param y Initial Y position.
     */
    public Brick(Texture sprite, int x, int y) {
        super(sprite, x, y);
    }

}
