package com.balitechy.spacewar.main.Factory;


import com.balitechy.spacewar.main.Game.Game;
import com.balitechy.spacewar.main.Interfaces.BackgroundRenderInterface;
import com.balitechy.spacewar.main.Interfaces.BulletRenderInterface;
import com.balitechy.spacewar.main.Interfaces.GameStyleInterface;
import com.balitechy.spacewar.main.Interfaces.PlayerRenderInterface;
import com.balitechy.spacewar.main.Models.Vectorial.BackgroundVectorial;
import com.balitechy.spacewar.main.Models.Vectorial.BulletVectorial;
import com.balitechy.spacewar.main.Models.Vectorial.PlayerVectorial;


import java.awt.*;

public class GameVectorFactory implements GameStyleInterface {
    private Game game;
    private Color color;

    public GameVectorFactory(Color color) {
        this.color = color;
    }

    @Override
    public PlayerRenderInterface createPlayer(double x, double y, Game game) {
        return new PlayerVectorial(x, y, game, color);
    }

    @Override
    public BackgroundRenderInterface createBackground() {
        return new BackgroundVectorial(color);
    }

    @Override
    public BulletRenderInterface createBullet(double x, double y, Game game) {
        return new BulletVectorial(x, y, game, color);
    }
}
