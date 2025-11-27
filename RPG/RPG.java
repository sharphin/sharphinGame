import javax.swing.*;

import file.MapLoad;
import secret.GameUtil;
import secret.Game_view;

public class RPG extends JFrame {

    public static void main(String[] args) {
        new RPG();
    }
    public RPG() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(GameUtil.FRAME_X, GameUtil.FRAME_Y);
        setTitle("GAME");
        setVisible(true);
        MapLoad ml = new MapLoad();
        Game_view gv = new Game_view(ml.LoadMap(1));
        super.add(gv);
    }
}