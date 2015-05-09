/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Monika
 */
//segment, na ktory mozna wskoczyc 

public class SegmentBlockV extends Segment {

    public SegmentBlockV(int x, int y, String file) {
        super(x, y, file);
    }

    public void collisionV(Sprite sprite) {
        if (sprite.jumpingDown() && sprite.getBottomY() == y) {
            sprite.stopJump();
        }
    }
}
