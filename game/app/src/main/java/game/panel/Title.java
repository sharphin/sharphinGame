package game.panel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import game.util.FontUtil;
import game.util.GameUtil;

public class Title extends JPanel{
    private int y = 60;
    private Font font;
    private Font font2;
    private Load_paint lp;
    public Title(){
        lp = new Load_paint();
        setSize(GameUtil.PANEL_X, GameUtil.PANEL_Y);
        setBackground(Color.BLACK);
        FontUtil fl = new FontUtil();
        font = fl.setFontSize_Mplus1Code(30f);
        font2 = fl.setFontSize_Mplus1Code(50f);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(Load_paint.load_panel_open) {
            lp.paint_load(g);
            return;
        }
        g.setColor(Color.WHITE);
        g.setFont(font2);
        g.drawString("リベンジ", 240, 200);
        g.setFont(font);
        g.drawString("NEW GAME", 280, 280);
        g.drawString("LOAD", 310, 340);
        g.drawString("EXIT", 310, 400);
        g.fillRect(250,235+y,180,6);
    }
    public void yyy(int p) {
       y = 60 * p;
    }
    public void controll(int key) {
        lp.controll(key);
    }
}