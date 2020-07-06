
package src.com.chickenInavaders;

import src.com.chickenInavaders.gameui.GameUI;
import src.com.chickenInavaders.gameui.IGameUI;
import javax.swing.*;
import java.awt.*;

public class TestFrame extends JFrame {
    IGameUI gameUI;

    TestFrame() {
        setLayout(new BorderLayout());
        gameUI = new GameUI(new LayoutManager());
        add((GameUI) gameUI, BorderLayout.CENTER);
        gameUI.addGameObserver(new TestObserver());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(600, 800);

    }
    /*
     * public static void main(String args []) { SwingUtilities.invokeLater(new
     * Runnable() {
     * 
     * @Override public void run() { new TestFrame(); } }); }
     */
}
