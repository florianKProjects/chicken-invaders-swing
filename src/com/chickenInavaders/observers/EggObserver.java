package src.com.chickenInavaders.observers;

import src.com.chickenInavaders.Commons;
import src.com.chickenInavaders.SoundPlayer;
import src.com.chickenInavaders.gameui.GameState;
import src.com.chickenInavaders.gameui.LevelState;
import src.com.chickenInavaders.sprites.Egg;
import src.com.chickenInavaders.sprites.SpriteState;

import java.util.Observable;
import java.util.Observer;

public class EggObserver implements Observer {
    GameState gameState;

    public EggObserver(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void update(Observable o, Object arg) {

        if (gameState.stopGameFlag) {
            if (gameState.stopGameFrames == 0) {
                gameState.timer.stop();
                gameState.stopGameFrames = 10;
                SoundPlayer.play(Commons.EXPLODE_SOUND);
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
            if (gameState.shots.get(i).getState() != SpriteState.Alive)
                continue;
            if (currEgg.intersect(gameState.shots.get(i))) {
                gameState.shots.get(i).setState(SpriteState.Dead);
                currEgg.setState(SpriteState.Dying);
                SoundPlayer.play(Commons.WHOOSH_SOUND);
                gameState.score += 10;
                break;
            }
        }
        if (currEgg.getState() == SpriteState.Alive) {
            if (currEgg.intersect(gameState.ship)) {
                gameState.lives--;
                currEgg.setState(SpriteState.Dying);
                if (gameState.lives == 0) {
                    gameState.ship.setState(SpriteState.Dying);
                    gameState.levelState = LevelState.Lose;
                    gameState.stopGameFlag = true;
                } else
                    gameState.ship.flash();
            }
        }
    }
}
