package src.com.chickenInavaders.model;

import javax.swing.*;
import java.util.Observer;

public interface IGameController {
    void startLevel(int level,int lives,int tick,int eggInterval);
    void resume();
    void pause();
    void addGameObserver(Observer gameObserver);
}
