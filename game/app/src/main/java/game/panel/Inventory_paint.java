package game.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import game.logic.Game_states;
import game.logic.Item;
import game.logic.Talk;
import game.main_panel.Maps;
import game.util.FontUtil;
import game.util.GameUtil;

public class Inventory_paint {
    Font font;
    Item item = new Item();
    private int index = 0;
    private Image itemImage = Toolkit.getDefaultToolkit().getImage("gamedata/item_data/items.png");
    public Inventory_paint() {
        FontUtil fl = new FontUtil();
        font = fl.setFontSize_Mplus1Code(30f);
    }
    public void paint_inventory(Graphics g) {
        for(int i = 0; i < GameUtil.MAX_ITEM; i++) {
            int cx = 0,cy = 0;
            g.setColor(new Color(255,255,255,150));
            g.fillRect((i*50)+150, 400, 50, 50);
            switch(Game_states.getInventory(i)) {
                case 0: cx = 0; cy = 0; break;
                case 7: cx = 32; cy = 0; break;
            }
            if(Game_states.getInventory(i) != -1) g.drawImage(itemImage, (i*50)+159, 409, (i*50)+191, 441, cx, cy, cx+32, cy+32, null);
            g.setColor(Color.BLACK);
            g.drawRect((i*50)+150, 400, 50, 50);

        }
        g.setColor(Color.WHITE);
        g.drawRect((index*50)+149,399, 52,52);
    }
    public void controll(int key, int x, int y, Maps maps, Talk_paint tp) {
        if(key == KeyEvent.VK_0) {
            Game_states.updateControll_state(GameUtil.PLAY);
        }
        switch(key) {
            case 56 -> index = 7;
            case 55 -> index = 6;
            case 54 -> index = 5;
            case 53 -> index = 4;
            case 52 -> index = 3;
            case 51 -> index = 2;
            case 50 -> index = 1;
            case 49 -> index = 0;
        }
        //System.out.println(Game_states.getInventory(index));
        if(key == KeyEvent.VK_SPACE) {
            int xxx = (x-4)>>5;
            int yyy = (y-4)>>5;
            for(int i = 0; i < 4;i++) {
                switch(i) {
                    case 1: xxx = (x+28)>>5;yyy = y>>5; break;
                    case 2: xxx = (x+28)>>5;yyy = (y+28)>>5; break;
                    case 3: xxx = x>>5;yyy = (y+28)>>5; break;
                }
                int ontile = hit_tile(xxx,yyy,maps);
                if(ontile < 0) {
                    int taken_item = maps.take_key_item(xxx,yyy);
                    item.setInventory(taken_item,getInventoryIndex());
                    break;
                } else if(ontile > 3000000) {
                    if(Game_states.getInventory(index) == 7) {
                        Game_states.updateControll_state((Game_states.getControll_state() & ~GameUtil.PLAY)+GameUtil.TALK);
                        new Talk(0, 1);
                        maps.door_open(xxx, yyy);
                    }
                }
            }
        }
    }
    public int getInventoryIndex() {
        return index;
    }
    private int hit_tile(int x, int y, Maps maps) {
        int ontile = maps.map_tile(x, y);
        return ontile;
    }
}
