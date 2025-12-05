package game.save_load;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import game.logic.Game_states;
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
            return null;
        }
        return save_slot;
    }
    public boolean gameStatesLoad(String name,String filepath) {
        StringBuilder sb = new StringBuilder();
        sb.append("savedata/").append(filepath).append("/states.csv");
        String data[] = new String[0];
        try (BufferedReader br = new BufferedReader(new FileReader(sb.toString()))) {
            data = br.readLine().split(",");
        } catch (IOException e) {
            e.printStackTrace();
        }
        int intdataList[] = new int[9];
        long longdataList[] = new long[3];
        longdataList[0] = Long.parseLong(data[0]);
        intdataList[0] = Integer.parseInt(data[1]);
        intdataList[1] = Integer.parseInt(data[2]);
        intdataList[2] = Integer.parseInt(data[3]);
        intdataList[3] = Integer.parseInt(data[4]);
        intdataList[4] = Integer.parseInt(data[5]);
        intdataList[5] = Integer.parseInt(data[6]);
        intdataList[6] = Integer.parseInt(data[7]);
        intdataList[7] = Integer.parseInt(data[8]);
        intdataList[8] = Integer.parseInt(data[9]);
        longdataList[1] = Long.parseLong(data[10]);
        longdataList[2] = Long.parseLong(data[11]);
        for(int i = 0; i < intdataList.length; i++) {
            if(cheating(intdataList[i], Integer.parseInt(data[i+14]))) return false;
        }
        if(cheating(longdataList[0], Long.parseLong(data[13]))) return false;
        if(cheating(longdataList[1], Long.parseLong(data[23]))) return false;
        if(cheating(longdataList[2], Long.parseLong(data[24]))) return false;
        int item_list[] = items_decryption(Long.parseLong(data[12]),Long.parseLong(data[25]));

        new Game_states(name, filepath, intdataList, longdataList, item_list);
        return true;
    }
    public int[] items_decryption(long itemsInfo,long pad) {
        int arr[] = new int[GameUtil.MAX_ITEM];
        if(cheating(itemsInfo, pad)) return new int[]{-1,-1,-1,-1,-1,-1,-1,-1};
        for(int i = 0; i< GameUtil.MAX_ITEM;i++) {
            if(i != 0)itemsInfo = itemsInfo>>8;
            long tmp = itemsInfo & 0b011111111;
            arr[i] = (int)tmp;
        }
        return arr;
    }
    public int[] coords_decrypt(long cryp, long crypincyip) {
        if(cheating(cryp, crypincyip)) return new int[]{-1,-1,-1};
        int tmp[] = {(int)(cryp>>38),(int)(cryp>>19) & 524287,(int)(cryp & 127)};
        return tmp;
    }
    private boolean cheating(int num, int pad) {
        int mask = -1498760516;
        if((num ^ mask) != pad) return true;
        return false;
    }
    private boolean cheating(long num, long pad) {
        long mask = 35741560992348860l;
        if((num ^ mask) != pad) return true;
        return false;
    }
}