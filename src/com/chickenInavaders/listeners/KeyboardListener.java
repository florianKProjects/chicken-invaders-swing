package src.com.chickenInavaders.listeners;

import src.com.chickenInavaders.Settings;
import src.com.chickenInavaders.gameui.GameUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;

public class KeyboardListener implements KeyListener {
    private GameUI gameUI;
    private Settings settings;
    private int leftKey = 37;
    private int rightKey = 39;
    private int fireKey = 32;
    private int pauseGamekKey = 27;
    private boolean isFireKeyUp = true;
    private long lastTime = 0;

    public KeyboardListener(GameUI gameUI) {
        this.gameUI = gameUI;
        gameUI.setFocusable(true);
        gameUI.requestFocusInWindow();
        settings = Settings.getInstance();
        leftKey = Integer.parseInt(settings.getKeyboardLayoutPlayer1("left"));
        rightKey = Integer.parseInt(settings.getKeyboardLayoutPlayer1("right"));
        fireKey = Integer.parseInt(settings.getKeyboardLayoutPlayer1("fire"));
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
        if (e.getKeyCode() == fireKey &&
                isFireKeyUp == true &&
                (System.currentTimeMillis() - lastTime) > 200) {
            gameUI.createShot();
            isFireKeyUp = false;
        } else if (e.getKeyCode() == leftKey)
            gameUI.moveLeft();
        else if (e.getKeyCode() == rightKey)
            gameUI.moveRight();
        else if (e.getKeyCode() == pauseGamekKey) {
            gameUI.panelGraph.cardLayout.show(gameUI.panelGraph.cardPane, "GamePause");
            gameUI.pause();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == fireKey) {
            isFireKeyUp = true;
            lastTime = System.currentTimeMillis();
        }
    }
}
