package panel.epi_and_pro;

import java.awt.*;
import java.awt.event.*;

import java.util.List;
import java.util.ArrayList;

import java.io.*;
import java.nio.file.*;
import java.nio.charset.Charset;

public class Epilogue extends Thread{ 
    private final int COL_MAX = 20;
    
    private final int LETTER_MAX = 60;

    private String serif_unit[] = {"�Q�[���N���A!?","�Q�[���N���A!!","�������Ă���",
                                   "���̂��Ă������"};
    private static String col_one = "";
    private static boolean finish;
    StringBuilder bui1 = new StringBuilder();


    private String splited_serif[] = {""};
    private List<String> name_list = new ArrayList<>(17);
    private String file_name[] = {"dialogue/end/eat_space_buy.txt",
                                  "dialogue/end/narration.txt",
                                  "dialogue/end/taxi_driver.txt",
                                  "dialogue/end/voice_from_phone.txt",
                                  "dialogue/end/�ے�.txt"};

    private Thread th = new Thread(this);
    
    private int time = 60;
    private int i, v = 1;
    private int x = 310,y = 110;
    private int serif_num;

    public int flgs, enemy_num;
    public Epilogue() {
        th.start();
    }
    public static final void paint_ending(Graphics g,Font font) {
        if(!finish) return;
        g.setColor(Color.BLACK);
        g.fillRect(0,0,600,600);
        g.setFont(font);
        g.setColor(new Color(0,0,0));
        g.fillRoundRect(100, 350, 300, 100, 10, 10);
        g.setColor(Color.WHITE);
        g.drawRoundRect(100, 350, 300, 100, 10, 10);
        g.drawString(col_one, 125,380);
    }
    public void ending_flg(int object_num) {
        switch(object_num) {
            case 1: input_splited_serif(0);
                    
                    flgs = 1;
                    break;
            case 2: input_splited_serif(1);
                    flgs = 1;
                    break;
            case 3: input_splited_serif(2);
                    flgs = 1;
                    break;
            case 4: input_splited_serif(3);
                    flgs = 3; enemy_num = 0;
                    break;
        }
        finish = true;
    }
    public void controll(KeyEvent e, int key) {
        if(key == KeyEvent.VK_ENTER && finish) {
            System.exit(0);
        }
    }
    private void concatenate(int i) {
        if(i < COL_MAX) {
            bui1.append(splited_serif[i]);
            col_one = bui1.toString();
        }
    }
    private void letter_reset() {
        bui1.delete(0,COL_MAX);
        col_one = "";

        i = 0;
    }
    private void flg_reset() {
        flgs = 0; i = 0;
    }
    public void run() {//Runnable����Ȃ���Timer�ɂ�����
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
                if(flgs <= 5) flgs = 16+1;
                if(flgs == 9) flgs = 48+1;
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