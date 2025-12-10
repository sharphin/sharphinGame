package game.panel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import game.logic.Game_states;
import game.util.FontUtil;
import game.util.GameUtil;

public class PC_paint {
    private final int width = GameUtil.PANEL_X, height = GameUtil.PANEL_Y;
    private Font font;
    private Font font1;
    private String ERROR = "";
    private int cursor_i;
    private boolean now_login;
    private StringBuilder password;
    //private String password;
    public PC_paint() {
        password = new StringBuilder();
        FontUtil fl = new FontUtil();
        font = fl.setFontSize_Mplus1Code(20f);
        font1 = fl.setFontSize_Mplus1Code(35f);
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
            g.drawString("LOGIN SUCSESS", 270, 270);
        } else {
            g.drawString("LOGIN",300,200);
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
        }
    }
    public void controll(int key) {
        ERROR = "";
        if(key == KeyEvent.VK_ESCAPE) {
            Game_states.updateControll_state((Game_states.getControll_state()+GameUtil.PLAY) & ~GameUtil.PC);
        }
        if(key == KeyEvent.VK_ENTER) {
            if(!password.toString().equals(Game_states.getPCPassword())) {
                ERROR = "INCORRECT";
            } else {
                now_login = true;
            }
        }
        switch(key) {
            case 127 -> password_delete(cursor_i);
            case 90 -> password_type('Z');
            case 89 -> password_type('Y');
            case 88 -> password_type('X');
            case 87 -> password_type('W');
            case 86 -> password_type('V');
            case 85 -> password_type('U');
            case 84 -> password_type('T');
            case 83 -> password_type('S');
            case 82 -> password_type('R');
            case 81 -> password_type('Q');
            case 80 -> password_type('P');
            case 79 -> password_type('O');
            case 78 -> password_type('N');
            case 77 -> password_type('M');
            case 76 -> password_type('L');
            case 75 -> password_type('K');
            case 74 -> password_type('J');
            case 73 -> password_type('I');
            case 72 -> password_type('H');
            case 71 -> password_type('G');
            case 70 -> password_type('F');
            case 69 -> password_type('E');
            case 68 -> password_type('D');
            case 67 -> password_type('C');
            case 66 -> password_type('B');
            case 65 -> password_type('A');
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
            case 32 -> password_type(' ');
            case 8  -> password_backspace(cursor_i);
        }
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
}