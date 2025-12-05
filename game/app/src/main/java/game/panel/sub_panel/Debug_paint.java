package game.panel.sub_panel;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;

import game.util.FontUtil;

public class Debug_paint {
    Font font;
    public Debug_paint() {

        FontUtil fl = new FontUtil();
        font = fl.setFontSize_Mplus1Code(30f);
    }
    public void paint_debug(Graphics g, int x, int y) {
        g.setColor(Color.WHITE);
        g.drawString("X: "+x,500,40);
        g.drawString("Y: "+y,570,40);
    }
}
