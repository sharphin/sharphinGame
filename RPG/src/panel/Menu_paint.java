package panel;
import util.FontUtil;
import util.GameUtil;

import java.awt.event.*;
import java.awt.*;
import logic.Game_states;

public class Menu_paint{
    private int x,y = 50;
    public boolean item_check, enter;

    Color black = new Color(0, 0, 0, 150);
    Font font = new Font("Arial", Font.BOLD, 20);

    public Menu_paint() {
        FontUtil fl = new FontUtil();
        font = fl.setFontSize_Mplus1Code(30f);
    }

    public void paint_items(Graphics g) {
        g.setColor(black);
        g.fillRect(0, 0, GameUtil.PANEL_X, GameUtil.PANEL_Y);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("ITEMS", 100, 150);
    }
    public void controll(int key) {
        int dire = 0;
        if(key == KeyEvent.VK_1) {
            Game_states.updateControll_state(GameUtil.PLAY);
        }

        if(key == KeyEvent.VK_UP)      dire = 3;
        if(key == KeyEvent.VK_DOWN)    dire = 4;
        if(key == KeyEvent.VK_ENTER) {

        } else {
        }
    }
    public void item_add(int floors[][][], int floor_num, int xx, int yy) {
        if(floors[floor_num][yy][xx] == 20) {
        }
    }
    static final int duplicate_check(String str) {
        return -1;
    }
    public void item_open() {
        item_check = !item_check;
    }
}