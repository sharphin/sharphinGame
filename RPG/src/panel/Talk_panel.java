package panel;

import java.awt.*;
import java.awt.event.*;

import java.util.List;
import java.util.ArrayList;

import java.io.*;
import java.nio.file.*;
import java.nio.charset.Charset;

public class Talk_panel extends Thread{ 
    private final int COL_MAX = 20;
    
    private final int LETTER_MAX = 60;

    private final String[] buy_items = {"�}��","���g��","�Ă���","�Ē�","���{��","�r�[��"};
    private final int item_price[] =   { 200,   300,     450,     250,   300,     400};
    
    private String serif_unit[] = {""};

    private String col_one = "";
    private String col_two = "";
    private String col_three = "";
    StringBuilder bui1 = new StringBuilder();
    StringBuilder bui2 = new StringBuilder();
    StringBuilder bui3 = new StringBuilder();
    

    private String splited_serif[] = {""};
    private List<String> name_list = new ArrayList<>(17);
    private String file_name[] = {"dialogue/serif/eat_space_buy.txt",
                                  "dialogue/serif/narration.txt",
                                  "dialogue/serif/taxi_driver.txt",
                                  "dialogue/serif/voice_from_phone.txt",
                                  "dialogue/serif/�ے�.txt",
                                  "dialogue/serif/�В�.txt",
                                  "dialogue/serif/��l��.txt",
                                  "dialogue/serif/�����Ј�.txt",
                                  "dialogue/serif/�����Ј�_����.txt",
                                  "dialogue/serif/�H���̂��΂����_���j.txt",
                                  "dialogue/serif/�ꖱ.txt",
                                  "dialogue/serif/�N��_��.txt",
                                  "dialogue/serif/�j���Ј�.txt",
                                  "dialogue/serif/����_��.txt",
                                  "dialogue/serif/����.txt",};

    private Thread th = new Thread(this);
    
    private int time = 60;
    private int i, v = 1;//i�����[�J���ϐ�����Ȃ����Ƃɒ���
    private int x = 310,y = 110;
    private int serif_num, count;

