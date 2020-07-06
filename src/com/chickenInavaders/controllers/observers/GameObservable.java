package src.com.chickenInavaders.controllers.observers;

import src.com.chickenInavaders.model.LevelState;

import java.util.Observable;

public class GameObservable extends Observable {

    public void notifyLevelState(LevelState levelState){
        setChanged();
        notifyObservers(levelState);
    }

}
