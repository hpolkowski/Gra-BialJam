
import java.util.ArrayList;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Monika
 */

public class SpriteController implements Runnable {

    private final Sprite sprite;
    private final Sprite sprite2;
    private final ArrayList<Segment> plansza;
    private final JPanel panel;

    public SpriteController(Sprite sp, Sprite sp2, ArrayList<Segment> pl, JPanel pan) {
        sprite = sp;
        sprite2 = sp2;
        plansza = pl;
        panel = pan;
    }

    @Override
    public void run() {
        while (true) {
            sprite.tick(sprite2);
            sprite2.tick(sprite);
            for (Segment s : plansza) {
                s.tick();
            }
            panel.repaint();
            Thread.currentThread().yield();
            try {
                Thread.currentThread().sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
