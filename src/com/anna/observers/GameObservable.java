package com.anna.observers;

import com.anna.SoundPlayer;
import com.anna.gameui.LevelState;

import java.util.Observable;

public class GameObservable extends Observable {

    public void notifyLevelState(LevelState levelState){
        switch (levelState){
            case Lose:
                SoundPlayer.play("C:\\Program Files (x86)\\Microsoft Office\\root\\Office16\\MEDIA\\EXPLODE.WAV");
                break;
            case Win:
            default:
                break;


        }
        setChanged();
        notifyObservers(levelState);
    }

}
