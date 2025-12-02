package util;

public class GameUtil {
    public static final int TILE = 32;
    public static final int FRAME_X = 716;
    public static final int FRAME_Y = 539;
    public static final int PANEL_X = 700;
    public static final int PANEL_Y = 500;
    public static final int MAP_X_LEN = 32;
    public static final int MAP_Y_LEN = 32;
    public static final int WALK = 4;
    public static final int DASH = 8;
    public static final int MAX_ITEM = 8;
    public static final int MAX_SAVE_SLOT = 8;
    
    public static final int MAX_HP = 2500;
    public static final int MAX_HUNGER_LEVEL = 1000;
    public static final int MAX_MONEY = 1000000;
    public static final int MAX_LUCK = 25;
    public static final int MAX_MENTAL = 500;
    public static final int MAX_HEALTH = 500;
    public static final int MAX_IQ = 1000000;
    public static final int MAX_STAMINA = 1000;
    public static final int MAX_LIKELABILITY = 1000;
    public static final long MAX_DEBT = Long.MAX_VALUE;
    public static final long MAX_LOAN = Long.MAX_VALUE;
    public static final long MAX_BANK_MONEY = Long.MAX_VALUE;
    //操作ステート
    public static final int PLAY = 0b000000001;
    public static final int MENU = 0b000000010;
    public static final int POSE = 0b000000100;
    public static final int SAVE = 0b000001000;
    public static final int INVENTORY = 0b000010000;
    public static final int GAME_EXIT = 0b1000000000;
}
