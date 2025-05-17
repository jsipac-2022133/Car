import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    public MyWorld()
    {    
        super(300, 533, 1); 

        
        GreenfootImage fondo = new GreenfootImage("Pista.jpeg");

        
        fondo.scale(getWidth(), getHeight());

        
        setBackground(fondo);
    }
}
