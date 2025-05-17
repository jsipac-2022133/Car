import greenfoot.*;  

public class MyWorld extends World {
    
    private counter score;
    private counter level;
    
    private int speed_car;
    private int num_adelantamientos;
    private int num_adelantamientos_nivel;
    
    private Car1 car1;
    private int num_rivales;
    
    private int spawnDelay;    
    private int spawnCounter;  
    
    private final int MAX_RIVALES = 3; 
    
    public MyWorld() {    
        super(300, 533, 1);                 
        GreenfootImage fondo = new GreenfootImage("Pista.jpeg");                
        fondo.scale(getWidth(), getHeight());                
        setBackground(fondo);
        
        num_adelantamientos = 0;
        num_adelantamientos_nivel = 4;
        
        speed_car = 3;         
        spawnDelay = 15;       
        spawnCounter = 0;
        
        score = new counter("Score: ");
        level = new counter("Level: ");
        level.add(1);
        car1 = new Car1(speed_car);        
        
        addObject(car1, 150, 480);        
        addObject(score, 75, 60);
        addObject(level, 80, 90);
    }
    
    public void act() {
        aumentarDificultad();
        controlarSpawn();
    }
    
    private void controlarSpawn() {
        if (num_rivales >= MAX_RIVALES) return; 
        
        spawnCounter++;
        if (spawnCounter >= spawnDelay) {
            spawnCounter = 0;
            crearRival();
        }
    }
    
    private void crearRival() {
        int minDistanceY = 175;  
        
        for (Car2 rival : getObjects(Car2.class)) {
            if (rival.getY() < minDistanceY) {
                return;
            }
        }
        
        int carril = getRandomNumber(0, 2);
        int x = 100 + 50 * carril;
        addObject(new Car2(speed_car), x, 10);
        num_rivales++;
    }
    
    public int getRandomNumber(int start, int end) {
        int normal = Greenfoot.getRandomNumber(end - start + 1);
        return normal + start;
    }
    
    public void aumentarPuntuacion(int valor) {
        score.add(valor);
    }
    
    public void aumentarNumAdelantamientos() {
        num_adelantamientos++;
    }
    
    public void disminuirNumRivales() {
        num_rivales--;
    }
    
    public void aumentarDificultad() {
        if (num_adelantamientos == num_adelantamientos_nivel) {
            num_adelantamientos = 0;
            num_adelantamientos_nivel += 2;
            speed_car++;
            car1.aumentaVelocidad();
            level.add(1);
            
            for (Car2 c : getObjects(Car2.class)) {
                c.aumentaVelocidad();
            }
            
            if (spawnDelay > 10) {   
                spawnDelay -= 2;
            }
        }
    }
}
