/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Monika
 */

//segment bez mozliwosci przejscia
public class SegmentBlock extends Segment {
    public SegmentBlock(int x, int y, String file) {
        super(x, y, file);
    }

    public void collisionV(Sprite sprite) {
        sprite.stopJump();
    }

    public void collisionH(Sprite sprite) {
        sprite.stopMove();
    }
}
