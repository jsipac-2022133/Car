import greenfoot.*;

public class Turbo extends Actor {
    public Turbo() {
        GreenfootImage img = new GreenfootImage("turbo.png"); // 60x60 aprox
        img.scale(30, 30);
        setImage(img);
    }

    public void act() {
        setLocation(getX(), getY() + 2);
        if (getY() > getWorld().getHeight()) getWorld().removeObject(this);
    }
}
