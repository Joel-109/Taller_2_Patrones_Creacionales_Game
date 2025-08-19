package com.balitechy.spacewar.main.Renderer.sprite;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import com.balitechy.spacewar.main.Game;
import com.balitechy.spacewar.main.Player;
import com.balitechy.spacewar.main.SpritesImageLoader;
import com.balitechy.spacewar.main.Renderer.PlayerRenderer;

public class SpritePlayerAdapter extends Player implements PlayerRenderer {

    private BufferedImage sprite;

    public SpritePlayerAdapter(
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
    public void render(Graphics g) {
        if (sprite != null) {
            g.drawImage(sprite, (int) getX(), (int) getY(), WIDTH, HEIGHT, null);
        } else {
            g.setColor(java.awt.Color.RED);
            g.fillRect((int) getX(), (int) getY(), WIDTH, HEIGHT);
        }
    }
}
