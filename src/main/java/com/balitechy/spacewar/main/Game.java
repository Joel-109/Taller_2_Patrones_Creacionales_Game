package com.balitechy.spacewar.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.balitechy.spacewar.main.Renderer.FactoryLoader;
import com.balitechy.spacewar.main.Renderer.GameElementFactory;
import com.balitechy.spacewar.main.Renderer.SpriteGameElementFactory;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 320;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final int SCALE = 2;
    public final String TITLE = "Space War 2D";

    private boolean running = false;
    private Thread thread;

    private Player player;
    private BulletController bullets;
    private IBackgroundRenderer currentBackground;
    private GameElementFactory factory;

    public void init() {
        requestFocus();
        addKeyListener(new InputHandler(this));

        factory = FactoryLoader.getFactory();
        if (factory == null) {
            throw new IllegalStateException("No se ha cargado ninguna fábrica.");
        }

        // Crear jugador usando la fábrica
        player = factory.createPlayer(this);

        // Controlador de balas
        bullets = new BulletController();

        // Fondo
        try {
            currentBackground = factory.createBackgroundRenderer(this);
        } catch (Exception e) {
            System.err.println("No se pudo cargar el fondo, se usará fondo vacío.");
            currentBackground = (g, game) -> {}; // fondo vacío
        }
    }

    public void tick() {
        player.tick();
        bullets.tick();
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        try {
            if (currentBackground != null) currentBackground.render(g, this);
            player.render(g);
            bullets.render(g);
        } catch (IOException e) {
            e.printStackTrace();
        }

        g.dispose();
        bs.show();
    }

    private synchronized void start() {
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop() {
        if (!running) return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    @Override
    public void run() {
        init();
        long lastTime = System.nanoTime();
        final double ticksPerSecond = 60.0;
        double ns = 1000000000 / ticksPerSecond;
        double delta = 0;
        int updates = 0, frames = 0;
        long timer = System.currentTimeMillis();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if (delta >= 1) {
                tick();
                updates++;
                delta--;
            }

            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(updates + " ticks, fps " + frames);
                updates = 0;
                frames = 0;
            }
        }

        stop();
    }

    public static void main(String[] args) {
        JFrame menu = new JFrame("Selecciona Vista");
        menu.setLayout(new java.awt.FlowLayout());
        JButton btnSprite = new JButton("Sprite");
        JButton btnVector = new JButton("Vector");

        btnSprite.addActionListener((ActionEvent e) -> {
            try {
                FactoryLoader.loadFactory(FactoryLoader.FactoryType.SPRITE);

                // Forzar carga de imagen solo si es SpriteFactory
                if (FactoryLoader.getFactory() instanceof SpriteGameElementFactory) {
                    SpriteGameElementFactory spriteFactory = (SpriteGameElementFactory) FactoryLoader.getFactory();
                    spriteFactory.getSprites().loadImage();
                }

                menu.dispose();
                startGame();

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(menu,
                        "No se pudo cargar la imagen de sprites. Verifica la ruta.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        btnVector.addActionListener((ActionEvent e) -> {
            FactoryLoader.loadFactory(FactoryLoader.FactoryType.VECTOR);
            menu.dispose();
            startGame();
        });

        menu.add(btnSprite);
        menu.add(btnVector);
        menu.setSize(250, 100);
        menu.setLocationRelativeTo(null);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setVisible(true);
    }

    private static void startGame() {
        Game game = new Game();
        JFrame frame = new JFrame(game.TITLE);
        frame.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        frame.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        frame.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        frame.add(game);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        game.start();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_RIGHT: player.setVelX(5); break;
            case KeyEvent.VK_LEFT: player.setVelX(-5); break;
            case KeyEvent.VK_UP: player.setVelY(-5); break;
            case KeyEvent.VK_DOWN: player.setVelY(5); break;
            case KeyEvent.VK_SPACE: player.shoot(factory); break;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_LEFT: player.setVelX(0); break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN: player.setVelY(0); break;
        }
    }

    public BulletController getBullets() {
        return bullets;
    }
}
