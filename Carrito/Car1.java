import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Car1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Car1 extends Actor
{
    public Car1() {
        GreenfootImage imagen = getImage();

        int nuevoAncho = 40;

        int nuevoAlto = (int)(imagen.getHeight() * (nuevoAncho / (double)imagen.getWidth()));

        imagen.scale(nuevoAncho, nuevoAlto);

        setImage(imagen);
    }

    public void act(){
        if(Greenfoot.isKeyDown("right")&& getX() < 200){
            setLocation(getX()+2,getY() );
                      
        }
        if(Greenfoot.isKeyDown("left")&& getX() > 100){
            setLocation(getX()-2,getY() );
        }
        if(Greenfoot.isKeyDown("up")){
            setLocation(getX(),getY()-2 );
        }
        if(Greenfoot.isKeyDown("down")){
            setLocation(getX(),getY()+2 );
        }
    }
}
