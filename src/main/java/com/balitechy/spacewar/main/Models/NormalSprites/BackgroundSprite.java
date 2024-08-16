package com.balitechy.spacewar.main.Models.NormalSprites;

import com.balitechy.spacewar.main.Game.SpritesImageLoader;
import com.balitechy.spacewar.main.Models.Abstract.AbstractBackground;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BackgroundSprite extends AbstractBackground {
    
    
    @Override
    public void renderBackground(Graphics g, Canvas c) {
        try {
            BufferedImage background = null;
            SpritesImageLoader bg;
            bg = new SpritesImageLoader("/bg.png");
            bg.loadImage();
            background = bg.getImage(0, 0, 640, 480);
            g.drawImage(background, 0, 0, c.getWidth(), c.getHeight(), c);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
