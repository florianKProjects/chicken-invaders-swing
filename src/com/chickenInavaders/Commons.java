package com.chickenInavaders;

public interface Commons {

    int BOARD_WIDTH = 1024; // 358
    int BOARD_HEIGHT = 768; // 350
    int BORDER_RIGHT = 30;  // 30
    int BORDER_LEFT = 30;    // 5

    int GROUND = Commons.BOARD_HEIGHT - (int)(Commons.BOARD_HEIGHT * 0.1);
    int BOMB_HEIGHT = 5;

    int CHICKEN_HEIGHT = 30; //12
    int CHICKEN_WIDTH = 30;  //12
    int CHICKEN_INIT_X = 150;
    int CHICKEN_INIT_Y = 5;
    int CHICKEN_SPACE_BETWEEN = 30;

    int GO_DOWN = 15;
    int NUMBER_OF_ALIENS_TO_DESTROY = 24;
    int CHANCE = 5;
    int DELAY = 3; //17

    int PLAYER_WIDTH = 18;
    int PLAYER_HEIGHT = 30;
    int PLAYER_X = (Commons.BOARD_WIDTH / 2) - (Commons.PLAYER_WIDTH / 2);
    int PLAYER_Y = Commons.GROUND - (Commons.PLAYER_HEIGHT * 2);
}
