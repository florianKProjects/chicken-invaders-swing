package com.anna.gameui;
import com.anna.observers.GameObservable;
import com.anna.sprites.Chicken;
import com.anna.sprites.Ship;
import com.anna.sprites.Sprite;
import javax.swing.Timer;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class GameState {
    public int stopGameFrames;
    public boolean stopGameFlag = false;
    public LevelState levelState;
    public  Timer timer;
    public int eggInterval;
    public int eggFrames;
    public List<Sprite> chickens;
    public List<Sprite> eggs;
    public List<Sprite> shots;
    public Ship ship;
    public int level;
    public int lives;
    public int score;
    public int gamePanelHeight;
    public int gamePanelWidth;
    public GameObservable gameObservable;

    GameState(int gamePanelHeight,int gamePanelWidth){
        this.gamePanelHeight=gamePanelHeight;
        this.gamePanelWidth=gamePanelWidth;
        chickens = new ArrayList<Sprite>();
        for(int i=0;i<40;i++)
            chickens.add(new Chicken());
        int row=-1;

        for(int i=0;i<40;i++){
            if(i%8==0) row++;
            chickens.get(i).position= new Point(20+(i%8)*70,50+row*70);
        }

        ship=new Ship();
        ship.position=new Point((gamePanelWidth-90)/2,gamePanelHeight-150);
        shots=new ArrayList<Sprite>();
        eggs=new ArrayList<Sprite>();


    }
}
