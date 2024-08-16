package com.balitechy.spacewar.main.Controllers;

import com.balitechy.spacewar.main.Models.Abstract.AbstractBullet;

import java.awt.*;
import java.util.LinkedList;

public class ControllerBullet {

	private LinkedList<AbstractBullet> bl = new LinkedList<>();

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
		for (int i = 0; i < bl.size(); i++) {
			bl.get(i).renderBullet(g);
		}
	}

	public void addBullet(AbstractBullet bullet) {
		bl.add(bullet);
	}

	public void removeBullet(AbstractBullet bullet) {
		bl.remove(bullet);
	}
}

