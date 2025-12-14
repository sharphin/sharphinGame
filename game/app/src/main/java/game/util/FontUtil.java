package game.util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class FontUtil {
    private static Font Mplus1Code;
    public void initMplus1Code() {
        try{
            Mplus1Code = Font.createFont(Font.TRUETYPE_FONT,new File(GameUtil.FILE_PATH+"gamedata/font/Mplus1Code-ExtraLight.ttf"));
        } catch(FontFormatException e){
            System.out.println("FormatE");
        } catch(IOException e){
            System.out.println("ioE");
        }
    }
    public Font setFontSize_Mplus1Code(float fontsize) {
        Mplus1Code = Mplus1Code.deriveFont(fontsize);
        return Mplus1Code;
    }
}
