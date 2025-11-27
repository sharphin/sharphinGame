package secret;

import java.util.Arrays;

public class Game_logic {

    public Game_logic() {}
    private static int game_state_flag;
    private int sum_flip_count;

    static boolean pass;
    static boolean[][] BOARD2 = new boolean[8][8];
    static int[][] BOARD = new int[][] {{0,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0},
                                        {0,0,0,1,2,0,0,0},
                                        {0,0,0,2,1,0,0,0},
                                        {0,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0}};

    private final int[][] dire = new int[][] {{ 1, 0},
                                              { 1,-1},
                                              { 0,-1},
                                              {-1,-1},
                                              {-1, 0},
                                              {-1, 1},
                                              { 0, 1},
                                              { 1, 1}};
    public int sum_flip_count() {
        return sum_flip_count;
    }
    public int game_state_flag() {
        return game_state_flag;
    }
    public void put_option() {
        int white = 0;
        int black = 0;
        pass = true;
        for(boolean[] tmp: BOARD2) {
            Arrays.fill(tmp,false);
        }
        for(int y = 0;y < GameUtil.MAP_Y;y++) {
            for(int x = 0;x < GameUtil.MAP_X;x++) {
                sum_flip_count = 0;
                switch (BOARD[y][x]){
                    case 1 -> black++;
                    case 2 -> white++;
                }
                if(BOARD[y][x] != 0) continue;
                put_stone(false, x, y);
                int count = sum_flip_count;
                if(count > 0) {
                    pass = false;
                    BOARD2[y][x] = true;
                }
            }
        }
        System.out.println("white: "+white+" black: "+black);
        if(pass) turn_flip();
    }
    public void game_result(int white, int black) {
        if(black == white) {
            game_state_flag = 0b100; 
        } else if(black > white) {
            game_state_flag = 0b1000;
        } else {
            game_state_flag = 0b10000;
        }
    }
    public void put_stone(boolean put,int x, int y) {
        int stone = (game_state_flag & 1)+1;
        boolean can_turn_flip = false;
        for(int i = 0; i < 8;i++) {
            int[] BXY = new int[8];
            int[][] XY = new int[8][2];
            int a = x;
            int b = y;
            for(int j = 0;j < 8; j++) {
                a = a + dire[i][0];
                b = b + dire[i][1];
                if(a >=0 && a < 8 && b >=0 && b < 8) {
                    BXY[j] = BOARD[b][a];
                    //System.out.println("x: "+a+" y: "+b+" coords: "+BXY[j]);
                    XY[j][0] = a;
                    XY[j][1] = b;
                }
            }
            //System.out.println(Arrays.toString(BXY));
            int count = can_stone_flip_count(BXY,stone);
            sum_flip_count += count;
            if(!put) continue;
            if(stone_fliped(BXY, XY, stone, x, y, count)) can_turn_flip = true;
        }
        if(can_turn_flip) turn_flip();
        can_turn_flip = false;
    }

    private void turn_flip() {
        game_state_flag = (~(game_state_flag)) & 1; 
    }
    public int can_stone_flip_count(int bxy[], int stone) {
        int count = 0;
        for(int i = 0; i < 8; i++) {
            if(bxy[i]==stone) {
                count = i;
                break;
            } else if(bxy[i]==0) {
                count = 0;
                break;
            }
        }
        return count;
    }
    public boolean stone_fliped(int bxy[],int xy[][], int stone,int x, int y, int count) {
        if(count==0) return false;
        for(int i = 0; i < count; i++) {   
            //System.out.println(Arrays.toString(bxy));     
            if(bxy[i]!=0 && bxy[i]!=stone) {
                BOARD[xy[i][1]][xy[i][0]] = stone;
                BOARD[y][x] = stone; 
                //System.out.println("x: "+xy[i][0]+" y: "+xy[i][1]);
            }
        }
        return true;
    }
}