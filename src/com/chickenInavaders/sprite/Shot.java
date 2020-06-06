package com.chickenInavaders.sprite;

import com.chickenInavaders.Commons;

import javax.swing.ImageIcon;

public class Shot extends Sprite {

    public Shot() {
    }

    public Shot(int x, int y) {

        initShot(x, y);
    }

    private void initShot(int x, int y) {

        String shotImg = "src/images/shot.png";
        ImageIcon ii = new ImageIcon(shotImg);
        setImage(ii.getImage());

        setX(x + Commons.PLAYER_WIDTH / 2);
        setY(y - 10);
    }
}
