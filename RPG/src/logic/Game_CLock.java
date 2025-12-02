package logic;

import java.time.LocalDateTime;

import util.GameUtil;

public class Game_CLock implements Runnable{

    private static LocalDateTime clock;
    public Game_CLock(LocalDateTime time) {
        clock = time;
        Thread th = new Thread(this);
        th.start();
    }
    public void run() {
        while (Game_states.getControll_state() != GameUtil.GAME_EXIT) {
            if((Game_states.getControll_state() & GameUtil.PLAY) == GameUtil.PLAY) clock = clock.plusSeconds(1);
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {}
        }
    }
    public LocalDateTime getNowTime() {
        return clock;
    }
}
