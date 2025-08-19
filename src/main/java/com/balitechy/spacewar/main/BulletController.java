package com.balitechy.spacewar.main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

public class BulletController {
	private LinkedList<Bullet> bl = new LinkedList<Bullet>();

	public void tick() {
		for (int i = 0; i < bl.size(); i++) {
			if (bl.get(i).getY() < 0) {
				removeBullet(bl.get(i));
			} else {
				bl.get(i).tick();
			}
		}
	}

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g; // convertimos a Graphics2D
		for (int i = 0; i < bl.size(); i++) {
			bl.get(i).draw(g2); // llamamos al método draw de Bullet
		}
	}

	public void addBullet(Bullet bullet) {
		bl.add(bullet);
	}

	public void removeBullet(Bullet bullet) {
		bl.remove(bullet);
	}
}
