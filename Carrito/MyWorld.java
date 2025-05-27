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

    private int vidas = 3;
    private int puntos = 100;

    private int bananaSpawnCounter = 0;
    private int bananaSpawnDelay = 250;

    private int turboSpawnCounter = 0;
    private int turboSpawnDelay = 600;

    private int estrellaSpawnCounter = 0;
    private int estrellaSpawnDelay = 700;

    private GreenfootSound musicaFondo = new GreenfootSound("mariokart.mp3");

    public MyWorld() {
        super(300, 533, 1);

        GreenfootImage fondo = new GreenfootImage("Pista.jpeg");
        fondo.scale(getWidth(), getHeight());
        setBackground(fondo);

        num_adelantamientos = 0;
        num_adelantamientos_nivel = 4;
        speed_car = 3;
        spawnDelay = 25;
        spawnCounter = 0;

        score = new counter("Score: ");
        level = new counter("Level: ");
        level.add(1);

        car1 = new Car1(speed_car);

        addObject(car1, 150, 480);
        addObject(score, 75, 60);
        addObject(level, 80, 90);

        musicaFondo.setVolume(60);
        musicaFondo.playLoop();

        actualizarUI();
    }

    public void act() {
        aumentarDificultad();
        controlarSpawn();
        generarBananas();
        generarTurbos();
        generarEstrellas();
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
            if (rival.getY() < minDistanceY) return;
        }

        int carril = getRandomNumber(0, 2);
        int[] posiciones = {100, 150, 200};
        addObject(new Car2(speed_car), posiciones[carril], 10);
        num_rivales++;
    }

    public int getRandomNumber(int start, int end) {
        int normal = Greenfoot.getRandomNumber(end - start + 1);
        return normal + start;
    }

    public void aumentarPuntuacion(int valor) {
        score.add(valor);
        puntos += valor;
        actualizarUI();
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

    public void restarPuntos(int cantidad) {
        puntos -= cantidad;
        if (puntos < 0) puntos = 0;
        actualizarUI();
    }

    public void restarVida() {
        vidas--;
        if (vidas <= 0) {
            musicaFondo.stop();

            GreenfootImage imagen = new GreenfootImage(getWidth(), getHeight());
            imagen.setColor(Color.BLACK);
            imagen.fill();
            imagen.setColor(Color.RED);
            imagen.setFont(new Font("Arial", 30));
            imagen.drawString("GAME OVER,", 70, 220);
            imagen.drawString("¡Pilas en la carretera!", 15, 260);

            setBackground(imagen);
            Greenfoot.stop();
        }
        actualizarUI();
    }

    public void actualizarUI() {
        GreenfootImage texto = new GreenfootImage("Puntos: " + puntos, 24, Color.BLACK, new Color(0, 0, 0, 0));
        getBackground().drawImage(texto, 180, 10);
        texto = new GreenfootImage("Vidas: " + vidas, 24, Color.BLACK, new Color(0, 0, 0, 0));
        getBackground().drawImage(texto, 180, 40);
    }

    private void generarBananas() {
        bananaSpawnCounter++;
        if (bananaSpawnCounter >= bananaSpawnDelay) {
            bananaSpawnCounter = 0;
            int carril = getRandomNumber(0, 2);
            int[] posiciones = {100, 150, 200};
            addObject(new Banano(speed_car), posiciones[carril], 0);
        }
    }

    private void generarTurbos() {
        turboSpawnCounter++;
        if (turboSpawnCounter >= turboSpawnDelay) {
            turboSpawnCounter = 0;
            int carril = getRandomNumber(0, 2);
            int[] posiciones = {100, 150, 200};
            addObject(new Turbo(), posiciones[carril], 0);
        }
    }

    private void generarEstrellas() {
        estrellaSpawnCounter++;
        if (estrellaSpawnCounter >= estrellaSpawnDelay) {
            estrellaSpawnCounter = 0;
            int carril = getRandomNumber(0, 2);
            int[] posiciones = {100, 150, 200};
            addObject(new Estrella(), posiciones[carril], 0);
        }
    }
}
