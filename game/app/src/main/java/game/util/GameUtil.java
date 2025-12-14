package game.util;

public class GameUtil {
    public static final int TILE = 32;
    public static final int FRAME_X = 716;
    public static final int FRAME_Y = 539;
    public static final int PANEL_X = 700;
    public static final int PANEL_Y = 500;
    public static final int MAP_X_LEN = 34;
    public static final int MAP_Y_LEN = 34;
    public static final int MAX_MAP_KIND = 64;
    public static final int WALK = 4;
    public static final int DASH = 6;
    public static final int MAX_ITEM = 8;
    public static final int MAX_SAVE_SLOT = 8;
    public static final int MAX_ALL_ITEMS = 255;
    
    //操作ステート
    public static final int PROLOGUE =   0b00000000000;
    public static final int PLAY =       0b00000000001;
    public static final int MENU =       0b00000000010;
    public static final int POSE =       0b00000000100;
    public static final int SAVE =       0b00000001000;
    public static final int INVENTORY =  0b00000010000;
    public static final int TALK      =  0b00000100000;
    public static final int PC =         0b00001000000;
    public static final int ITEM_DELETE =0b00010000000;
    public static final int GAME_EXIT =  0b01000000000;
    public static final int DEBUG =     0b010000000000;
    public static final int GAME_CLEAR =0b010000000000000000;

    public static final int MAX_X = 20000;
    public static final int MAX_Y = 20000;
    public static final int MAX_MAP_LEN = 625;
    public static final String FILE_PATH = "";
    //public static final String FILE_PATH = "/Program Files/Revenge/app/";
}
