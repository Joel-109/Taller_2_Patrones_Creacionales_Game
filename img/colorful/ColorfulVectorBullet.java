package com.balitechy.spacewar.colorful;
import com.balitechy.spacewar.main.Bullet;
import java.awt.Graphics; import java.awt.Color;

public class ColorfulVectorBullet implements Bullet {
    private int x,y; private boolean alive=true;
    @Override public void update(){ y-=6; if(y<-10) alive=false; }
    @Override public void draw(Graphics g){ java.awt.Color prev=g.getColor(); g.setColor(Color.YELLOW); g.fillOval(x-3,y-6,6,10); g.setColor(prev);}    
    @Override public boolean isAlive(){return alive;}
}

