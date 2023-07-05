package ui;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;

import helpz.Constants.Towers;
import objects.Tower;
import scenes.Playing;

public class ActionBar extends Bar {
	private MyButton bMenu;
	private Playing playing;
	private MyButton[] towerButtons;
	private Tower selectedTower;
	private Tower displayedTower;
	
	private DecimalFormat formatter;
	
	//oro inicial
	private int gold = 180;

	private boolean showTowerCost;
	private int towerCostType;
	
	private int lives = 10;

	public ActionBar(int x, int y, int width, int height, Playing playing) {
		super(x, y, width, height);
		this.playing = playing;
		formatter = new DecimalFormat("0.0");

		initButtons();
	}

	private void initButtons() {

		bMenu = new MyButton("Menu", 2, 648, 100, 30);

		towerButtons = new MyButton[3];
		int w = 50;
		int h = 50;
		int xStart = 110;
		int yStart = 650;
		int xOffset = (int) (w * 1.1f);

		for (int i = 0; i < towerButtons.length; i++) {
			towerButtons[i] = new MyButton("", xStart + xOffset * i, yStart, w, h, i);

		}

	}
	
	public void removeOneLife() {
		lives--;
		if(lives <=0)
			System.out.println("perdiste");
	}

	private void drawWaveInfo(Graphics g) {
		g.setFont(new Font("LucidaSans", Font.BOLD, 20));
		drawWaveTimerInfo(g);
		drawEnemiesLeftInfo(g);
		drawWavesLeftInfo(g);

	}
	
	
	
	private void drawButtons(Graphics g) {
		bMenu.draw(g);

		for (MyButton b : towerButtons) {
			// g.fillRect(b.x, b.y, b.width, b.height); // sirve para rellenar el fondo
			// transparente
			g.drawImage(playing.getTowerManager().getTowerImgs()[b.getId()], b.x, b.y, b.width, b.height, null);

			drawButtonFeedback(g, b);
		}

	}
	
	private void drawEnemiesLeftInfo(Graphics g) {
		int remaining = playing.getEnemyManager().getAmountOfAliveEnemies();
		g.setColor(new Color(207, 198, 184));
		g.drawString("Enemies Left: " + remaining, 425, 790);
	}
	
	private void drawWaveTimerInfo(Graphics g) {
		if (playing.getWaveManager().isWaveTimerStarted()) {

			g.setColor(new Color(207, 198, 184));
			float timeLeft = playing.getWaveManager().getTimeLeft();
			String formattedText = formatter.format(timeLeft);
			g.drawString("Time Left: " + formattedText, 425, 750);
		}
	}
	
	private void drawWavesLeftInfo(Graphics g) {
		int current = playing.getWaveManager().getWaveIndex();
		int size = playing.getWaveManager().getWaves().size();
		g.setColor(new Color(207, 198, 184));
		g.drawString("Wave " + (current + 1) + " / " + size, 425, 770);

	}
	
	public void draw(Graphics g) {

		// fondo
		g.setColor(new Color(71, 45, 60));
		g.fillRect(x, y, width, height);
		g.setColor(new Color(207, 198, 184));
		g.fillRect(x, y, width, 5);

		// botones
		drawButtons(g);

		// dibujamos la torre seleciconada
		drawDisplayedTower(g);
		
		drawWaveInfo(g);

	
	
		drawGoldAmount(g);
		
		if (showTowerCost)
			drawTowerCost(g);
		
		
		g.setColor(new Color(207, 198, 184));
		g.drawString("Lives: " + lives,110,750);
	}
	
	

	private void drawTowerCost(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(280, 650, 120, 50);
		g.setColor(Color.black);
		g.drawRect(280, 650, 120, 50);

		g.drawString("" + getTowerCostName(), 285, 670);
		g.drawString("Cost: " + getTowerCostCost() + "g", 285, 695);

		// Show this if player lacks gold for the selected tower.
		if (isTowerCostMoreThanCurrentGold()) {
			g.setColor(Color.RED);
			g.drawString("Can't Afford", 270, 725);

		}

	}
	
	
	private boolean isTowerCostMoreThanCurrentGold() {
		return getTowerCostCost() > gold;
	}

