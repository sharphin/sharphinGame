package save_load;
import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;

import javax.swing.*;
import java.awt.*;

import java.util.Calendar;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class Save{

    private int v = 1;
    private int year,month, day;
    Load data_view = new Load(); 
    Calendar calender = Calendar.getInstance();

    JTextField input_name;

    private static long status;
    private static int coords;
    private static int power;
    private static int item_quantity[] = new int[10];
    private static List<String>items = new ArrayList<>(10);
    private static String equip[] = new String[2];

    public static Save save_coords(int x, int y) {
        return new Save(x, y);
    }
    public static Save save_info(List<String>items,int item_quantity[],String equip[]) {
        return new Save(items,item_quantity, equip);
    }
    public static Save save_status(int hp,int mp,int my_money,int offe, int defe) {
        return new Save(hp,mp,my_money,offe,defe);
    }
    private Save(int x, int y){ 
        Save.coords = (y << 2)+(x << 12);
    }
    private Save(int hp,int mp,int my_money,int offe_power,int defe_power) {
        long hhp = hp;
        long mmp = mp;
        long mmoney= my_money;
        Save.power = (offe_power << 14)+defe_power;
        Save.status = mmoney+(mmp<<15)+(hhp<<24);
    }
    private Save(List<String> items, int item_quantity[], String equip[]) {
        Save.items = items;
        Save.equip = equip;
        Save.item_quantity = item_quantity;

        input_name = new JTextField("",10);
        JPanel panel = new JPanel();
        panel.setLayout(null);
     
        JButton save_bt = new JButton("SAVE");
        JButton cancel_bt = new JButton("CANCEL");

        save_bt.setBounds(15,50,100,30);
        cancel_bt.setBounds(115,50,100,30);
        save_bt.setBounds(15,50,100,30);
        cancel_bt.setBackground(Color.WHITE);
        save_bt.setBackground(Color.WHITE);
        input_name.setBounds(15,10,200,30);

        panel.add(input_name);
        panel.add(save_bt);
        panel.add(cancel_bt);
    }
    public void date() {
        year =  calender.get(Calendar.YEAR);
        month = calender.get(Calendar.MONTH)+1;
        day =   calender.get(Calendar.DATE);
    }
    public void write() {
        date();
        String name = input_name.getText();
        List<String> list = new ArrayList<String>(10);
        try {
            Path path = Paths.get("RPG/savedata/savedata.txt");
            for(int i = 0; i < Load.save_list.length; i++) { 
                if((v-1) == i) {
                    StringBuilder builder = new StringBuilder();
                    builder.append(name).append(",");
                    builder.append(year).append(",");
                    builder.append(month).append(",");
                    builder.append(day).append(",");

                    builder.append(coords).append(",");
                    builder.append(status).append(",");
                    builder.append(power).append(",");
                    for(int j = 0; j < 10;j++) {
                        builder.append(item_quantity[j]).append(",");
                    }
                    builder.append(equip[0]).append(",");
                    builder.append(equip[1]).append(",");
                    for(int j = 0; j < items.size();j++) {
                        builder.append(items.get(j)).append(",");
                    }
                    Load.save_list[i] = builder.toString();
                }
            }
            list = Arrays.asList(Load.save_list);
            Files.write(path, list, Charset.forName("shift-JIS"));
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}