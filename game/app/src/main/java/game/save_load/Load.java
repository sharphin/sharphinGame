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
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.home")+ "/AppData/Local/Revenge/savedata/saveData.dat"))) {
            String data;
            for(int i = 0; i < GameUtil.MAX_SAVE_SLOT;i++) {
                data = br.readLine();
                if(data == null) continue;
                save_slot[i] = data;
            }
        } catch (IOException e) {
            return save_slot;
        }
        return save_slot;
    }
    public boolean gameStatesLoad(String name,String filepath) {
        StringBuilder sb = new StringBuilder();
        String dirPath = System.getProperty("user.home")+ "/AppData/Local/Revenge/savedata/";
        sb.append(dirPath).append(filepath).append("/states.csv");
        String data[][] = new String[2][];
        try (BufferedReader br = new BufferedReader(new FileReader(sb.toString()))) {
            data[0] = br.readLine().split(",");
            data[1] = br.readLine().split(",");
        } catch (IOException e) {
            e.printStackTrace();
        }
        sb = new StringBuilder();
        sb.append(dirPath).append(filepath).append("/mask.csv");
        String data_mask[][] = new String[2][];
        try (BufferedReader br = new BufferedReader(new FileReader(sb.toString()))) {
            data_mask[0] = br.readLine().split(",");
            data_mask[1] = br.readLine().split(",");
        } catch (IOException e) {
            e.printStackTrace();
        }
        int intdataList[] = new int[6];
        long got_item_flags[] = new long[4];
        long longdata = Long.parseLong(data[0][0]);
        intdataList[0] = Integer.parseInt(data[0][1]);
        intdataList[1] = Integer.parseInt(data[0][2]);
        intdataList[2] = Integer.parseInt(data[0][3]);
        intdataList[3] = Integer.parseInt(data[0][4]);
        intdataList[4] = Integer.parseInt(data[0][5]);
        intdataList[5] = Integer.parseInt(data[0][6]);
        if(cheating(longdata, Long.parseLong(data_mask[0][0]))) return false;
        for(int i = 0; i < intdataList.length; i++) {
            if(cheating(intdataList[i], Integer.parseInt(data_mask[0][i+1]))) return false;
        }
        intdataList[5] = passwordDecrypt(intdataList[5]);
        int item_list[] = items_decryption(Long.parseLong(data[0][7]),Long.parseLong(data_mask[0][7]));
        if(item_list[0] == -1) return false;
        for(int i = 0; i < got_item_flags.length; i++) {
            long tmp = Long.parseLong(data[1][i]);
            if(cheating(tmp, Long.parseLong(data_mask[1][i]))) return false;
            got_item_flags[i] = tmp;
        }
        new Game_states(name, filepath, intdataList, longdata, item_list, got_item_flags);
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
    private static int passwordDecrypt(int num) {
        return ~num & 1048575;
    }
    private boolean cheating(int num, int pad) {
        int mask = -1498760516;
        if(Integer.reverse(num ^ mask) != pad) return true;
        return false;
    }
    private boolean cheating(long num, long pad) {
        long mask = 35741560992348860l;
        if(Long.reverse(num ^ mask) != pad) return true;
        return false;
    }
}