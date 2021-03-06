package src.com.chickenInavaders.view.panels;

import src.com.chickenInavaders.*;
import src.com.chickenInavaders.view.LayoutManager;
import src.com.chickenInavaders.controllers.GameController;
import src.com.chickenInavaders.view.GameButton;
import src.com.chickenInavaders.view.ImageDraw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePauseP  extends JPanel {
    private GameButton exitB;
    private GameButton resumeB;
    private GameButton restartB;
    private LayoutManager l;
    private ImageDraw BackGround;

    public GamePauseP(LayoutManager l) {
        this.l = l ;
        setBounds(0, 0, Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        exitB = new GameButton(Commons.EXIT_IMAGE);
        resumeB = new GameButton(Commons.RESUME_IMAGE);
        restartB = new GameButton(Commons.RESTART_IMG);
        setLayout(null);
        init();

    }

    private void init() {
        buttonGenerator();
        BackGround = new ImageDraw(new ImageIcon(Commons.GAME_PAUSE_IMAGE).getImage(), 0, 0, Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);

    }

    public void paintComponent(Graphics g) {
        g.drawImage(BackGround.image, BackGround.loc_x, BackGround.loc_y, BackGround.width, BackGround.height, this);

    }
    private void buttonGenerator() {

        resumeB.setText("Resume");
        resumeB.setBounds(210, 310, 196, 68);
        resumeB.addActionListener(evt -> {
            l.cardLayout.show(l.cardPane, "Game");
            l.cardPane.transferFocus();
            l.gameUI.resume();
        });
        add(resumeB);

        restartB.setText("Restart");
        restartB.setBounds(220, 400, 172, 68);
        restartB.addActionListener(evt -> {
            int level = l.gameUI.gameState.level;
            int lives = l.gameUI.gameState.lives;
            l.gameUI = new GameController(l);
            l.gameUI.setName("Game");
            l.gameUI.addGameObserver(new GameObserver(l));
            l.gameUI.startLevel(level, lives, 12, 50);
            l.cardPane.add("Game", l.gameUI);
            l.cardLayout.show(l.cardPane, "Game");
            l.cardPane.transferFocus();
        });
        add(restartB);

        exitB.setText("Exit");
        exitB.setBounds(240, 490, 130, 68);
        //exitB.setRolloverIcon(new ImageIcon(Commons.EXIT_IMAGE));
        exitB.addActionListener(evt -> {
            l.cardLayout.show(l.cardPane, "MainMenu");
            l.cardPane.transferFocus();
            l.gameUI.resume();
        });
        add(exitB);
    }

}