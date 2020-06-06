package com.anna;

import com.anna.gameui.GameState;
import com.anna.gameui.LevelState;

import java.util.Observable;
import java.util.Observer;

public class TestObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        LevelState levelState = (LevelState)arg;
        switch (levelState){
            case Win:
                System.out.println("level completed");
                break;
            case Lose:
                System.out.println("you lose");
                break;
            case Started:
                System.out.println("started");
                break;
        }
    }
}
