package panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import logic.Game_states;
import util.FontUtil;
import util.GameUtil;

public class Inventory_paint {
    Font font;
    private int index = 0;
    private Image itemImage = Toolkit.getDefaultToolkit().getImage("RPG/gamedata/item_data/items.png");
    public Inventory_paint() {
        FontUtil fl = new FontUtil();
        font = fl.setFontSize_Mplus1Code(30f);
    }
    public void paint_inventory(Graphics g) {
        for(int i = 0; i < GameUtil.MAX_ITEM; i++) {
            int cx = 0,cy = 0;
            g.setColor(new Color(255,255,255,150));
            g.fillRect((i*50)+150, 400, 50, 50);
            switch(Game_states.getItem(i)) {
                case 0: cx = 0; cy = 0; break;
            }
            if(Game_states.getItem(i) != 0) g.drawImage(itemImage, (i*50)+159, 409, (i*50)+191, 441, cx, cy, cx+32, cy+32, null);
            g.setColor(Color.BLACK);
            g.drawRect((i*50)+150, 400, 50, 50);

        }
        g.setColor(Color.WHITE);
        g.drawRect((index*50)+149,399, 52,52);
    }
    public void controll(int key) {
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
    }
}
