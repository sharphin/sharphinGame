package game.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import game.frame.BaseFrame;
import game.logic.Game_states;
import game.main_panel.CCharacter;
import game.main_panel.Prologue_panel;
import game.util.FontUtil;
import game.util.GameUtil;

public class CharaCreate_panel extends JPanel implements KeyListener{

    private final int width = GameUtil.PANEL_X, height = GameUtil.PANEL_Y;
    private int cursor_i;
    private int key;
    private boolean shift;
    private StringBuilder name = new StringBuilder();
    private String ERROR = "";
    private int xcoords1[][];
    private int xcoords2[][];
    private int xcoords3[][];
    private int xcoords4[][];
    private Font font;
    public CharaCreate_panel() {
        setSize(width, height);
        addKeyListener(this);
        setFocusable(true);
        FontUtil fl = new FontUtil();
        font = fl.setFontSize_Mplus1Code(20f);
        coords_init();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,width,height);
        g.setColor(Color.WHITE);
        g.drawRect(5, 5, width-10, height-10);
        g.drawRect(10, 210, width-20, 40);
        g.setFont(font);
        g.drawRect(196, 440, 263, 40);
        if(key == 32) g.fillRect(196, 440, 263, 40);
        g.drawRect(623, 305, 62, 40);
        if(key == 8 || key == 127) g.fillRect(623, 305, 62, 40);
        g.drawRect(593, 350, 92, 40);
        if(key == 10) g.fillRect(593, 350, 92, 40);
        g.drawRect(15, 305, 63, 40);
        g.drawRect(15, 350, 78, 40);
        g.drawRect(15, 395, 103, 40);
        g.drawRect(83, 440, 40, 40);
        g.drawRect(128, 440, 63, 40);

