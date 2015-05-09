
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
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


//klasa bazowa dla segmentow

public class Segment {
    protected Image img;
    protected int x, y;
    protected int W, H;

    public Segment(int x, int y, String file) {
        this.x = x;
        this.y = y;
        img = new ImageIcon(file).getImage();
        W = img.getWidth(null);
        H = img.getHeight(null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, W, H);
    }

    public void draw(Graphics g) {
        g.drawImage(img, x, y, null);
    }

    public void tick() {
    }

    public void collisionV(Sprite sprite) {
    }

    public void collisionH(Sprite sprite) {
    }
}
