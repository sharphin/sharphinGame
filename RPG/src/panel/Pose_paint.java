package panel;

import java.awt.event.*;
import java.awt.*;

import frame.BaseFrame;
import logic.Game_states;
import save_load.Save;
import util.FontUtil;
import util.GameUtil;
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
        g.drawString("SAVE", 80, 50);

        g.drawString("BACK TITLE", 80, 100);
        g.setColor(Color.WHITE);
        g.drawRect(70, 20+y, 150, 50);
        if((Game_states.getControll_state() & GameUtil.SAVE) == GameUtil.SAVE) {
            sp.paint_save(g);
        }
    }
    public void controll(KeyEvent e, int key,int xx, int yy) {
        if(key == KeyEvent.VK_ESCAPE) {
            Game_states.updateControll_state(1);
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
                    save = new Save(xx, yy);
                    Game_states.updateControll_state(GameUtil.POSE + GameUtil.SAVE);
                }
                if(v == 1) BaseFrame.frame_generator().back_title(new Title());
            }
            System.out.println(v);
            y = 50*v;
        }
    }
}
