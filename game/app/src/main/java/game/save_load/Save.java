package game.save_load;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.ZoneId;
import java.util.HexFormat;

import game.logic.Game_states;
import game.main_panel.Maps;

public class Save {
    private int x,y, map_number;
    private long play_time;
    public Save(int x, int y, int map_number, long play_time){
        this.x = x;
        this.y = y;
        this.map_number = map_number;
        this.play_time = play_time;
    }
    public void write(int saveslot, boolean game_clear, String save_slot[]) {
        String dirPath = System.getProperty("user.home")+ "/AppData/Local/Revenge/savedata/";
        String filepath = dirPath+"saveData.dat";
        File f = new File(dirPath);
        f.mkdirs();
        mkfile(filepath);

        String dir = save_slot_SHA_256("/savedata/"+saveslot);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filepath),false))) {
            for(int i = 0; i < save_slot.length; i++) {
                String write_str = save_slot[i];
                if(saveslot == i) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(Game_states.getName()).append(",");
                    sb.append(play_time).append(",");
                    sb.append(coords_encrypt(x, y, map_number)).append(",");
                    sb.append(dir).append(",");
                    sb.append(makeDataProtect(coords_encrypt(x, y, map_number))).append(",");
                    if(game_clear){sb.append(1);} else {sb.append(0);}
                    write_str = sb.toString();
                }
                if(write_str != null) bw.write(write_str);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        dir = filepathBuilder(dirPath,dir);
        File mapf = new File(dir);
        mapf.mkdirs();
        Maps maps = new Maps();
        for(int ii = 0; ii < maps.readMapKinds();ii++)  {
            filepath = filepathBuilder(dir,maps.map_file_name(ii));
            mkfile(filepath);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filepath),false))) {
                int map[][] = maps.getMap(ii);
                for(int i = 0; i < map.length; i++) {
                    StringBuilder sb = new StringBuilder();
                    for(int j = 0; j < map[0].length-1; j++) {
                        sb.append(map[i][j]).append(",");
                    }
                    sb.append(map[i][map[0].length-1]);
                    bw.write(sb.toString());
                    bw.newLine();
                }
                bw.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        filepath = dir+"/states.csv";
        mkfile(filepath);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filepath),false))) {
            StringBuilder sb = new StringBuilder();
            sb.append(Game_states.getTodayTime().atZone(ZoneId.systemDefault()).toEpochSecond()).append(",");
            sb.append(Game_states.getHunger_level()).append(",");
            sb.append(Game_states.getMoney()).append(",");
            sb.append(Game_states.getRoute_branch()).append(",");
            sb.append(Game_states.getMental()).append(",");
            sb.append(Game_states.getStamina()).append(",");
            sb.append(passwordEncrypt(Game_states.getPCPassword())).append(",");
            sb.append(encryption(Game_states.getAllInventory())).append(",");
            bw.write(sb.toString());
            sb.delete(0, sb.length());
            bw.newLine();
            sb.append(Game_states.getAllItemDictionary()[0]).append(",");
            sb.append(Game_states.getAllItemDictionary()[1]).append(",");
            sb.append(Game_states.getAllItemDictionary()[2]).append(",");
            sb.append(Game_states.getAllItemDictionary()[3]).append(",");
            bw.write(sb.toString());
            bw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        filepath = dir+"/mask.csv";
        mkfile(filepath);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filepath),false))) {
            StringBuilder sb = new StringBuilder();
            sb.append(makeDataProtect(Game_states.getTodayTime().atZone(ZoneId.systemDefault()).toEpochSecond())).append(",");
            sb.append(makeDataProtect(Game_states.getHunger_level())).append(",");
            sb.append(makeDataProtect(Game_states.getMoney())).append(",");
            sb.append(makeDataProtect(Game_states.getRoute_branch())).append(",");
            sb.append(makeDataProtect(Game_states.getMental())).append(",");
            sb.append(makeDataProtect(Game_states.getStamina())).append(",");
            sb.append(makeDataProtect(passwordEncrypt(Game_states.getPCPassword()))).append(",");
            sb.append(makeDataProtect(encryption(Game_states.getAllInventory())));
            bw.write(sb.toString());
            sb.delete(0, sb.length());
            bw.newLine();
            sb.append(makeDataProtect(Game_states.getAllItemDictionary()[0])).append(",");
            sb.append(makeDataProtect(Game_states.getAllItemDictionary()[1])).append(",");
            sb.append(makeDataProtect(Game_states.getAllItemDictionary()[2])).append(",");
            sb.append(makeDataProtect(Game_states.getAllItemDictionary()[3])).append(",");
            bw.write(sb.toString());
            bw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    private void mkfile(String filepath) {
        Path path = Paths.get(filepath);
        if(!(Files.exists(path))){
            try {
                Files.createFile(path);
            } catch (IOException e) {}
        }
    }
    private String filepathBuilder(String dir, String filename) {
        StringBuilder sb = new StringBuilder();
        return sb.append(dir).append(filename).toString();
    } 
    private String save_slot_SHA_256(String slot) {
        String hexString = "";
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] sha256Byte = sha256.digest(slot.getBytes());
            HexFormat hex = HexFormat.of().withLowerCase();
            hexString = hex.formatHex(sha256Byte);
        } catch (NoSuchAlgorithmException e) {
            
        }
        return hexString;
    }
    public long encryption(int itemsInfo[]) {
        long encrypted = itemsInfo[0];
        for(int i = 1,shift = 8; i< itemsInfo.length;shift+=8,i++) {
            long num = (long)itemsInfo[i]<<shift;
            encrypted += num;
        }
        return encrypted;
    }
    public long coords_encrypt(int x, int y, int map) {
        long ffff = (long)x<<19;
        ffff = ffff+(long)y<<19;
        ffff = ffff+(long)map;
        return ffff;
    }
    private static int passwordEncrypt(int num) {
        return ~num & 1048575;
    }
    private int makeDataProtect(int num) {
        int mask = -1498760516;
        return Integer.reverse(num ^ mask);
    }
    private long makeDataProtect(long num) {
        long mask = 35741560992348860l;
        return Long.reverse(num ^ mask);
    }
}