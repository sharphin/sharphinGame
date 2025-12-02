package panel;

import java.awt.event.*;
import java.awt.*;
import logic.Game_states;
import save_load.Load;
import save_load.Save;
import util.FontUtil;
import util.GameUtil;

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
        for(int i = 0; i < save_slot_length();i++) {
            StringBuilder sb = new StringBuilder();
            String sss[] = save_slot[i].split(",");
            sb.append(sss[0]).append(",").append(sss[1]);
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
        if(v < 0) v = save_slot.length+1;
        if(v > save_slot_length()) v = 0;
        if(key == KeyEvent.VK_ENTER) {
            save.write(v, save_slot);
            setSave_slot();
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
            if(tmp[0].equals("")) return i;
        }
        return 8;
    }
}
