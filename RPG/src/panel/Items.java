package panel;

import static panel.Charctor_status.*;
import panel.epi_and_pro.*;
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
import java.util.Arrays;
import java.util.ArrayList;
import java.util.SplittableRandom;

public class Items extends JPanel{
    public Items(String items[],long status, int power,int lquantity[],String lequip[]) {
        HP = (int)(status >> 24);
        MP = (int)((status >> 15)&511);
        my_money = (int)(status & 32767);
        offence = (power >> 14);

        defence = power & 16383;
        quantity = Arrays.copyOf(lquantity,10);
        equip = lequip;
        List<String>llist = Arrays.asList(items);
        list = new ArrayList<>(llist);

        for(int i = 0; i < 10; i++) list.remove("");
        hashmap_init();
    }
    public Items() {
        list.add("");
        list.add("");
        hashmap_init();
    }
    Color black = new Color(0, 0, 0, 150);
    private int x, y = 50;
    private static int v = 0,d = 1,square_width = 60;

    public boolean item_check, enter;

    private Image face = Toolkit.getDefaultToolkit().getImage("image/hikakin7.jpg");

    private final String item[] ={};
    private static String cant_use = "";

    SplittableRandom random = new SplittableRandom();
    private static Epilogue epi = new Epilogue();
    public void paint_items(Graphics g,Font font) {
        if(!item_check) return;
        g.setFont(font);
        g.setColor(black);
        g.fillRect(0, 0, 16<<5, 15<<5);
        g.setColor(black);
        g.fillRoundRect(45,60,120,30,10,10);
        g.fillRoundRect(165,60,120,30,10,10);
        g.fillRoundRect(165,90,120,30,10,10);
        g.fillRoundRect(165,120,120,30,10,10);
        g.fillRoundRect(285,60,80,30,10,10);
        g.fillRoundRect(45,29,310,30,10,10);

        g.setColor(Color.WHITE);
        g.drawString("�A�C�e��  ", 75, 80);
        g.drawString("�����i  ",195,80);
        g.drawString("�Z�[�u",300,80);
        if(d == 1 && list.size() > 0 && v > 0) {
            g.drawString(hashmap.get(list.get(v-1)).substring(2),50,50);
        }
        g.fillRoundRect(285,130,220,330,5,5);
        g.drawImage(face,310,140, this);

        g.setColor(Color.BLACK);
        g.drawString("�����@�Ԓ|",295,335);
        g.drawString("KUROI-COMPANY�i���j ������",295,320);
        g.drawString("������: "+my_money + "�~",295,400);
        g.drawString("HP; "+HP+"/2500  MP: "+MP+"/500",295,360);
        g.drawString("buraku0209@intlook.jp",295,440);

        for(int i = 0 ;i < list.size(); i++) {
            g.setColor(black);
            g.fillRoundRect(45,(30*i)+90,120,30,10,10);
            g.setColor(Color.WHITE);
            g.drawString(list.get(i),50,110+(30*i));
            if(quantity[i] > 1) {
                g.drawString(" �~ "+quantity[i],120,110+(30*i));
            }
        }
        if(equip != null) {
            if(d == 2 && v > 0) {
                g.drawString(hashmap.get(equip[v-1]).substring(2),50,50);
            }
            g.setColor(Color.WHITE);
            g.drawString(equip[0],170,110);
            g.drawString(equip[1],170,140);
        }
        g.setColor(Color.BLACK);
        g.drawString("�U����: "+offence+" �h���: "+defence,295,380);
        g.setColor(Color.WHITE);
        if(list.size() != 0 && d == 1 && v > 0) {
            Item_use.use_save_dispose(g,list.get(v-1)+"���ǂ���",0);
        }
        if(d == 3) {
            if(v == 0) Item_use.use_save_dispose(g,"�Z�[�u����",3);
            if(v == 1) Item_use.use_save_dispose(g,"���I����",5);
        }
        g.drawString(cant_use, 250,110);
        g.drawRoundRect(x,y, square_width,30, 10,10);
    }
    public void controll(KeyEvent e, int key) {
        epi.controll(e, key);
        int dire = 0;
        int move_pixel = 0;
        if(v == 0 && !Item_use.enter) {
            if(key == KeyEvent.VK_LEFT)    dire = 1;
            if(key == KeyEvent.VK_RIGHT)   dire = 2;
        }
        if(key == KeyEvent.VK_UP)      dire = 3;
        if(key == KeyEvent.VK_DOWN)    dire = 4;
        if(key == KeyEvent.VK_ENTER) {
            cant_use = "";
         
            Sounds.play_sound(1);
            if(d == 1 && v == 0) return;
            if(d != 2) Item_use.enter = !Item_use.enter;
            if(!Item_use.enter && Item_use.v == 0) {
                if(d == 1 && list.size() > 0) {
                    if(Item_use.d == 0)  equip_or_heal(list.get(v-1),v-1, false);
                    if(Item_use.d == 1)  item_remove(v-1);

                } else if(d == 3) {
                    if(v == 0 && Item_use.d == 0) {
                        Save.save_status(HP,MP,my_money,offence,defence);
                        Save.save_info(list,quantity,equip);
                    } else if(v == 1 && Item_use.d == 0) {
                        System.exit(1000);
                    }
                }
            }
        } else {
            Sounds.play_sound(0);
        }
        if(Item_use.enter){
           Item_use.choose(e, key);
        } else {
            Item_use.v = 0;
            if(dire == 1 && d > 1)  d--;
            if(dire == 2 && d < 3)  d++;
            if(dire == 3 && v > 0)  v--;
            if(d == 1) {
                if(dire == 4 && v < list.size()) v++;
            } else if(d == 2){
                if(dire == 4 && v < 2 && !(equip[0].equals("") || equip[1].equals(""))) v++;
            }
            if(d != 3) {
                square_width = 120;move_pixel = 75;
            } else {
                int dd = four_five(d);
                square_width = 80;move_pixel = 70+(dd*5);
            }
            y = (30*v)+60;
            x = (120*d)-move_pixel;
        }
    }
    public void item_add(int floors[][][], int floor_num, int xx, int yy) {
        if(floors[floor_num][yy][xx] == 20) {
            if(list.size() >= 10)  return;
            int rand = random.nextInt(0,7);
            int index = duplicate_check(item[rand]);
            if(index == -1) {
                list.add(item[rand]);
            } else {
                quantity[index]++;
            }
            floors[floor_num][yy][xx] = 0;
        }
    }
    static final int duplicate_check(String str) {
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).equals(str) && quantity[i] < 9) {
                return i;
            }
        }
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
            for(int i = 0; i < 19; i++) {
                hashmap.put(item[i],list_comm.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static final int get_item_id(String item_name) {
        String str = hashmap.get(item_name).substring(0,2);
        return Integer.parseInt(str);   
    }
    static void equip_or_heal(String item_name,int vv, boolean buff) {
        int att = 350, def = 250;
        int item_id = get_item_id(item_name);
        switch(item_id) {
            case 11: HP = HP + (int)(HP * 0.15); break;
            case 12: HP = HP + (int)(HP * 0.5);  break;
            case 13: HP = 2500;                  break;
            case 21: MP = MP + (int)(MP * 0.15); break;
            case 22: MP = MP + (int)(MP * 0.5);  break;
            case 23: MP = 500;                   break;
            case 31: offence = att+100;  break;
            case 32: offence = att+350;  break;
            case 33: offence = att+400;  break;
            case 34: offence = 9999;     break;
            case 41: defence = def+100;  break;
            case 42: defence = def+200;  break;
            case 43: defence = def+250;  break;
            case 44: defence = 9999;     break;
        }
        if(30 < item_id && item_id  < 35) {
            if(!(equip[0].equals(""))) list.add(equip[0]);
            equip[0] = list.get(vv);
        } else if(40 < item_id && item_id  < 45) {
            if(!(equip[1].equals(""))) list.add(equip[1]);
            equip[1] = list.get(vv);
        } else if(item_id == 53) {
            epi.ending_flg(3);

        } else if(item_id >= 50 && !buff) {
            cant_use = "";
        }
        if(HP >= 2500) HP = 2500;
        if(MP >= 500) MP = 500;
        if((item_id & 50)!= 50) item_decrease(vv, item_name);
    }
    private void item_remove(int list_num) {
        list.remove(list.get(list_num));
        quantity[list_num] = 0;
        array_trim(list_num);
        v = 1;
    }
    private static void item_decrease(int index,String item_name) {
        quantity[index]--;
        cant_use = "";
        if(quantity[index] == 0) {
            list.remove(item_name);
            array_trim(index);
        }
        v = 1;
    }
    private static void array_trim(int index) {
        int i,len = quantity.length-1; 
        for(i = index; i < len; i++) quantity[i] = quantity[i+1];
        quantity[i] = 0;
    }
    private int four_five(int number) {
        if(number >= 5) return number;
        return 1;
    }
}