package com.balitechy.spacewar.main.Renderer.sprite;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import com.balitechy.spacewar.main.Bullet;
import com.balitechy.spacewar.main.Game;
import com.balitechy.spacewar.main.SpritesImageLoader;
import com.balitechy.spacewar.main.Renderer.BulletRenderer;

public class SpriteBulletRenderer extends Bullet implements BulletRenderer {

    private final BufferedImage sprite;

    public SpriteBulletRenderer(
            double x,
            double y,
            Game game,
            SpritesImageLoader loader,
            int top, int left, int width, int height) {

        super(x, y, game);

        BufferedImage tempSprite;
        try {
            tempSprite = loader.getImage(top, left, width, height);
        } catch (Exception e) {
            e.printStackTrace();
            tempSprite = null;
        }
        this.sprite = tempSprite;
    }

    @Override
    public void draw(Graphics2D g) {
        if (sprite != null) {
            g.drawImage(sprite, (int) x, (int) y, WIDTH, HEIGHT, null);
        } else {
            g.setColor(java.awt.Color.RED);
            g.fillRect((int) x, (int) y, WIDTH, HEIGHT);
        }
    }
}
