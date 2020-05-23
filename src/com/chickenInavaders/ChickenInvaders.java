package com.chickenInavaders;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class ChickenInvaders extends JFrame  {

    public ChickenInvaders() {

        initUI();
    }

    private void initUI() {

        add(new Board());

        setTitle("Chicken Invaders");
        setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var ex = new ChickenInvaders();
            ex.setVisible(true);
        });
    }
}
