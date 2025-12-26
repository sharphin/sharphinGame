package game.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import game.logic.BlackDye;
import game.logic.Game_states;
import game.logic.Talk;
import game.util.FontUtil;
import game.util.GameUtil;

public class Talk_paint {
    //private int talk_line;
    Font font1;
    private int message_line = 0;
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
        String messagestr[] = {"","","",""};
        if(Talk.getMainMessage() != null) {
            String temp = Talk.getMainMessage()[message_line];
            if(Talk.getMainMessage()[message_line].length() > 69) {
                messagestr[3] = temp.substring(69);
                temp = Talk.getMainMessage()[message_line].substring(0,69);
            }
            if(Talk.getMainMessage()[message_line].length() > 46) {
                messagestr[2] = temp.substring(46);
                temp = Talk.getMainMessage()[message_line].substring(0,46);
            }
            if(Talk.getMainMessage()[message_line].length() > 23) {
                messagestr[1] = temp.substring(23);
                temp = Talk.getMainMessage()[message_line].substring(0,23);
                messagestr[0] = temp;
            } else {
                messagestr[0] = Talk.getMainMessage()[message_line];
            }
            g.drawString(messagestr[0], 110, 400);
            g.drawString(messagestr[1], 110, 430);
            g.drawString(messagestr[2], 110, 460);
            g.drawString(messagestr[3], 110, 490);
        }
    }
    public void controll(int key, int i,int message_id, int hit_tile) {
        if(key == KeyEvent.VK_ENTER) message_line++;
        if(key == KeyEvent.VK_UP) {}
        if(key == KeyEvent.VK_DOWN) {}
        if(message_line >= Talk.getMainMessage().length) {
            Game_states.updateControll_state((Game_states.getControll_state()+GameUtil.PLAY) & ~GameUtil.TALK);
            if(hit_tile == 47) {
                new BlackDye();
            }
            message_line = 0;
        }
    }
}
