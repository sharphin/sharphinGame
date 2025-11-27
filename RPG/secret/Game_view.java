package secret;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Game_view extends JPanel implements MouseListener,Runnable{

    Game_logic gl = new Game_logic();
    private int map[][];
    public Game_view(int map[][]) {
        this.map = map;
        setSize(GameUtil.PANEL_X, GameUtil.PANEL_Y);
        addMouseListener(this);
    }
    public void paint(Graphics g) {
        g.setColor(new Color(34,139,34));
        g.fillRect(1,1,WIDTH-1, HEIGHT-1);
        for(int y = 0;y < GameUtil.MAP_Y;y++) {
            for(int x = 0;x < GameUtil.MAP_X;x++) {
                g.setColor(Color.BLACK);
                switch (map[y][x]){
                    case 1:  g.setColor(Color.BLACK);      break;
                    case 0:  g.setColor(Color.WHITE);      break;
                }
                g.fillRect((y*GameUtil.MASU),(x*GameUtil.MASU),GameUtil.MASU,GameUtil.MASU);
            }
        }
    }
    public void run() {
        while(true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }
    }
    public void mouseClicked(MouseEvent e) {
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}