	private String getTowerCostName() {
		return helpz.Constants.Towers.GetName(towerCostType);
	}

	private int getTowerCostCost() {
		return helpz.Constants.Towers.GetTowerCost(towerCostType);
	}

		
	
	
	
	private void drawDisplayedTower(Graphics g) {
		if (displayedTower != null) {
			g.setColor(Color.gray);
			g.fillRect(410, 650, 220, 85);
			g.setColor(Color.black);
			g.drawRect(410, 650, 220, 85);
			g.drawRect(420, 655, 50, 50);
			// aqui podemos cambiar cuando selecionamos la torre,

			// Creamos una rectangulo/cuadrado antes de la torre para cambiar el fondo, ya
			// que las torres son .png
			g.setColor(new Color(71, 45, 60));
			g.fillRect(420, 655, 50, 50);
			g.drawImage(playing.getTowerManager().getTowerImgs()[displayedTower.getTowerType()], 420, 655, 50, 50,
					null);
			g.setFont(new Font("LucidaSans", Font.BOLD, 15));
			// aqui podemos cambiar cuando selecionamos la torre,
			g.drawString("" + Towers.GetName(displayedTower.getTowerType()), 490, 665);
			g.drawString("ID: " + displayedTower.getId(), 490, 680);

			drawDisplayedTowerBorder(g);
			drawDisplayedRange(g);
		}
	}

	private void drawGoldAmount(Graphics g) {
		g.drawString("Gold: " + gold + "g", 110, 725);

	}
	
	private void drawDisplayedRange(Graphics g) {
		g.setColor(new Color(207, 198, 184));
		g.drawOval(displayedTower.getX() + 16 - ((int) displayedTower.getRange()*2) / 2,
				displayedTower.getY() + 16 - ((int) displayedTower.getRange()*2) / 2, 
				(int) displayedTower.getRange()*2,(int) displayedTower.getRange()*2);
	}

	private void drawDisplayedTowerBorder(Graphics g) {

		g.setColor(Color.GRAY);
		g.drawRect(displayedTower.getX(), displayedTower.getY(), 32, 32);
	}

	public void displayTower(Tower t) {
		displayedTower = t;

	}

	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			SetGameState(MENU);
		else {
			for (MyButton b : towerButtons) {
				if (b.getBounds().contains(x, y)) {
					if (!isGoldEnoughForTower(b.getId()))
						return;
					
					selectedTower = new Tower(0, 0, -1, b.getId());
					playing.setSelectedTower(selectedTower);
					return;
				}
			}
		}

	}

	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		showTowerCost = false;
		for (MyButton b : towerButtons)
			b.setMouseOver(false);

		if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseOver(true);
		else {
			for (MyButton b : towerButtons)
				if (b.getBounds().contains(x, y)) {
					b.setMouseOver(true);
					showTowerCost = true;
					towerCostType = b.getId();
					return;
				}
		}
	}

	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMousePressed(true);
		else
			for (MyButton b : towerButtons)
				if (b.getBounds().contains(x, y)) {
					b.setMousePressed(true);
					return;
				}

	}

	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
		for (MyButton b : towerButtons)
			b.resetBooleans();

	}
	
	private boolean isGoldEnoughForTower(int towerType) {

		return gold >= helpz.Constants.Towers.GetTowerCost(towerType);
	}
	
	public void payForTower(int towerType) {
		this.gold -= helpz.Constants.Towers.GetTowerCost(towerType);

	}
	
	public void addGold(int getReward) {
		this.gold += getReward;

	}
	
	
	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getLives() {
		return lives;
	}

}
