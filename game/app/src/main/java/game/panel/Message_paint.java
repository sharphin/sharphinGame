package game.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import game.logic.BlackDye;
import game.logic.Game_states;
import game.logic.Message;
import game.util.FontUtil;
import game.util.GameUtil;

public class Message_paint {
    Font font1;
    private int message_line = 0;
    public Message_paint() {
        FontUtil fl = new FontUtil();
        font1 = fl.setFontSize_Mplus1Code(20f);
    }
    public void paint_message(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(100, 70, 500, 400);
        g.setFont(font1);
        g.setColor(Color.BLACK);
        String messagestr[] = {"","","","","","",""};
        if(Message.getMainMessage() != null) {
            String temp = Message.getMainMessage()[message_line];
            if(Message.getMainMessage()[message_line].length() > 138) {
                messagestr[6] = temp.substring(138);
                temp = Message.getMainMessage()[message_line].substring(0,138);
            }
            if(Message.getMainMessage()[message_line].length() > 115) {
                messagestr[5] = temp.substring(115);
                temp = Message.getMainMessage()[message_line].substring(0,115);
            }
            if(Message.getMainMessage()[message_line].length() > 92) {
                messagestr[4] = temp.substring(92);
                temp = Message.getMainMessage()[message_line].substring(0,92);
            }
            if(Message.getMainMessage()[message_line].length() > 69) {
                messagestr[3] = temp.substring(69);
                temp = Message.getMainMessage()[message_line].substring(0,69);
            }
            if(Message.getMainMessage()[message_line].length() > 46) {
                messagestr[2] = temp.substring(46);
                temp = Message.getMainMessage()[message_line].substring(0,46);
            }
            if(Message.getMainMessage()[message_line].length() > 23) {
                messagestr[1] = temp.substring(23);
                temp = Message.getMainMessage()[message_line].substring(0,23);
                messagestr[0] = temp;
            } else {
                messagestr[0] = Message.getMainMessage()[message_line];
            }
            g.drawString(messagestr[0], 110, 100);
            g.drawString(messagestr[1], 110, 130);
            g.drawString(messagestr[2], 110, 160);
            g.drawString(messagestr[3], 110, 190);
            g.drawString(messagestr[4], 110, 220);
            g.drawString(messagestr[5], 110, 250);
            g.drawString(messagestr[6], 110, 280);
        }
    }
    public void controll(int key, int i,int message_id, int hit_tile) {
        if(key == KeyEvent.VK_ENTER) message_line++;
        if(key == KeyEvent.VK_UP) {}
        if(key == KeyEvent.VK_DOWN) {}
        if(message_line >= Message.getMainMessage().length) {
            Game_states.updateControll_state((Game_states.getControll_state()+GameUtil.PLAY) & ~GameUtil.MESSAGE);
            if(hit_tile == 47) {
                new BlackDye();
            }
            message_line = 0;
        }
    }
}
