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
    private int line, itemline;
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
        g.drawString("ITEM DICT", 40, 50);
        g.setFont(font2);
        for(int i = 0; i <= 12;i++) {
            g.drawString(item.item_name(i+itemline),400,(30*i)+70);
        }
        g.fillRect(400,(30*line)+75,250,3);
        g.fillRect(672,scroll_y()+50,3,10);
        g.fillRect(670,39,7,10);
        g.fillRect(670,423,7,10);
    }
    public void controll(int key) {
        if(key == KeyEvent.VK_CONTROL) {
            line = 0; itemline = 0;
            Game_states.updateControll_state((Game_states.getControll_state()+GameUtil.PLAY) & ~GameUtil.MENU);
        }

        if(key == KeyEvent.VK_UP) {
            if(line > 0) {line--;}
            else {itemline--;}
        }
        if(key == KeyEvent.VK_DOWN){
            if(line < 12) {line++;}
            else {itemline++;}
        }
        if(itemline < 0) {
            itemline = GameUtil.MAX_ALL_ITEMS-13;
            line = 12;
        }
        if(itemline > GameUtil.MAX_ALL_ITEMS-13) {
            itemline = 0;
            line = 0;
        }
        if(key == KeyEvent.VK_ENTER) {}
    }
    private int scroll_y() {
        return ((itemline+line)*30)/21;
    }
}