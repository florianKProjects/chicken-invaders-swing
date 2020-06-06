package com.anna.sprites;
import com.sun.glass.ui.Size;

import javax.swing.*;
import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

public class Shot extends Sprite {
    final static Image alive = new ImageIcon("src/com/anna/images/shot.png").getImage();
    private final static Dictionary<SpriteState,Image> shipImages = new Hashtable<SpriteState, Image>(){
        {
            put(SpriteState.Alive,alive);
            put(SpriteState.Dying,alive);
        }
    };

    public Shot() {
        super(shipImages, new Size(20,45),true,0);
    }
}

