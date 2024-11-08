package Charactor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Environment extends Charactor_Template implements Method_Template {
    public static int CLOUD = 0, BUILDING = 1;
    private int startX;
    private int speed;
    private int eType;
    private Timer timeMove;
    
    public Environment(int x, int y, JPanel page, int eType, int speed) {
        setX(x);
        setY(y);
        setStartX(x);
        setSpeed(speed);
        seteType(eType);
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
    public int getStartX() {
        return startX;
    }
    public void setStartX(int startX) {
        this.startX = startX;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public int geteType() {
        return eType;
    }
    public void seteType(int eType) {
        this.eType = eType;
    }
    public Timer getTimeMove() {
        return timeMove;
    }
    public void setTimeMove(Timer timeMove) {
        this.timeMove = timeMove;
    }

    public void move(JPanel page) {
        setTimeMove(new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (getX() + 400 < 0) {
                    setX(getStartX());
                }
                setX(getX() - getSpeed());
                page.repaint();
            }
        }));
        getTimeMove().start();
    }

    public void stop() {
        getTimeMove().stop();
    }

    public String getEvType(int eType) {
        String[] name = new String[] { "cloud.png", "building.png" };
        return name[geteType()];
    }

    public BufferedImage getImage() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("img/" + getEvType(this.eType)));
            return image;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
}
