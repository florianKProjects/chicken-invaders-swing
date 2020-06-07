package com.anna;
import com.anna.gameui.GameUI;
import com.anna.gameui.IGameUI;
import javax.swing.*;
import java.awt.*;

public class TestFrame extends JFrame {
    IGameUI gameUI;

    TestFrame(){
        setSize(600,800);
        setLayout(new BorderLayout());
        gameUI = new GameUI();
        add(gameUI.getPanel(),BorderLayout.CENTER);
        gameUI.addGameObserver(new TestObserver());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        gameUI.startLevel(1,3,10,200);


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
