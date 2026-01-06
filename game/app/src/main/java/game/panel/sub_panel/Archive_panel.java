package game.panel.sub_panel;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import game.util.FontUtil;
import game.util.GameUtil;

public class Archive_panel extends JPanel implements MouseListener,MouseMotionListener{
    private Font font;
    private int file_len = 2;
    private int cursor_i;
    private int fucus_page;
    public Archive_panel() {
        setSize(GameUtil.PANEL_X, GameUtil.PANEL_Y);
        addMouseListener(this);
        addMouseMotionListener(this);
        FontUtil fl = new FontUtil();
        font = fl.setFontSize_Mplus1Code(20f);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(new Color(176,196,222));
        g.fillRect(0, 0, GameUtil.PANEL_X, GameUtil.PANEL_Y);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawRect(2,2,  200, 496);
        g.drawString("#A40000について",10,40);
        g.drawString("2013/06/14",10,70);
        g.setColor(new Color(255,255,255,100));
        g.fillRect(4, 30*cursor_i+20, 190, 30);
    }
    public void mouseClicked(MouseEvent e) {
        cursor_i = (e.getY()-30)/30;
        if(cursor_i < 0) cursor_i = 0;
        IO.println(cursor_i);
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    public void mouseMoved(MouseEvent e) {
        cursor_i = (e.getY()-30)/30;
        if(cursor_i < 0) cursor_i = 0;
        repaint();
    }
    public void mouseDragged(MouseEvent e) {}
}
