package src.com.chickenInavaders.view;

import src.com.chickenInavaders.ChickenInvaders;
import src.com.chickenInavaders.Commons;
import src.com.chickenInavaders.GameObserver;
import src.com.chickenInavaders.controllers.settings.SaveReader;
import src.com.chickenInavaders.view.panels.*;
import src.com.chickenInavaders.controllers.GameController;

import javax.swing.*;
import java.awt.*;

import javax.swing.JFrame;

public class LayoutManager extends JFrame {

    public SettingsP settingsP;
    public SaveReader gameSaves;
    public EndGameMenu endGameP;
    public MainMenu mainMenuP;
    public StartGameP startGameP;
    public AboutP aboutP;
    public JPanel cardPane;
    public CardLayout card;
    public CardLayout cardLayout;
    public ChickenInvaders mainFrame;
    public GameController gameUI;
    public GamePauseP gamePauseP;

    public LayoutManager() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Chicken Invaders");
        setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 1, 0, 0));
        init();
    }

    private void init() {
        //all tha game panels
        gameSaves = new SaveReader();
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

        gameUI = new GameController(this);
        gameUI.setName("Game");
        gameUI.addGameObserver(new GameObserver());

        endGameP = new EndGameMenu(this);
        endGameP.setName("EndGameMenu");

        gamePauseP = new GamePauseP(this);
        gamePauseP.setName("GamePause");

        //StartGame
        cardPane.setLayout(card);
        cardPane.add("Game", gameUI);
        cardPane.add("StartGame", startGameP);
        cardPane.add("MainMenu", mainMenuP);
        cardPane.add("Settings", settingsP);
        cardPane.add("About", aboutP);
        cardPane.add("EndGameMenu", endGameP);
        cardPane.add("GamePause", gamePauseP);
        add(cardPane);
        cardLayout = (CardLayout) cardPane.getLayout();
        cardLayout.show(cardPane, "MainMenu");
        setVisible(true);

    }

}
