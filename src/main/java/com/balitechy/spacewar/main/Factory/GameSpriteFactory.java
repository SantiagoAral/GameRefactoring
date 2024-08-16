package com.balitechy.spacewar.main.Factory;

import com.balitechy.spacewar.main.Game.Game;
import com.balitechy.spacewar.main.Interfaces.GameStyleInterface;
import com.balitechy.spacewar.main.Interfaces.BackgroundRenderInterface;
import com.balitechy.spacewar.main.Interfaces.BulletRenderInterface;
import com.balitechy.spacewar.main.Interfaces.PlayerRenderInterface;
import com.balitechy.spacewar.main.Models.NormalSprites.BackgroundSprite;
import com.balitechy.spacewar.main.Models.NormalSprites.BulletSprite;
import com.balitechy.spacewar.main.Models.NormalSprites.PlayerSprite;


public class GameSpriteFactory implements GameStyleInterface {
    public Game game;

    @Override
    public PlayerRenderInterface createPlayer(double x, double y, Game game) {
        return new PlayerSprite(x, y, game);
    }

    @Override
    public BackgroundRenderInterface createBackground() {
        return new BackgroundSprite();
    }

    @Override
    public BulletRenderInterface createBullet(double x, double y, Game game) {
        return new BulletSprite(x, y, game);
    }
}
