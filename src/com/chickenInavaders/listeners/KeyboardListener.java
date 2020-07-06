package src.com.chickenInavaders.listeners;

import src.com.chickenInavaders.Settings;
import src.com.chickenInavaders.gameui.GameUI;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import javax.swing.*;

public class KeyboardListener implements KeyListener {
    GameUI gameUI;
    Settings settings;
    int leftKey = 37;
    int rightKey = 39;
    int fireKey = 32;

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
        if (e.getKeyCode() == fireKey)
            gameUI.createShot();
        else if (e.getKeyCode() == leftKey)
            gameUI.moveLeft();
        else if (e.getKeyCode() == rightKey)
            gameUI.moveRight();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
