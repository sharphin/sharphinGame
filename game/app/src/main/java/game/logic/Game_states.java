package game.logic;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import game.util.GameUtil;

public class Game_states {
    private static String name;
    private static int hp;
    private static int hunger_level;
    private static int money;
    private static long bank_money;
    private static int luck;
    private static int mental;
    private static int health;
    private static int stamina;
    private static int likeAbility;
    private static int branch_state;
    private static int controll_state = 1;
    private static int inventory[];
    private static int item_strage[];
    //private static int like_abirity[];
    private static long debt;
    private static long loan;
    private static long item_dictionary[];
    private static String map_data_path;
    private static LocalDateTime today;
    public Game_states(String newname) {
        name = newname;
        hp = 2500;
        hunger_level = 500;
        money = 10000;
        luck = 120;
        mental = 400;
        health = 500;
        stamina = 1000;
        likeAbility = 1000;
        debt = 100;
        loan = 200;
        bank_money = init_bank_money(newname);
        inventory = new int[8];
        //like_abirity = new int[64];
        item_strage = new int[255];
        item_dictionary = init_item_dict(newname);
        today = LocalDateTime.now();
        branch_state = 0;
        controll_state = 1;
        map_data_path = "gamedata/map_data";
    }
    public Game_states(String loadname,String filepath, int[] intadata, long[] longdata, int loadinventory[], long itemDict[], int itemStrage[]) {
        name = loadname;
        today = unixTimeToLocalDateTime(longdata[0]);
        hp = intadata[0];
        hunger_level = intadata[1];
        money = intadata[2];
        bank_money = intadata[3];
        branch_state = intadata[4];
        luck = intadata[5];
        mental = intadata[6];
        health = intadata[7];
        stamina = intadata[8];
        debt = longdata[1];
        loan = longdata[2];
        inventory = loadinventory;
        item_dictionary = itemDict;
        item_strage = itemStrage;
        controll_state = 1;
        map_data_path = new StringBuilder().append("savedata/").append(filepath).toString();
    }
    public static String getName(){
        return name;
    }
    public static int[] getAllInventory() {
        return inventory;
    }
    public static int getInventory(int index) {
        if(index >= GameUtil.MAX_ITEM) return -1;
        return inventory[index];
    }
    static void updateItemDictionary(int i) {
        int area = i>>6;
        int shift = i-(area<<6);
        if(i >= GameUtil.MAX_ALL_ITEMS) return;
        item_dictionary[area] = item_dictionary[area] | (long)(1<<shift);
    }
    public static long[] getAllItemDictionary() {
        return item_dictionary;
    }
    public static int searchItemDictionary(int i) {
        int area = i>>6;
        int shift = i-(area<<6);
        if(i >= GameUtil.MAX_ALL_ITEMS) return -2;
        if(Long.bitCount(item_dictionary[area] & (long)(1<<shift)) == 0) return -1;
        return i;
    }
    static void updateItemStrage(int index) {
        if(index >= GameUtil.MAX_ALL_ITEMS) return;
        item_strage[index]++;
    }
    public static int[] getAllItemStrage() {
        return item_strage;
    }
    public static int getItemStrage(int index) {
        if(index >= GameUtil.MAX_ALL_ITEMS) return -1;
        return item_strage[index];
    }
    static int addInventory(int item, int index) {
        int result = 0;
        if(index >= GameUtil.MAX_ITEM  || index < 0) return -1;
        if(inventory[index] != 0) result = inventory[index];
        inventory[index] = item;
        return result;
    }
    static int inventoryswap(int index1, int index2) {
        if(index1 >= GameUtil.MAX_ITEM  || index1 < 0) return -1;
        if(index2 >= GameUtil.MAX_ITEM  || index2 < 0) return -1;
        int temp = inventory[index1];
        inventory[index1] = inventory[index2];
        inventory[index2] = temp;
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
    public static long getBank_money() {
        return bank_money;
    }
    static void updateBank_money(int new_bank_money) {
        bank_money = new_bank_money;
    }
    public static LocalDateTime getTodayTime() {
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
    public static int getLuck() {
        return luck;
    }
    static void updateLuck(int newLuck) {
        luck = newLuck > GameUtil.MAX_LUCK ? GameUtil.MAX_LUCK : newLuck;
    }
    public static int getMental() {
        return mental;
    }
    static void updateMental(int newMental) {
        mental = newMental > GameUtil.MAX_MENTAL ? GameUtil.MAX_MENTAL : newMental;
    }
    public static int getHealth() {
        return health;
    }
    static void updateHealth(int newMHealth) {
        health = newMHealth > GameUtil.MAX_HEALTH ? GameUtil.MAX_HEALTH : newMHealth;
    }
    public static int getStamina() {
        return stamina;
    }
    static void updateStamina(int newStamina) {
        stamina = newStamina > GameUtil.MAX_STAMINA ? GameUtil.MAX_STAMINA : newStamina;
    }
    public static int getLikeAbility() {
        return likeAbility;
    }
    static void updateLikeAbility(int newLikeAbility) {
        stamina = newLikeAbility > GameUtil.MAX_LIKELABILITY ? GameUtil.MAX_LIKELABILITY : newLikeAbility;
    }
    public static long getDebt() {
        return debt;
    }
    static void updateDebt(int newDebt) {
        debt = newDebt > GameUtil.MAX_DEBT ? GameUtil.MAX_DEBT : newDebt;
    }
    public static long getLoan() {
        return loan;
    }
    static void updateLoan(int newLoan) {
        loan = newLoan > GameUtil.MAX_LOAN ? GameUtil.MAX_LOAN : newLoan;
    }
    private LocalDateTime unixTimeToLocalDateTime(long unixtime) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(unixtime), ZoneId.systemDefault());
    }
    private long[] init_item_dict(String name) {
        long [] tmp = new long[4];
        if(name.equals("COMPLETE")) {
            tmp[0] = Long.MAX_VALUE;
            tmp[1] = Long.MAX_VALUE;
            tmp[2] = Long.MAX_VALUE;
            tmp[3] = Long.MAX_VALUE;
        } 
        return tmp;
    }
    private long init_bank_money(String name) {
        return switch(name) {
            case "RICH" -> Long.MAX_VALUE;
            case "POOR" -> 10000;
            default -> 200000;
        };
    }
}
