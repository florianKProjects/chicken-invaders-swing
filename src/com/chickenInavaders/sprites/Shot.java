package src.com.chickenInavaders.sprites;
import com.sun.glass.ui.Size;

import javax.swing.*;
import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

public class Shot extends Sprite {
    private final static Dictionary<SpriteState,Image> shipImages = new Hashtable<SpriteState, Image>(){
        {
            put(SpriteState.Alive,new ImageIcon("src/com/chickenInavaders/images/shot.png").getImage());
            put(SpriteState.Dying,new ImageIcon("src/com/chickenInavaders/images/shot.png").getImage());
        }
    };

    public Shot() {
        super(shipImages, new Size(20,45),true,0);
    }
}

