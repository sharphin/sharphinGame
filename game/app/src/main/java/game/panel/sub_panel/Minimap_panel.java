package game.panel.sub_panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import game.util.FontUtil;
import game.util.GameUtil;

public class Minimap_panel extends JPanel implements MouseListener,MouseMotionListener{
    Rectangle bf1 = new Rectangle(20,20,80,50);
    Rectangle f1 = new Rectangle(100,20,80,50);
    Rectangle f2 = new Rectangle(180,20,80,50);
    Rectangle f3 = new Rectangle(260,20,80,50);
    Rectangle ff = new Rectangle(20,20,80,50);
    private Font font;
    private Image mapImage;
    private int focusbutton;
    private String btnText[] = new String[]{"B1F","1F","2F","3F"};
    public Minimap_panel() {
        setSize(GameUtil.PANEL_X, GameUtil.PANEL_Y);
        addMouseListener(this);
        addMouseMotionListener(this);
        FontUtil fl = new FontUtil();
        font = fl.setFontSize_Mplus1Code(30f);
        mapImage = Toolkit.getDefaultToolkit().getImage(GameUtil.FILE_PATH+"gamedata/image/minimapB1F.png");
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(176,196,222));
        g.fillRect(0, 0, GameUtil.PANEL_X, GameUtil.PANEL_Y);
        g.setFont(font);
        paintRect(g, 0,focusbutton, bf1);
        paintRect(g, 1,focusbutton, f1);
        paintRect(g, 2,focusbutton, f2);
        paintRect(g, 3,focusbutton, f3);
        g.setColor(new Color(255,255,255,100));
        g.fillRect((int)ff.getX(),(int)ff.getY(),(int)ff.getWidth(),(int)ff.getHeight());
        g.drawImage(mapImage, 20, 150, this);
    }
    private void paintRect(Graphics g,int num,int focus,Rectangle rect) {
        g.setColor(Color.BLACK);
        if(num == focus) {
            g.fillRect((int)rect.getX(),(int)rect.getY(),(int)rect.getWidth(),(int)rect.getHeight());
            g.setColor(Color.WHITE);
        } else {
            g.drawRect((int)rect.getX(),(int)rect.getY(),(int)rect.getWidth(),(int)rect.getHeight());
        }
        g.drawString(btnText[num],40+(num*80), 55);
    }
    public void mouseClicked(MouseEvent e) {
        if(bf1.contains(e.getX(),e.getY())) {
            focusbutton = 0;
            mapImage = Toolkit.getDefaultToolkit().getImage(GameUtil.FILE_PATH+"gamedata/image/minimapB1F.png");
        } else if(f1.contains(e.getX(),e.getY())){
            focusbutton = 1;
            mapImage = Toolkit.getDefaultToolkit().getImage(GameUtil.FILE_PATH+"gamedata/image/minimap1F.png");
        } else if(f2.contains(e.getX(),e.getY())) {
            focusbutton = 2;
            mapImage = Toolkit.getDefaultToolkit().getImage(GameUtil.FILE_PATH+"gamedata/image/minimap2F.png");
        } else if(f3.contains(e.getX(),e.getY())){
            focusbutton = 3;
            mapImage = Toolkit.getDefaultToolkit().getImage(GameUtil.FILE_PATH+"gamedata/image/minimap3F.png");
        }
        repaint();
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    public void mouseMoved(MouseEvent e) {
        if(bf1.contains(e.getX(),e.getY())) ff.setLocation(20,20);
        if(f1.contains(e.getX(),e.getY()))  ff.setLocation(100,20);
        if(f2.contains(e.getX(),e.getY()))  ff.setLocation(180,20);
        if(f3.contains(e.getX(),e.getY()))  ff.setLocation(260,20);
        repaint();
    }
    public void mouseDragged(MouseEvent e) {}
}
