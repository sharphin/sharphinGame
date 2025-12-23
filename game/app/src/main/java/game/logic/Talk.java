package game.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import game.util.GameUtil;
public class Talk {
    private String message_list[] = {"/prologue.dat","/message1.dat"};
    private static String main_message[] = new String[1];
    public Talk(String itemname,int file_index, int message_id) {
        Game_states.updateControll_state((Game_states.getControll_state() & ~GameUtil.PLAY)+GameUtil.TALK);
        main_message = messageread(itemname,file_index, message_id);
    }
    public static String[] getMainMessage() {
        return main_message;
    }
    private String[] messageread(String itemname,int i, int message_id) {
        StringBuilder data = new StringBuilder(itemname);
        try (BufferedReader br = new BufferedReader(new FileReader("gamedata/message_data"+message_list[i]))) {
            String str = "";
            for(int j = -1; j < message_id; j++) {
                str = br.readLine();
            }
            data.append(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.toString().split(",");
    }
}
