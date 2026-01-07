package game.panel.sub_panel;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import game.logic.Game_states;
import game.util.FontUtil;
import game.util.GameUtil;

public class Archive_panel extends JPanel implements MouseListener,MouseMotionListener{
    private Font font;
    private int cursor_i;
    private int focus_page;
    private String archive_list[] = {"/#A40000について.txt",
                                    "/20130417.txt",
                                    "/20130614.txt",
                                    "/20140309.txt",
                                    "/20240923.txt"};
    private List<String> txtlist = new ArrayList<>();
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
        g.drawRect(2,2,  210, 496);
        for(int i = 0; i < archive_list.length; i++) {
            g.drawString(archive_list[i].substring(1),10,(i*30)+30);
        }
        g.setColor(new Color(255,255,255,100));
        g.fillRect(4, 30*cursor_i+10, 200, 30);
        g.setColor(Color.WHITE);
        g.drawRect(215,2,  482, 496);
        if(txtlist.size() > 0) {
            for(int i = 0; i < txtlist.size(); i++) {
                g.drawString(txtlist.get(i+(focus_page*12)),220,(i*30)+30);
            }
        }
    }
    private void textRead(int i) {
        txtlist.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("gamedata/message_data/archive"+archive_list[i]))) {
            String str = br.readLine();
            while(str != null) {
                str = str.replace(".--.", Game_states.getName());
                txtlist.add(str);
                str = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        if(x <= 204) {
            IO.println(cursor_i);
            textRead(cursor_i);
        } else {

        }
        repaint();
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    public void mouseMoved(MouseEvent e) {
        cursor_i = (e.getY()-20)/30;
        if(cursor_i < 0) cursor_i = 0;
        if(archive_list.length <= cursor_i) cursor_i = archive_list.length-1;
        repaint();
    }
    public void mouseDragged(MouseEvent e) {}
}
