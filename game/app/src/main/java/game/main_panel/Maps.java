package game.main_panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import game.logic.Game_states;
import game.util.GameUtil;

public class Maps {
private String[] map_list = {"/map0.csv",
                             "/map1.csv",
                             "/map2.csv",
                             "/map3.csv",
                             "/map4.csv",
                             "/map5.csv",
                             "/map6.csv",
                             "/map7.csv",
                             "/map8.csv",
                             "/map9.csv",
                             "/map10.csv",
                             "/map11.csv",
                             "/map12.csv",
                             "/map13.csv",
                             "/map14.csv",
                             "/map15.csv",
                             "/map16.csv",
                             "/map17.csv",
                             "/map18.csv",
                             "/map19.csv",
                             "/map20.csv",
                             "/map21.csv",
                             "/map22.csv",
                             "/map23.csv",
                             "/map24.csv",
                             "/map25.csv",
                             "/map26.csv",
                             "/map27.csv",
                             "/map28.csv",
                             "/map29.csv",
                             "/map30.csv",
                             "/map31.csv",
                             "/map32.csv",
                             "/map33.csv",
                             "/map34.csv",
                             "/map35.csv",
                             "/map36.csv",
                             "/map37.csv",
                             "/map38.csv",
                             "/map39.csv",
                             "/map40.csv",
                             "/map41.csv",
                             "/map42.csv",
                             "/map43.csv",
                             "/map44.csv",
                             "/map45.csv",
                             "/map46.csv",
                             "/map47.csv",
                             "/map48.csv",
                             "/map49.csv",
                             "/map50.csv",
                             "/map51.csv",
                             "/map52.csv",
                             "/map53.csv",
                             "/map54.csv",
                             "/map55.csv",
                             "/map56.csv",
                             "/map57.csv",
                             "/map58.csv",
                             "/map59.csv"};
                             
    private Image mapImage = Toolkit.getDefaultToolkit().getImage(GameUtil.FILE_PATH+"gamedata/image/map.png");
    private int tile = GameUtil.TILE;
    private static int active_map_num;
    private static int map[][][];
    private int map_move_key = 2097152;
    private int key_item_mask = 4096;
    
    private int color_delta = 0;
    public Maps() {}

