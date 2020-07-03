package src.com.chickenInavaders.observers;

import src.com.chickenInavaders.SoundPlayer;
import src.com.chickenInavaders.gameui.LevelState;

import java.util.Observable;

public class GameObservable extends Observable {

    public void notifyLevelState(LevelState levelState) {
        switch (levelState) {
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
