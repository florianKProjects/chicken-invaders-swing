package src.com.chickenInavaders.observers;

import src.com.chickenInavaders.gameui.LevelState;

import java.util.Observable;

public class GameObservable extends Observable {

    public void notifyLevelState(LevelState levelState){
        setChanged();
        notifyObservers(levelState);
    }

}
