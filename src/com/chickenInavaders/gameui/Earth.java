package src.com.chickenInavaders.gameui;
import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;
import src.com.chickenInavaders.sprites.Sprite;
import src.com.chickenInavaders.sprites.SpriteState;
import com.sun.glass.ui.Size;

import javax.swing.*;

public class Earth extends Sprite{
    private final static Dictionary<SpriteState,Image> bkgImages = new Hashtable<SpriteState, Image>(){
        {
            put(SpriteState.Alive,new ImageIcon("src/com/chickenInavaders/images/earth.png").getImage());
        }
    };
    public Earth() {
        super(bkgImages, new Size(600,50),false,24);
        position.y=720;
    }
}



