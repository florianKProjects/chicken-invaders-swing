package src.com.chickenInavaders;

public interface Commons {
    // Change it for debug
    boolean IS_DEBUG = false;


    //int BOARD_WIDTH = 1024; // 358
    //int BOARD_HEIGHT = 768; // 350
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
    //--------Json Files ------------------------
    String SETTINGS_FILE = "src/com/chickenInavaders/resources/data/Settings.json";

    String SAVES_FILE = "src/com/chickenInavaders/resources/data/Save.json";
    //------------------Ship-------------------
    String RED_SHIP_GAME = "src/com/chickenInavaders/resources/assets/images/red_ship.png";
    String BLUE_SHIP_GAME = "src/com/chickenInavaders/resources/assets/images/ship.png";
    String BOOM_SHIP = "src/com/chickenInavaders/resources/assets/images/boom.png";
    //---------------- MainMenuP -------
    int BOARD_WIDTH = 600;
    int BOARD_HEIGHT = 800;
    String BACKGROUND_IMG = "src/com/chickenInavaders/resources/assets/images/SpaceBackGound.jpg";
    String MAIN_GAME_NAME_IMG = "src/com/chickenInavaders/resources/assets/images/MainMenu/MainWelcom.png";

    String START_GAME_IMG = "src/com/chickenInavaders/resources/assets/images/Buttons/StartGame.png";
    String START_GAME__HOVER_IMG = "src/com/chickenInavaders/resources/assets/images/Buttons/StartGameHover.png";

    String SETTINGS_IMG = "src/com/chickenInavaders/resources/assets/images/Buttons/Settings.png";
    String SETTINGS_HOVER_IMG = "src/com/chickenInavaders/resources/assets/images/Buttons/SettingsHover.png";

    String ABOUT_IMG = "src/com/chickenInavaders/resources/assets/images/Buttons/About.png";
    String ABOUT_HOVER_IMG = "src/com/chickenInavaders/resources/assets/images/Buttons/AboutHover.png";

    String EXIT_GAME_IMG = "src/com/chickenInavaders/resources/assets/images/Buttons/ExitGame.png";
    String EXIT_GAME_HOVER_IMG = "src/com/chickenInavaders/resources/assets/images/Buttons/ExitGameHover.png";
    //-----------------END -------------------------------------------
    //---------------- MainMenuP --------
    String SETTINGS_BACKGROUND_IMG = "src/com/chickenInavaders/resources/assets/images/SettingsBackgrond.jpg";
    String BACK_IMG = "src/com/chickenInavaders/resources/assets/images/Buttons/back.png";
    String BACK_HOVER_IMG = "src/com/chickenInavaders/resources/assets/images/Buttons/BackHover.png";
    String SAVE_IMG= "src/com/chickenInavaders/resources/assets/images/Buttons/Save.png";
    String SAVE_HOVER_IMG= "src/com/chickenInavaders/resources/assets/images/Buttons/SaveHover.png";
    //-----------------END -------------------------------------------
    //------------------------ StartGameP ---------------------------
    String LOAD_FILE = "src/com/chickenInavaders/resources/data/Save.json";
    String BACKGROUND_ONE_PLAYER_IMG = "src/com/chickenInavaders/resources/assets/images/ONE_PLAYER_IMG.jpg";
    String BACKGROUND_TWO_PLAYER_IMG = "src/com/chickenInavaders/resources/assets/images/TWO_PLAYER_IMG.jpg";
    String START_IMG = "src/com/chickenInavaders/resources/assets/images/Buttons/StartGame.png";
    String START_HOVER_IMG = "src/com/chickenInavaders/resources/assets/images/Buttons/StartGameHover.png";
    String LEVEL_1 = "src/com/chickenInavaders/resources/assets/images/Buttons/Level1.png";
    String LEVEL_2 = "src/com/chickenInavaders/resources/assets/images/Buttons/Level2.png";
    String LEVEL_3 = "src/com/chickenInavaders/resources/assets/images/Buttons/Level3.png";
    String LEVEL_1_HOVER = "src/com/chickenInavaders/resources/assets/images/Buttons/Level1Hover.png";
    String LEVEL_2_HOVER = "src/com/chickenInavaders/resources/assets/images/Buttons/Level2Hover.png";
    String LEVEL_3_HOVER = "src/com/chickenInavaders/resources/assets/images/Buttons/Level3Hover.png";
    String LEVEL_2_LOCK = "src/com/chickenInavaders/resources/assets/images/Buttons/Level2Lock.png";
    String LEVEL_3_LOCK = "src/com/chickenInavaders/resources/assets/images/Buttons/Level3Lock.png";
    String RED_SHIP = "src/com/chickenInavaders/resources/assets/images/shipRed.png";
    String BLUE_SHIP = "src/com/chickenInavaders/resources/assets/images/shipBlue.png";
    //-----------------END -------------------------------------------
    //------------------------ AboutP ---------------------------
    String BACKGROUND_ABOUT_IMG = "src/com/chickenInavaders/resources/assets/images/About.jpg";
    String TXT_ABOUT_IMG = "src/com/chickenInavaders/resources/assets/images/Abouttest.png";

    //Sounds
    String CLICK_SOUND = "src/com/chickenInavaders/resources/assets/sounds/CLICK.WAV";
    String WHOOSH_SOUND = "src/com/chickenInavaders/resources/assets/sounds/WHOOSH.WAV";
    String EXPLODE_SOUND = "src/com/chickenInavaders/resources/assets/sounds/EXPLODE.WAV";
    String CRACKED_SOUND = "src/com/chickenInavaders/resources/assets/sounds/CRACK.WAV";
    String BUZZ_SOUND = "src/com/chickenInavaders/resources/assets/sounds/BUZZ.WAV";
    //------------------------ AboutP ---------------------------
    String END_GAME_BACKGOURND = "src/com/chickenInavaders/resources/assets/images/EngGameMenu.jpg";
    //-------------------GAME PAUSE P ----------------------------------------
    String GAME_PAUSE_IMAGE = "src/com/chickenInavaders/resources/assets/images/PausedImage.jpg";
    String EXIT_IMAGE = "src/com/chickenInavaders/resources/assets/images/Buttons/ExitImg.png";
    String RESTART_IMG = "src/com/chickenInavaders/resources/assets/images/Buttons/RestartImg.png";
    String RESUME_IMAGE = "src/com/chickenInavaders/resources/assets/images/Buttons/ResumeImg.png";
}
