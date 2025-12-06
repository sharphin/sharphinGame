package game.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import game.logic.Game_states;
import game.logic.Item;
import game.util.FontUtil;
import game.util.GameUtil;

public class Menu_paint{
    //private Image mapImage = Toolkit.getDefaultToolkit().getImage("RPG/gamedata/image/map.png");
    Item item;
    Color black;
    Font font1;
    Font font2;
    public Menu_paint() {
        item = new Item();
        black = new Color(0, 0, 0, 150);
        FontUtil fl = new FontUtil();
        font1 = fl.setFontSize_Mplus1Code(30f);
        font2 = fl.setFontSize_Mplus1Code(20f);
    }
    public void paint_items(Graphics g) {
        g.setColor(black);
        g.fillRect(0, 0, GameUtil.PANEL_X, GameUtil.PANEL_Y);
        g.setColor(Color.WHITE);
        g.setFont(font1);
        g.drawString("ITEMS", 40, 50);
        g.setFont(font2);
        for(int i = 0; i < GameUtil.MAX_ALL_ITEMS;i++) {
            g.drawString(item.item_name(i),400,(30*i)+50);
        }
    }
    public void controll(int key) {
        int dire = 0;
        if(key == KeyEvent.VK_CONTROL) {
            Game_states.updateControll_state((Game_states.getControll_state()+GameUtil.PLAY) & ~GameUtil.MENU);
        }

        if(key == KeyEvent.VK_UP)      dire = 3;
        if(key == KeyEvent.VK_DOWN)    dire = 4;
        if(dire == 1)
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
}