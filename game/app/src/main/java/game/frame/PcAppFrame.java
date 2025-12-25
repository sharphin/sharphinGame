package game.frame;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import game.panel.PC_paint;
import game.panel.sub_panel.Minimap_panel;
import game.panel.sub_panel.Truth_panel;
import game.util.GameUtil;

public class PcAppFrame extends JFrame implements WindowListener{
    PC_paint pp;
    Character charc;
    private static boolean view_mini_map;
    private static boolean view_truth;
    private String app_name;
    public PcAppFrame(String app_name) {
        ImageIcon icon = new ImageIcon(GameUtil.FILE_PATH+"gamedata/image/icon.png");
        setIconImage(icon.getImage());
        setBounds(330,130,GameUtil.FRAME_X, GameUtil.FRAME_Y);
        setTitle(app_name);
        setVisible(true);
        setResizable(false);
        addWindowListener(this);
        this.app_name = app_name;
        switch(app_name) {
            case "mini map": view_mini_map = true;
                             add(new Minimap_panel());
                             break;
            case "truth":    view_truth = true;
                             add(new Truth_panel());
                             break;
        }
        if(app_name.equals("mini map")) view_mini_map = true;
        if(app_name.equals("truth")) view_truth = true;
    }
    public void setViewMiniMap(boolean minimap){
        view_mini_map = minimap;
    }
    public void setViewTruth(boolean truth){
        view_truth = truth;
    }
    public static boolean ViewMiniMap(){
        return view_mini_map;
    }
    public static boolean ViewTruth(){
        return view_truth;
    }

    public void windowClosed(WindowEvent e) {
        switch(app_name) {
            case "mini map" -> view_mini_map = false;
            case "truth"    -> view_truth = false;
        }
    }
    public void windowOpened(WindowEvent e) {}
    public void windowClosing(WindowEvent e) {
        switch(app_name) {
            case "mini map" -> view_mini_map = false;
            case "truth"    -> view_truth = false;
        }
    }
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}

}
