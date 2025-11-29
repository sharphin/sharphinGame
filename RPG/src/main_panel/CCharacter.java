package main_panel;
import panel.*;

import save_load.*;
import util.GameUtil;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
public class CCharacter extends JPanel implements KeyListener,Runnable{
    private int dire, direction;
    private int x, y;

    private boolean front_leg_left;
    private final int width, height;
    private int alpha = 255;
    private int next_serif;
    private final Font font = new Font("HGPGothicM", Font.PLAIN, 15);
    

    Maps maps = new Maps();
    Talk cm = new Talk(); 

    public CCharacter(int x, int y,int height, int width) {
        this.x = x;
        this.y = y;
        this.width = width; 
        this.height = height;

        setSize(width, height);
        addKeyListener(this);
        setFocusable(true);

        Thread th = new Thread(this);
        th.start(); 
        Charactor_walk char_walk = new Charactor_walk(); 
        char_walk.start();
        maps.loadMap(1);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON); 

        maps.paint_map(g, scroll_x(), scroll_y());
        int xx = x+scroll_x();
        int yy = y+scroll_y();
        int y1 = 120;
        int x1 = 0;
        if(dire != 0) direction = dire;
        switch(direction) {
            case 1:  y1 = 40;   break;
            case 2:  y1 = 0;    break;
            case 3:  y1 = 80;   break;
            case 4:  y1 = 120;  break; 
        }
        g.setColor(Color.BLACK);
        g.fillRect(xx, yy, GameUtil.TILE, GameUtil.TILE);
    }
    private boolean can_move(int x, int y) {
        if(x < 0 || x >= GameUtil.MAP_X_LEN) return false;
        if(y < 0 || y >= GameUtil.MAP_Y_LEN) return false;    
        if(maps.map_coords(x, y) >= 1 &&
           maps.map_coords(x, y) <= 69) return false;     
        return true;
    }
    private void char_move() {
        int speed = GameUtil.SPEED;
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
        Save.save_coords(x,y);
    }
    private void floor_change(int xx, int yy) {
        if(maps.map_coords(xx,yy) == 71) {
            x = 530;
            y = 80;  direction = 3;
            direction = 4;
        } else if(maps.map_coords(xx,yy) == 70) {
            x = 592;
            y = 80;  direction = 3;
            direction = 4;
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
        if(key == KeyEvent.VK_ENTER) {
            if(next_serif >= 4) {
                next_serif++;
            }
        }
        int xx = x >> 5;
        int yy = y >> 5;

        int object_num = maps.map_coords(xx,yy);

        if(key == KeyEvent.VK_LEFT)    dire = 1;
        if(key == KeyEvent.VK_RIGHT)   dire = 2;
        if(key == KeyEvent.VK_UP)      dire = 3;
        if(key == KeyEvent.VK_DOWN)    dire = 4;
        if(key == KeyEvent.VK_SPACE && cm.flgs == 0) {
            floor_change(xx,yy);
            if(cm.battle_start()) cm.communicate_flg(object_num,false); 
        }
    } 
    public void keyReleased(KeyEvent e) {
        dire = 0;
    }

    public void keyTyped(KeyEvent e) {}
    public void run() {
        while(true) {
            char_move();
            if(next_serif > 4 && alpha > 1) alpha -= 2;
            repaint();
            try{
                Thread.sleep(14);
            } catch(InterruptedException e) {}
        }
    }
    private class Charactor_walk extends Thread {
        public void run() {
            while(true) {
                if(dire != 0) front_leg_left = !front_leg_left;
                try{
                    Thread.sleep(300);
                } catch(InterruptedException e) {}
            }
        }
    }
}
