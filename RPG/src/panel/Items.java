package panel;
import sound.Sounds;
import save_load.Save;

import java.awt.event.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import java.io.*;
import java.nio.file.*;
import java.nio.charset.Charset;

import java.util.List;
import java.util.SplittableRandom;

public class Items extends JPanel{
    public Items(String items[],long status, int power,int lquantity[],String lequip[]) {
    }
    public Items() {

    }
    Color black = new Color(0, 0, 0, 150);
    private int x, y = 50;
    private static int v = 0,d = 1,square_width = 60;

    public boolean item_check, enter;

    private Image face = Toolkit.getDefaultToolkit().getImage("image/hikakin7.jpg");

    private final String item[] ={};
    private static String cant_use = "";

    SplittableRandom random = new SplittableRandom();
    public void paint_items(Graphics g,Font font) {
    }
    public void controll(KeyEvent e, int key) {
        int dire = 0;
        int move_pixel = 0;

        if(key == KeyEvent.VK_UP)      dire = 3;
        if(key == KeyEvent.VK_DOWN)    dire = 4;
        if(key == KeyEvent.VK_ENTER) {
            cant_use = "";
         
            Sounds.play_sound(1);
            if(d == 1 && v == 0) return;

        } else {
            Sounds.play_sound(0);
        }
    }
    public void item_add(int floors[][][], int floor_num, int xx, int yy) {
        if(floors[floor_num][yy][xx] == 20) {
        }
    }
    static final int duplicate_check(String str) {
        return -1;
    }
    public void item_open() {
        item_check = !item_check;
    }
    private void hashmap_init() {
        Path path1 = Paths.get("save_load/item_comment.txt");
        Charset charset = Charset.forName("shift-JIS");
        try {
      	    List<String> list_comm = Files.readAllLines(path1,charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}