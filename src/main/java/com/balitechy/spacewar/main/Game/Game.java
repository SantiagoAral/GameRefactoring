package com.balitechy.spacewar.main.Game;

import com.balitechy.spacewar.main.Controllers.ControllerBullet;
import com.balitechy.spacewar.main.Factory.GameSpriteFactory;

import com.balitechy.spacewar.main.Factory.GameVectorFactory;
import com.balitechy.spacewar.main.Interfaces.GameStyleInterface;
import com.balitechy.spacewar.main.Interfaces.BackgroundRenderInterface;
import com.balitechy.spacewar.main.Interfaces.PlayerRenderInterface;
import com.balitechy.spacewar.main.Models.Abstract.AbstractPlayer;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.Scanner;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 320;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final int SCALE = 2;
    public final String TITLE = "Space War 2D";

    private boolean running = false;
    private Thread thread;

    // Factories and game components
    private GameStyleInterface gameStyle;
    private PlayerRenderInterface player;
    private ControllerBullet bullets;
    private BackgroundRenderInterface background;
	private SpritesImageLoader sprites;

    public void init() {
        requestFocus();

        sprites = new SpritesImageLoader("/sprites.png");
        
		try {
			sprites.loadImage();
		} catch (IOException e) {
			e.printStackTrace();
		}

        background = gameStyle.createBackground();
        player = gameStyle.createPlayer((WIDTH * SCALE - AbstractPlayer.WIDTH) / 2, HEIGHT * SCALE - 50, this);
        bullets = new ControllerBullet();

        // Add keyboard listener
        addKeyListener(new InputHandler(this));
    }

	public SpritesImageLoader getSprites() {
		return sprites;
	}

	public ControllerBullet getBullets() {
		return bullets;
	}

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_RIGHT:
                player.setVelX(5);
                break;

            case KeyEvent.VK_LEFT:
                player.setVelX(-5);
                break;

            case KeyEvent.VK_UP:
                player.setVelY(-5);
                break;

            case KeyEvent.VK_DOWN:
                player.setVelY(5);
                break;

            case KeyEvent.VK_SPACE:
                player.shoot();
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_RIGHT:
                player.setVelX(0);
                break;

            case KeyEvent.VK_LEFT:
                player.setVelX(0);
                break;

            case KeyEvent.VK_UP:
                player.setVelY(0);
                break;

            case KeyEvent.VK_DOWN:
                player.setVelY(0);
                break;
        }
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
        System.exit(1);
    }

    @Override
    public void run() {
        init();

        long lastTime = System.nanoTime();
        final double numOfTicks = 60.0;
        double ns = 1000000000 / numOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
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
                System.out.println(updates + "ticks, fps " + frames);
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    public void tick() {
        player.tick();
        bullets.tick();
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        //////////////////////////////////
        background.renderBackground(g, this);
        player.renderPlayer(g);
        bullets.render(g);
        /////////////////////////////////
        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n Elige el estilo del juego:");
        System.out.println("1. Vectorial");
        System.out.println("2. Sprite");
        int choice = scanner.nextInt();

        Game game = new Game();

        if (choice == 1) {
            System.out.println("Elige una opción de color para el estilo vectorial:");
            System.out.println("1. Rojo");
            System.out.println("2. Verde");
            System.out.println("3. Azul");
            System.out.println("4. Amarillo");
            System.out.println("5. Magenta");
            System.out.println("6. Cian");
            System.out.println("7. Negro");
            System.out.println("8. Personalizado (RGB)");
            int colorChoice = scanner.nextInt();

            Color color;

            switch (colorChoice) {
                case 1:
                    color = Color.RED;
                    break;
                case 2:
                    color = Color.GREEN;
                    break;
                case 3:
                    color = Color.BLUE;
                    break;
                case 4:
                    color = Color.YELLOW;
                    break;
                case 5:
                    color = Color.MAGENTA;
                    break;
                case 6:
                    color = Color.CYAN;
                    break;
                case 7:
                    color = Color.BLACK;
                    break;
                case 8:
                    // Personalizado
                    System.out.print("Introduce el valor para el Rojo (0-255): ");
                    int red = scanner.nextInt();

                    System.out.print("Introduce el valor para el Verde (0-255): ");
                    int green = scanner.nextInt();

                    System.out.print("Introduce el valor para el Azul (0-255): ");
                    int blue = scanner.nextInt();

                    // Validar los valores RGB y corregir si están fuera de rango
                    red = Math.max(0, Math.min(255, red));
                    green = Math.max(0, Math.min(255, green));
                    blue = Math.max(0, Math.min(255, blue));

                    color = new Color(red, green, blue);
                    break;
                default:
                    System.out.println("Opción no válida. Se usará el color por defecto (Negro).");
                    color = Color.BLACK;
                    break;
            }

            game.gameStyle = new GameVectorFactory(color);
        } else {
            game.gameStyle = new GameSpriteFactory();
        }

        game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        JFrame frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();
    }





}
