package main_panel;
import panel.*;
import panel.epi_and_pro.*;

import save_load.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
public class CCharacter extends JPanel implements KeyListener,Runnable{

    private Image jiki= Toolkit.getDefaultToolkit().getImage("image/enemy1.png");

    private int dire, direction;
    private int x, y,floor_num; 

    private boolean front_leg_left, new_game;
    private final int width, height;
    private int alpha = 255;
    private int next_serif;
    private final Font font = new Font("HGPGothicM", Font.PLAIN, 15);
    

    Maps maps = new Maps();
    Prologue pro = new Prologue();
    Communicate cm = new Communicate(); 

    public CCharacter(int x, int y, int floor_num, boolean new_game, int height, int width) {
        this.new_game = new_game;
        this.x = x;
        this.y = y;
        this.floor_num = floor_num;
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
    public void paintComponent(Graphics g) {//�`��
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON); 

        maps.paint_map(g, scroll_x(), scroll_y(), floor_num);
        int xx = x-14+scroll_x();
        int yy = y-45+scroll_y();
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
        g.fillRect(xx, yy, 15, 15);
        //g.drawImage(jiki, xx, yy, xx+28, yy+28, x1, y1, x1+40, y1+40, null);
    }
    private boolean can_move(int x, int y) {
        if(maps.map_coords(x, y) >= 1 &&
           maps.map_coords(x, y) <= 69) return false;     
        return true;
    }
    private void char_move() {
        int speed = 3;
        int fx = 0, fx1 = 0, fy = 0, fy1 = 0;
        switch(dire) {
            case 1: fx = (x-7 - speed) >> 5;
                    fy = (y-7) >> 5;
                    fy1 = (y+7) >> 5;
                    if(can_move(fx,fy) && can_move(fx,fy1))  x -= speed;
                    break;
            case 2: fx = (x+7 + speed) >> 5;
                    fy = (y-7) >> 5;
                    fy1 = (y+7) >> 5;
                    if(can_move(fx,fy) && can_move(fx,fy1))  x += speed;
                    break;
            case 3: fy = (y-13-speed) >> 5;
                    fx = (x-5) >> 5;
                    fx1 = (x+5) >> 5;
                    if(can_move(fx,fy) && can_move(fx1,fy))  y -= speed;
                    break;
            case 4: fy = (y+13+speed) >> 5;
                    fx = (x-5) >> 5;
                    fx1 = (x+5) >> 5;
                    if(can_move(fx,fy) && can_move(fx1,fy))  y += speed;
                    break; 
        }
        Save.save_coords(x,y,floor_num);
    }
    private void floor_change(int xx, int yy) {
        if(maps.map_coords(xx,yy) == 71) {
            x = 530;  floor_num++;
            y = 80;  direction = 3;
            direction = 4;
        } else if(maps.map_coords(xx,yy) == 70) {
            x = 592;  floor_num--;
            y = 80;  direction = 3;
            direction = 4;
        }    
    }
    private int scroll_x() {
        int map_width = maps.map_x_length();
        int panel_width = map_width << 5;
        if(x >= panel_width-(width >> 1))  return width - panel_width;
        if(x <= width >> 1) return 0;
        return (width >> 1) - x;
    }
    private int scroll_y() {
        int map_height = maps.map_y_length();
        int panel_height = map_height << 5;
        if(y >= panel_height-((height >> 1)+32)) return height - panel_height+32;
        if(y <= height >> 1) return 0;
        return (height >> 1) - y;
    }
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_ENTER && new_game && pro.can_serif_change()) {
            if(next_serif < 4) {
                pro.serif_change(++next_serif);
            } else if(next_serif >= 4) {
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
            if(cm.battle_start()) cm.communicate_flg(floor_num, object_num,false); 
        }
    } 
    public void keyReleased(KeyEvent e) {
        dire = 0;
    }
    public static final void array_trans_form(int x, int y, int floor_num, int after) {
        //floors[floor_num][x][y] = after;
    }
    public void keyTyped(KeyEvent e) {}
    public void run() {
        while(true) {
            char_move();
            if(next_serif > 4 && alpha > 1) { alpha -= 2;
            } else if(alpha <= 1) { new_game = false;}
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
