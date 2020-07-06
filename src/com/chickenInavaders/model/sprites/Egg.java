package src.com.chickenInavaders.model.sprites;

import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

import com.sun.glass.ui.Size;
import src.com.chickenInavaders.model.Sprite;
import src.com.chickenInavaders.model.SpriteState;

import javax.swing.*;

public class Egg extends Sprite {
    private final static Dictionary<SpriteState,Image> eggImages = new Hashtable<SpriteState, Image>(){
        {
            put(SpriteState.Alive,new ImageIcon("src/com/chickenInavaders/resources/assets/images/Egg.png").getImage());
            put(SpriteState.Dying,new ImageIcon("src/com/chickenInavaders/resources/assets/images/brokeEgg.png").getImage());
        }
    };
    public Egg() {
        super(eggImages, new Size(20,25),true,24);
    }
}

