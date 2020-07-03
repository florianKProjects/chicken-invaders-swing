package src.com.chickenInavaders;

import src.com.chickenInavaders.sprites.Chicken;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

public class Level {

    public HashMap<Integer,LevelConfig> levelList;
    public  int maxLevel ;
    public int[][] LevelConfig = { { 1, 0,12, 50 },
            { 2, 0,11, 30 },
            { 3, 0,10, 20 }};

    private static final Level instance = new Level();

    public Level(){
        this.levelList = new HashMap<Integer,LevelConfig>();
        levelProcess();
        maxLevel = levelList.size();
    }
    public static Level getInstance() {return instance;}

    private void  levelProcess()
    {
        for (int[] level : LevelConfig)
        {
            levelList.put(level[0],new LevelConfig(level[0],level[1],level[2],level[3]));
        }
    }

//----------------- level Config  ------------------------------------
public class LevelConfig {
    public int levelNum;
    public int score;
    public int tick;
    public int eggInterval;
    public String shipColor;

    public LevelConfig(int level, int score, int tick, int eggInterval) {
        this.levelNum = level;
        this.score = score;
        this.tick = tick;
        this.eggInterval = eggInterval;
        this.shipColor = "";
    }

    public LevelConfig() {
        this(0, 0, 0, 0);
    }
}
}

