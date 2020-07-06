package src.com.chickenInavaders.model.sprites;
import com.sun.glass.ui.Size;
import src.com.chickenInavaders.model.Sprite;
import src.com.chickenInavaders.model.SpriteState;

import javax.swing.*;
import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

public class Shot extends Sprite {
    private final static Dictionary<SpriteState,Image> shipImages = new Hashtable<SpriteState, Image>(){
        {
            put(SpriteState.Alive,new ImageIcon("src/com/chickenInavaders/resources/assets/images/shot.png").getImage());
            put(SpriteState.Dying,new ImageIcon("src/com/chickenInavaders/resources/assets/images/shot.png").getImage());
        }
    };

    public Shot() {
        super(shipImages, new Size(20,45),true,0);
    }
}

