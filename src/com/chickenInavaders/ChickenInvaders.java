package src.com.chickenInavaders;
import src.com.chickenInavaders.view.LayoutManager;

import javax.swing.*;

public class ChickenInvaders {
    public LayoutManager panelManager;


    public ChickenInvaders() {
        panelManager = new LayoutManager();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            ChickenInvaders mainFrame = new ChickenInvaders();
        });
    }
}
