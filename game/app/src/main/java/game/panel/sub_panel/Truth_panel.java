package game.panel.sub_panel;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

import game.util.GameUtil;

public class Truth_panel extends JPanel implements MouseListener {
    public Truth_panel() {
        setSize(GameUtil.PANEL_X, GameUtil.PANEL_Y);
        addMouseListener(this);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(new Color(176,196,222));
        g.fillRect(0, 0, GameUtil.PANEL_X, GameUtil.PANEL_Y);
    }
    public void mouseClicked(MouseEvent e) {
        IO.println("4444");
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    
}
