package game.logic;

import game.main_panel.Maps;

public class Puzzle {
    private boolean resolved_puzzle[] = new boolean[5];
    private boolean puzzle1[][]  = new boolean[][]{
        {true,false,true,false,true,false},
        {true,false,true,false,true,false},
        {true,false,true,false,true,false},
        {true,false,true,false,true,false},
        {true,false,true,false,true,false},
        {true,false,true,false,true,false}
    };
    public void puzzle1_check(Maps map) {
        if(resolved_puzzle[0]) return;
        if(map.map_number() != 28) return;
        for(int i = 18; i < 24; i++) {
            for(int j = 22; j < 28; j++) {
                if(map.far_map_tile(28, j, i) == 103) {
                    if(!puzzle1[i-18][j-22]) return;
                } else if(map.far_map_tile(28, j, i) == 102){
                    if(puzzle1[i-18][j-22]) return;
                }
            }
        }
        for(int i = 18; i < 24; i++) {
            for(int j = 22; j < 28; j++) {
                if(map.far_map_tile(28, j, i) == 103) {
                    map.updataMapTile(28 ,j, i, 105);
                } else if(map.far_map_tile(28, j, i) == 102){
                    map.updataMapTile(28 ,j, i, 104);
                }
            }
        }
        map.updataMapTile(20, 15, 19, -110593);
        resolved_puzzle[0] = true;
        IO.println("OK");
    }   
}
