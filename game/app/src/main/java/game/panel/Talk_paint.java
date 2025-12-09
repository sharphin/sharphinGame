package game.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

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
        if(Talk.getMainMessage() != null) {
            g.drawString(Talk.getMainMessage()[message_line], 110, 400);
        }
    }
    public void controll(int key, int i,int message_id) {
        if(key == KeyEvent.VK_ENTER) message_line++;
        if(key == KeyEvent.VK_UP) {}
        if(key == KeyEvent.VK_DOWN) {}
        if(message_line >= Talk.getMainMessage().length) {
            Game_states.updateControll_state((Game_states.getControll_state()+GameUtil.PLAY) & ~GameUtil.TALK);
            message_line = 0;
        }
    }
}
