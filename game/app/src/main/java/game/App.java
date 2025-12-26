package game;

import game.frame.BaseFrame;
import game.frame.PcAppFrame;
import game.util.FontUtil;
public class App {
    public String getGreeting() {
        return "WELCOME TO GAME!";
    }
    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
        FontUtil font = new FontUtil();
        font.initMplus1Code();
        BaseFrame.frame_generator();
        int key_item_mask = 4096;
        int key_item = 20;
        int tile_num = 3;
        int fff = (key_item<<13)+key_item_mask +tile_num;
        IO.println("zip "+~(fff)+" "+Integer.toBinaryString(fff));
        new PcAppFrame("QR", 556, 579);
    }
}
