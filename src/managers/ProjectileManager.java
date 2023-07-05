package managers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enemies.Enemy;

import helpz.LoadSave;
import objects.Projectile;
import objects.Tower;
import scenes.Playing;
import static helpz.Constants.Towers.*;
import static helpz.Constants.Projectiles.*;

public class ProjectileManager {

	private Playing playing;
	private ArrayList<Projectile> projectiles = new ArrayList<>();
	private BufferedImage[] proj_imgs;
	private int proj_id = 0;

	public ProjectileManager(Playing playing) {
		this.playing = playing;

		importImgs();

	}

	private void importImgs() {

		BufferedImage atlas = LoadSave.getSpriteAtlas();

		proj_imgs = new BufferedImage[3];

		proj_imgs[2] = atlas.getSubimage(34 * 32, 2 * 32, 32, 32);// mago
		proj_imgs[1] = atlas.getSubimage(36 * 32, 3 * 32, 32, 32);// campesino
		proj_imgs[0] = atlas.getSubimage(35 * 32, 7 * 32, 32, 32);// caballero

	}

	public void newProjectile(Tower t, Enemy e) {
		int type = getProjType(t);

		int xDist = (int) (t.getX() - e.getX());
		int yDist = (int) (t.getY() - e.getY());
		int totDist = Math.abs(xDist) + Math.abs(yDist);

		float xPer = (float) Math.abs(xDist) / totDist;

		float xSpeed = xPer * helpz.Constants.Projectiles.GetSpeed(type);
		float ySpeed = helpz.Constants.Projectiles.GetSpeed(type) - xSpeed;

		if (t.getX() > e.getX())
			xSpeed *= -1;
		if (t.getY() > e.getY())
			ySpeed *= -1;

		float arcValue = (float) Math.atan(yDist / (float) xDist);
		float rotate = (float) Math.toDegrees(arcValue);

		if (xDist < 0)
			rotate += 180;

		projectiles
				.add(new Projectile(t.getX() + 16, t.getY() + 16, xSpeed, ySpeed, t.getDmg(), rotate, proj_id++, type));

	}

	private int getProjType(Tower t) {
		switch (t.getTowerType()) {
		case MAGO:
			return FIREBOLT;
		case CAMPESINO:
			return SPEAR;
		case CABALLERO:
			return SWORD;

		}
		return 0;
	}

	public void update() {
		for (Projectile p : projectiles)
			if (p.isActive()) {
				p.move();
				if (isProjHittingEnemy(p)) {
					p.setActive(false);
				} else {
					// we do nothing
				}
			} else if (isProOutsideBounds(p)) {
				p.setActive(false);
			}

	}

	// para cuando un proyectil falle y salga de la ventana, se elimine
	private boolean isProOutsideBounds(Projectile p) {
		if (p.getPos().x > 0)
			if (p.getPos().x <= 640)
				if (p.getPos().y >= 0)
					if (p.getPos().y <= 800)
						return false;
		return true;
	}

	private boolean isProjHittingEnemy(Projectile p) {
		for (Enemy e : playing.getEnemyManager().getEnemies()) {
			if (e.isAlive())
				if (e.getBounds().contains(p.getPos())) {
					e.hurt(p.getDmg());

					return true;
				}
		}
		return false;
	}

	public void draw(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		for (Projectile p : projectiles)
			if (p.isActive()) {

				g2d.translate(p.getPos().x, p.getPos().y);
				g2d.rotate(Math.toRadians(p.getRotation()));
				g2d.drawImage(proj_imgs[p.getProjectileType()], -16, -16, null);
				g2d.rotate(-Math.toRadians(p.getRotation()));
				g2d.translate(-p.getPos().x, -p.getPos().y);

			}

	}

}
