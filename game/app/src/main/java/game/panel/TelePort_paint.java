package game.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import game.logic.Game_states;
import game.save_load.Save;
import game.util.FontUtil;
import game.util.GameUtil;

public class TelePort_paint {
    private int state = 0;
    private int y = 0, v = 0;
    public boolean item_check, enter;
    Font font;
    Save save;
    Color black = new Color(0, 0, 0, 150);
    Save_paint sp = new Save_paint();
    public TelePort_paint() {
        FontUtil fl = new FontUtil();
        font = fl.setFontSize_Mplus1Code(20f);
    }

    public void paint_teleport(Graphics g) {
        g.setColor(black);
        g.fillRect(0, 0, GameUtil.PANEL_X, GameUtil.PANEL_Y);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("TRAVEL", 40, 50);
        g.setColor(Color.WHITE);
        g.fillRect(250, 65+y, 250, 2);
        for(int i = 0;i < 8; i++) {
            g.drawString(i+"", 250, 50+(i*50));
        }
    }
    public void controll(int key) {
        if(key == KeyEvent.VK_T) {
            Game_states.updateControll_state((Game_states.getControll_state()+GameUtil.PLAY) & ~GameUtil.TELEP);
            state_init();
        }
        if(key == KeyEvent.VK_ENTER) {
            state++;
        }
    }
    private void state_init() {
        state = 0;
        v = 0;
    }
}
