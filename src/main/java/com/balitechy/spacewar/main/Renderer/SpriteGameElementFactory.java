package com.balitechy.spacewar.main.Renderer;

import java.io.IOException;

import com.balitechy.spacewar.main.Bullet;
import com.balitechy.spacewar.main.Game;
import com.balitechy.spacewar.main.IBackgroundRenderer;
import com.balitechy.spacewar.main.Player;
import com.balitechy.spacewar.main.Renderer.sprite.SpriteBackgroundAdapter;
import com.balitechy.spacewar.main.Renderer.sprite.SpriteBulletAdapter;
import com.balitechy.spacewar.main.Renderer.sprite.SpritePlayerAdapter;
import com.balitechy.spacewar.main.SpritesImageLoader;

public class SpriteGameElementFactory implements GameElementFactory {

    private final SpritesImageLoader spriteSheet;

    public SpriteGameElementFactory() {
        spriteSheet = new SpritesImageLoader("/resources/sprites.png");
        try {
            spriteSheet.loadImage(); // carga inmediata
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("No se pudo cargar sprites.png");
        }
    }

    @Override
    public Player createPlayer(Game game) {
        return new SpritePlayerAdapter(100, 400, game, spriteSheet, 219, 304, 56, 28);
    }

    @Override
    public Bullet createBullet(Game game, double x, double y) {
        return new SpriteBulletAdapter(x, y, game, spriteSheet, 225, 356, 8, 8);
    }

    @Override
    public IBackgroundRenderer createBackgroundRenderer(Game game) {
        return new SpriteBackgroundAdapter(spriteSheet, 0, 0, 1024, 768); // ajusta según tu hoja de sprites
    }

    public SpritesImageLoader getSprites() {
        return spriteSheet;
    }
}
