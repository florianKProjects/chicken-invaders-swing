package com.chickenInavaders;

import com.chickenInavaders.GamePanels.*;

import javax.swing.*;
import java.awt.*;
import java.net.PortUnreachableException;
import javax.swing.JFrame;

public class LayoutManager extends JFrame  {

    public SettingsP settingsP ;
    public MainMenu mainMenuP;
    public StartGameP startGameP;
    public AboutP aboutP;
    public JPanel cardPane;
    public CardLayout card;
    public CardLayout cardLayout;
    public ChickenInvaders mainFrame;

    public  LayoutManager(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Chicken Invaders");
        setSize(600,800);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1,1,0,0));
        init();

    }
    private void  init(){
        //all tha game panels
        cardPane = new JPanel();
        card = new CardLayout();

        settingsP = new SettingsP(this);
        settingsP.setName("Settings");

        mainMenuP = new MainMenu(this);
        mainMenuP.setName("MainMenu");

        startGameP = new StartGameP(this);
        startGameP.setName("StartGame");

        aboutP = new AboutP(this);
        aboutP.setName("About");
        //StartGame
        cardPane.setLayout(card);
        cardPane.add("StartGame",startGameP);
        cardPane.add("MainMenu",mainMenuP);
        cardPane.add("Settings",settingsP);
        cardPane.add("About",aboutP);

        add(cardPane);
        cardLayout = (CardLayout) cardPane.getLayout();
        cardLayout.show(cardPane, "MainMenu");
        setVisible(true);

    }
}
