
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Monika
 */

//************************* postac gracza

public class Sprite {

    private static final Image img = new ImageIcon("src/PlayerB.png").getImage();

    private final int[] anim = {0, 1, 2, 1};
    private int frame = 2;		// klatka animacji
    private boolean mirror = false;     // postac patrzy w lewo/ prawo
    private int moving = 0;		// ruch w poziomie
    private int jumping = 0;            // ruch w pionie
    private final ArrayList<Segment> plansza;

    private int x = 150, y = 0; 	// pozycja na ekranie
    private final int W = 32, H = 32;   // wysokosc i szerokosc sprite'a
    
    public boolean isJumping = false;

    public Sprite(ArrayList<Segment> pl, int x, int y) {
        this.x = x;
        this.y = y;
        plansza = pl;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, W, H);
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getBottomY() {
        return y + H;
    }

    public void jump() {		// poruszanie postacia
        if (jumping == 0 && !isJumping) {
            jumping = 8;
        }
    }

    public boolean isJumping() {
        return jumping > 0;
    }

    public boolean jumpingDown() {
        return jumping < 0;
    }

    public void stopJump() {
        jumping = 0;
    }

    public void stopMove() {
        moving = 0;
    }

    public void left() {
        moving = -3;
        mirror = false;
    }

    public void right() {
        moving = 3;
        mirror = true;
    }

    public void stop() {
        moving = 0;
        frame = 2;
    }

    private boolean canGo(int dx, int dy) {
        for (Segment s : plansza) {
            if (s.getBounds().intersects(x + dx, y + dy, W, H)) {
                return false;
            }
        }
        return true;
    }

    private void collide(int dx, int dy, Sprite sprite) {
        for (Segment s : plansza) {
            if (s.getBounds().intersects(x + dx, y + dy, W, H)) {
                if (dx != 0) {
                    s.collisionH(this);
                }
                if (dy != 0) {
                    s.collisionV(this);
                }
            }
        }
        if(sprite.getBounds().intersects(x + dx, y + dy, W, H))
            this.stopJump();
    }

    public void tick(Sprite sprite) {
        if (moving != 0) {// animacja ruchu
            frame++;
            while (frame >= anim.length) {
                frame -= anim.length;
            }
        }
        // przesuniecie w poziomie
        for (int i = 0; i < Math.abs(moving); ++i) {
            collide((int) Math.signum(moving), 0, sprite);
            x += (int) Math.signum(moving);
        }

        // przesuniecie w pionie
        for (int i = 0; i < Math.abs(jumping); ++i) {
            collide(0, -(int) Math.signum(jumping), sprite);
            y -= (int) Math.signum(jumping);
        }
        // czy mamy grunt pod nogami?
        jumping--;
        collide(0, 1, sprite);
        if (jumping != 0) {
            frame = 0;
        }
        if (jumping == 0 && moving == 0) {
            frame = 2;
        }
    }

    public void draw(Graphics g) {
        g.drawImage(img, x + (mirror ? W : 0), y, x + (mirror ? 0 : W), y + H,
                anim[frame] * W, 0, anim[frame] * W + W, H, null);
    }
}

