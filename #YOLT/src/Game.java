
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

public class Game extends JPanel {

    private final int TILESIZE = 32;

    private ArrayList<Segment> plansza;
    private Sprite sprite;
    private Sprite sprite2;

    private ArrayList<Segment> stworzPlansze(String plik) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(plik));
            ArrayList<Segment> plansza = new ArrayList<Segment>();
            String linia;
            int x, y = 4, liczba, znaki;
            char znak, cyfra1, cyfra2;
            while ((linia = br.readLine()) != null) {
                x = 4;
                znaki = 0;
                while ((linia.length() - znaki) >= 3) {
                    znak = linia.charAt(znaki++);
                    cyfra1 = linia.charAt(znaki++);
                    cyfra2 = linia.charAt(znaki++);
                    liczba = (cyfra1 - '0') * 10 + (cyfra2 - '0');
                    switch (znak) {
                        case 'X':
                            x += liczba * TILESIZE;
                            break;
                        case 'F':
                            for (int i = 0; i < liczba; ++i) {
                                Segment s = new SegmentBlock(x, y, "src/floor.png");
                                plansza.add(s);
                                x += TILESIZE;
                            }
                            break;
                        case 'B':
                            for (int i = 0; i < liczba; ++i) {
                                Segment s = new SegmentBlockV(x, y, "src/block2.png");
                                plansza.add(s);
                                x += TILESIZE;
                            }
                            break;
                        case 'L':
                            for (int i = 0; i < liczba; ++i) {
                                Segment s = new SegmentLedder(x, y, "src/ladder.png");
                                plansza.add(s);
                                x += TILESIZE;
                            }
                            break;
                        case 'G':
                            for (int i = 0; i < liczba; ++i) {
                                Segment s = new SegmentAnim(x, y, "src/bonus.png", new int[]{0, 0, 0, 1, 1, 1, 2, 2, 3, 3, 2, 2, 1, 1, 1, 0, 0});
                                plansza.add(s);
                                x += TILESIZE;
                            }
                            break;
                    }
                }
                y += TILESIZE;
            }
            br.close();
            return plansza;
        } catch (IOException e) {
            System.out.println("Blad wczytania planszy");
            e.printStackTrace();
            return null;
        }
    }

    public Game(String plik) {
        setPreferredSize(new Dimension(424, 268));
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ev) {
                switch (ev.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_RIGHT:
                        sprite.stop();
                        break;
                        
                    case KeyEvent.VK_A:
                    case KeyEvent.VK_D:
                        sprite2.stop();
                        break;
                        
                    case KeyEvent.VK_UP:
                        sprite.isJumping = false;
                        break;
                        
                    case KeyEvent.VK_W:
                        sprite2.isJumping = false;
                        break;
                }
            }

            public void keyPressed(KeyEvent ev) {
                switch (ev.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        sprite.left();
                        break;
                    case KeyEvent.VK_RIGHT:
                        sprite.right();
                        break;

                    case KeyEvent.VK_UP:
                        sprite.jump();
                        sprite.isJumping = true;
                        break;
                        
                    case KeyEvent.VK_A:
                        sprite2.left();
                        break;
                        
                    case KeyEvent.VK_D:
                        sprite2.right();
                        break;
                        
                    case KeyEvent.VK_W:
                        sprite2.jump();
                        sprite2.isJumping = true;
                        break;
                }
            }
        });

        setFocusable(true);
        plansza = stworzPlansze(plik);
        sprite = new Sprite(plansza, 50, 0);
        sprite2 = new Sprite(plansza, 100, 0);

        new Thread(new SpriteController(sprite, sprite2, plansza, this)).start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        for (Segment s : plansza) {
            s.draw(g);
        }
        sprite.draw(g);
        sprite2.draw(g);
    }
}