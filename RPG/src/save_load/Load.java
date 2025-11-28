package save_load;

import java.io.*;
import java.nio.file.*;
import java.nio.charset.Charset;

import java.util.List;
import java.awt.*;
public class Load{

    static String save_list[] = {",,,,,,",",,,,,,",",,,,,,",",,,,,,",
                                 ",,,,,,",",,,,,,",",,,,,,",",,,,,,"};
    Color select = new Color(200,200,200,155);

    private String view_savedata[] = {"","","","","","","",""};
    public Load() {}
    private void read() {
        Path path = Paths.get("RPG/savedata/saveData.txt");
        Charset charset = Charset.forName("shift-JIS");
        try {
      	    List<String> lineList = Files.readAllLines(path,charset);
            String data[];
            int i = -1;
            for (String line : lineList) {
                i++;
                data = line.split(",");
                if(data.length == 0) continue;
                save_list[i] = line;
                view_savedata[i] = data[1]+"�N "+data[2]+"�� "+data[3]+"��"+"     " +data[0];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }  
    }
}