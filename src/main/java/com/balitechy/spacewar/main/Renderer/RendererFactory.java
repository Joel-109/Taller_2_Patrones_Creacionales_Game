package com.balitechy.spacewar.main.Renderer;


public interface RendererFactory {
    PlayerRenderer createPlayerRenderer();
    BulletRenderer createBulletRenderer();
    IBackgroundRenderer createBackgroundRenderer();
}
