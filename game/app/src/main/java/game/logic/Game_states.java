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
    private static int bank_money;
    private static int luck;
    private static int mental;
    private static int health;
    private static int stamina;
    private static int likeAbility;
    private static long debt;
    private static long loan;

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
        luck = 120;
        mental = 400;
        health = 500;
        stamina = 1000;
        likeAbility = 1000;
        debt = 100;
        loan = 200;
        bank_money = 200000;
        items = new int[8];
        today = LocalDateTime.now();
        branch_state = 0;
        controll_state = 1;
        map_data_path = "gamedata/map_data";
    }
    public Game_states(String loadname,String filepath, int[] intadata, long[] longdata, int[]item_list) {
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
        items = item_list;
        controll_state = 1;
        map_data_path = new StringBuilder().append("savedata/").append(filepath).toString();
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
}
