package src.com.chickenInavaders.model.sprites;
import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;
import src.com.chickenInavaders.model.Sprite;
import src.com.chickenInavaders.model.SpriteState;
import com.sun.glass.ui.Size;
import javax.swing.*;

public class Bkg extends Sprite{
    private final static Dictionary<SpriteState,Image> bkgImages = new Hashtable<SpriteState, Image>(){
        {
            put(SpriteState.Alive,new ImageIcon("src/com/chickenInavaders/resources/assets/images/bkg.png").getImage());
        }
    };
    public Bkg() {
        super(bkgImages, new Size(600,800),false,24);
    }
}



