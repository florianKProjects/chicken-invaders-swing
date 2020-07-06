package src.com.chickenInavaders.gameui;

import javax.swing.*;
import java.util.Observer;

public interface IGameUI {
    void startLevel(int level,int lives,int tick,int eggInterval);
    void resume();
    void pause();
    void addGameObserver(Observer gameObserver);
}
