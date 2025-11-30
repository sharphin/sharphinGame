package save_load;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.format.DateTimeFormatter;
import java.util.HexFormat;

import logic.Game_states;
import main_panel.Maps;
import util.GameUtil;

public class Save {
    private int x,y;
    public Save(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void write(int saveslot, String save_slot[]) {
        File f = new File("RPG/savedata/saveData.txt");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String mf = "RPG/savedata/"+save_slot_SHA_256("RPG/savedata/"+saveslot);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f,false))) {
            for(int i = 0; i < save_slot.length; i++) {
                String write_str = save_slot[i];
                if(saveslot == i) {
                    StringBuilder sb =new StringBuilder();
                    sb.append(Game_states.getName()).append(",");
                    sb.append(Game_states.getTODAY()).append(",");
                    sb.append(x).append(",");
                    sb.append(y).append(",");
                    sb.append(Game_states.getHP()).append(",");
                    sb.append(Game_states.getHunger_level()).append(",");
                    sb.append(Game_states.getMoney()).append(",");
                    sb.append(Game_states.getBank_money()).append(",");
                    sb.append(Game_states.getBranch_state()).append(",");
                    sb.append(Game_states.getControll_state()).append(",");
                    sb.append(mf).append(",");
                    for(int j = 0; j < GameUtil.MAX_ITEM; j++) {
                        if(i+1 < Game_states.getAllItem().size()) {
                            sb.append(Game_states.getItem(j)).append(",");
                        } else {
                            sb.append(",");
                        }
                    }
                    write_str = sb.toString();
                }
                bw.write(write_str);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        String filepath = mf+"/map1.csv";
        File mapf = new File(mf);
        mapf.mkdirs();
        mapf = new File(filepath);
        Path path = Paths.get(filepath);
                System.out.println(Files.exists(path));
        if(!(Files.exists(path))){
            try {
                Files.createFile(path);
            } catch (IOException e) {}
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(mapf,false))) {
            Maps maps = new Maps();
            int map[][] = maps.getMap();
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
    private String save_slot_SHA_256(String slot) {
        String hexString = "";
        try {
			MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
			byte[] sha256Byte = sha256.digest(slot.getBytes());
			
			//Java17以降からしか使用出来ない
			HexFormat hex = HexFormat.of().withLowerCase();
			hexString = hex.formatHex(sha256Byte);
			System.out.println("SHA256");
			System.out.println(hexString);
		} catch (NoSuchAlgorithmException e) {
			
		}
        return hexString;
    }
}