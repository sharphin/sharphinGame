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
        g.drawString("X: "+x,500,40);
        g.drawString("Y: "+y,570,40);
        g.drawString("map_num: "+map_num,500,70);
        g.drawString("hp: "+Game_states.getHP(),500,100);
        g.drawString("hunger_level: "+Game_states.getHunger_level(),500,130);
        g.drawString("bank_money: "+Game_states.getBank_money(),500,160);
        g.drawString("luck: "+Game_states.getLuck(),500,190);
        g.drawString("mental: "+Game_states.getMental(),500,220);
        g.drawString("lhealth: "+Game_states.getHealth(),500,250);
        g.drawString("stamina: "+Game_states.getStamina(),500,280);
        g.drawString("dept: "+Game_states.getDebt(),500,310);
        g.drawString("loan: "+Game_states.getLoan(),500,340);
        g.drawString("branch_state: "+Game_states.getBranch_state(),500,370);
        g.drawString("game_state: "+Game_states.getControll_state(),500,400);
    }
}
