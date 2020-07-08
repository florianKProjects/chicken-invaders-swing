package src.com.chickenInavaders.tests;

import junit.framework.TestCase;
import org.junit.Test;
import org.mockito.*;
import src.com.chickenInavaders.model.Sprite;

import java.awt.*;

public class TestSprite extends  TestCase{

    private Sprite sprite1;
    private Sprite sprite2;

    public void TestSprite(){ }

    protected void setUp() {
        sprite1 = Mockito.mock(Sprite.class);
        sprite2 = Mockito.mock(Sprite.class);
    }

    protected void tearDown() {}

    @Test
    /* testIntersection
    * testing between sprite1(x1,y1) to sprite2(x2,y2)
    * */
    public void testIntersect(){
        sprite1.position = new Point(100,100);
        sprite2.position = new Point(500, 500);
        Mockito.doReturn(false).when(sprite1).intersect(sprite2);
        assertFalse("False: 2 objects should not be do intersect", sprite1.intersect(sprite2));

        sprite1.position = new Point(100,100);
        sprite2.position = new Point(110, 110);
        Mockito.doReturn(true).when(sprite1).intersect(sprite2);
        assertTrue("True: 2 objects should intersect", sprite1.intersect(sprite2));

        sprite1.position = new Point(100,100);
        sprite2.position = new Point(90, 90);
        Mockito.doReturn(true).when(sprite1).intersect(sprite2);
        assertTrue("True: 2 objects should intersect", sprite1.intersect(sprite2));

        sprite1.position = new Point(100,100);
        sprite2.position = new Point(110, 90);
        Mockito.doReturn(true).when(sprite1).intersect(sprite2);
        assertTrue("True: 2 objects should intersect", sprite1.intersect(sprite2));

        sprite1.position = new Point(100,100);
        sprite2.position = new Point(90, 110);
        Mockito.doReturn(true).when(sprite1).intersect(sprite2);
        assertTrue("True: 2 objects should intersect", sprite1.intersect(sprite2));
    }

    @Test
    /* testMove
    *  tests if sprite move to the right direction
    * */
    public void testMove() {
        sprite1.position = new Point(0,0);
        Mockito.doReturn(new Point(5,5)).when(sprite1).move(5,5);
        assertEquals("sprite1.position move to correct direction", new Point(5,5), sprite1.move(5,5));
    }
}
