package src.com.chickenInavaders;

public interface Commons {
    // Change it for debug
    boolean IS_DEBUG = true;

    // int BOARD_WIDTH = 1024; // 358
    // int BOARD_HEIGHT = 768; // 350
    int BORDER_RIGHT = 30; // 30
    int BORDER_LEFT = 30; // 5

    int GROUND = Commons.BOARD_HEIGHT - (int) (Commons.BOARD_HEIGHT * 0.1);
    int BOMB_HEIGHT = 5;

    int CHICKEN_HEIGHT = 30; // 12
    int CHICKEN_WIDTH = 30; // 12
    int CHICKEN_INIT_X = 150;
    int CHICKEN_INIT_Y = 5;
    int CHICKEN_SPACE_BETWEEN = 30;

    int GO_DOWN = 15;
    int NUMBER_OF_ALIENS_TO_DESTROY = 24;
    int CHANCE = 5;
    int DELAY = 3; // 17

    int PLAYER_WIDTH = 18;
    int PLAYER_HEIGHT = 30;
    int PLAYER_X = (Commons.BOARD_WIDTH / 2) - (Commons.PLAYER_WIDTH / 2);
    int PLAYER_Y = Commons.GROUND - (Commons.PLAYER_HEIGHT * 2);
    // --------Json Files ------------------------
    String SETTINGS_FILE = "src/com/chickenInavaders/jsonFiles/Settings.json";

    String SAVES_FILE = "src/com/chickenInavaders/jsonFiles/Save.json";

    // ---------------- MainMenuP -------
    int BOARD_WIDTH = 600;
    int BOARD_HEIGHT = 800;
    String BACKGROUND_IMG = "src/com/chickenInavaders/images/SpaceBackGound.jpg";
    String MAIN_GAME_NAME_IMG = "src/com/chickenInavaders/images/MainMenu/MainWelcom.png";

    String START_GAME_IMG = "src/com/chickenInavaders/images/Buttons/StartGame.png";
    String START_GAME__HOVER_IMG = "src/com/chickenInavaders/images/Buttons/StartGameHover.png";

    String SETTINGS_IMG = "src/com/chickenInavaders/images/Buttons/Settings.png";
    String SETTINGS_HOVER_IMG = "src/com/chickenInavaders/images/Buttons/SettingsHover.png";

    String ABOUT_IMG = "src/com/chickenInavaders/images/Buttons/About.png";
    String ABOUT_HOVER_IMG = "src/com/chickenInavaders/images/Buttons/AboutHover.png";

    String EXIT_GAME_IMG = "src/com/chickenInavaders/images/Buttons/ExitGame.png";
    String EXIT_GAME_HOVER_IMG = "src/com/chickenInavaders/images/Buttons/ExitGameHover.png";
    // -----------------END -------------------------------------------
    // ---------------- MainMenuP --------
    String SETTINGS_BACKGROUND_IMG = "src/com/chickenInavaders/images/SettingsBackgrond.jpg";
    String BACK_IMG = "src/com/chickenInavaders/images/Buttons/back.png";
    String BACK_HOVER_IMG = "src/com/chickenInavaders/images/Buttons/BackHover.png";
    String SAVE_IMG = "src/com/chickenInavaders/images/Buttons/Save.png";
    String SAVE_HOVER_IMG = "src/com/chickenInavaders/images/Buttons/SaveHover.png";
    // -----------------END -------------------------------------------
    // ------------------------ StartGameP ---------------------------
    String LOAD_FILE = "src/com/chickenInavaders/jsonFiles/Save.json";
    String BACKGROUND_ONE_PLAYER_IMG = "src/com/chickenInavaders/images/ONE_PLAYER_IMG.jpg";
    String BACKGROUND_TWO_PLAYER_IMG = "src/com/chickenInavaders/images/TWO_PLAYER_IMG.jpg";
    String START_IMG = "src/com/chickenInavaders/images/Buttons/StartGame.png";
    String START_HOVER_IMG = "src/com/chickenInavaders/images/Buttons/StartGameHover.png";
    String LEVEL_1 = "src/com/chickenInavaders/images/Buttons/Level1.png";
    String LEVEL_2 = "src/com/chickenInavaders/images/Buttons/Level2.png";
    String LEVEL_3 = "src/com/chickenInavaders/images/Buttons/Level3.png";
    String LEVEL_1_HOVER = "src/com/chickenInavaders/images/Buttons/Level1Hover.png";
    String LEVEL_2_HOVER = "src/com/chickenInavaders/images/Buttons/Level2Hover.png";
    String LEVEL_3_HOVER = "src/com/chickenInavaders/images/Buttons/Level3Hover.png";
    String LEVEL_2_LOCK = "src/com/chickenInavaders/images/Buttons/Level2Lock.png";
    String LEVEL_3_LOCK = "src/com/chickenInavaders/images/Buttons/Level3Lock.png";
    String RED_SHIP = "src/com/chickenInavaders/images/shipRed.png";
    String BLUE_SHIP = "src/com/chickenInavaders/images/shipBlue.png";
    // -----------------END -------------------------------------------
    // ------------------------ AboutP ---------------------------
    String BACKGROUND_ABOUT_IMG = "src/com/chickenInavaders/images/About.jpg";
    String TXT_ABOUT_IMG = "src/com/chickenInavaders/images/Abouttest.png";

    // Sounds
    String CLICK_SOUND = "src/com/chickenInavaders/sounds/CLICK.WAV";
    String WHOOSH_SOUND = "src/com/chickenInavaders/sounds/WHOOSH.WAV";
    String EXPLODE_SOUND = "src/com/chickenInavaders/sounds/EXPLODE.WAV";
    // ------------------------ AboutP ---------------------------
    String END_GAME_BACKGOURND = "src/com/chickenInavaders/images/EngGameMenu.jpg";
}
