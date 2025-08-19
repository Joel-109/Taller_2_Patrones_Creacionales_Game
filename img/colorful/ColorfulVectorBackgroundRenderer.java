package com.balitechy.spacewar.colorful;

import com.balitechy.spacewar.main.BackgroundRenderer;
import java.awt.Graphics; import java.awt.Color;

public class ColorfulVectorBackgroundRenderer implements BackgroundRenderer {
    @Override
    public void draw(Graphics g, int width, int height) {
        java.awt.Color prev = g.getColor();
        for (int i=0;i<80;i++){
            g.setColor(new Color((i*37)%256, (i*59)%256, (i*97)%256));
            int x = (i*31)%width; int y=(i*43)%height;
            g.fillOval(x,y,2+(i%3),2+(i%3));
        }
        g.setColor(prev);
    }
}