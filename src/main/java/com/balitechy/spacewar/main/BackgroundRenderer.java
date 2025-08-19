package com.balitechy.spacewar.main;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BackgroundRenderer implements IBackgroundRenderer {

    private BufferedImage background;
    private boolean imageLoaded = false;

    public BackgroundRenderer() {
        try {
            SpritesImageLoader loader = new SpritesImageLoader("./resources/bg.png");
            loader.loadImage();
            background = loader.getImage(0, 0, 640, 480);
            imageLoaded = true;
        } catch (IOException e) {
            imageLoaded = false;
        }
    }

    @Override
    public void render(Graphics g, Canvas c) {
        if (imageLoaded) {
            g.drawImage(background, 0, 0, c.getWidth(), c.getHeight(), null);
        } else {
            // Si no se carga la imagen, dibuja un fondo negro por defecto
            g.setColor(java.awt.Color.BLACK);
            g.fillRect(0, 0, c.getWidth(), c.getHeight());
        }
    }
}
