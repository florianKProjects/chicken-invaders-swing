package com.chickenInavaders.sprite;

import com.chickenInavaders.Commons;

import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

public class Ship extends Sprite {

    private int width;

    public Ship() {

        initShip();
    }

    private void initShip() {

        String playerImg = "src/images/player.png";
        ImageIcon ii = new ImageIcon(playerImg);

        width = ii.getImage().getWidth(null);
        setImage(ii.getImage());

        setX(Commons.PLAYER_X);
        setY(Commons.PLAYER_Y);
    }

    public void act() {

        x += dx;

        if (x <= 2) {

            x = 2;
        }

        if (x >= Commons.BOARD_WIDTH - 2 * width) {

            x = Commons.BOARD_WIDTH - 2 * width;
        }
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 2;
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 0;
        }
    }
}
