package game.main_panel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import game.frame.BaseFrame;
import game.logic.Game_states;
import game.logic.Talk;
import game.util.FontUtil;
import game.util.GameUtil;

public class Prologue_panel extends JPanel implements KeyListener,Runnable{

    private final int width = GameUtil.PANEL_X+3, height = GameUtil.PANEL_Y+3;
    private int message_line,i;
    private boolean hereMessage_tail = true;
    private boolean prologuefin;
    private String message_list[];
    private StringBuilder sb;
    private String name;
    Font font;
    public Prologue_panel(String name) {
        setSize(width, height);
        addKeyListener(this);
        setFocusable(true);
        this.name = name;
        sb = new StringBuilder();
        Thread th = new Thread(this);
        th.start();
        FontUtil fl = new FontUtil();
        font = fl.setFontSize_Mplus1Code(20f);
        new Talk();
        message_list = Talk.getMainMessage();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0,width, height);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString(sb.toString(),10,100);
    }
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_ENTER  && hereMessage_tail) {
            sb = new StringBuilder();
            hereMessage_tail = false;
            i = 0;
            message_line++;
        }
        if(key == KeyEvent.VK_SPACE) {
            new Game_states(name);
            prologuefin = true;
            BaseFrame.frame_generator().panel_change(new CCharacter(600, 500,6,(long)0,Game_states.getTodayTime()),1);
        }
        System.out.println(i+" "+message_line);
    }
    public void keyReleased(KeyEvent e) {}

    public void keyTyped(KeyEvent e) {}
    public void run() {
        int j =0;
        while(!prologuefin) {
            if(message_list == null) continue;
            if(i >= message_list[message_line].length()) {
                hereMessage_tail = true;
                continue;
            }

            sb.append(message_list[message_line].charAt(i));

            i++;
            repaint();
            try{
                Thread.sleep(30);
            } catch(InterruptedException e) {}

        }
    }
}
