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
        for(int i = 0; i < save_slot.length;i++) {
            if(save_slot[i] == null || save_slot[i].equals("")) continue;
            StringBuilder sb = new StringBuilder();
            String sss[] = save_slot[i].split(",");
            sb.append(sss[0]).append(",").append(sss[1]);
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
            new Game_states(v,load);
            String str[] = save_slot[v].split(",");
            if(str.length <= 1) return;
            int x = Integer.parseInt(str[2]); 
            int y = Integer.parseInt(str[3]); 
            BaseFrame.frame_generator().panel_change(new CCharacter(x, y, Game_states.getTODAY()),v);
            load_panel_open = false;
        }
        y = 50*v;
    }

    private void setSave_slot() {
        Load load = new Load();
        save_slot = load.read();
    }
}