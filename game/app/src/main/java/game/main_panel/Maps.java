package game.main_panel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import game.logic.Game_states;
import game.util.GameUtil;

public class Maps{
    private String[] map_list = {"/map0.csv","/map1.csv","/map2.csv","/map3.csv","/map4.csv","/map5.csv"};
    private Image mapImage = Toolkit.getDefaultToolkit().getImage("gamedata/image/map.png");
    private int tile = GameUtil.TILE;
    private int active_map_num;
    private static int map[][][];
    private int map_move_key = 8192;
    //private int map_paint_mask = 0b1000000000000;
    public Maps() {}

    public void loadMap(int index) {
        active_map_num = index;
        map = new int [map_list.length][GameUtil.MAP_Y_LEN][GameUtil.MAP_X_LEN];
        for(int i = 0; i < map.length;i++) {
            map[i] = mapread(map[i],i);
        }
    }
    private int[][] mapread(int map[][],int index) {
        try (BufferedReader br = new BufferedReader(new FileReader(mapPathBuilder(Game_states.getMapDataPath(),index)))) {
            for(int i = 0; i < map.length;i++) {
                String data = br.readLine();
                String split_data[] = data.split(",");
                for(int j = 0; j < map[i].length;j++) {
                    map[i][j] = Integer.parseInt(split_data[j]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
    public final void paint_map(Graphics g, int sx, int sy) {
        int x1=1, y1=1,x2 = 0, y2 = 0;
        for(int i = 0; i < map[active_map_num].length; i++) {
            y1 = (i << 5)+sy-32;
            for(int j = 0; j < map[active_map_num][i].length; j++) {
                x1 = (j << 5)+sx-32;
                switch(map[active_map_num][i][j]){
                    case 0:  x2 = 0;     break;
                    case 1:  x2 = 32;    break;
                    case 2:  x2 = 64;    break;
                    case 3:  x2 = 96;    break;
                    case 4:  x2 = 128;   break;
                    case 5:  x2 = 160;   break;
                    case 6:  x2 = 192;   break;
                    case 7:  x2 = 224;   break;
                    case 8:  x2 = 256;   break;
                    case 9:  x2 = 288;   break;
                    case 10: x2 = 320;   break;
                    case 11: x2 = 352;   break;
                    case 12: x2 = 384;   break;
                    case 13: x2 = 416;   break;
                    case 14: x2 = 448;   break;
                    case 15: x2 = 480;   break;
                    case 16: x2 = 512;   break;
                    default: x2 = 544;   break;
                }
                g.drawImage(mapImage, x1, y1, x1+tile, y1+tile,x2, y2, x2+tile, tile, null);
            }
        }
    }
    public String map_file_name(int i) {
        return map_list[i];
    }
    public void updateActiveMapNum(int index) {
        active_map_num = index;
    }
    public int map_number() {
        return active_map_num;
    }
    public int map_tile(int x ,int y) {
        return map[active_map_num][y][x];
    }
    public int readMapKinds() {
        return map.length;
    }
    public int map_x_length() {
        return map[active_map_num][0].length;
    }
    public int map_y_length() {
        return map[active_map_num].length;
    }
    public void object_swap(int x ,int y, int og) {
        map[active_map_num][y][x] = og;
    }
    public int[][] getMap(int i) {
        return map[i];
    }
    public int getMapMoveKey() {
        return map_move_key;
    }
    private String mapPathBuilder(String dir, int i) {
        StringBuilder sb = new StringBuilder();
        return sb.append(dir).append(map_list[i]).toString();
    }
}