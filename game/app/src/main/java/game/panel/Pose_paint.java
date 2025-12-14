package game.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import game.logic.Game_states;
import game.save_load.Save;
import game.util.FontUtil;
import game.util.GameUtil;

public class Pose_paint{
    private int y = 0, v = 0;
    public boolean item_check, enter;
    Font font;
    Save save;
    Color black = new Color(0, 0, 0, 150);
    Save_paint sp = new Save_paint();
    public Pose_paint() {
        FontUtil fl = new FontUtil();
        font = fl.setFontSize_Mplus1Code(30f);
    }

    public void paint_pose(Graphics g) {
        g.setColor(black);
        g.fillRect(0, 0, GameUtil.PANEL_X, GameUtil.PANEL_Y);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("SAVE", 40, 50);

        g.drawString("BACK TITLE", 40, 100);
        g.setColor(Color.WHITE);
        g.fillRect(30, 65+y, 170, 2);
        if((Game_states.getControll_state() & GameUtil.SAVE) == GameUtil.SAVE) {
            sp.paint_save(g);
        }
    }
    public void controll(int key,int xx, int yy, int map_number, long play_time) {
        if(key == KeyEvent.VK_ESCAPE) {
            Game_states.updateControll_state((Game_states.getControll_state()+GameUtil.PLAY) & ~GameUtil.POSE);
        }
        if((Game_states.getControll_state() & GameUtil.SAVE) == GameUtil.SAVE) {
            if(save != null) sp.controll(key, save);
        } else {
            if(key == KeyEvent.VK_UP)      v--;
            if(key == KeyEvent.VK_DOWN)    v++;
            if(v < 0) v = 1;
            if(v > 1) v = 0;
            if(key == KeyEvent.VK_ENTER) {
                if(v == 0) {
                    save = new Save(xx, yy, map_number,play_time);
                    Game_states.updateControll_state(GameUtil.POSE + GameUtil.SAVE);
                }
                if(v == 1) {
                    Game_states.updateControll_state(GameUtil.GAME_EXIT);
                }
            }
            y = 50*v;
        }
    }
}
