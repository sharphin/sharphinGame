package logic;

import java.util.List;

import save_load.Load;
import util.GameUtil;

import java.time.LocalDate;
import java.util.ArrayList;

public class Game_states {
    private static String name;
    private static int hp;
    private static int hunger_level;
    private static int money;
    private static int bank_money;
    private static List<String> items;
    private static LocalDate today;
    private static int branch_state;
    private static int controll_state = 1;
    private static String map_data_path;

    public Game_states(String newname) {
        name = newname;
        hp = 2500;
        hunger_level = 500;
        money = 10000;
        bank_money = 200000;
        items = new ArrayList<String>();
        today = LocalDate.now();
        branch_state = 0;
        controll_state = 1;
        map_data_path = "RPG/gamedata/map";
    }
    public Game_states(int index) {
        Load load = new Load();
        String str[] = load.gameLoad(index).split(",");
        name = str[0];
        today = LocalDate.parse(str[1]);
        hp = Integer.parseInt(str[4]);
        hunger_level = Integer.parseInt(str[5]);
        money = Integer.parseInt(str[6]);
        bank_money = Integer.parseInt(str[7]);
        branch_state = Integer.parseInt(str[8]);
        controll_state = 1;
        map_data_path = str[10];
        items = new ArrayList<String>();
        if(str.length <= 10) return;
        for(int i = 10; i< str.length; i++) {
            if(str[i].equals("")) break;
            items.add(str[i]);
        }
    }
    public static String getName(){
        return name;
    }
    public static List<String> getAllItem() {
        return items;
    }
    public static String getItem(int index) {
        if(index >= GameUtil.MAX_ITEM) return "error";
        return items.get(index);
    }
    static void addItem(String item, int x, int y) {
        if(items.size()>= GameUtil.MAX_ITEM) {

        } else {
            items.add(item);
        }
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
    public static LocalDate getTODAY() {
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
