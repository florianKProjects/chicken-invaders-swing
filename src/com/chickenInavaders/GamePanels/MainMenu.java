package src.com.chickenInavaders.GamePanels;

import src.com.chickenInavaders.GameButton;
import src.com.chickenInavaders.ImageDraw;
import src.com.chickenInavaders.LayoutManager;
import src.com.chickenInavaders.Commons;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {

    private GameButton startGameB;
    private GameButton settingsB;
    private GameButton aboutB;
    private GameButton exitGameB;

    private ImageDraw BackGrond;
    private ImageDraw GameName;

    private LayoutManager mainFrame;
    public MainMenu(){
        init();
    }
    public MainMenu(LayoutManager L){
        mainFrame = L;
        init();
    }
    public void init()
    {
        setLayout(new MigLayout(
                "fill,hidemode 3,alignx center" ,"[fill][fill][][fill]" , "[fill][fill][fill][fill][fill][][][][][][]" ));
        setUpImages();
        startGame();
        settingsGame();
        exitGame();
        openAbout();
        add(startGameB, "cell 2 50");
        add(settingsB, "cell 2 51");
        add(aboutB, "cell 2 52");
        add(exitGameB, "cell 2 53");
    }

    private void startGame(){
        startGameB = new GameButton(Commons.START_GAME_IMG);
        startGameB.setRolloverIcon(new ImageIcon(Commons.START_GAME__HOVER_IMG));
        startGameB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                startGameB.setSelected(!startGameB.isSelected());
                mainFrame.cardLayout.show(mainFrame.cardPane, "StartGame");
            }
        });
    }
    private void settingsGame(){
        settingsB = new GameButton(Commons.SETTINGS_IMG);
        settingsB.setRolloverIcon(new ImageIcon(Commons.SETTINGS_HOVER_IMG));
        settingsB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                startGameB.setSelected(!startGameB.isSelected());
                mainFrame.cardLayout.show(mainFrame.cardPane, "Settings");
            }
        });
    }
    private void exitGame(){
        exitGameB = new GameButton(Commons.EXIT_GAME_IMG);
        exitGameB.setRolloverIcon(new ImageIcon(Commons.EXIT_GAME_HOVER_IMG));
        exitGameB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                startGameB.setSelected(!startGameB.isSelected());
                System.exit(0);
            }
        });
    }
    private void openAbout(){
        aboutB = new GameButton(Commons.ABOUT_IMG);
        aboutB.setRolloverIcon(new ImageIcon(Commons.ABOUT_HOVER_IMG));
        aboutB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                aboutB.setSelected(!startGameB.isSelected());
                mainFrame.cardLayout.show(mainFrame.cardPane, "About");
            }
        });
    }
    private void setUpImages(){
        BackGrond = new ImageDraw(new ImageIcon(Commons.BACKGROUND_IMG).getImage(),0,0,Commons.BOARD_WIDTH,Commons.BOARD_HEIGHT);
        GameName = new ImageDraw(new ImageIcon(Commons.MAIN_GAME_NAME_IMG).getImage(),135,80,300,300);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(BackGrond.image, BackGrond.loc_x, BackGrond.loc_y, BackGrond.width, BackGrond.height, this);
        g.drawImage(GameName.image, GameName.loc_x, GameName.loc_y, GameName.width,GameName.height, this);


    }
}