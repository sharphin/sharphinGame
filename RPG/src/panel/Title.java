package panel;
import java.awt.*;
import javax.swing.JPanel;

import util.GameUtil;

public class Title extends JPanel{

    Image title= Toolkit.getDefaultToolkit().getImage("RPG/gamedata/image/title.png");
    private int y = 50;

    Load_paint lp;
    public Title(){
        lp = new Load_paint();
        setSize(GameUtil.PANEL_X, GameUtil.PANEL_Y);
        setBackground(Color.BLACK);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(Load_paint.load_panel_open) {
            lp.paint_load(g);
            return;
        }
        g.drawImage(title, 0,0,this);
        g.setColor(Color.WHITE);

        g.fillRect(250,235+y,180,10);
    }
    public void yyy(int p) {
       y = 60 * p;
    }
    public void controll(int key) {
        lp.controll(key);
    }
}