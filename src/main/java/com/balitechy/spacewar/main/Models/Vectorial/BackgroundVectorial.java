package com.balitechy.spacewar.main.Models.Vectorial;

import com.balitechy.spacewar.main.Game.Game;
import com.balitechy.spacewar.main.Interfaces.BackgroundRenderInterface;


import java.awt.*;

public class BackgroundVectorial implements BackgroundRenderInterface {

    private Color color;


    public BackgroundVectorial(Color color) {
        this.color = color;
    }
    @Override
    public void renderBackground(Graphics g, Canvas c) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, c.getWidth(), c.getHeight());

        g.setColor(color);

        int diameter = 220;
        int x = 0;
        int y = 0;

        g.drawOval(x, y, diameter, diameter);
    }
}
