package managers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import enemies.Enemy;
import helpz.LoadSave;
import objects.Projectile;
import objects.Tower;
import scenes.Playing;
import static helpz.Constants.Towers.*;
import static helpz.Constants.Projectiles.*;
import objects.Projectile;

public class TowerManager {

	private Playing playing;
	private BufferedImage[] towerImgs;
	private ArrayList<Tower> towers = new ArrayList<>();
	private int towerAmount = 0;

	public TowerManager(Playing playing) {
		this.playing = playing;

		loadTowerImgs();

	}

	// sprites de las torres
	private void loadTowerImgs() {
		BufferedImage atlas = LoadSave.getSpriteAtlas();
		towerImgs = new BufferedImage[3];
		towerImgs[0] = atlas.getSubimage(24 * 32, 1 * 32, 32, 32);// Mago
		towerImgs[1] = atlas.getSubimage(25 * 32, 1 * 32, 32, 32);// campesino
		towerImgs[2] = atlas.getSubimage(28 * 32, 0 * 32, 32, 32);// caballero

	}

	public void addTower(Tower selectedTower, int xPos, int yPos) {
		towers.add(new Tower(xPos, yPos, towerAmount++, selectedTower.getTowerType()));

	}

	public void update() {
		for (Tower t : towers) {
			t.update();
			attackEnemyIfClose(t);
		}
	}

	private void attackEnemyIfClose(Tower t) {
		for (Enemy e : playing.getEnemyManager().getEnemies()) {
			if (e.isAlive())
				if (isEnemyInRange(t, e)) {
					if (t.isCooldownOver()) {
						playing.shootEnemy(t, e);
						t.resetCooldown();
					}
				} else {
					// we do nothing
				}
		}

	}

	private boolean isEnemyInRange(Tower t, Enemy e) {
		int range = helpz.Utilz.getHypoDistance(t.getX(), t.getY(), e.getX(), e.getY());
		return range < t.getRange();
	}

	public void draw(Graphics g) {

		for (Tower t : towers)
			g.drawImage(towerImgs[t.getTowerType()], t.getX(), t.getY(), null);

//		g.drawImage(towerImgs[MAGO], tower.getX(), tower.getY(), null);

	}

	public Tower getTowerAt(int x, int y) {
		for (Tower t : towers)
			if (t.getX() == x)
				if (t.getY() == y)
					return t;
		return null;
	}

	public BufferedImage[] getTowerImgs() {
		return towerImgs;
	}

}
