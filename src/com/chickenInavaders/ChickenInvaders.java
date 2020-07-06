package src.com.chickenInavaders;


/*
import javax.swing.*;
import java.awt.*;
*/

import javax.swing.*;

public class ChickenInvaders {
    public LayoutManager panelManager;


    public ChickenInvaders() {
        panelManager = new LayoutManager();
        initUI();
    }
    private void initUI() {

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChickenInvaders mainFrame = new ChickenInvaders();
        });

    }
}

/*
EventQueue.invokeLater(() -> {

            var ex = new ChickenInvaders();
            ex.setVisible(true);
        });
    }
 */