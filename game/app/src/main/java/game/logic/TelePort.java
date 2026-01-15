package game.logic;

import java.util.ArrayList;
import java.util.List;

import game.main_panel.Maps;

public class TelePort {
    private static List<int[]> toTeleportList = new ArrayList<>();
    private static List<String> list_tag_name = new ArrayList<>();
    public static int markTeleportCoords(int map_num, int x, int y, String tag,Maps map) {
        for(int[] tmp: toTeleportList) {
            if(tmp[1] == x>>5) return -3;
            if(tmp[2] == y>>5) return -3;
        }
        for(String tmp: list_tag_name) {
            if(tag.equals(tmp)) return -1;
        }
        if(tag.isEmpty()) return -2; 
        if(map.map_tile(map_num, x>>5, y>>5) != 0) return -4; 
        int coords[] = new int[3];
        coords[0] = map_num;
        coords[1] = x>>5;
        coords[2] = y>>5;
        toTeleportList.add(coords);
        list_tag_name.add(tag);
        map.updataMapTile(map_num,x>>5,y>>5,106);
        return 0;
    }
    public static int markUpdate(int i, int map_num, int x, int y, String tag ,Maps map) {
        if(tag.isEmpty()) return -2;
        int coords[] = new int[3];
        coords[0] = map_num;
        coords[1] = x>>5;
        coords[2] = y>>5;
        int fff[] = toTeleportList.get(i);
        map.updataMapTile(fff[0],fff[1],fff[2],0);
        toTeleportList.remove(i);
        list_tag_name.remove(i);
        toTeleportList.add(coords);
        list_tag_name.add(tag);
        map.updataMapTile(map_num,x>>5,y>>5,106);
        return 0;
    }
    public static int markDelete(int i,Maps map) {
        int fff[] = toTeleportList.get(i);
        map.updataMapTile(fff[0],fff[1],fff[2],0);
        toTeleportList.remove(i);
        list_tag_name.remove(i);
        return 0;
    }
    public void removeTeleportCoords(int index) {
        toTeleportList.remove(index);
        list_tag_name.remove(index);
    }
    public static int[] getToTeleportCoords(int index) {
        return toTeleportList.get(index);
    }
    public static String getTagName(int index) {
        return list_tag_name.get(index);
    }
    public static int size() {
        return list_tag_name.size();
    }
}
