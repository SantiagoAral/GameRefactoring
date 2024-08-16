package com.balitechy.spacewar.main.Models.Abstract;

import com.balitechy.spacewar.main.Game.Game;
import com.balitechy.spacewar.main.Interfaces.BulletRenderInterface;


import java.awt.*;

public abstract class AbstractBullet implements BulletRenderInterface {
    protected double x;
    protected double y;
    public static final int WIDTH = 11;
    public static final int HEIGHT = 21;

    public AbstractBullet(double x, double y, Game game) {
        this.x = x;
        this.y = y;
    }

    public void tick() {
        y -= 5;
    }

    public double getY() {
        return y;
    }
    
    public abstract void renderBullet(Graphics g);
}
