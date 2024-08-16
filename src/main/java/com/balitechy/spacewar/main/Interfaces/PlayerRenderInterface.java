package com.balitechy.spacewar.main.Interfaces;

import java.awt.*;

public interface PlayerRenderInterface {
    void renderPlayer(Graphics g);
    void setVelX(double velX);
    void setVelY(double velY);
    void shoot();
    void tick();
}
