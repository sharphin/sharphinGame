package game.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import game.logic.Game_states;
import game.logic.TelePort;
import game.main_panel.CCharacter;
import game.main_panel.Maps;
import game.save_load.Save;
import game.util.FontUtil;
import game.util.GameUtil;

public class TelePort_paint {
    private StringBuilder tagName;
    private String error;
    private int v1,v2,state, cursor_i;
    private boolean shift;
    Font font;
    Save save;
    Color black = new Color(0, 0, 0, 150);
    Save_paint sp = new Save_paint();
    public TelePort_paint() {
        FontUtil fl = new FontUtil();
        font = fl.setFontSize_Mplus1Code(20f);
        tagName = new StringBuilder();
    }

    public void paint_teleport(Graphics g) {
        g.setColor(black);
        g.fillRect(0, 0, GameUtil.PANEL_X, GameUtil.PANEL_Y);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("TRAVEL", 40, 50);
        g.setColor(Color.WHITE);
        g.fillRect(200, 65+(v1*50), 250, 2);
        for(int i = 0; i < TelePort.size(); i++) {
            if(state < 2 || v1 != i)g.drawString(TelePort.getTagName(i), 200, 50+(i*50));
        }
        if(state == 1) {
            g.drawRoundRect(460, 70, 150, 130,5,5);
            g.drawString("移動", 480, 100);
            g.drawString("上書き", 480, 135);
            g.drawString("消去", 480, 170);
            g.fillRect(475, 105+(v2*35), 130, 2);
        }
        if(state >= 2) {
            if(tagName.isEmpty())g.drawString("名前を入力してください。", 200, 50+(v1*50));
            g.drawString(error, 460, 50);
            g.fillRect(200, 25+(v1*50), 2, 30);
            g.drawString(tagName.toString(), 200, 50+(v1*50));
            g.drawString(tagName.toString(), 200, 50+(v1*50));
        }
    }
    public void controll(int key, int map_num, int x, int y,Maps maps,CCharacter cha) {
        if(key == KeyEvent.VK_ENTER) {
            if(state == 0) {
                if(v1 > TelePort.size()-1 || TelePort.size() == 0) {
                    state = 2;
                } else {
                    state = 1;
                }
            } else if(state == 1) {
                if(v2 == 0) {
                    int tmp[] = TelePort.getToTeleportCoords(v1);
                    cha.coords_move(tmp[0], tmp[1]<<5,tmp[2]<<5);
                    Game_states.updateControll_state((Game_states.getControll_state()+GameUtil.PLAY) & ~GameUtil.TELEP);
                    reset();
                }
                if(v2 == 1) state = 3;
                if(v2 == 2) {
                    TelePort.markDelete(v1,maps);
                    reset();
                }

            } else if(state == 2){
                int result = TelePort.markTeleportCoords(map_num,x,y, tagName.toString(),maps);
                if(result == -1) {
                    error = "重複しています。";
                    return;
                }
                if(result == -2) {
                    error = "一文字以上入力してください。";
                    return;
                }
                if(result == -3) {
                    error = "座標が重複しています。";
                    return;
                }
                if(result == -4) {
                    error = "床以外にはマークできません。";
                    return;
                }
                reset();
            } else if(state == 3) {
                TelePort.markUpdate(v1, map_num, x, y, tagName.toString(),maps);
                reset();
            }
        }
        if(state == 0) {
            if(key == KeyEvent.VK_UP && v1 > 0)   v1--; 
            if(key == KeyEvent.VK_DOWN && v1 < TelePort.size()) v1++;
        } else if(state == 1) {
            if(key == KeyEvent.VK_UP && v2 > 0)   v2--; 
            if(key == KeyEvent.VK_DOWN && v2 < 2) v2++;
        }
        if(key == KeyEvent.VK_T && state < 2) {
            Game_states.updateControll_state((Game_states.getControll_state()+GameUtil.PLAY) & ~GameUtil.TELEP);
            state_init();
        }
        if(state >= 2) {
            error = "";
            if(key == KeyEvent.VK_SHIFT) shift = !shift;
            switch(key) {
                case 127 -> name_delete(cursor_i);
                case 90 -> name_type(key);
                case 89 -> name_type(key);
                case 88 -> name_type(key);
                case 87 -> name_type(key);
                case 86 -> name_type(key);
                case 85 -> name_type(key);
                case 84 -> name_type(key);
                case 83 -> name_type(key);
                case 82 -> name_type(key);
                case 81 -> name_type(key);
                case 80 -> name_type(key);
                case 79 -> name_type(key);
                case 78 -> name_type(key);
                case 77 -> name_type(key);
                case 76 -> name_type(key);
                case 75 -> name_type(key);
                case 74 -> name_type(key);
                case 73 -> name_type(key);
                case 72 -> name_type(key);
                case 71 -> name_type(key);
                case 70 -> name_type(key);
                case 69 -> name_type(key);
                case 68 -> name_type(key);
                case 67 -> name_type(key);
                case 66 -> name_type(key);
                case 65 -> name_type(key);
                case 57 -> name_type('9');
                case 56 -> name_type('8');
                case 55 -> name_type('7');
                case 54 -> name_type('6');
                case 53 -> name_type('5');
                case 52 -> name_type('4');
                case 51 -> name_type('3');
                case 50 -> name_type('2');
                case 49 -> name_type('1');
                case 48 -> name_type('0');
                case 39 -> cursor_inc();
                case 37 -> cursor_dec();
                case 32 -> name_type(' ');
                case 8  -> name_backspace(cursor_i);
            }
            if(key == KeyEvent.VK_ESCAPE) reset();
        }
    }
    private void reset() {
        tagName.delete(0, tagName.length());
        state = 0;
        cursor_i = 0;
    }
    private void name_type(int token) {
        if(!shift) token = token |32;
        tagName.insert(cursor_i, (char)token);
        cursor_i++;
    }
    private void name_backspace(int i) {
        if(i <= 0 || tagName.length() <= 0) return;
        tagName.deleteCharAt(i-1);
        cursor_i--;
    }
    private void name_delete(int i) {
        if(i >= tagName.length()) return;
            tagName.deleteCharAt(i);
    }
    private void cursor_inc() {
        if(cursor_i < tagName.length()) cursor_i++;
    }
    private void cursor_dec() {
        if(cursor_i > 0) cursor_i--;
    }
    private void state_init() {
        state = 0;
        v1 = 0;
    }
}
