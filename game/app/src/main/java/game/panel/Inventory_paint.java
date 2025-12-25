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
    private int v = 0;
    private Image itemImage = Toolkit.getDefaultToolkit().getImage(GameUtil.FILE_PATH+"gamedata/item_data/items.png");
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
                case 19:  cx = 32; cy = 0; break;
            }
            if(Game_states.getInventory(i) != 0) g.drawImage(itemImage, (i*50)+159, 409, (i*50)+191, 441, cx, cy, cx+32, cy+32, null);
            g.setColor(Color.BLACK);
            g.drawRect((i*50)+150, 400, 50, 50);
        }
        if((Game_states.getControll_state() & GameUtil.ITEM_DELETE) == GameUtil.ITEM_DELETE) {
            g.setColor(new Color(0,0,0,150));
            g.fillRoundRect(440, 300, 100, 99, 5, 5);
            g.setColor(Color.WHITE);
            g.drawRoundRect(440, 300, 100, 99, 5, 5);
            g.drawString("置く",450,325);
            g.drawString("捨てる",450,355);
            g.drawString("閉じる",450,385);
            g.fillRect(450,(30*v)+330,70,3);
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
        if((Game_states.getControll_state() & GameUtil.ITEM_DELETE) == GameUtil.ITEM_DELETE) {
            if(key == KeyEvent.VK_UP && v-1 >= 0) v--;
            if(key == KeyEvent.VK_DOWN && v+1 <= 2) v++;
            if(key == KeyEvent.VK_ENTER) {
                if(v == 0) {
                    maps.put_key_item(Game_states.getInventory(index), (x+14)>>5, (y+14)>>5);
                    item.removeInventory(index);
                } else if(v == 1){
                    item.removeInventory(index);
                }
                Game_states.updateControll_state(Game_states.getControll_state() & ~GameUtil.ITEM_DELETE);
            }
        }
        if(key == KeyEvent.VK_DELETE && Game_states.getInventory(index)!=0) {
            Game_states.updateControll_state(Game_states.getControll_state() | GameUtil.ITEM_DELETE);
        }
        if(key == KeyEvent.VK_SPACE) {
            boolean door_open = false;
            boolean diff_key = false;
            for(int i = 0; i < 9;i++) {
                int xxx = (x+14)>>5;
                int yyy = (y+14)>>5;
                switch(i) {
                    case 1: xxx--;yyy--; break;
                    case 2: xxx--;       break;
                    case 3: xxx--;yyy++; break;
                    case 4: xxx++;yyy--; break;
                    case 5: xxx++;       break;
                    case 6: xxx++;yyy++; break;
                    case 7: yyy--; break;
                    case 8: yyy++; break;
                }
                int ontile = hit_tile(xxx,yyy,maps);
                if(ontile < 0) {
                    int taken_item = maps.ref_key_item(xxx,yyy);
                    int setslot = item.setInventory(taken_item,getInventoryIndex());
                    if(setslot == -1)  {
                        new Talk("",1, 4);
                    } else {
                        maps.take_key_item(xxx, yyy);
                        new Talk(item.item_name(Game_states.getInventory(setslot)),1, 2);
                    }
                    break;
                } else if(ontile > 3000000 && Game_states.getInventory(index) != -1) {
                    if(Item.key_check(Game_states.getInventory(index), ontile)) {
                        door_open = true;
                        maps.door_open(xxx, yyy);
                    } else {
                        diff_key = true;
                    }
                }
            }
            if(door_open) {
                new Talk(item.item_name(Game_states.getInventory(index)),1, 1);
            } else if(diff_key){
                new Talk("",1, 3);
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
