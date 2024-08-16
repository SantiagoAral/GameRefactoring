package com.balitechy.spacewar.main.Models.NormalSprites;

import com.balitechy.spacewar.main.Game.Game;
import com.balitechy.spacewar.main.Models.Abstract.AbstractBullet;
import com.balitechy.spacewar.main.Models.Abstract.AbstractPlayer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerSprite extends AbstractPlayer {
    private BufferedImage image;

    public PlayerSprite (double x, double y, Game game) {
        super(x, y, game);
        image = game.getSprites().getImage(219, 304, WIDTH, HEIGHT);
    }

    @Override
    public void renderPlayer(Graphics g) {
        g.setColor(Color.white);
		g.drawImage(image, (int) x, (int) y, null);
    }

    @Override
    public void shoot() {
        AbstractBullet bullet = new BulletSprite(x + (WIDTH / 2) - 5, y - 18, game);
        game.getBullets().addBullet(bullet);
    }
}
