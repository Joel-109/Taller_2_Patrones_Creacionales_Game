package com.balitechy.spacewar.main.Renderer;

import com.balitechy.spacewar.main.Bullet;
import com.balitechy.spacewar.main.Game;
import com.balitechy.spacewar.main.IBackgroundRenderer;
import com.balitechy.spacewar.main.Player;

public interface GameElementFactory {
    
    Player createPlayer(Game game);

    Bullet createBullet(Game game, double x, double y);

    IBackgroundRenderer createBackgroundRenderer(Game game);

    default boolean requiresSprites() {
        // Por defecto, las fábricas no requieren sprites.
        return false;
    }
}
