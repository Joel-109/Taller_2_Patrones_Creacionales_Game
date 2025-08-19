package com.balitechy.spacewar.main.Renderer.sprite;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import com.balitechy.spacewar.main.Game;
import com.balitechy.spacewar.main.IBackgroundRenderer;
import com.balitechy.spacewar.main.SpritesImageLoader;

public class SpriteBackgroundRenderer implements IBackgroundRenderer {

    private final BufferedImage background;

    
    public SpriteBackgroundRenderer(SpritesImageLoader loader, int top, int left, int width, int height) {
        BufferedImage tempBg;
        try {
            tempBg = loader.getImage(top, left, width, height);
        } catch (Exception e) {
            e.printStackTrace();
            tempBg = null;
        }
        this.background = tempBg;
    }

    /**
     * Dibuja el fondo en el área del juego.
     * Si la imagen no está disponible, dibuja un fondo negro como fallback.
     */
    @Override
    public void render(Graphics g, Canvas c) {
        try {
            SpritesImageLoader loader = new SpritesImageLoader("./resources/bg.png");
            loader.loadImage();
            BufferedImage background = loader.getImage(0, 0, 640, 480);
            g.drawImage(background, 0, 0, c.getWidth(), c.getHeight(), null);
        } catch (IOException e) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, c.getWidth(), c.getHeight());
        }
    }
}
