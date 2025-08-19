package com.balitechy.spacewar.colorful;

import com.balitechy.spacewar.main.Player;
import java.awt.Graphics;
import java.awt.Color;

public class ColorfulVectorPlayer extends Player {
    private int x,y;
    public ColorfulVectorPlayer(int x,int y){this.x=x;this.y=y;}
    @Override public void update() {}
    @Override
    public void draw(Graphics g){
        Color prev = g.getColor();
        g.setColor(Color.MAGENTA);
        int[] xs = {x, x-12, x+12};
        int[] ys = {y-16, y+12, y+12};
        g.fillPolygon(xs, ys, 3);
        g.setColor(prev);
    }
    @Override public int getX(){return x;} @Override public int getY(){return y;}
}