package game.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Talk {
    private String message_list[] = {"/message1.dat"};
    private static String main_message[] = new String[1];
    public Talk(int file_index, int message_id) {
        main_message = messageread(file_index, message_id);
    }
    public Talk() {
        main_message = loadPrologue();
    }
    public static String[] getMainMessage() {
        return main_message;
    }
    private String[] messageread(int i, int message_id) {
        String data = "";
        try (BufferedReader br = new BufferedReader(new FileReader("gamedata/message_data"+message_list[i]))) {
            for(int j = -1; j < message_id; j++) {
                data = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.split(",");
    }
    private String[] loadPrologue() {
        List<String> datas = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader("gamedata/message_data/prologue.dat"))) {
            String data;
            while ((data = br.readLine()) != null) {
                datas.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datas.toArray(new String[datas.size()]);
    }
}
