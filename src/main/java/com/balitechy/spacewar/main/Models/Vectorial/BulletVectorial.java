package com.balitechy.spacewar.main.Models.Vectorial;

import com.balitechy.spacewar.main.Game.Game;
import com.balitechy.spacewar.main.Models.Abstract.AbstractBullet;

import java.awt.*;

public class BulletVectorial extends AbstractBullet {
    private int bulletWidth = 3;
    private int bulletHeight = 10;
    private Color color;

    public BulletVectorial(double x, double y, Game game, Color color) {
        super(x, y, game);
        this.color = color;
    }

    @Override
    public void renderBullet(Graphics g) {
        g.setColor(color);
        g.fillRect((int) x, (int) y, bulletWidth, bulletHeight);
    }
}

