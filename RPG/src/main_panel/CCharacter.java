package main_panel;
import panel.*;
import util.FontUtil;
import util.FormatUtil;
import util.GameUtil;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;

import javax.swing.JPanel;

import logic.Game_CLock;
import logic.Game_states;
public class CCharacter extends JPanel implements KeyListener,Runnable{
    private int dire;
    //private int direction;
    private int x, y, speed = GameUtil.WALK;

    private boolean front_leg_left;
    private final int width = GameUtil.PANEL_X+3, height = GameUtil.PANEL_Y+3;
    
    private Game_CLock clock;
    Font font;
    Maps maps = new Maps();
    Talk_panel cm = new Talk_panel();
    Pose_paint pp = new Pose_paint(); 
    Menu_paint mp = new Menu_paint();
    Inventory_paint ip = new Inventory_paint();
    public CCharacter(int x, int y, LocalDateTime clock) {
        this.x = x;
        this.y = y;
        setSize(width, height);
        addKeyListener(this);
        setFocusable(true);
        this.clock = new Game_CLock(clock);
        Thread th = new Thread(this);
        th.start();
        Charactor_walk char_walk = new Charactor_walk(); 
        char_walk.start();
        maps.loadMap(1);
        FontUtil fl = new FontUtil();
        font = fl.setFontSize_Mplus1Code(20f);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        maps.paint_map(g, scroll_x(), scroll_y());
        int xx = x+scroll_x();
        int yy = y+scroll_y();
        //int y1 = 120;
        //int x1 = 0;
        //if(dire != 0) direction = dire;
        //switch(direction) {
        //    case 1:  y1 = 40;   break;
        //    case 2:  y1 = 0;    break;
        //    case 3:  y1 = 80;   break;
        //    case 4:  y1 = 120;  break;
        //}
        g.setColor(Color.BLACK);
        g.setFont(font);
        g.drawString(clock.getNowTime().format(FormatUtil.format1),10, 30);
        g.fillRect(xx, yy, GameUtil.TILE, GameUtil.TILE);
        g.drawString("所持金:"+Game_states.getMoney(),500,30);
        if((Game_states.getControll_state() & GameUtil.INVENTORY) == GameUtil.INVENTORY) {
            ip.paint_inventory(g);
        }
        if((Game_states.getControll_state() & GameUtil.POSE) == GameUtil.POSE) {
            pp.paint_pose(g);
        } else if((Game_states.getControll_state() & GameUtil.MENU) == GameUtil.MENU) {
            mp.paint_items(g);
        }
    }
    private boolean can_move(int x, int y) {
        if(x < 0 || x >= GameUtil.MAP_X_LEN) return false;
        if(y < 0 || y >= GameUtil.MAP_Y_LEN) return false;    
        if(maps.map_coords(x, y) >= 1 &&
           maps.map_coords(x, y) <= 6) return false;     
        return true;
    }
    private void char_move() {
        int tile = GameUtil.TILE;
        int fx = 0, fx1 = 0, fy = 0, fy1 = 0;
        switch(dire) {
            case 1: fx = (x - speed) >> 5;
                    fy = y >> 5;
                    fy1 = (y+tile) >> 5;
                    if(can_move(fx,fy) && can_move(fx,fy1))  x -= speed;
                    break;
            case 2: fx = (x+tile + speed) >> 5;
                    fy = y >> 5;
                    fy1 = (y+tile) >> 5;
                    if(can_move(fx,fy) && can_move(fx,fy1))  x += speed;
                    break;
            case 3: fy = (y-speed) >> 5;
                    fx = x >> 5;
                    fx1 = (x+tile) >> 5;
                    if(can_move(fx,fy) && can_move(fx1,fy))  y -= speed;
                    break;
            case 4: fy = (y+tile+speed) >> 5;
                    fx = x >> 5;
                    fx1 = (x+tile) >> 5;
                    if(can_move(fx,fy) && can_move(fx1,fy))  y += speed;
                    break; 
        }
    }
    private int scroll_x() {
        int map_width = maps.map_x_length() << 5;
        if(x >= map_width-(width >> 1))  return width - map_width;
        if(x <= width >> 1) return 0;
        return (width >> 1) - x;
    }
    private int scroll_y() {
        int map_height = maps.map_y_length() << 5;
        if(y >= map_height-(height >> 1)) return height - map_height;
        if(y <= height >> 1) return 0;
        return (height >> 1) - y;
    }
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if((Game_states.getControll_state() & GameUtil.POSE) == GameUtil.POSE) {
            pp.controll(key,x,y);
        } else if((Game_states.getControll_state() & GameUtil.MENU) == GameUtil.MENU) {
            mp.controll(key);
        } else if((Game_states.getControll_state() & GameUtil.PLAY) == GameUtil.PLAY) {
            if(key == KeyEvent.VK_ESCAPE) {
                Game_states.updateControll_state((Game_states.getControll_state() & ~GameUtil.PLAY)+GameUtil.POSE);
            }
            if(key == KeyEvent.VK_CONTROL) {
                Game_states.updateControll_state((Game_states.getControll_state() & ~GameUtil.PLAY)+GameUtil.MENU);
            }
            if((Game_states.getControll_state() & GameUtil.INVENTORY) == GameUtil.INVENTORY) {
                ip.controll(key);
            } else {
                if(key == KeyEvent.VK_0) Game_states.updateControll_state(Game_states.getControll_state()+GameUtil.INVENTORY);
            }
            if(key == KeyEvent.VK_E)       speed = GameUtil.DASH;
            if(key == KeyEvent.VK_LEFT)    dire = 1;
            if(key == KeyEvent.VK_RIGHT)   dire = 2;
            if(key == KeyEvent.VK_UP)      dire = 3;
            if(key == KeyEvent.VK_DOWN)    dire = 4;
            if(key == KeyEvent.VK_SPACE) {}
            if(key == KeyEvent.VK_ENTER) {}
            
        }
        repaint();
    } 
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_E)   {
            speed = GameUtil.WALK;
        } else {
            dire = 0;
        }
    }

    public void keyTyped(KeyEvent e) {}
    public void run() {
        while(Game_states.getControll_state() != GameUtil.GAME_EXIT) {
            char_move();
            if((Game_states.getControll_state() & GameUtil.PLAY) == GameUtil.PLAY)repaint();
            try{
                Thread.sleep(14);
            } catch(InterruptedException e) {}
        }
    }
    private class Charactor_walk extends Thread {
        public void run() {
            while(Game_states.getControll_state() != GameUtil.GAME_EXIT) {
                if(dire != 0) front_leg_left = !front_leg_left;
                try{
                    Thread.sleep(300);
                } catch(InterruptedException e) {}
            }
        }
    }
}
