package com.balitechy.spacewar.main.Models.Vectorial;

import com.balitechy.spacewar.main.Game.Game;
import com.balitechy.spacewar.main.Models.Abstract.AbstractBullet;
import com.balitechy.spacewar.main.Models.Abstract.AbstractPlayer;

import java.awt.*;

public class PlayerVectorial extends AbstractPlayer {
    private Color color;

    public PlayerVectorial(double x, double y, Game game, Color color) {
        super(x, y, game);
        this.color = color;
    }

    @Override
    public void renderPlayer(Graphics g) {
        g.setColor(color);

        int[] xPoints = {
                (int) x,
                (int) x + WIDTH,
                (int) x + WIDTH / 2
        };
        int[] yPoints = {
                (int) y + HEIGHT,
                (int) y + HEIGHT,
                (int) y
        };

        g.drawPolygon(xPoints, yPoints, 3);
    }

    @Override
    public void shoot() {
        AbstractBullet bullet = new BulletVectorial(x + (WIDTH / 2) - 5, y - 18, game, color);
        game.getBullets().addBullet(bullet);
    }
}
