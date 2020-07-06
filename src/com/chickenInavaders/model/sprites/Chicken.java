package src.com.chickenInavaders.model.sprites;
import com.sun.glass.ui.Size;
import src.com.chickenInavaders.model.Sprite;
import src.com.chickenInavaders.model.SpriteState;

import javax.swing.*;
import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

public class Chicken extends Sprite {
    private final static Dictionary<SpriteState,Image> chickenImages = new Hashtable<SpriteState, Image>(){
        {
            put(SpriteState.Alive,new ImageIcon("src/com/chickenInavaders/resources/assets/images/BigChicken1.gif").getImage());
            put(SpriteState.Dying,new ImageIcon("src/com/chickenInavaders/resources/assets/images/explosion1.png").getImage());
        }
    };

    public Chicken() {
        super(chickenImages, new Size(50,50),true,24);
    }
}