    static boolean battle_begin, talk_finish = true;
    public int flgs, enemy_num;
    public Talk_panel() {
        th.start();
    }
    public void paint_message(Graphics g,Font font) {
        if(flgs == 0) return;
            g.setFont(font);
            g.setColor(new Color(0,0,0));
            g.fillRoundRect(100, 350, 300, 100, 10, 10);
            g.setColor(Color.WHITE);
            g.drawRoundRect(100, 350, 300, 100, 10, 10);
            g.setColor(new Color(0,0,0));
            g.fillRoundRect(115, 340, 150, 25, 10, 10);
            g.setColor(Color.WHITE);
            g.drawRoundRect(115, 340, 150, 25, 10, 10);

            g.drawString(name_list.get(serif_num),125,355);
            g.drawString(col_one, 125,380);
            g.drawString(col_two, 125,400);
            g.drawString(col_three, 125,420);
        if(flgs != 49) return; 
            g.fillRoundRect(300,100,150,250,10,10);
            g.setColor(Color.BLACK);
            g.drawRoundRect(x,y,100,20,10,10);
            for(int i = 0; i < 6; i++) {
                g.drawString(buy_items[i]+ "  " +item_price[i] +"�~",315,125+(i*40));
            }
    }
    public void communicate_flg(int object_num,boolean a) {
        if(flgs == 201 || !a) {
            letter_reset();
            flg_reset();
            return;
        }
        switch(object_num) {
            case 100: serif_num = 8;
                      load_dialogue(file_name[0]);
                      flgs = 9;
                      break;
            case 101: serif_num = 12;
                      load_dialogue(file_name[1]);
                      flgs = 1;
                      break;
            case 102: serif_num = 11;
                      load_dialogue(file_name[2]);
                      flgs = 1;
                      break;
            case 103: serif_num = 10;
                      load_dialogue(file_name[3]);
                      flgs = 1;
                      break;
            case 104: serif_num = 1;
                      load_dialogue(file_name[4]);
                      flgs = 3; enemy_num = 0;
                      battle_begin = true;
                      break;
            case 105: serif_num = 4;
                      load_dialogue(file_name[5]);
                      flgs = 3; enemy_num = 3;
                      battle_begin = true;
                      break;
            case 106: serif_num = 0; 
                      load_dialogue(file_name[6]);
                      flgs = 1;
                      break;
            case 107: serif_num = 7;
                      load_dialogue(file_name[7]);
                      flgs = 1;
                      break;
            case 108: serif_num = 7; 
                      load_dialogue(file_name[8]);
                      flgs = 1;
                      break;
            case 109: serif_num = 8; 
                      load_dialogue(file_name[9]);
                      flgs = 81;
                      break;
            case 110: serif_num = 3;
                      load_dialogue(file_name[10]);
                      flgs = 3; enemy_num = 2;
                      battle_begin = true;
                      break;
            case 111: serif_num = 9; 
                      load_dialogue(file_name[11]);
                      flgs = 1;
                      break;
            case 112: serif_num = 5; 
                      load_dialogue(file_name[12]);
                      flgs = 1;
                      break;
            case 113: serif_num = 6; 
                      load_dialogue(file_name[13]);
                      flgs = 1;
                      break;
            case 114: serif_num = 2; 
                      load_dialogue(file_name[14]);
                      flgs = 3; enemy_num = 1;
                      battle_begin = true;
                      break;
        }
    }
    public boolean battle_start() {
        return battle_begin;
    }
    private void concatenate(int i) {
        if(i < COL_MAX) {
            bui1.append(splited_serif[i]);
            col_one = bui1.toString();
        } else if(i < (COL_MAX << 1)){
            bui2.append(splited_serif[i]);
            col_two = bui2.toString();
        } else if(i < (COL_MAX << 1)+COL_MAX){
            bui3.append(splited_serif[i]);
            col_three = bui3.toString();
        }
    }
    public void controll(KeyEvent e, int key) {
        if(i < splited_serif.length) return;
        if(key == KeyEvent.VK_SPACE) {
            if(count < serif_unit.length-1) input_splited_serif(++count);
            else flg_reset();
            if(flgs == 81) {
                flgs = 201;
            }
            letter_reset();
        }
        if(flgs != 203) return;
        int dire = 0;
        if(key == KeyEvent.VK_UP)      dire = 3;
        if(key == KeyEvent.VK_DOWN)    dire = 4;
        
        if(key == KeyEvent.VK_ENTER) {
            letter_reset();
        }
        if(dire == 3 && v > 1) v--;
        if(dire == 4 && v < 6) v++;
        y = (40*v)+70;
        dire = 0;
    }
    private void letter_reset() {
        bui1.delete(0,COL_MAX);
        bui2.delete(0,COL_MAX);
        bui3.delete(0,COL_MAX); 
        col_one = "";
        col_two = "";
        col_three = "";
        i = 0;
    }
    private void flg_reset() {
        flgs = 0; i = 0;
    }
    public void run() {
        boolean first_talk_delay = true;
        while(true) {
            if(i < splited_serif.length && i < LETTER_MAX && (flgs & 1) == 1) {
                if(first_talk_delay) {
                    talk_delay();
                    first_talk_delay = false;
                }
                concatenate(i);
                i++;
            }
            if(i >= LETTER_MAX || i >= splited_serif.length) {
                if(flgs <= 5) flgs = 201;
                if(flgs == 9) flgs = 203;
            }
            try{
                Thread.sleep(time);
            } catch(InterruptedException e) {}
        }
    }
    private void talk_delay() {
        try{ Thread.sleep(210);
        } catch(InterruptedException e) {}
   }
    private void load_dialogue(String file_path) {
        Path path = Paths.get(file_path);
        Path path1 = Paths.get("dialogue/serif/charc_name.txt");
        Charset charset = Charset.forName("shift-JIS");
        try {
      	    List<String> line_list = Files.readAllLines(path,charset);
      	    name_list = Files.readAllLines(path1,charset);
            serif_unit = line_list.toArray(new String[line_list.size()]);
            input_splited_serif(0);
        } catch (IOException e) {
            e.printStackTrace();
        }  
    }
    private void input_splited_serif(int index) {
        splited_serif = serif_unit[index].split("");
    }
}