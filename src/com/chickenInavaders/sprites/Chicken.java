package src.com.chickenInavaders.sprites;
import com.sun.glass.ui.Size;

import javax.swing.*;
import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

public class Chicken extends Sprite {
    final static Image noWings = new ImageIcon("src/com/chickenInavaders/images/BigChicken2.png").getImage();
    final static Image wings = new ImageIcon("src/com/chickenInavaders/images/BigChicken1.png").getImage();
    private final static Dictionary<SpriteState,Image> chickenImages = new Hashtable<SpriteState, Image>(){
        {
            put(SpriteState.Alive,wings);
            put(SpriteState.Dying,new ImageIcon("src/com/chickenInavaders/images/explosion1.png").getImage());
        }
    };

    public Chicken() {
        super(chickenImages, new Size(50,50),true,24);

    }
}

