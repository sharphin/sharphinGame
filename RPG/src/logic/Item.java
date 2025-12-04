package logic;

import util.GameUtil;

public class Item {
//０は空欄
//アイテム数上限255種類
    private static int item_dict[] = new int[GameUtil.MAX_ALL_ITEMS];
    public Item() {
        item_dict[0] = 1;
    }

    public int[] getItem_dictionary() {
        return item_dict;
    }
    public String item_name(int index) {
        //ファイルから読み込むか検討
        return switch(item_dict[index]) {
            case 1 -> "バスケットボール";
            case 2 -> "OPHONE";
            default -> "???";
        };
    }
}
