import frame.BaseFrame;
import util.FontUtil;

public final class RPG {
    public static void main(String[] args) {
        FontUtil font = new FontUtil();
        font.initMplus1Code();
        BaseFrame.frame_generator();
    }
}