package src.com.chickenInavaders.controllers;

import src.com.chickenInavaders.model.SpriteState;
import src.com.chickenInavaders.model.GameState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TickController implements ActionListener {
    GameController gameUI;

    public TickController(GameController gameUI){
        this.gameUI = gameUI;
    }

    private void moveEggs(){
        GameState gameState = gameUI.gameState;
        gameState.eggs.forEach(egg->{
            if(egg.getState()== SpriteState.Alive)
                egg.position.y++;
        });
    }

    private void createEgg(){
        GameState gameState = gameUI.gameState;
        gameState.eggFrames--;
        if(gameState.eggFrames==0){
            gameState.eggFrames = gameState.eggInterval;
            gameUI.createEgg();
        }
    }

    private void moveShots(){
        GameState gameState = gameUI.gameState;
        gameState.shots.forEach(shot->{
            if(shot.getState()==SpriteState.Alive)
                shot.position.y-=2;
        });
    }

    private void moveChickens(){
        int step = 1;
        int side =  gameUI.getWidth();
        GameState gameState = gameUI.gameState;
        gameState.chickens.forEach(chickens->{
            if(chickens.position.x == -60)
                chickens.position.x += side+60;
            if(chickens.position.x == 660)
                chickens.position.x -= (side+120);
        });

        for(int i = 0 ;i < 40; i++){
            if(i % 8 == 0) step = -step;
            gameState.chickens.get(i).position.x+=step;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        createEgg();
        moveEggs();
        moveShots();
        moveChickens();
        gameUI.repaint();
    }
}
