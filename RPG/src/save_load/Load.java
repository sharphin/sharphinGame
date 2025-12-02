package save_load;

import java.io.*;
import util.GameUtil;
public class Load{
    public Load() {}
    public String[] read() {
        String save_slot[] = {"","","","","","","",""};
        try (BufferedReader br = new BufferedReader(new FileReader("RPG/savedata/saveData.txt"))) {
            int index = 0;
            String data;
            while ((data = br.readLine()) != null) {
                if(data.equals("")) break;

                save_slot[index] = data;
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return save_slot;
    }
    public String gameLoad(int index) {
        String save_slot[] = {"","","","","","","",""};
        try (BufferedReader br = new BufferedReader(new FileReader("RPG/savedata/saveData.txt"))) {
            int i = 0;
            String data;
            while ((data = br.readLine()) != null) {
                save_slot[i] = data;
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return save_slot[index];
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