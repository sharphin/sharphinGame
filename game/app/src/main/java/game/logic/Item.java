package game.logic;

import game.util.GameUtil;

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
        return switch(Game_states.searchItemDictionary(index)) {
            case 0 -> "バスケットボール";
            case 1 -> "OPHONE";
            case 2 -> "漫画";
            case 3 -> "PC";
            case 4 -> "コップ";
            case 5 -> "ギター";
            case 6 -> "箸";
            case 7 -> "A";
            case 8 -> "B";
            case 19 -> "C";
            case 20 -> "D";
            case 21 -> "E";
            case 22 -> "F";
            case 23 -> "G";
            case 24 -> "H";
            case 25 -> "I";
            case 26 -> "J";
            case 27 -> "K";
            case 28 -> "L";
            case 29 -> "M";
            case 30 -> "N";
            case 31 -> "O";
            case 32 -> "P";
            case 33 -> "Q";
            case 34 -> "R";
            case 35 -> "S";
            case 36 -> "T";
            case 37 -> "U";
            case 38 -> "V";
            case 39 -> "W";
            case 40 -> "X";
            case 41 -> "Y";
            case 42 -> "Z";
            case 43 -> "43";
            case 44 -> "44";
            case 45 -> "45";
            case 46 -> "46";
            case 47 -> "47";
            case 48 -> "48";
            case 49 -> "49";
            case 50 -> "50";
            case 51 -> "51";
            case 52 -> "52";
            case 53 -> "53";
            case 54 -> "54";
            case 55 -> "55";
            case 56 -> "56";
            case 57 -> "57";
            case 58 -> "58";
            case 59 -> "59";
            case 60 -> "60";
            case 61 -> "61";
            case 62 -> "62";
            case 63 -> "63";
            case 64 -> "64";
            case 65 -> "65";
            case 66 -> "66";
            case 67 -> "67";
            case 68 -> "68";
            case 69 -> "69";
            default -> "???";
        };
    }
}
