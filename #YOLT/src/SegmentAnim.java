
import java.awt.Graphics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Monika
 */

//segment animowany

public class SegmentAnim extends Segment {

    private int[] anim;

    public SegmentAnim(int x, int y, String file, int[] sequence) {
        super(x, y, file);
        anim = sequence;
    }
    public int frame = 0;

    public void tick() {
        frame++;
        while (frame >= anim.length) {
            frame -= anim.length;
        }
    }

    public void draw(Graphics g) {
        g.drawImage(img, x, y, x + W, y + H / 4,
                0, anim[frame] * H / 4, W, anim[frame] * H / 4 + H / 4, null);
    }
}
