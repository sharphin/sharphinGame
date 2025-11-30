package panel;
import java.awt.*;
import java.awt.event.*;

import frame.BaseFrame;
import logic.Game_states;
import save_load.Load;
import util.GameUtil;

public class Load_paint{
    private int x, y;

    private int v, d = 2;
    public static boolean load_panel_open;
    public static boolean load_confirm;
    private String save_slot[] = new String[GameUtil.MAX_SAVE_SLOT];

    Font font = new Font("Arial", Font.BOLD, 20);
    public Load_paint() {
        setSave_slot();
    }

    public void paint_load(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GameUtil.PANEL_X, GameUtil.PANEL_Y);
        g.setColor(Color.WHITE);
        g.setFont(font);
        for(int i = 0; i < GameUtil.MAX_SAVE_SLOT;i++) {
            g.drawString(save_slot[i], 300, (i+1)*50);
        }
        g.setColor(Color.WHITE);
        g.drawRect(300, 20+y, 300, 50);
    }
    public void controll(int key) {
        if(key == KeyEvent.VK_ESCAPE) {
            load_panel_open = false;
        }
        if(key == KeyEvent.VK_UP)      v--;
        if(key == KeyEvent.VK_DOWN)    v++;
        if(v < 0) v = GameUtil.MAX_SAVE_SLOT;
        if(v > save_slot_length()) v = 0;
        if(key == KeyEvent.VK_ENTER) {
            new Game_states(v);
            String str[] = save_slot[v].split(",");
            int x = Integer.parseInt(str[2]); 
            int y = Integer.parseInt(str[3]); 
            BaseFrame.frame_generator().panel_change(new main_panel.CCharacter(x, y),v);
            load_panel_open = false;
        }
        y = 50*v;
    }

    private void setSave_slot() {
        Load load = new Load();
        save_slot = load.read();
    }

    private int save_slot_length() {
        int i;
        for(i = 0; i < save_slot.length; i++) {
            String tmp[] = save_slot[i].split(",");
            if(tmp[0].equals("")) return i-1;
        }
        return 8;
    }
}