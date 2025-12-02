package logic;
import save_load.Load;
import util.FormatUtil;
import util.GameUtil;

import java.time.LocalDateTime;

public class Game_states {
    private static String name;
    private static int hp;
    private static int hunger_level;
    private static int money;
    private static int bank_money;
    private static int items[];
    private static LocalDateTime today;
    private static int branch_state;
    private static int controll_state = 1;
    private static String map_data_path;

    public Game_states(String newname) {
        name = newname;
        hp = 2500;
        hunger_level = 500;
        money = 10000;
        bank_money = 200000;
        items = new int[8];
        today = LocalDateTime.now();
        branch_state = 0;
        controll_state = 1;
        map_data_path = "RPG/gamedata/map_data";
    }
    public Game_states(int index) {
        Load load = new Load();
        String str[] = load.gameLoad(index).split(",");
        name = str[0];
        today = LocalDateTime.parse(str[1],FormatUtil.format1);
        hp = Integer.parseInt(str[4]);
        hunger_level = Integer.parseInt(str[5]);
        money = Integer.parseInt(str[6]);
        bank_money = Integer.parseInt(str[7]);
        branch_state = Integer.parseInt(str[8]);
        controll_state = 1;
        map_data_path = str[10];
        items = load.items_decryption(Long.parseLong(str[11]));
    }
    public static String getName(){
        return name;
    }
    public static int[] getAllItem() {
        return items;
    }
    public static int getItem(int index) {
        if(index >= GameUtil.MAX_ITEM) return -1;
        return items[index];
    }
    static int addItem(int item, int index) {
        int result = 0;
        if(index >= GameUtil.MAX_ITEM  || index < 0) return -1;
        if(items[index] != 0) result = items[index];
        items[index] = item;
        return result;
    }
    static int itemSwap(int index1, int index2) {
        if(index1 >= GameUtil.MAX_ITEM  || index1 < 0) return -1;
        if(index2 >= GameUtil.MAX_ITEM  || index2 < 0) return -1;
        int temp = items[index1];
        items[index1] = items[index2];
        items[index2] = temp;
        return 0;
    }
    public static int getHP() {
        return hp;
    }
    static void updateHP(int new_hp) {
        hp = new_hp;
    }
    public static int getHunger_level() {
        return hunger_level;
    }
    static void updateHunger_level(int new_hunger_level) {
        hunger_level = new_hunger_level;
    }
    public static int getMoney() {
        return money;
    }
    static void updateHMoney(int new_money) {
        money = new_money;
    }
    public static int getBank_money() {
        return bank_money;
    }
    static void updateBank_money(int new_bank_money) {
        bank_money = new_bank_money;
    }
    public static LocalDateTime getTODAY() {
        return today;
    }
    public static int getBranch_state() {
        return branch_state;
    }
    static void updateBranch_state(int new_branch_state) {
        branch_state = new_branch_state;
    }
    public static int getControll_state() {
        return controll_state;
    }
    public static void updateControll_state(int new_controll_state) {
        controll_state = new_controll_state;
    }
    public static String getMapDataPath() {
        return map_data_path;
    }
}
