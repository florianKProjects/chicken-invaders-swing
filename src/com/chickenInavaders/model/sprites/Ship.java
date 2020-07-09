package src.com.chickenInavaders.model.sprites;
import com.sun.glass.ui.Size;
import src.com.chickenInavaders.Commons;
import src.com.chickenInavaders.model.Sprite;
import src.com.chickenInavaders.model.SpriteState;

import javax.swing.*;
import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

public class Ship extends Sprite {
    private final static Dictionary<SpriteState,Image> shipImages = new Hashtable<SpriteState, Image>(){
        {
            put(SpriteState.Alive,new ImageIcon(Commons.BLUE_SHIP_GAME).getImage());
            put(SpriteState.Dying,new ImageIcon(Commons.BOOM_SHIP).getImage());
        }
    };

    public Ship() {
        super(shipImages, new Size(90,90),false,0);
    }
    public void  changeShipColor(String Color)
    {   if(Color.equals("red"))
        shipImages.put(SpriteState.Alive,new ImageIcon(Commons.RED_SHIP_GAME).getImage());
        else
        shipImages.put(SpriteState.Alive,new ImageIcon(Commons.BLUE_SHIP_GAME).getImage());
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

