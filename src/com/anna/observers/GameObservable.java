package com.anna.observers;

import com.anna.gameui.GameState;
import com.anna.gameui.LevelState;

import java.util.Observable;

public class GameObservable extends Observable {

    public void notifyLevelState(LevelState levelState){
        setChanged();
        notifyObservers(levelState);
    }

}
