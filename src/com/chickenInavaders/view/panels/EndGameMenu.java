package src.com.chickenInavaders.view.panels;

import src.com.chickenInavaders.*;
import src.com.chickenInavaders.controllers.settings.Level;
import src.com.chickenInavaders.view.LayoutManager;
import src.com.chickenInavaders.controllers.GameController;
import src.com.chickenInavaders.view.GameButton;
import src.com.chickenInavaders.view.ImageDraw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndGameMenu extends JPanel {
    private ImageDraw BackGrond;
    private LayoutManager l;
    private JLabel scoreLabel;
    private String gmaeScore;
    private GameButton yesB;
    private GameButton noB;
    private Level levles = Level.getInstance();
    public EndGameMenu(LayoutManager l) {
        setLayout(null);
        this.l = l;
        gmaeScore = "";
        scoreLabel = new JLabel();
        yesB = new GameButton("");
        noB = new GameButton("");
        init();

    }

    private void init() {
        setUpScoreLable();
        setUpImages();
        setUpGameButton();
        add(scoreLabel);
        add(yesB);
        add(noB);

    }

    private void setUpImages() {
        BackGrond = new ImageDraw(new ImageIcon(Commons.END_GAME_BACKGOURND).getImage(), 0, 0, Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(BackGrond.image, BackGrond.loc_x, BackGrond.loc_y, BackGrond.width, BackGrond.height, this);
    }

    private void setUpScoreLable() {
        scoreLabel.setBounds(268, 320, 100, 100);
        scoreLabel.setFont(new Font("accidental pregnancy", Font.BOLD, 35));
        scoreLabel.setForeground(new Color(145, 191, 237));
        setScore("0");

    }

    private void setUpGameButton() {
        yesB.setText("Yes");
        yesB.setBounds(315, 470, 100, 100);
        yesB.setFont(new Font("accidental pregnancy", Font.BOLD, 30));
        yesB.setForeground(new Color(255, 255, 255));
        yesB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                l.gameUI = new GameController(l);
                l.gameUI.setName("Game");
                l.gameUI.addGameObserver(new GameObserver(l));
                l.gameUI.startLevel(1, 3, levles.levelList.get(1).tick, levles.levelList.get(1).eggInterval);
                l.cardPane.add("Game", l.gameUI);
                l.startGameP.setPlayerid(Integer.toString(l.gameSaves.getLastIndex()));
                l.cardLayout.show(l.cardPane, "Game");
                l.cardPane.transferFocus();

            }
        });
        noB.setText("No");
        noB.setBounds(200, 470, 100, 100);
        noB.setFont(new Font("accidental pregnancy", Font.BOLD, 30));
        noB.setForeground(new Color(255, 255, 255));
        noB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                noB.setSelected(!yesB.isSelected());
                l.cardLayout.show(l.cardPane, "MainMenu");
            }
        });
    }

    public void setScore(String score) {
        gmaeScore = score;
        scoreLabel.setText(score);
    }

    public void gameOver() {

    }

    public String getScore() {
        return gmaeScore;
    }

    // public EndGameMenu(){}
    // private void restartGame(){}
    // private void returnToMainMenu(){}

}
