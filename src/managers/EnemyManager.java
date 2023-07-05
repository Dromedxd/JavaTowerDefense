package managers;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enemies.Blue;
import enemies.Enemy;
import enemies.Green;
import enemies.Yellow;
import helpz.LoadSave;
import objects.PathPoint;
import scenes.Playing;
import static helpz.Constants.Direction.*;
import static helpz.Constants.Tiles.*;
import static helpz.Constants.Enemies.*;;

public class EnemyManager {

	private Playing playing;
	private BufferedImage[] enemyImgs;
	private ArrayList<Enemy> enemies = new ArrayList<>();
	private PathPoint start, end;
	private int HPBarWidth = 20;

//	private float speed = 0.5f;

	public EnemyManager(Playing playing, PathPoint start, PathPoint end) {
		enemyImgs = new BufferedImage[3];// solo tengo 3 enemigos
		this.playing = playing;
		this.start = start;
		this.end = end;

//		addEnemy(BLUE);
//		addEnemy(GREEN);
//		addEnemy(YELLOW);

		loadEnemyImgs();

	}

	private void loadEnemyImgs() {

		BufferedImage atlas = LoadSave.getSpriteAtlas();

		// Azul
		enemyImgs[0] = atlas.getSubimage(32 * 2, 32 * 22, 32, 32);

		// Verde
		enemyImgs[1] = atlas.getSubimage(32 * 8, 32 * 23, 32, 32);

		// Amarillo
		enemyImgs[2] = atlas.getSubimage(32 * 8, 32 * 22, 32, 32);

	}

	public void update() {

		for (Enemy e : enemies)
			if (e.isAlive())
				updateEnemyMove(e);

	}

	public void updateEnemyMove(Enemy e) {
		if (e.getLastDir() == -1)
			setNewDirectionAndMove(e);

		int newX = (int) (e.getX() + getSpeedAndWidth(e.getLastDir(), e.getEnemyType()));
		int newY = (int) (e.getY() + getSpeedAndHeight(e.getLastDir(), e.getEnemyType()));

		if (getTileType(newX, newY) == ROAD_TILE) {
			e.move(GetSpeed(e.getEnemyType()), e.getLastDir());
		} else if (isAtEnd(e)) {
			e.kill();
			playing.removeOneLife();
		} else {
			setNewDirectionAndMove(e);
		}
	}

	private void setNewDirectionAndMove(Enemy e) {
		int dir = e.getLastDir();

		int xCord = (int) (e.getX() / 32);
		int yCord = (int) (e.getY() / 32);

		fixEnemyOffsetTile(e, dir, xCord, yCord);

		if (isAtEnd(e))
			return;

		if (dir == LEFT || dir == RIGHT) {
			int newY = (int) (e.getY() + getSpeedAndHeight(UP, e.getEnemyType()));
			if (getTileType((int) e.getX(), newY) == ROAD_TILE)
				e.move(GetSpeed(e.getEnemyType()), UP);

			else
				e.move(GetSpeed(e.getEnemyType()), DOWN);
		} else {
			int newX = (int) (e.getX() + getSpeedAndWidth(RIGHT, e.getEnemyType()));

			if (getTileType(newX, (int) e.getY()) == ROAD_TILE)
				e.move(GetSpeed(e.getEnemyType()), RIGHT);
			else
				e.move(GetSpeed(e.getEnemyType()), LEFT);

		}

	}

	private void fixEnemyOffsetTile(Enemy e, int dir, int xCord, int yCord) {
		switch (dir) {
		// el pavo del tutorial fuma porros
//		case LEFT:
//			if (xCord > 0)
//				xCord--;
//
//			break;
//		case UP:
//			if (yCord > 0)
//				yCord--;
//			break;
		case RIGHT:
			if (xCord < 19)
				xCord++;
			break;
		case DOWN:
			if (yCord < 19)
				yCord++;
			break;
		}

		e.setPos(xCord * 32, yCord * 32);

	}

	private boolean isAtEnd(Enemy e) {
		if (e.getX() == end.getxCord() * 32)
			if (e.getY() == end.getyCord() * 32)
				return true;
		return false;

	}

	private int getTileType(int x, int y) {

		return playing.getTileType(x, y);

	}

	private float getSpeedAndHeight(int dir, int enemyType) {
		if (dir == UP)
			return -GetSpeed(enemyType);
		else if (dir == DOWN)
			return GetSpeed(enemyType) + 32;

		return 0;
	}

	private float getSpeedAndWidth(int dir, int enemyType) {
		if (dir == LEFT)
			return -GetSpeed(enemyType);
		else if (dir == RIGHT)
			return GetSpeed(enemyType) + 32;

		return 0;
	}

	public void spawnEnemy(int nextEnemy) {
		addEnemy(nextEnemy);

	}

	public void addEnemy(int enemyType) {

		int x = start.getxCord() * 32;
		int y = start.getyCord() * 32;
		switch (enemyType) {
		case BLUE:
			enemies.add(new Blue(x, y, 0, this));
			break;
		case YELLOW:
			enemies.add(new Yellow(x, y, 0, this));
			break;
		case GREEN:
			enemies.add(new Green(x, y, 0, this));
			break;

		}

//		enemies.add(new Blue(x, y, 0));
	}

	public void draw(Graphics g) {

		for (Enemy e : enemies) {
			if (e.isAlive()) {
				drawEnemy(e, g);
				drawHealthBar(e, g);
			}
		}
	}

	public int getAmountOfAliveEnemies() {
		int size = 0;
		for (Enemy e : enemies)
			if (e.isAlive())
				size++;

		return size;
	}

	private void drawHealthBar(Enemy e, Graphics g) {

		g.setColor(Color.red);
		g.fillRect((int) e.getX() + 16 - (getNewBarWidth(e) / 2), (int) e.getY() - 8, getNewBarWidth(e), 2);
	}

	private int getNewBarWidth(Enemy e) {
		return (int) (HPBarWidth * e.getHealthBarFloat());
	}

	private void drawEnemy(Enemy e, Graphics g) {
		g.drawImage(enemyImgs[e.getEnemyType()], (int) e.getX(), (int) e.getY(), null);
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public void rewardPlayer(int enemyType) {
		playing.rewardPlayer(enemyType);
	}

}
