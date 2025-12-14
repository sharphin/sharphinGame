package game.frame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import game.panel.CharaCreate_panel;
import game.panel.Load_paint;
import game.panel.Title;
import game.util.GameUtil;

public class BaseFrame extends JFrame implements KeyListener{
    Title ti;
    Character charc;
    private int v = 1;
    private static BaseFrame frame = new BaseFrame();

    public static BaseFrame frame_generator() {
        return frame;
    }
    private BaseFrame() {
        super.add(ti = new Title());
        ImageIcon icon = new ImageIcon(GameUtil.FILE_PATH+"gamedata/image/icon.png");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300,100,GameUtil.FRAME_X, GameUtil.FRAME_Y);
        setTitle("Revenge");
        setVisible(true);
        setResizable(false);
        addKeyListener(this);
    }
    public void panel_change(JPanel panel, int v) {
        frame_generator().getContentPane().removeAll();	
        frame_generator().add(panel);
        if(v != 3) panel.requestFocus();
        repaint();
    }
    public void back_title(Title ti) {
        this.ti = ti;
        frame_generator().getContentPane().removeAll();	
        frame_generator().add(ti);
        this.requestFocus();
        repaint();
    }
    public void keyPressed(KeyEvent e) {
        int dire = 0;
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_UP)      dire = 3;
        if(key == KeyEvent.VK_DOWN)    dire = 4;
        if(Load_paint.load_panel_open) {
            ti.controll(key);
        } else if(!Load_paint.load_panel_open) {

            if(dire == 3 && v > 1) v--;
            if(dire == 4 && v < 3) v++;
        
            ti.yyy(v);
            if(key == KeyEvent.VK_ENTER) {
                if(v == 3) {
                    System.exit(0);
                } else if(v == 2) {
                    Load_paint.load_panel_open = true;
                    v = 1;
                } else {
                    panel_change(new CharaCreate_panel(),v);
                }
            }
        }
        repaint();
    } 
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}
