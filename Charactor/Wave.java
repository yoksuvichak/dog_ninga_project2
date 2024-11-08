package Charactor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Wave extends Charactor_Template implements Method_Template {
    private int speed;
    private Timer timeMove;
    public Wave(int x, int y, int speed, JPanel page) {
        setX(x);
        setY(y);
        setSpeed(speed);
        this.move(page);
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
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public Timer getTimeMove() {
        return timeMove;
    }
    public void setTimeMove(Timer timeMove) {
        this.timeMove = timeMove;
    }

    public void move(JPanel page) {
        setTimeMove(new Timer(speed, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (getX() <= 0) {
                    setX((int) (1000 + (300 + Math.random() * 1000)));
                }
                setX(getX() - 30);
                page.repaint();
            }
        }));
        getTimeMove().start();
    }

    public BufferedImage getImage() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("img/tree.png"));
            return image;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
}
