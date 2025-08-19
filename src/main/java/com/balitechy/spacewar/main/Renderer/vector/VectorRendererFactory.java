package com.balitechy.spacewar.main.Renderer.vector;

import com.balitechy.spacewar.main.IBackgroundRenderer;
import com.balitechy.spacewar.main.Renderer.BulletRenderer;
import com.balitechy.spacewar.main.Renderer.PlayerRenderer;
import com.balitechy.spacewar.main.Renderer.RendererFactory;

public class VectorRendererFactory implements RendererFactory {

    @Override
    public PlayerRenderer createPlayerRenderer() {
        return new VectorPlayerRenderer();
    }

    @Override
    public BulletRenderer createBulletRenderer() {
        return new VectorBulletRenderer();
    }

    @Override
    public IBackgroundRenderer createBackgroundRenderer() {
        return new VectorBackgroundRenderer();
    }
}
