package save_load;

import java.io.*;
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
}