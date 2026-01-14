package game.logic;

import java.util.ArrayList;
import java.util.List;

public class TelePort {
    private int coords[] = new int[3];
    private List<int[]> toTeleportList = new ArrayList<>();
    private List<String> list_tag_name = new ArrayList<>();
    public void markTeleportCoords(int map_num, int x, int y, String tag) {
        coords[0] = map_num;
        coords[1] = x;
        coords[2] = y;
        toTeleportList.add(coords);
        list_tag_name.add(tag);
    }
    public void removeTeleportCoords(int index) {
        toTeleportList.remove(index);
        list_tag_name.remove(index);
    }
    public int[] getToTeleportCoords(int index) {
        return toTeleportList.get(index);
    }
    public String getTagName(int index) {
        return list_tag_name.get(index);
    }
}
