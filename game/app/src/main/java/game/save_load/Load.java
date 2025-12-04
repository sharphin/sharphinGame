package game.save_load;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import game.util.GameUtil;

public class Load {
    public Load() {}
    public String[] read() {
        String save_slot[] = new String[GameUtil.MAX_SAVE_SLOT];
        try (BufferedReader br = new BufferedReader(new FileReader("savedata/saveData.dat"))) {
            String data;
            for(int i = 0; i < GameUtil.MAX_SAVE_SLOT;i++) {
                data = br.readLine();
                if(data == null) continue;
                save_slot[i] = data;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return save_slot;
    }
    public String gameStatesLoad(String filepath) {
        String data = "";
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            data = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    public int[] items_decryption(long itemsInfo) {
        int arr[] = new int[GameUtil.MAX_ITEM];
        for(int i = 0; i< GameUtil.MAX_ITEM;i++) {
            if(i != 0)itemsInfo = itemsInfo>>8;
            long tmp = itemsInfo & 0b011111111;
            arr[i] = (int)tmp;
        }
        return arr;
    }
}