    public void loadMap(int index,boolean escaped) {
        active_map_num = index;
        map = new int [map_list.length][GameUtil.MAP_Y_LEN][GameUtil.MAP_X_LEN];
        for(int i = 0; i < map.length;i++) {
            map[i] = mapread(map[i],i);
        }
        if(escaped) put_password_tile();
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
        int x1=1, y1=1;
        int i_len = map[active_map_num].length;
        int j_len = map[active_map_num][0].length;
        for(int i = 0; i < i_len; i++) {
            y1 = (i << 5)+sy;
            for(int j = 0; j < j_len; j++) {
                x1 = (j << 5)+sx;
                int x2 = 0,y2 = 0;
                int ftile = map[active_map_num][i][j];
                if(ftile>= 8192) continue;
                if(ftile < 0) ftile = ~ftile;
                switch(ftile & (key_item_mask-1)){
                    case 0:  x2 = 0;     break;
                    case 1:  x2 = 704;   break;
                    case 2:  x2 = 736;   break;
                    case 3:  x2 = 768;   break;
                    case 5:  x2 = 32;    break;
                    case 9:  x2 = 160;   break;
                    case 10: x2 = 192;   break;
                    case 14: x2 = 320;   break;
                    case 17: x2 = 416;   break;
                    case 18: x2 = 448;   break;
                    case 19: x2 = 480;   break;
                    case 20: x2 = 512;   break;
                    case 21: x2 = 544;   break;
                    case 22: x2 = 576;   break;
                    case 23: x2 = 608;   break;
                    case 24: x2 = 640;   break;
                    case 25: x2 = 672;   break;
                    case 27: x2 = 832;   break;
                    case 28: x2 = 0;   y2 = 32;  break;
                    case 29: x2 = 32;  y2 = 32;  break;
                    case 30: x2 = 64;  y2 = 32;  break;
                    case 31: x2 = 96;  y2 = 32;  break;
                    case 100: x2 = 576;y2 = 32;  break;
                    case 101: x2 = 608;y2 = 32;  break;
                    case 32: x2 = 128; y2 = 32;  break;//ここから壁の紙
                    case 33: x2 = 192; y2 = 32;  break;
                    case 34: x2 = 256; y2 = 32;  break; 
                    case 35: x2 = 320; y2 = 32;  break;
                    case 36,40,44: x2 = 160; y2 = 32;  break;
                    case 37,41,45: x2 = 224; y2 = 32;  break;
                    case 38,42,46: x2 = 288; y2 = 32;  break;
                    case 39,43,47: x2 = 352; y2 = 32;  break;
                    case 4095: x2 = 864;break;
                    default: continue;
                }
                g.drawImage(mapImage, x1, y1, x1+tile, y1+tile,x2, y2, x2+tile, y2+tile, null);
                if(map[active_map_num][i][j] > -1) continue;
                if((~map[active_map_num][i][j] & key_item_mask) != key_item_mask) {
                    
                } else {
                    g.setColor(new Color(255,255,255,color_delta()));
                    g.fillOval(x1, y1, 5, 5);
                }
            }
        }
    }
    public final void paint_map2(Graphics g, int sx, int sy) {
        int x1=1, y1=1;
        int i_len = map[active_map_num].length;
        int j_len = map[active_map_num][0].length;
        for(int i = 0; i < i_len; i++) {
            y1 = (i << 5)+sy;
            for(int j = 0; j < j_len; j++) {
                x1 = (j << 5)+sx;
                int x2 = 0,y2 = 0;
                int ftile = map[active_map_num][i][j];
                if(ftile>= 8192) continue;
                switch(ftile & (key_item_mask-1)){
                    case 6:  x2 = 64;    break;
                    case 7:  x2 = 96;    break;
                    case 8:  x2 = 128;   break;
                    case 11: x2 = 224;   break;
                    case 12: x2 = 256;   break;
                    case 13: x2 = 288;   break;
                    case 15: x2 = 352;   break;
                    case 16: x2 = 384;   break;
                    case 26: x2 = 800;   break;
                    default: continue;
                }
                g.drawImage(mapImage, x1, y1, x1+tile, y1+tile,x2, y2, x2+tile, tile, null);
            }
        }
    }
    public int ref_key_item(int x, int y) {
        return (~map[active_map_num][y][x] & 2093056)>>13;
    }
    public int take_key_item(int x, int y) {
        int key_item = (~map[active_map_num][y][x] & 2093056)>>13;
        map[active_map_num][y][x] = ~map[active_map_num][y][x] & key_item_mask-1;
        return key_item;
    }
    public int put_key_item(int item_num, int x, int y) {
        if((map[active_map_num][y][x] & key_item_mask) != 0) return -1;
        map[active_map_num][y][x] = ~((item_num << 13)+key_item_mask+map[active_map_num][y][x]);
        //System.out.println(map[active_map_num][y][x] +" "+x+" "+y);
        return 0;
    }
    public boolean dye_black(int x, int y) {
        if(map[active_map_num][y][x] >= 4095) return false;  
        map[active_map_num][y][x] = 4095;
        return true;
    }
    public void put_password_tile() {
        map[38][25][5] = 35;
        map[38][26][5] = 53;
    }
    public void pc_on() {
        map[18][14][25] = 100;
        map[18][14][26] = 101;
    }
    public void pc_off() {
        map[18][14][25] = 22;
        map[18][14][26] = 23;
    }
    public void door_open(int x, int y) {
        map[active_map_num][y][x] = map[active_map_num][y][x]>>1;
        //System.out.println(map[active_map_num][y][x]);
    }
    public int take_mormal_item() {
        return 0;
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
    public void updataMapTile(int map_num,int x, int y) {
        map[map_num][y][x] = 0;
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
    private int color_delta() {
        color_delta+=4;
        if(color_delta>= 510) color_delta = 0;
        int offset_color_delta = color_delta>=255 ? ~color_delta & 0b11111111: color_delta;
        return offset_color_delta;
    }
}