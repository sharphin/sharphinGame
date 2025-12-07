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
        g.drawString("X: "+x,500,60);
        g.drawString("Y: "+y,570,60);
        g.drawString("map_num: "+map_num,500,90);
        g.drawString("hp: "+Game_states.getHP(),500,120);
        g.drawString("hunger_level: "+Game_states.getHunger_level(),500,150);
        g.drawString("bank_money: "+Game_states.getBank_money(),500,180);
        g.drawString("luck: "+Game_states.getLuck(),500,210);
        g.drawString("mental: "+Game_states.getMental(),500,240);
        g.drawString("lhealth: "+Game_states.getHealth(),500,270);
        g.drawString("stamina: "+Game_states.getStamina(),500,300);
        g.drawString("dept: "+Game_states.getDebt(),500,330);
        g.drawString("loan: "+Game_states.getLoan(),500,360);
        g.drawString("branch_state: "+Game_states.getBranch_state(),500,390);
        g.drawString("game_state: "+Game_states.getControll_state(),500,420);
    }
}
