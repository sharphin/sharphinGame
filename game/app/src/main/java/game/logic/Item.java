package game.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    public void setItemDictionary(int item) {
        Game_states.updateItemDictionary(item);
    }
    public int setInventory(int item,int index) {
       if(Game_states.searchItemDictionary(item)== -1)setItemDictionary(item);
        return Game_states.addInventory(item);
    }
    public void removeInventory(int index) {
        Game_states.inventoryremove(index);
    }
    public void swapInventory() {}
    public static boolean key_check(int key, int lock) {
        if(key == 14) return true;
        switch (lock) {
            case 4194338: if(key == 7) return true; break;
            case 4194418: if(key == 15) return true; break;
            case 5999998: if(key == 13) return true; break;
        }
        return false;
    }
    public String[] loadexplain() {
        List<String> datas = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(GameUtil.FILE_PATH+"gamedata/item_data/items.dat"))) {
            String data;
            while ((data = br.readLine()) != null) {
                datas.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datas.toArray(new String[datas.size()]);
    }
    public String item_name(int index) {
        return switch(Game_states.searchItemDictionary(index)) {
            case 1 -> "バスケットボール";
            case 2 -> "漫画";
            case 3 -> "iPhone";
            case 4 -> "コップ";
            case 5 -> "ギター";
            case 6 -> "箸";
            case 7 -> "ROOMKEY_A";
            case 8 -> "ROOMKEY_B";
            case 9 -> "ROOMKEY_C";
            case 10 -> "ROOMKEY_D";
            case 11 -> "ROOMKEY_E";
            case 12 -> "ROOMKEY_F";
            case 13 -> "ENTRANCEKEY";
            case 14 -> "MASTERKEY";
            case 15 -> "BASEMENTKEY";
            case 16 -> "メモ_A";
            case 17 -> "手帳";
            case 18 -> "カレンダー";
            case 19 -> "メモ_B";
            case 20 -> "卒業アルバム";
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
            case 38 -> "卒業アルバム";
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
            case 70 -> "70";
            case 71 -> "71";
            case 72 -> "72";
            case 73 -> "73";
            case 74 -> "74";
            case 75 -> "75";
            case 76 -> "76";
            case 77 -> "77";
            case 78 -> "78";
            case 79 -> "79";
            case 80 -> "80";
            case 81 -> "81";
            case 82 -> "82";
            case 83 -> "83";
            case 84 -> "84";
            case 85 -> "85";
            case 86 -> "86";
            case 87 -> "87";
            case 88 -> "88";
            case 89 -> "89";
            case 90 -> "90";
            case 91 -> "91";
            case 92 -> "92";
            case 93 -> "93";
            case 94 -> "94";
            case 95 -> "95";
            case 96 -> "96";
            case 97 -> "97";
            case 98 -> "98";
            case 99 -> "99";
            case 100 -> "100";
            case 101 -> "101";
            case 102 -> "102";
            case 103 -> "103";
            case 104 -> "104";
            case 105 -> "105";
            case 106 -> "106";
            case 107 -> "107";
            case 108 -> "108";
            case 109 -> "109";
            case 110 -> "110";
            case 111 -> "111";
            case 112 -> "112";
            case 113 -> "113";
            case 114 -> "114";
            case 115 -> "115";
            case 116 -> "116";
            case 117 -> "117";
            case 118 -> "118";
            case 119 -> "119";
            case 120 -> "120";
            case 121 -> "121";
            case 122 -> "122";
            case 123 -> "123";
            case 124 -> "124";
            case 125 -> "125";
            case 126 -> "126";
            case 127 -> "127";
            case 128 -> "128";
            case 129 -> "129";
            case 130 -> "130";
            case 131 -> "131";
            case 132 -> "132";
            case 133 -> "133";
            case 134 -> "134";
            case 135 -> "135";
            case 136 -> "136";
            case 137 -> "137";
            case 138 -> "138";
            case 139 -> "139";
            case 140 -> "140";
            case 141 -> "141";
            case 142 -> "142";
            case 143 -> "143";
            case 144 -> "144";
            case 145 -> "145";
            case 146 -> "146";
            case 147 -> "147";
            case 148 -> "148";
            case 149 -> "149";
            case 150 -> "150";
            case 151 -> "151";
            case 152 -> "152";
            case 153 -> "153";
            case 154 -> "154";
            case 155 -> "155";
            case 156 -> "156";
            case 157 -> "157";
            case 158 -> "158";
            case 159 -> "159";
            case 160 -> "160";
            case 161 -> "161";
            case 162 -> "162";
            case 163 -> "163";
            case 164 -> "164";
            case 165 -> "165";
            case 166 -> "166";
            case 167 -> "167";
            case 168 -> "168";
            case 169 -> "169";
            case 170 -> "170";
            case 171 -> "171";
            case 172 -> "172";
            case 173 -> "173";
            case 174 -> "174";
            case 175 -> "175";
            case 176 -> "176";
            case 177 -> "177";
            case 178 -> "178";
            case 179 -> "179";
            case 180 -> "180";
            case 181 -> "181";
            case 182 -> "182";
            case 183 -> "183";
            case 184 -> "184";
            case 185 -> "185";
            case 186 -> "186";
            case 187 -> "187";
            case 188 -> "188";
            case 189 -> "189";
            case 190 -> "190";
            case 191 -> "191";
            case 192 -> "192";
            case 193 -> "193";
            case 194 -> "194";
            case 195 -> "195";
            case 196 -> "196";
            case 197 -> "197";
            case 198 -> "198";
            case 199 -> "199";
            case 200 -> "200";
            case 201 -> "201";
            case 202 -> "202";
            case 203 -> "203";
            case 204 -> "204";
            case 205 -> "205";
            case 206 -> "206";
            case 207 -> "207";
            case 208 -> "208";
            case 209 -> "209";
            case 210 -> "210";
            case 211 -> "211";
            case 212 -> "212";
            case 213 -> "213";
            case 214 -> "214";
            case 215 -> "215";
            case 216 -> "216";
            case 217 -> "217";
            case 218 -> "218";
            case 219 -> "219";
            case 220 -> "220";
            case 221 -> "221";
            case 222 -> "222";
            case 223 -> "223";
            case 224 -> "224";
            case 225 -> "225";
            case 226 -> "226";
            case 227 -> "227";
            case 228 -> "228";
            case 229 -> "229";
            case 230 -> "230";
            case 231 -> "231";
            case 232 -> "232";
            case 233 -> "233";
            case 234 -> "234";
            case 235 -> "235";
            case 236 -> "236";
            case 237 -> "237";
            case 238 -> "238";
            case 239 -> "239";
            case 240 -> "240";
            case 241 -> "241";
            case 242 -> "242";
            case 243 -> "243";
            case 244 -> "244";
            case 245 -> "245";
            case 246 -> "246";
            case 247 -> "247";
            case 248 -> "248";
            case 249 -> "249";
            case 250 -> "250";
            case 251 -> "251";
            case 252 -> "252";
            case 253 -> "253";
            case 254 -> "254";
            case 255 -> "255";
            default -> "???";
        };
    }
}
