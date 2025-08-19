package com.balitechy.spacewar.main.Renderer;

import com.balitechy.spacewar.main.Renderer.sprite.SpriteRendererFactory;
import com.balitechy.spacewar.main.Renderer.vector.VectorRendererFactory;

public class RendererFactoryLoader {

    public enum FactoryType {
        SPRITE,
        VECTOR
    }

    private static RendererFactory factory;

    public static void loadFactory(FactoryType type) {
        switch (type) {
            case SPRITE:
                factory = new SpriteRendererFactory(); // Asegúrate de que esta clase exista y implemente RendererFactory
                break;
            case VECTOR:
                factory = new VectorRendererFactory(); // Asegúrate de que esta clase exista y implemente RendererFactory
                break;
            default:
                throw new IllegalArgumentException("Tipo de fábrica no soportado: " + type);
        }
    }

    public static RendererFactory getFactory() {
        if (factory == null) {
            throw new IllegalStateException("RendererFactory no inicializada. Llama primero a loadFactory().");
        }
        return factory;
    }
}
