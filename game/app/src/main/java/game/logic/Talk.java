package game.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Talk {
    private String message_list[] = {"/message1.dat"};
    private static String main_message[];
    public Talk(int file_index, int message_id) {
        main_message = messageread(file_index, message_id);
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
}
