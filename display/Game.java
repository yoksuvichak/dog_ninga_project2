package display;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import Charactor.*;
import Element.Element;
import event.Event;

public class Game extends JPanel implements KeyListener {

    private static final long serialVersionUID = 1L;
    private static final int SPEED = 50;
    private static final int DOG_SIZE = 60;
    private static final int WAVE_HEIGHT = 50;
    private static final int BASE = 400;
    private static final int X_START = 1000;

    private long points = 0;
    private long lastPressTime = 0;

    private Dog dog = new Dog(100, BASE - 50);
    static Display display;

    private Wave[] waveSet = makeWave(4); // Creating an array of waves
    private Environment[] envSet = makeEnv(2, Environment.CLOUD); // Creating an array of environmental elements (clouds)
    private Environment building = new Environment(X_START - 100, BASE - 150, this, Environment.BUILDING, 4); // Creating a building instance

    public Game() {
        setupPanel();
    }

    private void setupPanel() {
        this.setBounds(0, 0, 1000, 600); // Setting the size of the panel
        this.addKeyListener(this); // Adding the key listener to capture keyboard input
        this.setLayout(null); // Setting layout manager to null for absolute positioning
        this.setFocusable(true); // Making the panel focusable to receive key events
    }

    @Override
    public void paint(Graphics g) {
        try {
            super.paint(g);
            Graphics2D g2 = (Graphics2D) g;
            drawBackground(g2);
            drawScore(g2);
            drawDog(g2);
            drawWaves(g2);
            points++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawBackground(Graphics2D g2) throws IOException {
        g2.drawImage(ImageIO.read(new File("img/sky.png")), 0, 0, 2000, 1000, null);
        g2.drawImage(building.getImage(), building.getX(), building.getY(), 500, 200, null);
        g2.drawImage(ImageIO.read(new File("img/dir.png")), 0, BASE + 10, 2000, 220, null);
        for (Environment env : envSet) {
            g2.drawImage(env.getImage(), env.getX(), env.getY(), 250, 160, null);
        }
    }

    private void drawScore(Graphics2D g2) {
        g2.setFont(Element.getFont(30));
        g2.setColor(Color.white);
        g2.drawString("Points: " + points, 750, 40);
    }

    private void drawDog(Graphics2D g2) {
        g2.setColor(Color.RED);
        drawDogHealth(g2);
        g2.drawImage(dog.getImage(), dog.getX(), dog.getY(), DOG_SIZE, DOG_SIZE, null);
    }

    private void drawDogHealth(Graphics2D g2) {
        try {
            g2.drawImage(ImageIO.read(new File("img/heart.png")), 10, 20, 20, 20, null);
            g2.setStroke(new BasicStroke(18.0f));
            g2.setColor(new Color(241, 98, 69));
            g2.drawLine(60, 30, 60 + dog.getHealth(), 30);
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(6.0f));
            g2.drawRect(50, 20, 200, 20);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawWaves(Graphics2D g2) {
        for (Wave wave : waveSet) {
            g2.drawImage(wave.getImage(), wave.getX(), (wave.getY() - WAVE_HEIGHT), 40, WAVE_HEIGHT + 10, null);
            handleWaveCollision(g2, wave);
        }
    }

    private Wave[] makeWave(int size) {
        Wave[] waveSet = new Wave[size]; // Create array of waves
        int far = 500; // Initial distance for wave placement
        for (int i = 0; i < size; i++) {
            waveSet[i] = new Wave(X_START + far, BASE, SPEED, this); // Initialize each wave
            far += 500; // Increment distance for next wave
        }
        return waveSet; // Return the array of waves
    }

    private Environment[] makeEnv(int size, int eType) {
        Environment[] envSet = new Environment[size];
        int far = 0;
        for (int i = 0; i < size; i++) {
            envSet[i] = new Environment(X_START + far, 20, this, eType, 10);
            far += 600;
        }
        return envSet;
    }

    private void handleWaveCollision(Graphics2D g2, Wave wave) {
        if (Event.checkHit(dog, wave, DOG_SIZE, WAVE_HEIGHT)) { // Check for collision
            g2.setColor(new Color(241, 98, 69)); // Set color for collision effect
            g2.fillRect(0, 0, 1000, 1000); // Fill screen with collision color
            dog.setHealth(dog.getHealth() - 10);
            if (dog.getHealth() <= 0) { // Check if dog is dead
                display.endGame(points); // End the game and display points
                resetGame(); // Reset the game state
            }
        }
    }

    private void resetGame() {
        dog.setHealth(100);
        points = 0;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (System.currentTimeMillis() - lastPressTime > 600) {
            if (e.getKeyCode() == 32 || e.getKeyCode() == 38) {
                dog.move(this);
                lastPressTime = System.currentTimeMillis();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No action needed
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // No action needed
    }

    public static void main(String[] arg) {
        display = new Display();
    }
}
