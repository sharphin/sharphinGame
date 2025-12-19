package game.panel.sub_panel;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;

import game.logic.Game_states;
import game.util.FontUtil;

public class Debug_paint {
    Font font;
    public Debug_paint() {

        FontUtil fl = new FontUtil();
        font = fl.setFontSize_Mplus1Code(20f);
    }
    public void paint_debug(Graphics g, int x, int y, int map_num) {
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("X: "+(x>>5),500,30);
        g.drawString("Y: "+(y>>5),570,30);
        g.drawString("money:"+Game_states.getMoney(),500,60);
        g.drawString("map_num: "+map_num,500,90);
        g.drawString("hunger_level: "+Game_states.getHunger_level(),500,120);
        g.drawString("mental: "+Game_states.getMental(),500,150);
        g.drawString("stamina: "+Game_states.getStamina(),500,180);
        g.drawString("branch_state: "+Game_states.getRoute_branch(),500,210);
        g.drawString("game_state: "+Game_states.getControll_state(),500,240);
        if(map_num>= 69){
            g.drawString("floor: B1F",500,270);
        } else if(map_num>= 35){
            g.drawString("floor: 3F",500,270);
        } else if(map_num>= 19){
            g.drawString("floor: 2F",500,270);
        } else {
            g.drawString("floor: 1F",500,270);
        }
    }
}
