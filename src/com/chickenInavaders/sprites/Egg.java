package src.com.chickenInavaders.sprites;

import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

import com.sun.glass.ui.Size;

import javax.swing.*;

public class Egg extends Sprite{
    private final static Dictionary<SpriteState,Image> eggImages = new Hashtable<SpriteState, Image>(){
        {
            put(SpriteState.Alive,new ImageIcon("src/com/chickenInavaders/images/Egg.png").getImage());
            put(SpriteState.Dying,new ImageIcon("src/com/chickenInavaders/images/brokeEgg.png").getImage());
        }
    };
    public Egg() {
        super(eggImages, new Size(20,25),true,24);
    }
}

