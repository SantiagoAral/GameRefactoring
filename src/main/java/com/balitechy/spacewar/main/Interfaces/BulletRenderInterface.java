package com.balitechy.spacewar.main.Interfaces;

import java.awt.*;

public interface BulletRenderInterface {
    void renderBullet(Graphics g);
    void tick();
    double getY();
}
