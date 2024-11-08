package Charactor;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Dog extends Charactor_Template implements Method_Template {
    private int health = 180;
    private int speed = 120; // jump height
    public Dog(int x, int y) {
        setX(x);
        setY(y);
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getHealth() {
        return health;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void move(JPanel page) {
        jump(page);
    }

    public void jump(JPanel page) {
        setY(getY() - getSpeed());
        page.repaint();
        // --- fall ---
        Timer timer = new Timer(550, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setY(getY() + getSpeed());
                page.repaint();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public BufferedImage getImage() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("img/dog.png"));
            return image;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
}
