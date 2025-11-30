package main_panel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import logic.Game_states;
import util.GameUtil;

public class Maps{
    private Image mapImage = Toolkit.getDefaultToolkit().getImage("RPG/gamedata/image/map.png");
    private int tile = GameUtil.TILE;
    private static int map[][];

    public Maps() {}

    public int[][] loadMap(int index) {
        map = new int [GameUtil.MAP_Y_LEN][GameUtil.MAP_X_LEN];
        try (BufferedReader br = new BufferedReader(new FileReader(Game_states.getMapDataPath()+"/map1.csv"))) {
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
        for(int i = 0; i < map.length; i++) {
            y1 = (i << 5)+sy;
            for(int j = 0; j < map[i].length; j++) {
                x1 = (j << 5)+sx;
                switch(map[i][j]){
                    case 1:  x2 = 0;     break;
                    case 2:  x2 = 32;    break;
                    case 3:  x2 = 64;    break;
                    case 4:  x2 = 96;    break;
                    case 5:  x2 = 128;   break;
                    case 6:  x2 = 160;   break;
                    case 7:  x2 = 192;   break;
                    case 8:  x2 = 224;   break;
                    case 9:  x2 = 256;   break;
                    case 10: x2 = 288;   break;
                    case 11: x2 = 320;   break;
                    case 12: x2 = 352;   break;
                    case 13: x2 = 384;   break;
                    case 14: x2 = 416;   break;
                    case 15: x2 = 448;   break;
                    case 16: x2 = 480;   break;
                }
                g.drawImage(mapImage, x1, y1, x1+32, y1+32,x2, 0, x2+32, 32, null);
            }
        }
    }
    public int map_coords(int x ,int y) {
        return map[y][x];
    }
    public int map_x_length() {
        return map[0].length;
    }
    public int map_y_length() {
        return map.length;
    }
    public void map_swap(int x ,int y, int og) {
        map[y][x] = og;
    }
    public int[][] getMap() {
        return map;
    }
}