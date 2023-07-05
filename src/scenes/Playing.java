package scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enemies.Enemy;
import helpz.LoadSave;
import main.Game;
import managers.EnemyManager;
import managers.ProjectileManager;
import managers.TowerManager;
import managers.WaveManager;
import objects.PathPoint;
import objects.Tower;
import ui.ActionBar;
import ui.Toolbar;
import static helpz.Constants.Tiles.FILL_TILE;


public class Playing extends GameScene implements SceneMethods {

	private int[][] lvl;

	private ActionBar actionBar;
	private int mouseX, mouseY;
	private PathPoint start, end;
	private TowerManager towerManager;
	private ProjectileManager pManager;
	private WaveManager waveManager;
	private EnemyManager enemyManager;

	private Tower selectedTower;
	
	private int goldTick;
	private boolean goldWave=true;
	
	public Playing(Game game) {
		super(game);
		loadDefaultLevel();

		actionBar = new ActionBar(0, 640, 804, 160, this);
		enemyManager = new EnemyManager(this, start, end);
		towerManager = new TowerManager(this);
		pManager = new ProjectileManager(this);
		waveManager = new WaveManager(this);

	}

	private void loadDefaultLevel() {

		lvl = LoadSave.GetLevelData("new_level");
		ArrayList<PathPoint> points = LoadSave.GetLevelPathPoints("new_level");
		start = points.get(0);
		end = points.get(1);

	}

	public void setLevel(int[][] lvl) {
		this.lvl = lvl;
	}

	public void update() {
		
		waveManager.update();
		

		//el tick para el dinero
		goldTick++;
		if (goldTick % (60 * 3) == 0)
			actionBar.addGold(1);
		
		if (isAllEnemiesDead() && goldWave) {
			actionBar.addGold(10);
			goldWave=false;
		}
			
		
		if (isAllEnemiesDead()) {
			if (isThereMoreWaves()) {
				
				waveManager.startWaveTimer();
				if (isWaveTimerOver()) {
					waveManager.increaseWaveIndex();
					enemyManager.getEnemies().clear();
					waveManager.resetEnemyIndex();
					goldWave=true;
					// para añadir 10 de oro al acabar la ronda
					

				}
			}
		}

		if (isTimeForNewEnemy()) {
			spawnEnemy();
		}

		enemyManager.update();
		towerManager.update();
		pManager.update();
	}

	private boolean isThereMoreWaves() {
		return waveManager.isThereMoreWaves();

	}

	private boolean isAllEnemiesDead() {

		if (waveManager.isThereMoreEnemiesInWave())
			return false;

		for (Enemy e : enemyManager.getEnemies())
			if (e.isAlive())
				return false;

		return true;
	}

	private boolean isWaveTimerOver() {

		return waveManager.isWaveTimerOver();
	}
	
	private void spawnEnemy() {

		enemyManager.spawnEnemy(waveManager.getNextEnemy());
	}

	private boolean isTimeForNewEnemy() {
		if (waveManager.isTimeForNewEnemy()) {
			if (waveManager.isThereMoreEnemiesInWave())
				return true;
		}

		return false;
	}

	public void setSelectedTower(Tower selectedTower) {

		this.selectedTower = selectedTower;
	}

	
	@Override
	public void render(Graphics g) {

		drawLevel(g);
		actionBar.draw(g);
		enemyManager.draw(g);
		towerManager.draw(g);
		pManager.draw(g);
		drawSelectedTower(g);
		drawHightLight(g);
		drawWaveInfos(g);

	}

	private void drawHightLight(Graphics g) {
		g.setColor(Color.white);
		g.drawRect(mouseX, mouseY, 32, 32);
	}

	private void drawSelectedTower(Graphics g) {

		if (selectedTower != null)
			g.drawImage(towerManager.getTowerImgs()[selectedTower.getTowerType()], mouseX, mouseY, null);

	}

	private void drawLevel(Graphics g) {
		for (int y = 0; y < lvl.length; y++) {
			for (int x = 0; x < lvl[y].length; x++) {
				int id = lvl[y][x];
				g.drawImage(getSprite(id), x * 32, y * 32, null);
			}
		}
	}
	
private void drawWaveInfos(Graphics g) {
		

	}

	public int getTileType(int x, int y) {
		int xCord = x / 32;
		int yCord = y / 32;

		if (xCord < 0 || xCord > 19)
			return 0;
		if (yCord < 0 || xCord > 19)
			return 0;

		int id = lvl[y / 32][x / 32];
		return game.getTileManager().getTile(id).getTileType();
	}

	private BufferedImage getSprite(int spriteID) {
		return game.getTileManager().getSprite(spriteID);
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (y >= 640) {
			actionBar.mouseClicked(x, y);
		} else {
			if (selectedTower != null) {

				if (isTileFill(mouseX, mouseY)) {
					if (getTowerAt(mouseX, mouseY) == null) {
						towerManager.addTower(selectedTower, mouseX, mouseY);
						
						removeGold(selectedTower.getTowerType());
						selectedTower = null;
						
					}

				}

			} else {
				Tower t = getTowerAt(mouseX, mouseY);
//				if(t==null)
//					return;
//				else
				actionBar.displayTower(t);

			}
		}
	}

	private void removeGold(int towerType) {
		actionBar.payForTower(towerType);

	}
	
	private Tower getTowerAt(int x, int y) {
		return towerManager.getTowerAt(x, y);

	}

	private boolean isTileFill(int x, int y) {
		int id = lvl[y / 32][x / 32];
		int tileType = game.getTileManager().getTile(id).getTileType();

		return tileType == FILL_TILE;

	}

	// Para deseleccionar la torre
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			selectedTower = null;
		}
	}

	public void shootEnemy(Tower t, Enemy e) {
		pManager.newProjectile(t, e);
	}

	@Override
	public void mouseMoved(int x, int y) {

		if (y >= 640) {
			actionBar.mouseMoved(x, y);
		} else {
			mouseX = (x / 32) * 32;
			mouseY = (y / 32) * 32;
		}
	}

	@Override
	public void mousePressed(int x, int y) {
		if (y >= 640) {
			actionBar.mousePressed(x, y);
		}
	}

	@Override
	public void mouseReleased(int x, int y) {

		actionBar.mouseReleased(x, y);

	}

	@Override
	public void mouseDragged(int x, int y) {
		if (y >= 640) {

		}

	}
	
	public void rewardPlayer(int enemyType) {
		actionBar.addGold(helpz.Constants.Enemies.GetReward(enemyType));
	}

	public WaveManager getWaveManager() {
		return waveManager;
	}

	public TowerManager getTowerManager() {
		return towerManager;
	}

	public EnemyManager getEnemyManager() {
		return enemyManager;
	}
	
	public void removeOneLife() {
		actionBar.removeOneLife();
	}

}