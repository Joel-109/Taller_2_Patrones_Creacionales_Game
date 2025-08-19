package com.balitechy.spacewar.factories;

import com.balitechy.spacewar.colorful.ColorfulVectorPlayer;
import com.balitechy.spacewar.main.*;

public class ColorfulVectorGameElementFactory implements GameElementFactory {
    @Override
    public Player createPlayer() { return new ColorfulVectorPlayer(200,200); }
    @Override
    public Bullet createBullet() { return new ColorfulVectorBullet(); }
    @Override
    public BackgroundRenderer createBackgroundRenderer() { return new ColorfulVectorBackgroundRenderer(); }
}