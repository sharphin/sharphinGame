package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MapLoad {
    File file = new File("RPG/datafile/map1.csv");
    public int[][] LoadMap(int index) {
        int map[][] = new int [6][6];
        try (BufferedReader br = new BufferedReader(new FileReader("RPG/datafile/map1.csv"))) {
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
}
