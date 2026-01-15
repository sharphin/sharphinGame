package game.main_panel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDateTime;

import javax.swing.JPanel;

import game.frame.BaseFrame;
import game.logic.Game_CLock;
import game.logic.Game_states;
import game.logic.Message;
import game.logic.Puzzle;
import game.logic.TelePort;
import game.panel.Inventory_paint;
import game.panel.Menu_paint;
import game.panel.Message_paint;
import game.panel.PC_paint;
import game.panel.Pose_paint;
import game.panel.Talk_paint;
import game.panel.TelePort_paint;
import game.panel.sub_panel.Debug_paint;
import game.util.FontUtil;
import game.util.FormatUtil;
import game.util.GameUtil;

public class CCharacter extends JPanel implements KeyListener,Runnable{
    private int dire;
    private int direction;
    private Image charImage = Toolkit.getDefaultToolkit().getImage(GameUtil.FILE_PATH+"gamedata/image/sharphin.png");
    private int x, y, speed = GameUtil.WALK;
    private int hit_tile;
    private boolean front_leg_left;
    private boolean prologue;
    private final int width = GameUtil.PANEL_X+3, height = GameUtil.PANEL_Y+3;
    private final Color near_black = new Color(20);
    
    private int mapnum;

    private Game_CLock clock;
    Font font;
    Maps maps;
    Talk_paint tp;
    Pose_paint pp;
    PC_paint pcp;
    Menu_paint mp;
    Inventory_paint ip;
    Debug_paint dp;
    Message_paint mep;
    TelePort_paint tepp;
    Puzzle puzzle1;
    TelePort tep;
    public CCharacter(int x, int y, int map_number,Long play_time, LocalDateTime clock ,boolean prologue,boolean escaped) {
        this.x = x;
        this.y = y;
        setSize(width, height);
        addKeyListener(this);
        setFocusable(true);
        this.clock = new Game_CLock(clock,play_time);
        Charactor_walk char_walk = new Charactor_walk(); 
        char_walk.start();
        tep = new TelePort();


        mapnum = map_number;
        maps = new Maps();
        maps.loadMap(map_number,escaped,tep);
        tp = new Talk_paint();
        pcp = new PC_paint();
        mp = new Menu_paint();
        ip = new Inventory_paint();
        dp = new Debug_paint();
        mep = new Message_paint();
        pp = new Pose_paint();
        tepp = new TelePort_paint();
        FontUtil fl = new FontUtil();
        puzzle1 = new Puzzle();
        font = fl.setFontSize_Mplus1Code(20f);
        Thread th = new Thread(this);
        th.start();
        repaint();
        this.prologue = prologue;
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(near_black);
        g.fillRect(0, 0, width, height);
        int scroll_x = scroll_x();
        int scroll_y = scroll_y();
        int xx = x+scroll_x;
        int yy = y+scroll_y;
        int x1 = 0;
        if(dire != 0) direction = dire;
        switch(direction) {
            case 1:  x1 = 96;  break;
            case 2:  x1 = 32;  break;
            case 3:  x1 = 0;   break;
            case 4:  x1 = 64;  break;
        }
        maps.paint_map(g, scroll_x, scroll_y);
        g.drawImage(charImage, xx, yy-16, xx+28, yy+28,x1, 0, x1+GameUtil.TILE, 64, null);
        maps.paint_map2(g, scroll_x, scroll_y);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString(clock.getNowTime().format(FormatUtil.format1),10, 30);
        if((Game_states.getControll_state() & GameUtil.INVENTORY) == GameUtil.INVENTORY) {
            ip.paint_inventory(g);
        }
        if((Game_states.getControll_state() & GameUtil.POSE) == GameUtil.POSE) {
            pp.paint_pose(g);
        } else if((Game_states.getControll_state() & GameUtil.MENU) == GameUtil.MENU) { 
            mp.paint_items(g);
            repaint();
        } else if((Game_states.getControll_state() & GameUtil.PC) == GameUtil.PC){
            pcp.paint_pc(g,this);
        }
        if((Game_states.getControll_state() & GameUtil.DEBUG) == GameUtil.DEBUG){
            dp.paint_debug(g,x,y,maps.map_number());
        }
        if((Game_states.getControll_state() & GameUtil.TALK) == GameUtil.TALK){
            tp.paint_message(g);
        } else if((Game_states.getControll_state() & GameUtil.MESSAGE) == GameUtil.MESSAGE){
            mep.paint_message(g);
        } else if((Game_states.getControll_state() & GameUtil.TELEP) == GameUtil.TELEP){
            tepp.paint_teleport(g);
        }
        if(prologue) repaint();
    }
    private boolean can_move(int x, int y) {
        if(x < 0 || x >= GameUtil.MAP_X_LEN) return false;
        if(y < 0 || y >= GameUtil.MAP_Y_LEN) return false;
        int tmp = hit_tile(x, y);
        if(tmp >= 102 && tmp <= 106) return true;
        if(tmp >= 5) return false;
        return true;
    }
    private int hit_tile(int x, int y) {
        int ontile = maps.map_tile(x, y);
        if(ontile >= maps.getMapMoveKey()) {
            if(ontile <= 3000000) {
                if(ontile == 2999999) {
                    Game_states.updateControll_state(GameUtil.GAME_END);
                    BaseFrame.frame_generator().panel_change(new GameEnding("GAME CLEAR"), 1);
                    return 0;
                }
                map_move(ontile-maps.getMapMoveKey(),x,y);
            } else if((Game_states.getControll_state() & GameUtil.TALK) != GameUtil.TALK) {
                int message_id = 0;
                if(ontile == 4194408) message_id = 5;
                new Message("",1, message_id,true);
                repaint();
            }
        } else if(ontile < 0) {
            ontile = ~ontile & 4095;
        } else if(ontile == 4095) {
            Game_states.updateControll_state(GameUtil.GAME_END);
            BaseFrame.frame_generator().panel_change(new GameEnding("GAME OVER"), 1);
            return 0;
        }
        hit_tile = ontile;
        return ontile;
    }
    private void map_move(int map_id, int x, int y) {
        int before_map_od = maps.updateActiveMapNum(map_id);

        if(before_map_od == 58 && map_id == 59) {
            Maps.door_lock(map_id, 30, 7);
            Maps.door_lock(map_id, 31, 7);
        }
        mapnum = map_id;


        if(x <= 0) this.x = GameUtil.TILE*32;
        if(y <= 2) this.y = GameUtil.TILE*32;
        if(x >= 33) this.x = 64;
        if(y >= 33) this.y = 96;
    }
    private void body_on_switch() {
        int xxx = ((x+14)>>5);
        int yyy = ((y+28)>>5);
        int tmp = maps.map_tile(xxx, yyy);
        if(tmp == 102 || tmp == 103) {
            maps.updateTileSwitch(xxx, yyy,0b1111111 & (1 ^ (~(tmp ^ Integer.MAX_VALUE))));
        } else {
            maps.switchedTileReset();
        }
        puzzle1.puzzle1_check(maps);
    }
    private void char_move() {
        int tile = GameUtil.TILE-5;
        int fx = 0, fx1 = 0, fy = 0;
        switch(dire) {
            case 1: fx = (x - speed) >> 5;
                    fy = y+8 >> 5;
                    if(can_move(fx,fy))  x -= speed;
                    break;
            case 2: fx = (x+tile + speed) >> 5;
                    fy = y+8 >> 5;
                    if(can_move(fx,fy))  x += speed;
                    break;
            case 3: fy = (y-speed+8) >> 5;
                    fx = x >> 5;
                    fx1 = (x+tile) >> 5;
                    if(can_move(fx,fy) && can_move(fx1,fy))  y -= speed;
                    break;
            case 4: fy = (y+speed+8) >> 5;
                    fx = x >> 5;
                    fx1 = (x+tile) >> 5;
                    if(can_move(fx,fy) && can_move(fx1,fy))  y += speed;
                    break; 
        }
        body_on_switch();
    }
    private int scroll_x() {
        return (width >> 1) - x;
    }
    private int scroll_y() {
        return (height >> 1) - y;
    }
    public void coords_move(int map_num,int xx, int yy) {
        maps.updateActiveMapNum(map_num);
        x = xx;
        y = yy;
    }
    public void keyPressed(KeyEvent e) {
        if(prologue) return;
        int key = e.getKeyCode();
        if((Game_states.getControll_state() & GameUtil.POSE) == GameUtil.POSE) {
            pp.controll(key,x,y, maps.map_number(),clock.getPlayTime());
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
                ip.controll(key,x,y,maps, tp);
            } else {
                if(key == KeyEvent.VK_0) Game_states.updateControll_state(Game_states.getControll_state()+GameUtil.INVENTORY);
            }
            if(key == KeyEvent.VK_T) {
                Game_states.updateControll_state((Game_states.getControll_state() & ~GameUtil.PLAY)+GameUtil.TELEP);
            }
            if(key == KeyEvent.VK_E)       speed = GameUtil.DASH;
            if((Game_states.getControll_state() & GameUtil.ITEM_DELETE) != GameUtil.ITEM_DELETE) {
                if(key == KeyEvent.VK_LEFT)    dire = 1;
                if(key == KeyEvent.VK_RIGHT)   dire = 2;
                if(key == KeyEvent.VK_UP)      dire = 3;
                if(key == KeyEvent.VK_DOWN)    dire = 4;
            }
            if(key == KeyEvent.VK_SPACE) {
                switch(hit_tile) {
                    case 24,25 -> Game_states.updateControll_state((Game_states.getControll_state() & ~GameUtil.PLAY)+GameUtil.PC);
                    case 36 -> new Message("",1, 6,false);
                    case 37 -> new Message("",1,11,false);
                    case 38 -> new Message("",1, 8,false);
                    case 39 -> new Message("",1, 12,false);

                    case 40 -> new Message("",1, 7,false);
                    case 41 -> new Message("",1, 10,false);
                    case 42 -> new Message("",1, 16,false);
                    case 43 -> new Message(Game_states.getName(),1, 15,false);

                    case 44 -> new Message("",1, 14,false);
                    case 45 -> new Message("",1, 9,false);
                    case 46 -> new Message(Integer.toString(Game_states.getPCPassword()),1, 17,false);
                    case 47 -> new Message(Game_states.getName(),1, 13,false);
                    case 48 -> new Message("",2, 0,false);
                    case 51 -> new Message("",1, 18,false);
                }
            }
            if(key == KeyEvent.VK_BACK_QUOTE) {
                if((Game_states.getControll_state() & GameUtil.DEBUG) == GameUtil.DEBUG) {
                    Game_states.updateControll_state(Game_states.getControll_state() & ~GameUtil.DEBUG);
                } else {
                    Game_states.updateControll_state(Game_states.getControll_state()+GameUtil.DEBUG);
                }
            }
        } else if((Game_states.getControll_state() & GameUtil.TALK) == GameUtil.TALK){
            tp.controll(key,0,0,hit_tile);
        } else if((Game_states.getControll_state() & GameUtil.MESSAGE) == GameUtil.MESSAGE){
            mep.controll(key,0,0,hit_tile);
        } else if((Game_states.getControll_state() & GameUtil.PC) == GameUtil.PC) {
            pcp.controll(key,Game_states.getInventory(ip.getInventoryIndex()));
        }else if((Game_states.getControll_state() & GameUtil.TELEP) == GameUtil.TELEP) {
            tepp.controll(key, maps.map_number(),x,y,maps,this);
        }
        if(key == KeyEvent.VK_P) {
            maps.loadMap(mapnum,true,tep);
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
        if(prologue){
            sleep(2000);
            new Message("",0,18,false);
            prologue = false;
        }
        while(Game_states.getControll_state() != GameUtil.GAME_EXIT && Game_states.getControll_state() != GameUtil.GAME_END) {
            char_move();
            if((Game_states.getControll_state() & GameUtil.PC) == GameUtil.PC && PC_paint.password_correct()) {
                PC_paint.setPclogin();
                sleep(500);
                repaint();
            }
            sleep(16);
            if((Game_states.getControll_state() & GameUtil.PLAY) == GameUtil.PLAY) repaint();
        }
    }
    private void sleep(int millis) {
        try{
            Thread.sleep(millis);
        } catch(InterruptedException e) {}
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
