package game.panel.sub_panel;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class QR_panel extends JPanel {
    private int tmp[];
    private int size = 20;
    public QR_panel() {
        setSize(540, 540);
        tmp = new int[]{33297791,17123137,24384605,
                        24510301,24422749,17144641,
                        33379711,54528,33012906,
                        31154434,30230411,17904657,
                        24562935,22160682,24987515,
                        21074353,17702321,107288,
                        33411415,17065755,24494070,
                        24471775,24480013,17160121,
                        33365183};
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 540, 540);
        for(int i = 0;i < tmp.length;i++) {
            StringBuilder num = new StringBuilder(Integer.toBinaryString(tmp[i]));
            num = compzero(num);
            for(int j = 24;j >= 0;j--) {
                switch (num.charAt(j)) {
                    case '0'-> g.setColor(Color.WHITE);
                    case '1'-> g.setColor(Color.BLACK);
                }
                g.fillRect((j*size)+20, (i*size)+20, size, size);
            }
        }
    }
    private StringBuilder compzero(StringBuilder num) {
        if(num.length() > 24) return num;
        num.insert(0, '0');
        compzero(num);
        return num;
    }
}
