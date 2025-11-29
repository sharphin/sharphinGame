package main_panel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JPanel;

import util.GameUtil;

public class Maps extends JPanel{
    //private Image map = Toolkit.getDefaultToolkit().getImage("image/unnamed.jpg");
    //private Image charc = Toolkit.getDefaultToolkit().getImage("image/charc.png");
    private int tile = GameUtil.TILE;
    private int map[][];

    public Maps() {}

    public int[][] loadMap(int index) {
        map = new int [GameUtil.MAP_Y_LEN][GameUtil.MAP_X_LEN];
        try (BufferedReader br = new BufferedReader(new FileReader("RPG/gamedata/map/map1.csv"))) {
            for(int i = 0; i < map.length;i++) {
                for(int j = 0; j < map[i].length;j++) {
                    int schar = br.read();
                    if(schar == 13) schar = br.read();
                    if(schar == 10) schar = br.read();
                    map[i][j] = Character.getNumericValue((char)schar);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }
    public final void paint_map(Graphics g, int sx, int sy) {
        int x1=1, y1=1;
        for(int i = 0; i < map.length; i++) {
            y1 = (i << 5)+sy;
            for(int j = 0; j < map[i].length; j++) {
                x1 = (j << 5)+sx;
                switch(map[i][j]){
                    case 1 -> g.setColor(Color.BLACK);
                    default -> g.setColor(Color.WHITE);
                } 
                g.fillRect(x1, y1, tile, tile);
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
    public void map_swap() {

    }

}