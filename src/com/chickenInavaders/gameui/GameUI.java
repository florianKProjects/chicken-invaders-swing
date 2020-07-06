package src.com.chickenInavaders.gameui;

import src.com.chickenInavaders.Commons;
import src.com.chickenInavaders.LayoutManager;
import src.com.chickenInavaders.SoundPlayer;
import src.com.chickenInavaders.listeners.KeyboardListener;
import src.com.chickenInavaders.listeners.TickListener;
import src.com.chickenInavaders.observers.EggObserver;
import src.com.chickenInavaders.observers.GameObservable;
import src.com.chickenInavaders.observers.ShotObserver;
import src.com.chickenInavaders.sprites.Egg;
import src.com.chickenInavaders.sprites.Shot;
import src.com.chickenInavaders.sprites.Sprite;
import src.com.chickenInavaders.sprites.SpriteState;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.util.*;
import java.util.List;

public class GameUI extends JPanel implements IGameUI {
    Image image = new ImageIcon("src/com/chickenInavaders/images/Lives.png").getImage();
    public GameState gameState;
    public GameObservable gameObservable;
    Bkg bkg1 = new Bkg();
    Bkg bkg2 = new Bkg();
    private LayoutManager panelGraph;

    public GameUI(LayoutManager l) {
        panelGraph = l;
        setSize(600, 800);
        gameObservable = new GameObservable();
        KeyboardListener keyboardListener = new KeyboardListener(this);
        addKeyListener(keyboardListener);

        bkg1.position.y = -800;

        // startLevel(1,3,10,200);
    }

    // make the background move
    void paintBkg(Graphics graphics) {
        bkg1.position.y += 2;
        bkg2.position.y += 2;

        if (bkg1.position.y == 800)
            bkg1.position.y = -800;
        else if (bkg2.position.y == 800)
            bkg2.position.y = -800;

        bkg1.paint(graphics);
        bkg2.paint(graphics);
        Earth earth = new Earth();
        earth.paint(graphics);
    }

    public void moveLeft() {
        if (gameState.ship.position.x > 10)
            gameState.ship.position.x -= 5;
    }

    public void moveRight() {
        if (gameState.ship.position.x < getWidth() - 100)
            gameState.ship.position.x += 5;
    }

    public void createEgg() {
        // select random live chicken;
        Object[] liveChickens = gameState.chickens.stream().filter(c -> c.getState() == SpriteState.Alive).toArray();

        if (liveChickens.length == 0) {
            gameState.timer.stop();
            gameState.levelState = LevelState.Win;
            gameState.gameObservable.notifyLevelState(LevelState.Win);
            return;
        }

        Random ran = new Random();
        int x = ran.nextInt(liveChickens.length);
        Egg egg = new Egg();
        egg.position = new Point(((Sprite) liveChickens[x]).position);
        egg.position.x += 15;
        egg.position.y += 20;
        EggObserver eggObserver = new EggObserver(gameState);
        egg.addObserver(eggObserver);
        gameState.eggs.add(egg);

    }

    public void createShot() {
        Shot shot = new Shot();
        shot.position = new Point(gameState.ship.position);
        shot.position.x += 35;
        ShotObserver shotObserver = new ShotObserver(gameState);
        shot.addObserver(shotObserver);
        gameState.shots.add(shot);
        SoundPlayer.play(Commons.CLICK_SOUND);
    }

    private void paintList(List<Sprite> characters, Graphics graphics) {
        for (int i = 0; i < characters.size(); i++)
            characters.get(i).paint(graphics);
    }

    private void paintLevel(Graphics graphics) {
        graphics.setColor(Color.orange);
        graphics.drawRoundRect(25, 8, 85, 32, 20, 20);
        graphics.setColor(Color.white);
        Font f = new Font("Serif", Font.BOLD, 20);
        graphics.setFont(f);
        graphics.drawString("Level " + gameState.level, gameState.level > 9 ? 33 : 37, 30);
    }

    private void paintLives(Graphics graphics) {
        int x = 280;
        for (int i = 1; i <= gameState.lives; i++) {
            graphics.drawImage(image, x, 0, null);
            if (i % 2 == 0)
                x += (i * 40);
            else
                x -= (i * 40);
        }
    }

    private void paintScore(Graphics graphics) {
        graphics.setColor(Color.orange);
        Font f = new Font("Arial", Font.BOLD, 24);
        graphics.setFont(f);
        graphics.drawString("" + gameState.score, 450, 28);

    }

    private void paintGameOver(Graphics graphics) {
        graphics.setColor(Color.orange);
        Font f = new Font("Arial", Font.BOLD, 50);
        graphics.setFont(f);
        graphics.drawString("GAME OVER", 135, 450);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        paintBkg(graphics);
        paintLevel(graphics);
        paintLives(graphics);
        paintScore(graphics);
        paintList(gameState.chickens, graphics);
        paintList(gameState.eggs, graphics);
        paintList(gameState.shots, graphics);
        gameState.ship.paint(graphics);
        if (gameState.stopGameFlag) {
            if (gameState.levelState == LevelState.Lose)
                panelGraph.cardLayout.show(panelGraph.cardPane, "EndGameMenu");
            panelGraph.gameSaves.addRecord(panelGraph.startGameP.getPName(), gameState.score, gameState.level);
            panelGraph.endGameP.setScore(Integer.toString(gameState.score));
        }
    }

    @Override
    public void startLevel(int level, int lives, int tick, int eggInterval) {
        gameState = new GameState(getHeight(), getWidth());
        gameState.eggInterval = 200;
        gameState.gameObservable = gameObservable;
        gameState.level = level;
        gameState.lives = lives;
        gameState.score = 0;
        gameState.eggFrames = eggInterval;
        gameState.stopGameFlag = false;
        gameState.stopGameFrames = 10;
        gameState.timer = new Timer(tick, new TickListener(this));
        gameState.levelState = LevelState.Started;
        gameState.timer.start();
        gameObservable.notifyLevelState(gameState.levelState);

    }

    public void saveGame() {

    }

    @Override
    public void resume() {
        if (gameState.timer != null)
            gameState.timer.restart();
    }

    @Override
    public void pause() {
        gameState.timer.stop();
    }

    @Override
    public void addGameObserver(Observer gameObserver) {
        gameObservable.addObserver(gameObserver);
    }

    @Override
    public JPanel getPanel() {
        return this;
    }
}
