
package game.main_panel;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

import game.logic.Game_states;
import game.util.FontUtil;
import game.util.GameUtil;

public class GameEnding extends JPanel implements Runnable{
    
    private final int width = GameUtil.PANEL_X+3, height = GameUtil.PANEL_Y+3;
    private String endingMessage;
    Font font;
    public GameEnding(String endingMessage) {
        this.endingMessage = endingMessage;
        setSize(width, height);
        setFocusable(true);
        Thread th = new Thread(this);
        th.start();
        FontUtil fl = new FontUtil();
        font = fl.setFontSize_Mplus1Code(20f);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString(endingMessage,50,100);
    }
    public void run() {
        try{
            Thread.sleep(2000);
        } catch(InterruptedException e) {}
        Game_states.updateControll_state(GameUtil.GAME_EXIT);
    }
}