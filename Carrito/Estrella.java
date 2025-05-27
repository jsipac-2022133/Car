import greenfoot.*;

public class Estrella extends Actor {
    public Estrella() {
        GreenfootImage img = new GreenfootImage("estrella.png");
        img.scale(30, 30);
        setImage(img);
    }

    public void act() {
        setLocation(getX(), getY() + 2);
        if (getY() > getWorld().getHeight()) getWorld().removeObject(this);
    }
}
