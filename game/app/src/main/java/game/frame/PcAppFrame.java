package game.frame;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import game.panel.PC_paint;
import game.panel.sub_panel.Minimap_panel;
import game.panel.sub_panel.QR_panel;
import game.panel.sub_panel.Archive_panel;
import game.util.GameUtil;

public class PcAppFrame extends JFrame implements WindowListener{
    PC_paint pp;
    private static boolean view_mini_map;
    private static boolean view_truth;
    private static boolean view_qr;
    private String app_name;
    public PcAppFrame(String app_name, int width, int height) {
        ImageIcon icon = new ImageIcon(GameUtil.FILE_PATH+"gamedata/image/icon.png");
        setIconImage(icon.getImage());
        setBounds(330,130,width,height);
        setTitle(app_name);
        setVisible(true);
        setResizable(false);
        addWindowListener(this);
        this.app_name = app_name;
        switch(app_name) {
            case "mini map": view_mini_map = true;
                             add(new Minimap_panel());
                             break;
            case "archive":  view_truth = true;
                             add(new Archive_panel());
                             break;
            case "QR":       view_qr = true;
                             add(new QR_panel());
                             break;
        }
        if(app_name.equals("mini map")) view_mini_map = true;
        if(app_name.equals("archive")) view_truth = true;
        if(app_name.equals("QR")) view_qr = true;
    }
    public static boolean ViewMiniMap(){
        return view_mini_map;
    }
    public static boolean ViewTruth(){
        return view_truth;
    }
    public static boolean ViewQr(){
        return view_qr;
    }
    public void windowClosed(WindowEvent e) {}
    public void windowOpened(WindowEvent e) {}
    public void windowClosing(WindowEvent e) {
        switch(app_name) {
            case "mini map" -> view_mini_map = false;
            case "archive"    -> view_truth = false;
            case "QR"       -> view_qr = false;
        }
    }
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
}
