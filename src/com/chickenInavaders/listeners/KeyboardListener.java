package src.com.chickenInavaders.listeners;
import src.com.chickenInavaders.gameui.GameUI;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {
    GameUI gameUI;

    public KeyboardListener(GameUI gameUI){
        this.gameUI=gameUI;
        gameUI.setFocusable(true);
        gameUI.requestFocusInWindow();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case 32:
                gameUI.createShot();
                break;
            case 37:
                gameUI.moveLeft();
                break;
            case 39:
                gameUI.moveRight();
                break;
            default:break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
