package game.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import game.frame.BaseFrame;
import game.logic.Game_states;
import game.main_panel.CCharacter;
import game.main_panel.Prologue_panel;
import game.save_load.Load;
import game.util.FontUtil;
import game.util.GameUtil;

public class Load_paint{
    private int y, v;
    public static boolean load_panel_open;
    public static boolean load_confirm;
    private String save_slot[] = new String[GameUtil.MAX_SAVE_SLOT];

    Font font;
    Font font2;
    Load load;
    private String error = "";
    public Load_paint() {
        setSave_slot();
        FontUtil fl = new FontUtil();
        font = fl.setFontSize_Mplus1Code(20f);
        font2 = fl.setFontSize_Mplus1Code(15f);
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
        g.drawString(error, 110, 50);
        for(int i = 0; i < save_slot.length;i++) {
            if(save_slot[i] == null || save_slot[i].equals("")) {
                g.drawString("EMPTY", 310, (i+1)*50);
                continue;
            }
            String sss[] = save_slot[i].split(",");
            g.setFont(font);
            g.drawString(sss[0], 310, (i+1)*50);
            g.setFont(font2);
            g.drawString(parseTime(Long.parseLong(sss[1])), 500, (i+1)*50);
        }
        g.setColor(Color.WHITE);
        g.drawRect(300, 20+y, 350, 50);
    }
    public void controll(int key) {
        error = "";
        if(key == KeyEvent.VK_ESCAPE) {
            load_panel_open = false;
        }
        if(key == KeyEvent.VK_UP)      v--;
        if(key == KeyEvent.VK_DOWN)    v++;
        if(v < 0) {
            v = GameUtil.MAX_SAVE_SLOT-1;
        } else {
            if(v > GameUtil.MAX_SAVE_SLOT-1) v = 0;
        }
        if(key == KeyEvent.VK_ENTER) {
            if(save_slot[v] == null || save_slot[v].equals("")) return;
            String stc[] = save_slot[v].split(",");
            if(stc.length <= 1) return;
            int xymap[] = load.coords_decrypt(Long.parseLong(stc[2]),Long.parseLong(stc[4]));
            if(!load.gameStatesLoad(stc[0] ,stc[3]) || xymap[0] == -1) {
                error = "!BROKEN DATA!";
                return;
            }
            if((Game_states.getRoute_branch() & GameUtil.GAME_CLEAR) ==  GameUtil.GAME_CLEAR) {
                BaseFrame.frame_generator().panel_change(new Prologue_panel(stc[0],true),1);
                load_panel_open = false;
            } else {
                int x = xymap[0];
                int y = xymap[1];
                int map_number = xymap[2];
                long play_time = Long.parseLong(stc[1]);
                BaseFrame.frame_generator().panel_change(new CCharacter(x, y, map_number,play_time,Game_states.getTodayTime(),false),1);
                load_panel_open = false;
            }
        }
        y = 50*v;
    }

    private void setSave_slot() {
        load = new Load();
        save_slot = load.read();
    }
    private static String parseTime(long num) {
        int second = (int)num % 60;
        int minute  = (int)num/60;
        int hour = (int)(num/60)/60;
        return hour+"時間 "+minute+"分 "+second+"秒";
    }
}