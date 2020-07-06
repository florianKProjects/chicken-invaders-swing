package src.com.chickenInavaders.controllers;

import src.com.chickenInavaders.controllers.settings.SettingsController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardController implements KeyListener {
    private GameController gameUI;
    private SettingsController settingsController;
    private int leftKey;
    private int rightKey;
    private int fireKey;
    private int pauseGameKey = 27;
    private long lastTime = 0;
    private  int shotsInterval = 700;

    public KeyboardController(GameController gameUI) {
        this.gameUI = gameUI;
        gameUI.setFocusable(true);
        gameUI.requestFocusInWindow();
        settingsController = SettingsController.getInstance();
        leftKey = Integer.parseInt(settingsController.getKeyboardLayoutPlayer1("left"));
        rightKey = Integer.parseInt(settingsController.getKeyboardLayoutPlayer1("right"));
        fireKey = Integer.parseInt(settingsController.getKeyboardLayoutPlayer1("fire"));
    }

    private int getAsciiValue(String s) {
        char c = s.charAt(0);
        int n = KeyEvent.getExtendedKeyCodeForChar(c);
        return 0;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == fireKey){
            if(System.currentTimeMillis() - lastTime > shotsInterval) {
                gameUI.createShot();
                lastTime = System.currentTimeMillis();
            }
        } else if (e.getKeyCode() == leftKey)
            gameUI.moveLeft();
        else if (e.getKeyCode() == rightKey)
            gameUI.moveRight();
        else if (e.getKeyCode() == pauseGameKey) {
            gameUI.panelGraph.cardLayout.show(gameUI.panelGraph.cardPane, "GamePause");
            gameUI.pause();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
