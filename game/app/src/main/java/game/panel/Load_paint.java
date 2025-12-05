package game.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import game.frame.BaseFrame;
import game.logic.Game_states;
import game.main_panel.CCharacter;
import game.save_load.Load;
import game.util.FontUtil;
import game.util.GameUtil;

public class Load_paint{
    private int y, v;
    public static boolean load_panel_open;
    public static boolean load_confirm;
    private String save_slot[] = new String[GameUtil.MAX_SAVE_SLOT];

    Font font;
    Load load;
    public Load_paint() {
        setSave_slot();
        FontUtil fl = new FontUtil();
        font = fl.setFontSize_Mplus1Code(20f);
    }

    public void paint_load(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GameUtil.PANEL_X, GameUtil.PANEL_Y);
        g.setColor(Color.WHITE);
        g.setFont(font);
        if(save_slot == null) {
            g.drawString("LOAD ERROR", 110, 50);
            return;
        }
        for(int i = 0; i < save_slot.length;i++) {
            if(save_slot[i] == null || save_slot[i].equals("")) {
                g.drawString("EMPTY", 310, (i+1)*50);
                continue;
            }
            StringBuilder sb = new StringBuilder();
            String sss[] = save_slot[i].split(",");
            sb.append(sss[0]).append(",").append(parseTime(Long.parseLong(sss[1])));
            g.drawString(sb.toString(), 310, (i+1)*50);
        }
        g.setColor(Color.WHITE);
        g.drawRect(300, 20+y, 350, 50);
    }
    public void controll(int key) {
        if(key == KeyEvent.VK_ESCAPE) {
            load_panel_open = false;
        }
        if(key == KeyEvent.VK_UP)      v--;
        if(key == KeyEvent.VK_DOWN)    v++;
        if(v < 0) {
            v = GameUtil.MAX_SAVE_SLOT-1;
        }else {
            if(v > GameUtil.MAX_SAVE_SLOT-1) v = 0;
        }
        if(key == KeyEvent.VK_ENTER) {
            String str[] = save_slot[v].split(",");
            if(str.length <= 1) return;
            new Game_states(str[0], str[5],load);
            if(str.length <= 1) return;
            int x = Integer.parseInt(str[2]); 
            int y = Integer.parseInt(str[3]);
            int map_number = Integer.parseInt(str[4]);
            long play_time = Long.parseLong(str[1]);
            BaseFrame.frame_generator().panel_change(new CCharacter(x, y, map_number,play_time,Game_states.getTODAY()),v);
            load_panel_open = false;
        }
        y = 50*v;
    }

    private void setSave_slot() {
        load = new Load();
        save_slot = load.read();
    }
    private String parseTime(long second) {
        second = second % 60;
        int minute  = (int)second/60;
        int hour = minute/60;
        return hour+"時間 "+minute+"分 "+second+"秒";
    }
}