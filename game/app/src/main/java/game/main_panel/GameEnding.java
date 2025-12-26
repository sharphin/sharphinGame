
package game.main_panel;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JPanel;

import game.logic.Game_states;
import game.util.FontUtil;
import game.util.GameUtil;

public class GameEnding extends JPanel implements Runnable{
    
    private final int width = GameUtil.PANEL_X+3, height = GameUtil.PANEL_Y+3;
    private String endingMessage;
    Font font;
    public GameEnding(String endingMessage) {
        this.endingMessage = endingMessage;
        setSize(width, height);
        setFocusable(true);
        Thread th = new Thread(this);
        th.start();
        FontUtil fl = new FontUtil();
        font = fl.setFontSize_Mplus1Code(40f);
        if(this.endingMessage.equals("GAME CLEAR")) write();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(font);
        g.drawString(endingMessage,220,200);
    }
    public void write() {
        String dirPath = System.getProperty("user.home")+ "/Documents/Revenge/";
        File f = new File(dirPath);
        if(!f.exists())f.mkdirs();

        String filepath = f+"/fromcomputer.txt";
        f = new File(filepath);
        if(!f.exists()) {
            mkfile(filepath);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(f,false))) {
                bw.write("脱出おめでとうございます。");
                bw.newLine();
                bw.write("リアル脱出ゲームのリベンジは成功でしょうか？");
                bw.newLine();
                bw.newLine();
                bw.write("このゲームの開発者sharphinから伝言を預かっています。");
                bw.newLine();
                bw.write("・この場所にこのファイルが存在している限りは、少なくとも一度脱出した扱いとなる。");
                bw.newLine();
                bw.write("・脱出者のNEW GAMEには、仕掛けを追加してある。");
                bw.newLine();
                bw.write("・あのPCのパスワードは6桁の数字だが、正確な値は分からない。");
                bw.newLine();
                bw.write("・あのPCのパスワードはNEW GAMEのたびに生成される。");
                bw.newLine();
                bw.write("・プロローグが長ったらしいだろう？ 名前を「skip」にすれば飛ばせる。");
                bw.newLine();

                bw.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
    private void mkfile(String filepath) {
        Path path = Paths.get(filepath);
        if(!(Files.exists(path))){
            try {
                Files.createFile(path);
            } catch (IOException e) {}
        }
    }
    public void run() {
        try{
            Thread.sleep(2000);
        } catch(InterruptedException e) {}
        Game_states.updateControll_state(GameUtil.GAME_EXIT);
    }
}