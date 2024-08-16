package com.balitechy.spacewar.main.Interfaces;

import com.balitechy.spacewar.main.Game.Game;

public interface GameStyleInterface {
    PlayerRenderInterface createPlayer(double x, double y, Game game);
    BackgroundRenderInterface createBackground();
    BulletRenderInterface createBullet(double x, double y, Game game);
}
