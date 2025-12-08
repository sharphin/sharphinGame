package game.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import game.logic.Game_states;
import game.util.FontUtil;
import game.util.GameUtil;

public class Talk_paint {
    //private int talk_line;
    Font font1;
    private String[] message_list = {"/message1.dat"};
    private List<String> main_message = new ArrayList<String>();
    public Talk_paint() {
        FontUtil fl = new FontUtil();
        font1 = fl.setFontSize_Mplus1Code(20f);
    }
    public void paint_message(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRoundRect(100, 370, 500, 130,5,5);
        g.setColor(Color.WHITE);
        g.drawRoundRect(100, 370, 500, 130,5,5);
        g.setFont(font1);
        if(!main_message.isEmpty()) g.drawString(main_message.get(0), 110, 400);
    }
    public void controll(int key, int message_id) {
        messageread(message_id);
        if(key == KeyEvent.VK_ENTER) {
            Game_states.updateControll_state((Game_states.getControll_state()+GameUtil.PLAY) & ~GameUtil.TALK);
        }
        if(key == KeyEvent.VK_UP) {}
        if(key == KeyEvent.VK_DOWN) {}
    }
    private void messageread(int index) {

        try (BufferedReader br = new BufferedReader(new FileReader("gamedata/message_data"+message_list[index]))) {
            String data;
            while ((data = br.readLine()) != null) {
                main_message.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
