import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Car2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Car2 extends Actor
{
    public Car2() {
        GreenfootImage imagen = getImage();

        int nuevoAncho = 40;

        int nuevoAlto = (int)(imagen.getHeight() * (nuevoAncho / (double)imagen.getWidth()));

        imagen.scale(nuevoAncho, nuevoAlto);

        setImage(imagen);
    }

    
}

