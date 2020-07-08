package src.com.chickenInavaders;

import src.com.chickenInavaders.controllers.GameController;
import src.com.chickenInavaders.model.LevelState;
import src.com.chickenInavaders.view.LayoutManager;

import java.util.Observable;
import java.util.Observer;


public class GameObserver implements Observer {
    public LayoutManager panelGraph;
    private GameController gameUI;

    public GameObserver(LayoutManager l) {
        panelGraph = l;
    }

    ;

    public GameObserver() {
    }

    ;

    @Override
    public void update(Observable o, Object arg) {
        gameUI = panelGraph.gameUI;
        LevelState levelState = (LevelState) arg;
        switch (levelState) {
            case Win:
                winGame();
                break;
            case Lose:
                loseGame();
                break;
            case Started:
                if (Commons.IS_DEBUG)
                    System.out.println("started Level " + gameUI.gameState.level);
                break;
        }
    }

    private void winGame() {
        int maxLevel = gameUI.gameState.Gamelevel.maxLevel;
        if (maxLevel == gameUI.gameState.level) {
            saveGame();
            gameUI.paintGameWin(gameUI.getGraphics());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gameUI.gameState.timer.stop();
            panelGraph.cardLayout.show(panelGraph.cardPane, "MainMenu");
        } else {
            int tickNum = gameUI.gameState.Gamelevel.levelList.get(gameUI.gameState.level + 1).tick;
            int eggIntervalNum = gameUI.gameState.Gamelevel.levelList.get(gameUI.gameState.level + 1).eggInterval;
            gameUI.gameState.timer.stop();
            gameUI.changeLevel(gameUI.gameState.level + 1, gameUI.gameState.lives, tickNum, eggIntervalNum, gameUI.gameState.score);
        }
    }

    private void loseGame() {
        saveGame();
        panelGraph.endGameP.setScore(Integer.toString(gameUI.gameState.score));
        panelGraph.cardLayout.show(panelGraph.cardPane, "EndGameMenu");

    }

    private void saveGame() {
        if (panelGraph.startGameP.Playerid == null) {
            panelGraph.gameSaves.addRecord(panelGraph.startGameP.getPName(), gameUI.gameState.score, gameUI.gameState.level);
        } else {
            String id = panelGraph.startGameP.Playerid;
            if (gameUI.gameState.score > panelGraph.gameSaves.LoadsList.get(Integer.parseInt(id)).score)
                panelGraph.gameSaves.updateRecord(Integer.parseInt(id), "Score", gameUI.gameState.score);
            if (gameUI.gameState.level > panelGraph.gameSaves.LoadsList.get(Integer.parseInt(id)).level)
                panelGraph.gameSaves.updateRecord(Integer.parseInt(id), "Level", gameUI.gameState.level);

        }
        panelGraph.startGameP.loadSaves();
    }
}