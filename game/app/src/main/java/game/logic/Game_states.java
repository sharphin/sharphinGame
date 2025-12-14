package game.logic;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.ThreadLocalRandom;

import game.util.GameUtil;

public class Game_states {
    private static String name;
    private static int hunger_level;
    private static int money;
    private static int mental;
    private static int stamina;
    private static int route_branch;
    private static int controll_state = 1;
    private static int inventory[];
    private static long item_dictionary[];
    private static String map_data_path;
    private static LocalDateTime today;
    private static int pc_password;
    public Game_states(String newname, int sportsPerWeek, int sportsPerDay) {
        name = newname;
        money = 100000;
        mental = 500;
        stamina = calc_stamina(sportsPerWeek, sportsPerDay);
        inventory = new int[8];
        item_dictionary = init_item_dict(newname);
        today = LocalDateTime.now();
        route_branch = 0;
        controll_state = 17;
        map_data_path = "gamedata/map_data";
        pc_password = gen_pc_password();
    }
    public Game_states(String loadname,String filepath, int[] intadata, long longdata, int loadinventory[], long itemDict[]) {
        name = loadname;
        today = unixTimeToLocalDateTime(longdata);
        hunger_level = intadata[0];
        money = intadata[1];
        route_branch = intadata[2];
        mental = intadata[3];
        stamina = intadata[4];
        inventory = loadinventory;
        item_dictionary = itemDict;
        controll_state = 17;
        map_data_path = new StringBuilder().append(System.getProperty("user.home")+ "/AppData/Local/Revenge/savedata/").append(filepath).toString();
        pc_password = intadata[5];
        System.out.println(pc_password);
    }
    public static int getPCPassword() {
        return pc_password;
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
        if(i > GameUtil.MAX_ALL_ITEMS) return -2;
        if(Long.bitCount(item_dictionary[area] & (long)(1<<shift)) == 0) return -1;
        return i;
    }
    static int addInventory(int item) {
        int emptyi = -1;
        for(int i = 0; i < inventory.length; i++) {
            if(inventory[i] == 0) {
                emptyi = i;
                break;
            }
        }
        if(emptyi == -1) return -1;
        inventory[emptyi] = item;
        return emptyi;
    }
    static int inventoryswap(int index1, int index2) {
        if(index1 >= GameUtil.MAX_ITEM  || index1 < 0) return -1;
        if(index2 >= GameUtil.MAX_ITEM  || index2 < 0) return -1;
        int temp = inventory[index1];
        inventory[index1] = inventory[index2];
        inventory[index2] = temp;
        return 0;
    }
    static int inventoryremove(int index) {
        if(index >= GameUtil.MAX_ITEM  || index < 0) return -1;
        inventory[index] = 0;
        return 0;
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
    public static LocalDateTime getTodayTime() {
        return today;
    }
    public static int getRoute_branch() {
        return route_branch;
    }
    static void updateRoute_branch(int new_route_branch) {
        route_branch = new_route_branch;
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
    public static int getMental() {
        return mental;
    }
    static void updateMental(int newMental) {
        mental = newMental;
    }
    public static int getStamina() {
        return stamina;
    }
    static void updateStamina(int newStamina) {
        stamina = newStamina;
    }
    static void updateLikeAbility(int newLikeAbility) {
        stamina = newLikeAbility;
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
    public static int gen_pc_password() {
        ThreadLocalRandom rand  = ThreadLocalRandom.current();
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < 6; i++) {
            int randnum = rand.nextInt(10);
            sb.append(randnum);
        }
        System.out.println(sb.toString());
        return Integer.parseInt(sb.toString());
    }
    private int calc_stamina(int sportsPerWeek, int sportsPerDay) {
        return 100*sportsPerWeek*sportsPerDay;
    }
}
