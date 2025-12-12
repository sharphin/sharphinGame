package game.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import game.logic.Game_states;
import game.save_load.Load;
import game.save_load.Save;
import game.util.FontUtil;
import game.util.GameUtil;


public class Save_paint {
    Font font;
    Color black = new Color(0, 0, 0, 150);
    private int y,v = 0;
    private String save_slot[] = new String[GameUtil.MAX_SAVE_SLOT];
    public Save_paint() {
        setSave_slot();
        FontUtil fl = new FontUtil();
        font = fl.setFontSize_Mplus1Code(20f);
    }

    public void paint_save(Graphics g) {
        g.setColor(black);
        g.fillRect(0, 0, GameUtil.PANEL_X, GameUtil.PANEL_Y);
        g.setColor(Color.WHITE);
        g.setFont(font);
        for(int i = 0; i < GameUtil.MAX_SAVE_SLOT;i++) {
            if(save_slot[i] == null || save_slot[i].equals("")) continue;
            StringBuilder sb = new StringBuilder();
            String sss[] = save_slot[i].split(",");
            sb.append(sss[0]).append(",").append(parseTime(Long.parseLong(sss[1])));
            g.drawString(sb.toString(), 300, (i+1)*50);
        }
        g.setColor(Color.WHITE);
        g.fillRect(300, 65+y, 340, 3);
    }
    public void controll(int key,Save save) {
        if(key == KeyEvent.VK_ESCAPE) {
            Game_states.updateControll_state(1);
        }
        if(key == KeyEvent.VK_UP)      v--;
        if(key == KeyEvent.VK_DOWN)    v++;
        if(v < 0) {
            v = GameUtil.MAX_SAVE_SLOT-1;
        } else if(v > GameUtil.MAX_SAVE_SLOT-1) {
            v = 0;
        }
        if(key == KeyEvent.VK_ENTER) {
            save.write(v, false,save_slot);
            setSave_slot();
        }
        y = 50*v;
    }
    private void setSave_slot() {
        Load load = new Load();
        save_slot = load.read();
    }
    private static String parseTime(long num) {
        int second = (int)num % 60;
        int minute  = (int)num/60;
        int hour = (int)(num/60)/60;
        return hour+"時間 "+minute+"分 "+second+"秒";
    }
}
