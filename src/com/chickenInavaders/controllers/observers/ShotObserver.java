package src.com.chickenInavaders.controllers.observers;
import src.com.chickenInavaders.Commons;
import src.com.chickenInavaders.controllers.SoundController;
import src.com.chickenInavaders.model.LevelState;
import src.com.chickenInavaders.model.SpriteState;
import src.com.chickenInavaders.model.GameState;
import src.com.chickenInavaders.model.sprites.Shot;
import java.util.Observable;
import java.util.Observer;

public class ShotObserver  implements Observer {
    GameState gameState;

    public ShotObserver(GameState gameState) {
        this.gameState=gameState;
    }

    @Override
    public void update(Observable o, Object arg) {
        Shot currShot = (Shot) o;
        if (currShot.getState() != SpriteState.Alive || currShot.position.y<-100) {
            gameState.shots.remove(currShot);
            return;
        }
        for (int i = 0; i < gameState.chickens.size(); i++) {
            if (gameState.chickens.get(i).getState() != SpriteState.Alive) continue;
            if (currShot.intersect(gameState.chickens.get(i))) {
                gameState.chickens.get(i).setState(SpriteState.Dying);
                SoundController.play(Commons.WHOOSH_SOUND);
                gameState.score += 20;
                currShot.setState(SpriteState.Dead);
                checkIfLevelCompleted();
                break;
            }
        }
    }

    private void checkIfLevelCompleted(){
        Object[] liveChickens = gameState.chickens.stream().filter(c->c.getState() == SpriteState.Alive).toArray();
        if(liveChickens.length == 0){
            gameState.levelState=LevelState.Win;
        }
    }
}