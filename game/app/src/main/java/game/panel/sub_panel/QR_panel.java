package game.panel.sub_panel;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class QR_panel extends JPanel {
    private String tmp[];
    private int size = 20;
    public QR_panel() {
        setSize(540, 540);
        tmp = new String[]{
        "1111111000001010101111111",
        "1000001010100011101000001",
        "1011101000001010001011101",
        "1011101011111111101011101",
        "1011101001010100101011101",
        "1000001011001101101000001",
        "1111111010101010101111111",
        "0000000001101010100000000",
        "1111101111011110010101010",
        "1110110110110000100000010",
        "1110011010100011110001011",
        "1000100010011010000010001",
        "1011101101100110011110111",
        "1010100100010010100101010",
        "1011111010100011101111011",
        "1010000011001000110110001",
        "1000011100001110110110001",
        "0000000011010001100011000",
        "1111111011101000101010111",
        "1000001000110011100011011",
        "1011101011011111111110110",
        "1011101010110100011011111",
        "1011101011000100100001101",
        "1000001011101011110111001",
        "1111111010001110010111111"};
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 540, 540);
        for(int i = 0;i < tmp.length;i++) {
            for(int j = 0;j < tmp[i].length();j++) {
                switch (tmp[i].charAt(j)) {
                    case '0'-> g.setColor(Color.WHITE);
                    case '1'-> g.setColor(Color.BLACK);
                }
                g.fillRect((j*size)+20, (i*size)+20, size, size);
            }
        }
    }
}
