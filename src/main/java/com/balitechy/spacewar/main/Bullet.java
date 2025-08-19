package com.balitechy.spacewar.main;

import java.awt.Graphics2D;

public class Bullet {

    public static final int WIDTH = 11;
    public static final int HEIGHT = 21;

    protected double x;
    protected double y;
    protected double speed = 5;

    protected Game game;

    public Bullet(double x, double y, Game game) {
        this.x = x;
        this.y = y;
        this.game = game;
    }

    // --- getters & setters ---
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }

    public int getWidth() { return WIDTH; }
    public int getHeight() { return HEIGHT; }

    public void tick() {
        y -= speed;
        if (y + HEIGHT < 0 && game != null && game.getBullets() != null) {
            game.getBullets().removeBullet(this);
        }
    }

    // Método polimórfico para dibujar la bala
    public void draw(Graphics2D g) {
        // Por defecto no dibuja nada
    }
}
