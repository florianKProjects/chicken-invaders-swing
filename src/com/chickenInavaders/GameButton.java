package com.chickenInavaders;

import javax.swing.*;
import javax.swing.border.Border;

public class GameButton  extends JButton{

    public GameButton(String img) {
        this(new ImageIcon(img));
    }
    public GameButton() {
    }
    public GameButton(ImageIcon icon) {
        Border emptyBorder = BorderFactory.createEmptyBorder();
        setBorder(emptyBorder);
        setIcon(icon);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setSize(icon.getImage().getWidth(null), icon.getImage().getHeight(null));
        setText(null);
        setIconTextGap(0);
        setLayout(null);
    }
    public void setBound(String Bound)
    {
        // example "12,12,12,12"
        String[] BoundSplit = Bound.split("\\,");
        int boardWidth = 0;
        int boardHeight = 0;
        int x = Integer.parseInt(BoundSplit[0]);
        int y = Integer.parseInt(BoundSplit[1]);
        if (BoundSplit.length >2) {
            boardWidth = Integer.parseInt(BoundSplit[2]);
            boardHeight = Integer.parseInt(BoundSplit[3]);
        }
        setBounds(x,y,boardWidth,boardHeight);
    }
    public void ChangeImage(String img) {
        setIcon(new ImageIcon(img));
    }




}
