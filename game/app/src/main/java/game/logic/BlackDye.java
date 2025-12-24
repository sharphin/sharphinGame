package game.logic;

import java.util.concurrent.ThreadLocalRandom;

import game.main_panel.Maps;
import game.util.GameUtil;

public class BlackDye implements Runnable{
    private Maps map = new Maps();
    private ThreadLocalRandom rand  = ThreadLocalRandom.current();
    public BlackDye() {
        Thread th = new Thread(this);
        th.start();
    }
    private void dye() {
        if(Game_states.getControll_state() == GameUtil.GAME_EXIT || Game_states.getControll_state() == GameUtil.GAME_END) return;
        sleep(100);
        int x = rand.nextInt(33);
        int y = rand.nextInt(33);
        if(map.dye_black(x, y));
        dye();
    }
    private void sleep(int millis) {
        try{
            Thread.sleep(millis);
        } catch(InterruptedException e) {}
    }
    public void run() {
        dye();
    }
}
