package game.main_panel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import game.frame.BaseFrame;
import game.logic.Game_states;
import game.util.FontUtil;
import game.util.GameUtil;

public class Prologue_panel extends JPanel implements KeyListener,Runnable{

    private final int width = GameUtil.PANEL_X+3, height = GameUtil.PANEL_Y+3;
    private int message_line,i;
    private boolean hereMessage_tail;
    private boolean prologuefin = false;
    private String message_list[];
    private StringBuilder sb[] = new StringBuilder[2];
    private String name;
    private int sportsPerWeek;
    private int sportsPerDay;
    private int v = 1,bounds;

    Font font;
    public Prologue_panel(String name, boolean newGame_plus) {
        setSize(width, height);
        addKeyListener(this);
        setFocusable(true);
        this.name = name;
        message_list = loadPrologue();
        sb[0] = new StringBuilder();
        sb[1] = new StringBuilder();
        Thread th = new Thread(this);
        th.start();
        FontUtil fl = new FontUtil();
        font = fl.setFontSize_Mplus1Code(20f);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0,width, height);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString(sb[0].toString(),10,100);
        g.drawString(sb[1].toString(),10,140);
        if(message_line == 10) {
            for(int i = 1; i <= 7;i++) {
                g.drawString(i+"回 ",150,140+(i*40));
            }
            g.fillRect(150,145+(v*40),250,3);
        } else if(message_line == 11) {
            for(int i = 1; i <= 11;i+=3) {
                g.drawString(i+"時間 ~ "+(i+2)+"時間",150,180+((i/3)*40));
            }
            g.fillRect(150,145+(v*40),250,3);
        } else if(message_line == 12) {
            g.drawString(bounds+"時間",150,180);
            g.drawString((bounds+1)+"時間",150,220);
            g.drawString((bounds+2)+"時間",150,260);
            g.fillRect(150,145+(v*40),250,3);
        }
    }
    private String[] loadPrologue() {
        List<String> datas = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(GameUtil.FILE_PATH+"gamedata/message_data/prologue.dat"))) {
            String data;
            while ((data = br.readLine()) != null) {
                datas.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datas.toArray(new String[datas.size()]);
    }
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_ENTER && hereMessage_tail) {
            sb[0] = new StringBuilder();
            sb[1] = new StringBuilder();
            if(message_line == 10)sportsPerWeek = v;
            if(message_line == 1)sportsPerDay = bounds+1;
            if(message_line == 11) bounds = ((v-1)*3)+1;
            i = 0;
            v = 1;
            if(message_line+1 >= 18) {
                new Game_states(name, sportsPerWeek,sportsPerDay);
                prologuefin = true;
                BaseFrame.frame_generator().panel_change(new CCharacter(600, 500,7,(long)0,Game_states.getTodayTime(),true),1);
            } else {
                message_line++;
                hereMessage_tail = false;
            }
        }
        if(key == KeyEvent.VK_UP)      v--;
        if(key == KeyEvent.VK_DOWN)    v++;
        if(message_line == 6) {
            if(v <= 0) v = 7;
            if(v >= 8) v = 1;
        } else if(message_line == 7) {
            if(v <= 0) v = 4;
            if(v >= 5) v = 1;
        } else if(message_line == 8) {
            if(v <= 0) v = 3;
            if(v >= 4) v = 1;
        }
        repaint();
    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
    public void run() {
        try{
            Thread.sleep(2000);
        } catch(InterruptedException e) {}
        hereMessage_tail = true;
        while(!prologuefin) {
            if(i >= message_list[message_line].length()) {
                hereMessage_tail = true;
            } else {
                if(i < 31) {
                    sb[0].append(message_list[message_line].charAt(i));
                } else {
                    sb[1].append(message_list[message_line].charAt(i));
                }
            }
            i++;
            repaint();
            try{
                Thread.sleep(30);
            } catch(InterruptedException e) {}
        }
    }
}
