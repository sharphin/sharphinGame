package panel;

import java.awt.*;
import java.awt.event.*;

public final class Item_use{
    private static int x = 215, y = 150;
    static int v,d;

    static boolean enter;
    private static Color black = new Color(0, 0, 0, 150);
    private static String[] str = {"","","","","",""};
    private static int number;

    static void use_save_dispose(Graphics g, String str1, int num) {
        if(!enter) return;
        number = num;
        g.setColor(black);
        g.fillRoundRect(245,90, 200,150,10,10);
        g.setColor(Color.WHITE);
        g.drawRoundRect(245,90, 200,150,10,10);
        g.drawString(str1+"�܂����H",280,120);
        g.drawRoundRect(x,y, 85,30,10,10);
        g.drawString(str[num],265,155);
        g.drawString(str[num+1],350,155);
        if(num == 0) {
            g.drawString(str[num+2], 265,185);
        }
    }
    static void choose(KeyEvent e, int key) {
        if(key == KeyEvent.VK_LEFT)   d = 0;
        if(key == KeyEvent.VK_RIGHT)  d = 1;
        if(number == 0) {
            if(key == KeyEvent.VK_UP)   v = 0;
            if(key == KeyEvent.VK_DOWN) v = 1;
        } 
        x = (d*85)+260;
        y = (v*30)+135;
    }
}