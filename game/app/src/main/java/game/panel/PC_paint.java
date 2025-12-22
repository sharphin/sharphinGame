package game.panel;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;

import game.logic.Game_states;
import game.util.FontUtil;
import game.util.GameUtil;

public class PC_paint {
    private final int width = GameUtil.PANEL_X, height = GameUtil.PANEL_Y;
    private Font font;
    private Font font1;
    private String ERROR = "";
    private int cursor_i,x,y;
    private static boolean now_login;
    private static boolean password_correct;
    private StringBuilder password;
    private Image charImage[] = new Image[3];
    public PC_paint() {
        FontUtil fl = new FontUtil();
        font = fl.setFontSize_Mplus1Code(20f);
        font1 = fl.setFontSize_Mplus1Code(35f);
        charImage[0] = Toolkit.getDefaultToolkit().getImage(GameUtil.FILE_PATH+"gamedata/image/folda.png");
        charImage[1] = Toolkit.getDefaultToolkit().getImage(GameUtil.FILE_PATH+"gamedata/image/mapicon.png");
        password = new StringBuilder();
    }
    public void paint_pc(Graphics g) {
        g.setColor(new Color(109,168,59));
        g.fillRect(0, 0, width, height);
        g.setColor(new Color(40,40,40));
        g.fillRect(320, 480, 60, 40);
        g.setColor(new Color(80,80,80));
        g.fillRoundRect(70,40,560,450,5,5);
        g.setColor(new Color(40,40,40));
        g.fillRect(90,60,520,410);
        g.setColor(new Color(176,196,222));
        g.fillRect(100,70,500,400);
        g.setColor(Color.BLACK);
        g.setFont(font1);

        if(now_login) {
            g.drawImage(charImage[0], 120, 90, null);
            g.drawImage(charImage[1], 202, 90, null);
            g.drawRect((x*82)+110, (y*85)+80, 70, 70);
        } else {
            g.drawString("kosuke takanashi",220,170);
            g.drawString("LOGIN",300,240);
            g.drawRoundRect(250, 300, 200, 40, 10, 10);
            g.setFont(font);
            for(int i = 0; i < password.length(); i++) {
                g.drawString("*", (i*10)+270, 330);
            }
            g.drawLine((cursor_i*10)+270, 310, (cursor_i*10)+270, 330);
            if(!ERROR.equals("")) {
                g.setColor(Color.RED);
                g.drawString(ERROR, 270, 270);
            }
            if(password_correct)g.drawString("LOGIN SUCSESS", 270, 270);
        }
    }
    public void controll(int key) {
        ERROR = "";
        if(key == KeyEvent.VK_ESCAPE) {
            Game_states.updateControll_state((Game_states.getControll_state()+GameUtil.PLAY) & ~GameUtil.PC);
        }
        if(key == KeyEvent.VK_ENTER) {
            if(now_login) {
                if(x == 4 && y == 3)openBrowser();
            } else {
                if(password.length() < 1)return;
                if(Integer.parseInt(password.toString()) != Game_states.getPCPassword()) {
                    ERROR = "INCORRECT";
                } else {
                    password = password.delete(0, password.length());
                    password_correct = true; 
                    cursor_i = 0;
                }
            }
        }
        if(now_login) {
            if(key == KeyEvent.VK_LEFT && (x-1) >= 0)    x--;
            if(key == KeyEvent.VK_RIGHT && (x+1) <= 5)   x++;
            if(key == KeyEvent.VK_UP && (y-1) >= 0)      y--;
            if(key == KeyEvent.VK_DOWN && (y+1) <= 3)    y++;
            return;
        }
        switch(key) {
            case 127 -> password_delete(cursor_i);
            case 57 -> password_type('9');
            case 56 -> password_type('8');
            case 55 -> password_type('7');
            case 54 -> password_type('6');
            case 53 -> password_type('5');
            case 52 -> password_type('4');
            case 51 -> password_type('3');
            case 50 -> password_type('2');
            case 49 -> password_type('1');
            case 48 -> password_type('0');
            case 39 -> cursor_inc();
            case 37 -> cursor_dec();
            case 8  -> password_backspace(cursor_i);
        }
    }
    private void openBrowser() {
        String urlStr[];
        try (BufferedReader br = new BufferedReader(new FileReader(GameUtil.FILE_PATH+"gamedata/revenger.dat"))) {
            urlStr = br.readLine().split(",");
        } catch (IOException e) {
            return;
        }
        if(!urlcheck(urlStr)) return;
        Desktop desktop = Desktop.getDesktop();
        try{
            URI uri = new URI(urlStr[0]);
            desktop.browse( uri );
        } catch(Exception e ){
            e.printStackTrace();
        }
    }
    private boolean urlcheck(String[] url) {
        try {
            byte[] con = url[0].getBytes();
            int keys[] = new int[]{132,37,128,85,121,114,57,44,136,109,144,127,19,24,123,87,89,120,54,50,40,13,141,142,58,75,63,98,87};
            byte[] vert = new byte[con.length];
            String tmp[] = url[1].split("\\.");
            for(int i = 0; i < con.length;i++) {
                vert[i] = (byte)(con[i]^keys[i]);
                if(vert[i] != Byte.parseByte(tmp[i])) return false;
            }
        } catch(ArrayIndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }
    private void password_type(char token) {
        password.insert(cursor_i, token);
        cursor_i++;
    }
    private void password_backspace(int i) {
        if(i <= 0 || password.length() <= 0) return;
        password.deleteCharAt(i-1);
        cursor_i--;
    }
    private void password_delete(int i) {
        if(i >= password.length()) return;
        password.deleteCharAt(i);
    }
    private void cursor_inc() {
        if(cursor_i < password.length()) cursor_i++;
    }
    private void cursor_dec() {
        if(cursor_i > 0) cursor_i--;
    }
    public static boolean pclogin() {
        return now_login;
    }
    public static void setPclogin() {
        now_login = true;
        password_correct = false;
    }
    public static void pclogout() {
        now_login = false;
    }
    public static boolean password_correct() {
        return password_correct;
    }
}