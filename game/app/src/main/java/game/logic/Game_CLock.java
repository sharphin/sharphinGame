package game.logic;

import java.time.LocalDateTime;

import game.frame.BaseFrame;
import game.panel.PC_paint;
import game.panel.Title;
import game.util.GameUtil;

public class Game_CLock implements Runnable {

    private static LocalDateTime clock;
    private static long playtime;
    private int pc_leave_time;
    public Game_CLock(LocalDateTime time,long play_time) {
        clock = time;
        playtime = play_time;
        Thread th = new Thread(this);
        th.start();
    }
    public void run() {
        while (Game_states.getControll_state() != GameUtil.GAME_EXIT) {
            if((Game_states.getControll_state() & GameUtil.PLAY) == GameUtil.PLAY) {
                clock = clock.plusSeconds(1);
                playtime++;
                if(PC_paint.pclogin()) pc_leave_time++;
                if(pc_leave_time > 60) PC_paint.pclogout();
            }
            if(pc_leave_time > 0 && (Game_states.getControll_state() & GameUtil.PC) == GameUtil.PC) pc_leave_time = 0;
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {}
        }
        BaseFrame.frame_generator().back_title(new Title());
    }
    public LocalDateTime getNowTime() {
        return clock;
    }
    public long getPlayTime() {
        return playtime;
    }
}
