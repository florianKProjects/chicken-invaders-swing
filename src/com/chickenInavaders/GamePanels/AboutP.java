package src.com.chickenInavaders.GamePanels;

import src.com.chickenInavaders.Commons;
import src.com.chickenInavaders.LayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AboutP extends JPanel implements MouseListener {
    private JLabel BackGrondP;
    private ImageIcon aboutText;
    private final int ANIMATION_DELAY = 50;
    private int x = 0;
    private int y = 750;

    private LayoutManager panelGraph ;
    private Timer animationTimer;



    public AboutP(LayoutManager l ){
        panelGraph = l;
        BackGrondP = new JLabel();
        aboutText = new ImageIcon(Commons.TXT_ABOUT_IMG);
        init();
    }
    private void  init(){
        BackGrondP.setBounds(0, 0, Commons.BOARD_WIDTH,Commons.BOARD_HEIGHT);
        BackGrondP.setIcon(new ImageIcon(Commons.BACKGROUND_ABOUT_IMG));
        setLayout(null);
        startAnimation();
        addMouseListener(this);

    }
    public void startAnimation() {
        if (animationTimer == null) {
            animationTimer = new Timer(ANIMATION_DELAY, new TimerHandler());

            animationTimer.start();
        } else {
            if (!animationTimer.isRunning()) {
                animationTimer.restart();
            }
        }
    }
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon(Commons.BACKGROUND_ABOUT_IMG).getImage(),0, 0, Commons.BOARD_WIDTH,Commons.BOARD_HEIGHT,null);
        aboutText.paintIcon(this, g, x, y);

        y-= 2;
        if (Commons.IS_DEBUG)
            System.out.println("x: "+x +" y: "+y);
        if(y<-1600)
            stopAnimation();
    }

    public void stopAnimation() {
        animationTimer.stop();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        y=750;
        panelGraph.cardLayout.show(panelGraph.cardPane, "MainMenu");
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private class TimerHandler implements ActionListener {

        public void actionPerformed(ActionEvent actionEvent) {
            repaint();
        }
    }
}