        g.drawRect(465, 440, 63, 40);
        g.drawRect(533, 440, 40, 40);
        g.drawRect(573, 395, 67, 40);
        g.drawString("WHAT'S YOUR NAME ?", 20, 200);
        for(int i = 0; i < xcoords1[0].length; i++) {
            g.drawRect(xcoords1[0][i], 260, 40, 40);
            if(key == xcoords1[1][i]) g.fillRect(xcoords1[0][i], 260, 40, 40);
        }
        for(int i = 0; i < xcoords2[0].length; i++) {
            g.drawRect(xcoords2[0][i], 305, 40, 40);
            if(key == xcoords2[1][i]) g.fillRect(xcoords2[0][i], 305, 40, 40);
        }
        for(int i = 0; i < xcoords3[0].length; i++) {
            g.drawRect(xcoords3[0][i], 350, 40, 40);
            if(key == xcoords3[1][i]) g.fillRect(xcoords3[0][i], 350, 40, 40);
        }
        for(int i = 0; i < xcoords4[0].length; i++) {
            g.drawRect(xcoords4[0][i], 395, 40, 40);
            if(key == xcoords4[1][i]) g.fillRect(xcoords4[0][i], 395, 40, 40);
        }
        g.drawString("1", 73, 285);
        g.drawString("2", 118, 285);
        g.drawString("3", 163, 285);
        g.drawString("4", 208, 285);
        g.drawString("5", 253, 285);
        g.drawString("6", 298, 285);
        g.drawString("7", 343, 285);
        g.drawString("8", 388, 285);
        g.drawString("9", 433, 285);
        g.drawString("0", 478, 285);
        if(shift) {
            g.drawString("Q", 96, 330);
            g.drawString("W", 141, 330);
            g.drawString("E", 186, 330);
            g.drawString("R", 231, 330);
            g.drawString("T", 276, 330);
            g.drawString("Y", 321, 330);
            g.drawString("U", 366, 330);
            g.drawString("I", 411, 330);
            g.drawString("O", 456, 330);
            g.drawString("P", 501, 330);
            g.drawString("A", 111, 375);
            g.drawString("S", 156, 375);
            g.drawString("D", 201, 375);
            g.drawString("F", 246, 375);
            g.drawString("G", 291, 375);
            g.drawString("H", 336, 375);
            g.drawString("J", 381, 375);
            g.drawString("K", 426, 375);
            g.drawString("L", 471, 375);
            g.drawString("Z", 136, 420);
            g.drawString("X", 181, 420);
            g.drawString("C", 226, 420);
            g.drawString("V", 271, 420);
            g.drawString("B", 316, 420);
            g.drawString("N", 361, 420);
            g.drawString("M", 406, 420);
        } else {
            g.drawString("q", 96, 330);
            g.drawString("w", 141, 330);
            g.drawString("e", 186, 330);
            g.drawString("r", 231, 330);
            g.drawString("t", 276, 330);
            g.drawString("y", 321, 330);
            g.drawString("u", 366, 330);
            g.drawString("i", 411, 330);
            g.drawString("o", 456, 330);
            g.drawString("p", 501, 330);
            g.drawString("a", 111, 375);
            g.drawString("s", 156, 375);
            g.drawString("d", 201, 375);
            g.drawString("f", 246, 375);
            g.drawString("g", 291, 375);
            g.drawString("h", 336, 375);
            g.drawString("j", 381, 375);
            g.drawString("k", 426, 375);
            g.drawString("l", 471, 375);
            g.drawString("z", 136, 420);
            g.drawString("x", 181, 420);
            g.drawString("c", 226, 420);
            g.drawString("v", 271, 420);
            g.drawString("b", 316, 420);
            g.drawString("n", 361, 420);
            g.drawString("m", 406, 420);
        }
        g.drawString("/\\", 543, 330);
        g.drawString("\\/", 541, 420);
        g.drawString("<", 516, 375);
        g.drawString(">", 561, 375);
        g.drawString("START", 615, 375);
        g.drawString("DEL", 638, 330);
        g.drawString("ESC", 20, 285);
        g.drawString(ERROR,500, 200);
        for(int i = 0; i < name.length(); i++) {
            g.drawString(name.charAt(i)+"", (i*10)+20, 237);
        }
        g.drawLine((cursor_i*10)+20, 220, (cursor_i*10)+20, 240);
    }
    public void keyPressed(KeyEvent e) {
        key = e.getKeyCode();
        ERROR = "";
        if(key == KeyEvent.VK_ESCAPE) BaseFrame.frame_generator().back_title(new Title());
        if(key == KeyEvent.VK_ENTER) {
            if(name.toString().isEmpty()) {
                ERROR = "DON'T EMPTY";
            } else {
                if(name.toString().equals("COMPLETE") || name.toString().equals("skip")) {
                    new Game_states(name.toString(),1,1);
                    BaseFrame.frame_generator().panel_change(new CCharacter(300, 200,2,(long)0,Game_states.getTodayTime(),false),1);
                } else {
                    BaseFrame.frame_generator().panel_change(new Prologue_panel(name.toString(),false),1);
                }
            }
        }
        if(key == KeyEvent.VK_SHIFT) shift = !shift;
        switch(key) {
            case 127 -> name_delete(cursor_i);
            case 90 -> name_type(key);
            case 89 -> name_type(key);
            case 88 -> name_type(key);
            case 87 -> name_type(key);
            case 86 -> name_type(key);
            case 85 -> name_type(key);
            case 84 -> name_type(key);
            case 83 -> name_type(key);
            case 82 -> name_type(key);
            case 81 -> name_type(key);
            case 80 -> name_type(key);
            case 79 -> name_type(key);
            case 78 -> name_type(key);
            case 77 -> name_type(key);
            case 76 -> name_type(key);
            case 75 -> name_type(key);
            case 74 -> name_type(key);
            case 73 -> name_type(key);
            case 72 -> name_type(key);
            case 71 -> name_type(key);
            case 70 -> name_type(key);
            case 69 -> name_type(key);
            case 68 -> name_type(key);
            case 67 -> name_type(key);
            case 66 -> name_type(key);
            case 65 -> name_type(key);
            case 57 -> name_type('9');
            case 56 -> name_type('8');
            case 55 -> name_type('7');
            case 54 -> name_type('6');
            case 53 -> name_type('5');
            case 52 -> name_type('4');
            case 51 -> name_type('3');
            case 50 -> name_type('2');
            case 49 -> name_type('1');
            case 48 -> name_type('0');
            case 39 -> cursor_inc();
            case 37 -> cursor_dec();
            case 32 -> name_type(' ');
            case 8  -> name_backspace(cursor_i);
        }
        repaint();
    }
    private void name_type(int token) {
        if(!shift) token = token |32;
        name.insert(cursor_i, (char)token);
        cursor_i++;
    }
    private void name_backspace(int i) {
        if(i <= 0 || name.length() <= 0) return;
        name.deleteCharAt(i-1);
        cursor_i--;
    }
    private void name_delete(int i) {
        if(i >= name.length()) return;
            name.deleteCharAt(i);
    }
    public void keyReleased(KeyEvent e) {
        key = 0;
        repaint();
    }
    private void cursor_inc() {
        if(cursor_i < name.length()) cursor_i++;
    }
    private void cursor_dec() {
        if(cursor_i > 0) cursor_i--;
    }
    public void keyTyped(KeyEvent e) {}

    private void coords_init() {
        xcoords1 = new int[][]{{15,60,105,150,195,240,285,330,375,420,465,510,555,600,645},
                               {27,49,50,51,52,53,54,55,56,57,48,-1,-1,-1,-1}};
        xcoords2 = new int[][]{{83,128,173,218,263,308,353,398,443,488,533,578},
                               {81,87,69,82,84,89,85,73,79,80,38,-1}};
        xcoords3 = new int[][]{{98,143,188,233,278,323,368,413,458,503,548},
                               {65,83,68,70,71,72,74,75,76,37,39}};
        xcoords4 = new int[][]{{123,168,213,258,303,348,393,438,483,528,645},
                               {90,88,67,86,66,78,77,-1,-1,40,-1}};
    }
}
