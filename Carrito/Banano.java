import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Car2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Banano extends Actor{
    private int speed;
    
    public Banano(int v) {
        GreenfootImage imagen = getImage();

        int nuevoAncho = 40;

        int nuevoAlto = (int)(imagen.getHeight() * (nuevoAncho / (double)imagen.getWidth()));

        imagen.scale(nuevoAncho, nuevoAlto);

        setImage(imagen);
        
        speed=v;
    }

    public void act(){
        setLocation(getX(), getY()+speed);
        
        if(getY()>=getWorld().getHeight()-1){
            MyWorld mundo=(MyWorld) getWorld();
            mundo.removeObject(this);
            mundo.aumentarPuntuacion(10);
            mundo.disminuirNumRivales();
            mundo.aumentarNumAdelantamientos();
        }
    
    }    
    
    public void aumentaVelocidad() {
    speed++;
}

}