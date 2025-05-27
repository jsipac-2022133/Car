import greenfoot.*;

public class Car1 extends Actor {
    private int speed;
    private int derrapeTiempo = 0;
    private boolean estaDerrapando = false;

    private boolean turboActivo = false;
    private int turboTiempo = 0;

    private boolean invulnerable = false;
    private int invulnerableTiempo = 0;

    private GreenfootImage imagenOriginal;

    public Car1(int v) {
        GreenfootImage imagen = getImage();
        int nuevoAncho = 40;
        int nuevoAlto = (int)(imagen.getHeight() * (nuevoAncho / (double)imagen.getWidth()));
        imagen.scale(nuevoAncho, nuevoAlto);

        imagenOriginal = new GreenfootImage(imagen); // Guarda copia de la imagen original
        setImage(imagen);

        speed = v;
    }

    public void act() {
        if (estaDerrapando) {
            realizarDerrape();
        } else {
            moverJugador();
            verificarChoque();
        }

        if (turboActivo) {
            turboTiempo--;
            if (turboTiempo <= 0) {
                desactivarTurbo();
            }
        }

        if (invulnerable) {
            invulnerableTiempo--;

            if (invulnerableTiempo % 10 < 5) {
                getImage().setTransparency(150); // Efecto parpadeo
            } else {
                getImage().setTransparency(255);
            }

            if (invulnerableTiempo <= 0) {
                invulnerable = false;
                setImage(new GreenfootImage(imagenOriginal)); // Restaurar imagen original
            }
        }
    }

    private void moverJugador() {
        if (Greenfoot.isKeyDown("right") && getX() < 200) setLocation(getX() + speed, getY());
        if (Greenfoot.isKeyDown("left") && getX() > 100) setLocation(getX() - speed, getY());
        if (Greenfoot.isKeyDown("up")) setLocation(getX(), getY() - speed);
        if (Greenfoot.isKeyDown("down")) setLocation(getX(), getY() + speed);
    }

    public void verificarChoque() {
        MyWorld w = (MyWorld) getWorld();

        Actor choque = getOneIntersectingObject(Car2.class);
        if (choque != null && !invulnerable) {
            w.removeObject(choque);
            w.removeObject(this);
            Greenfoot.stop();
            return;
        }

        Actor banano = getOneIntersectingObject(Banano.class);
        if (banano != null && !estaDerrapando && !invulnerable) {
            w.removeObject(banano);
            Greenfoot.playSound("explosion.wav");
            estaDerrapando = true;
            derrapeTiempo = 20;
            moverUnCarrilAleatorio();
            w.restarPuntos(20);
            w.restarVida();
        }

        Actor turbo = getOneIntersectingObject(Turbo.class);
        if (turbo != null) {
            w.removeObject(turbo);
            activarTurbo();
            Greenfoot.playSound("turbo.mp3");
        }

        Actor estrella = getOneIntersectingObject(Estrella.class);
        if (estrella != null) {
            w.removeObject(estrella);
            activarInvulnerabilidad();
            Greenfoot.playSound("powerup.mp3");
        }
    }

    private void moverUnCarrilAleatorio() {
        int x = getX();
        if (x == 100) setLocation(150, getY());
        else if (x == 150) setLocation(Greenfoot.getRandomNumber(2) == 0 ? 100 : 200, getY());
        else if (x == 200) setLocation(150, getY());
    }

    public void realizarDerrape() {
        turn(Greenfoot.getRandomNumber(31) - 15);
        move(3);
        derrapeTiempo--;
        if (derrapeTiempo <= 0) {
            estaDerrapando = false;
            setRotation(0);
        }
    }

    public void aumentaVelocidad() {
        speed++;
    }

    private void activarTurbo() {
        turboActivo = true;
        turboTiempo = 150;
        speed += 2;
    }

    private void desactivarTurbo() {
        turboActivo = false;
        speed -= 2;
    }

    private void activarInvulnerabilidad() {
        invulnerable = true;
        invulnerableTiempo = 300;
    }
}
