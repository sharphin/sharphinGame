package game.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import game.logic.Game_states;
import game.logic.Item;
import game.util.FontUtil;
import game.util.GameUtil;

public class Menu_paint{
    Item item;
    Color black;
    Font font1;
    Font font2;
    private int line, itemline,cx = 0,cy = 0;
    private Image itemImage;
    private String explain[] = new String[255];
    public Menu_paint() {
        item = new Item();
        black = new Color(0, 0, 0, 150);
        FontUtil fl = new FontUtil();
        font1 = fl.setFontSize_Mplus1Code(30f);
        font2 = fl.setFontSize_Mplus1Code(20f);
        itemImage = Toolkit.getDefaultToolkit().getImage(GameUtil.FILE_PATH+"gamedata/item_data/items.png");
        explain = item.loadexplain();
    }
    public void paint_items(Graphics g) {
        g.setColor(black);
        g.fillRect(0, 0, GameUtil.PANEL_X, GameUtil.PANEL_Y);
        g.setColor(Color.WHITE);
        g.setFont(font1);
        g.drawString("ITEM DICT", 40, 50);
        int items_num = Game_states.searchItemDictionary(itemline+line+1);
        switch(items_num) {
            case 1:   cx = 0; cy = 0; break;
            case 2:   cx = 32; cy = 0; break;
            case 3:   cx = 64; cy = 0; break;
            case 4:   cx = 96; cy = 0; break;
            case 5:   cx = 128; cy = 0; break;
            case 6:   cx = 160; cy = 0; break;
            case 7:   cx = 192; cy = 0; break;
            case 8:   cx = 224; cy = 0; break;
            case 9:   cx = 256; cy = 0; break;
            case 10:  cx = 288; cy = 0; break;
            case 11:  cx = 320; cy = 0; break;
            case 12:  cx = 352; cy = 0; break;
            case 13:  cx = 384; cy = 0; break;
            case 14:  cx = 416; cy = 0; break;
            case 15:  cx = 448; cy = 0; break;
            case 16:  cx = 512; cy = 0; break;
            case 19:  cx = 64; cy = 0; break;
            case 20:  cx = 480; cy = 0; break;
        }
        g.setFont(font2);
        if (items_num != -1) {
            g.drawImage(itemImage, 420, 80, 490, 150, cx, cy, cx+32, cy+32, null);
            g.drawString(explain[itemline+line], 350, 200);
        }
        for(int i = 0; i <= 12;i++) {
            g.drawString(i+itemline+1+": ",50,(30*i)+90);
            g.drawString(item.item_name(i+itemline+1),100,(30*i)+90);
        }
        g.fillRect(10,(30*line)+95,250,3);
        g.fillRect(282,scroll_y()+70,3,10);
        g.fillRect(280,59,7,10);
        g.fillRect(280,443,7,10);
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