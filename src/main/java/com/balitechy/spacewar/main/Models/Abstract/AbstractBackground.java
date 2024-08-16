package com.balitechy.spacewar.main.Models.Abstract;

import com.balitechy.spacewar.main.Interfaces.BackgroundRenderInterface;

import java.awt.*;

public abstract class AbstractBackground implements BackgroundRenderInterface {
    public abstract void renderBackground(Graphics g, Canvas c);
}
