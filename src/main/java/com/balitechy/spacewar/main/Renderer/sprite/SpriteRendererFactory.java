package com.balitechy.spacewar.main.Renderer.sprite;

import com.balitechy.spacewar.main.Renderer.RendererFactory;
import com.balitechy.spacewar.main.Renderer.PlayerRenderer;
import com.balitechy.spacewar.main.Renderer.BulletRenderer;
import com.balitechy.spacewar.main.Renderer.IBackgroundRenderer;
import com.balitechy.spacewar.main.Game;
import com.balitechy.spacewar.main.Player;
import com.balitechy.spacewar.main.Bullet;
import com.balitechy.spacewar.main.SpritesImageLoader;

public class SpriteRendererFactory implements RendererFactory {

    private final SpritesImageLoader loader = new SpritesImageLoader("./resources/sprites.png");
    private final Game game;

    public SpriteRendererFactory(Game game) {
        this.game = game;
    }

    @Override
    public PlayerRenderer createPlayerRenderer() {
        // Devuelve tu adaptador de Player con todos los argumentos
        return new SpritePlayerAdapter(
            100, 400, game, loader, 0, 0, 32, 32
        );
    }

    @Override
    public BulletRenderer createBulletRenderer() {
        // Devuelve tu bullet actual
        return new SpriteBulletRenderer(
            100, 300, game, loader, 0, 0, 8, 8
        );
    }

    @Override
    public IBackgroundRenderer createBackgroundRenderer() {
        // Aquí podrías implementar un SpriteBackgroundRenderer similar
        return new SpriteBackgroundRenderer(loader, 0, 0, 640, 480);
    }
}
