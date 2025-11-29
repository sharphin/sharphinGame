package frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.*;

import panel.Load_panel;
import panel.Title;
import util.GameUtil;

public class BaseFrame extends JFrame implements KeyListener{
    Title ti = new Title();
    Load_panel lp = new Load_panel();
    Character charc;
    private int v = 1;
    private static BaseFrame frame = new BaseFrame();

    public static BaseFrame frame_generator() {
        return frame;
    }
    private BaseFrame() {
        super.add(ti);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300,100,GameUtil.FRAME_X, GameUtil.FRAME_Y);
        setTitle("Game");
        setVisible(true);
        setResizable(false);
        addKeyListener(this);
    }
    public void panel_change(JPanel panel, int v) {
        frame_generator().getContentPane().removeAll();	
        frame_generator().add(panel);
        if(v != 2) panel.requestFocus();
        repaint();
    }
    public void keyPressed(KeyEvent e) {
        int dire = 0;
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_UP)      dire = 3;
        if(key == KeyEvent.VK_DOWN)    dire = 4;
        if(Load_panel.load_panel_open) {
            lp.load_panel_controll(e, key);
        } else if(!Load_panel.load_panel_open) {

            if(dire == 3 && v > 1) v--;
            if(dire == 4 && v < 3) v++;
        
            ti.aaa(v);
            if(key == KeyEvent.VK_ENTER) {
                if(v == 3) {
                    System.exit(0);
                } else if(v == 2) {
                    Load_panel.load_panel_open = true;
                    v = 1;
                } else {
                    panel_change(new main_panel.CCharacter(85, 125, GameUtil.PANEL_Y, GameUtil.PANEL_X),v);
                }
            } 
        }
        repaint();
    } 
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}
