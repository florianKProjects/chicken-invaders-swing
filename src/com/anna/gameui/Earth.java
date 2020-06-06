package com.anna.gameui;
import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;
import com.anna.sprites.Sprite;
import com.anna.sprites.SpriteState;
import com.sun.glass.ui.Size;

import javax.swing.*;

public class Earth extends Sprite{
    private final static Dictionary<SpriteState,Image> bkgImages = new Hashtable<SpriteState, Image>(){
        {
            put(SpriteState.Alive,new ImageIcon("src/com/anna/images/earth.png").getImage());
        }
    };
    public Earth() {
        super(bkgImages, new Size(600,50),false,24);
        position.y=720;
    }
}



