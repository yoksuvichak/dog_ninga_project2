package Charactor;

public abstract class Charactor_Template {
    protected int x;
    protected int y;
    protected int speed;

    public abstract void setX(int x);
    public abstract void setY(int y);
    public abstract void setSpeed(int speed);
    public abstract int getX();
    public abstract int getY();
    public abstract int getSpeed();
}
