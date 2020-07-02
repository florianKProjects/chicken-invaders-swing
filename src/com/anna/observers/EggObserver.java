package com.anna.observers;

import com.anna.SoundPlayer;
import com.anna.gameui.GameState;
import com.anna.gameui.LevelState;
import com.anna.sprites.Egg;
import com.anna.sprites.SpriteState;

import java.util.Observable;
import java.util.Observer;

public class EggObserver implements Observer {
    GameState gameState;


    public EggObserver(GameState gameState) {
        this.gameState=gameState;
    }

    @Override
    public void update(Observable o, Object arg) {

        if (gameState.stopGameFlag) {
            if (gameState.stopGameFrames == 0) {
                gameState.timer.stop();
                gameState.stopGameFrames = 10;
                gameState.gameObservable.notifyLevelState(gameState.levelState);
            }
            gameState.stopGameFrames--;
            return;
        }
        Egg currEgg = (Egg) o;
        if (currEgg.getState() != SpriteState.Alive || currEgg.position.y >= gameState.gamePanelHeight) {
            gameState.eggs.remove(currEgg);
            return;
        }
        for (int i = 0; i < gameState.shots.size(); i++) {
            if (gameState.shots.get(i).getState() != SpriteState.Alive) continue;
            if (currEgg.intersect(gameState.shots.get(i))) {
                gameState.shots.get(i).setState(SpriteState.Dead);
                currEgg.setState(SpriteState.Dying);
                SoundPlayer.play("C:\\Program Files (x86)\\Microsoft Office\\Office12\\Groove\\Sounds\\Things\\WHOOSH_1.WAV");
                gameState.score += 10;
                break;
            }
        }
        if (currEgg.getState() == SpriteState.Alive) {
            if (currEgg.intersect(gameState.ship)) {
                gameState.lives--;
                currEgg.setState(SpriteState.Dying);
                if(gameState.lives==0) {
                    gameState.ship.setState(SpriteState.Dying);
                    gameState.levelState=LevelState.Lose;
                    gameState.stopGameFlag = true;
                }else gameState.ship.flash();
            }
        }
    }
}

