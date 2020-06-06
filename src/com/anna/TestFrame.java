package com.anna;
import com.anna.gameui.GameUI;
import com.anna.gameui.IGameUI;
import javax.swing.*;
import java.awt.*;

public class TestFrame extends JFrame {
    IGameUI gameUI;

    TestFrame(){
        setLayout(new BorderLayout());
        gameUI = new GameUI();
        add((GameUI)gameUI,BorderLayout.CENTER);
        gameUI.addGameObserver(new TestObserver());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(600,800);

    }

    public static void main(String args []) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TestFrame();
            }
        });
    }
}
