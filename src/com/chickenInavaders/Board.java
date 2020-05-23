package com.chickenInavaders;

import com.chickenInavaders.sprite.Chicken;
import com.chickenInavaders.sprite.Ship;
import com.chickenInavaders.sprite.Shot;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Board extends JPanel {

    private Dimension d;
    private List<Chicken> chickens;
    private Ship ship;
    private Shot shot;

    private int direction = -1;
    private int deaths = 0;

    private boolean inGame = true;
    private String explImg = "src/images/explosion.png";
    private String message = "Game Over";

    private Timer timer;

    public Board() {

        initBoard();
        gameInit();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        setBackground(Color.black);

        timer = new Timer(Commons.DELAY, new GameCycle());
        timer.start();

        gameInit();
    }

    private void gameInit() {

        chickens = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {

                var chicken = new Chicken(Commons.CHICKEN_INIT_X + Commons.CHICKEN_SPACE_BETWEEN * j,
                        Commons.CHICKEN_INIT_Y + Commons.CHICKEN_SPACE_BETWEEN * i);
                chickens.add(chicken);
            }
        }

        ship = new Ship();
        shot = new Shot();
    }

    private void drawChickens(Graphics g) {

        for (Chicken chicken : chickens) {

            if (chicken.isVisible()) {

                g.drawImage(chicken.getImage(), chicken.getX(), chicken.getY(), this);
            }

            if (chicken.isDying()) {

                chicken.die();
            }
        }
    }

    private void drawShip(Graphics g) {

        if (ship.isVisible()) {

            g.drawImage(ship.getImage(), ship.getX(), ship.getY(), this);
        }

        if (ship.isDying()) {

            ship.die();
            inGame = false;
        }
    }

    private void drawShot(Graphics g) {

        if (shot.isVisible()) {

            g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
        }
    }

    private void drawBombing(Graphics g) {

        for (Chicken a : chickens) {

            Chicken.Bomb b = a.getBomb();

            if (!b.isDestroyed()) {

                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.green);

        if (inGame) {

            g.drawLine(0, Commons.GROUND,
                    Commons.BOARD_WIDTH, Commons.GROUND);

            drawChickens(g);
            drawShip(g);
            drawShot(g);
            drawBombing(g);

        } else {

            if (timer.isRunning()) {
                timer.stop();
            }

            gameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void gameOver(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0, 0, Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, Commons.BOARD_WIDTH / 2 - 30, Commons.BOARD_WIDTH - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, Commons.BOARD_WIDTH / 2 - 30, Commons.BOARD_WIDTH - 100, 50);

        var small = new Font("Helvetica", Font.BOLD, 14);
        var fontMetrics = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, (Commons.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2,
                Commons.BOARD_WIDTH / 2);
    }

    private void update() {

        if (deaths == Commons.NUMBER_OF_ALIENS_TO_DESTROY) {

            inGame = false;
            timer.stop();
            message = "Game won!";
        }

        // ship
        ship.act();

        // shot
        if (shot.isVisible()) {

            int shotX = shot.getX();
            int shotY = shot.getY();

            for (Chicken chicken : chickens) {

                int alienX = chicken.getX();
                int alienY = chicken.getY();

                if (chicken.isVisible() && shot.isVisible()) {
                    if (shotX >= (alienX)
                            && shotX <= (alienX + Commons.CHICKEN_WIDTH)
                            && shotY >= (alienY)
                            && shotY <= (alienY + Commons.CHICKEN_HEIGHT)) {

                        var ii = new ImageIcon(explImg);
                        chicken.setImage(ii.getImage());
                        chicken.setDying(true);
                        deaths++;
                        shot.die();
                    }
                }
            }

            int y = shot.getY();
            y -= 4;

            if (y < 0) {
                shot.die();
            } else {
                shot.setY(y);
            }
        }

        // chickens

        for (Chicken chicken : chickens) {

            int x = chicken.getX();

            if (x >= Commons.BOARD_WIDTH - Commons.BORDER_RIGHT && direction != -1) {

                direction = -1;

                Iterator<Chicken> i1 = chickens.iterator();

                while (i1.hasNext()) {

                    Chicken a2 = i1.next();
                    a2.setY(a2.getY() + Commons.GO_DOWN);
                }
            }

            if (x <= Commons.BORDER_LEFT && direction != 1) {

                direction = 1;

                Iterator<Chicken> i2 = chickens.iterator();

                while (i2.hasNext()) {

                    Chicken a = i2.next();
                    a.setY(a.getY() + Commons.GO_DOWN);
                }
            }
        }

        Iterator<Chicken> it = chickens.iterator();

        while (it.hasNext()) {

            Chicken chicken = it.next();

            if (chicken.isVisible()) {

                int y = chicken.getY();

                if (y > Commons.GROUND - Commons.CHICKEN_HEIGHT) {
                    inGame = false;
                    message = "Invasion!";
                }

                chicken.act(direction);
            }
        }

        // bombs
        var generator = new Random();

        for (Chicken chicken : chickens) {

            int shot = generator.nextInt(15);
            Chicken.Bomb bomb = chicken.getBomb();

            if (shot == Commons.CHANCE && chicken.isVisible() && bomb.isDestroyed()) {

                bomb.setDestroyed(false);
                bomb.setX(chicken.getX());
                bomb.setY(chicken.getY());
            }

            int bombX = bomb.getX();
            int bombY = bomb.getY();
            int playerX = ship.getX();
            int playerY = ship.getY();

            if (ship.isVisible() && !bomb.isDestroyed()) {

                if (bombX >= (playerX)
                        && bombX <= (playerX + Commons.PLAYER_WIDTH)
                        && bombY >= (playerY)
                        && bombY <= (playerY + Commons.PLAYER_HEIGHT)) {

                    var ii = new ImageIcon(explImg);
                    ship.setImage(ii.getImage());
                    ship.setDying(true);
                    bomb.setDestroyed(true);
                }
            }

            if (!bomb.isDestroyed()) {

                bomb.setY(bomb.getY() + 1);

                if (bomb.getY() >= Commons.GROUND - Commons.BOMB_HEIGHT) {

                    bomb.setDestroyed(true);
                }
            }
        }
    }

    private void doGameCycle() {

        update();
        repaint();
    }

    private class GameCycle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            doGameCycle();
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            ship.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {

            ship.keyPressed(e);

            int x = ship.getX();
            int y = ship.getY();

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {

                if (inGame) {

                    if (!shot.isVisible()) {

                        shot = new Shot(x, y);
                    }
                }
            }
        }
    }
}
