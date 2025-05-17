import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Car1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Car1 extends Actor{
    private int speed;
    
    public Car1(int v) {
        GreenfootImage imagen = getImage();

        int nuevoAncho = 40;

        int nuevoAlto = (int)(imagen.getHeight() * (nuevoAncho / (double)imagen.getWidth()));

        imagen.scale(nuevoAncho, nuevoAlto);

        setImage(imagen);
        
        speed = v;
    }
          
    public void act(){
        if(Greenfoot.isKeyDown("right") && getX() < 200){
            setLocation(getX() + speed, getY());
        }
        if(Greenfoot.isKeyDown("left") && getX() > 100){
            setLocation(getX() - speed, getY());
        }
        if(Greenfoot.isKeyDown("up")){
            setLocation(getX(), getY() - speed);
        }
        if(Greenfoot.isKeyDown("down")){
            setLocation(getX(), getY() + speed);
        }
        
        verificarChoque();
    }
    
public void verificarChoque() {
    Actor choque = getOneIntersectingObject(Car2.class);
    if (choque != null) {
        MyWorld w = (MyWorld) getWorld();   

        w.disminuirNumRivales();            

        w.removeObject(choque);             
        w.removeObject(this);               
        Greenfoot.stop();
    }
}


    
    public void aumentaVelocidad(){
        speed++;
    }
}
