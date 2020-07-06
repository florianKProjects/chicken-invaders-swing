package src.com.chickenInavaders.sprites;
import com.sun.glass.ui.Size;

import javax.swing.*;
import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

public class Ship extends Sprite {
    private final static Dictionary<SpriteState,Image> shipImages = new Hashtable<SpriteState, Image>(){
        {
            put(SpriteState.Alive,new ImageIcon("src/com/chickenInavaders/images/ship.png").getImage());
            put(SpriteState.Dying,new ImageIcon("src/com/chickenInavaders/images/boom.png").getImage());
        }
    };

    public Ship() {
        super(shipImages, new Size(90,90),false,0);
    }

    @Override
    public void paint(Graphics graphics) {
        if(flashFrames%2==0)
            super.paint(graphics);
        if(flashFrames>0) flashFrames--;
    }

    int flashFrames=0;

    public void flash(){
        flashFrames=50;
    }
}

