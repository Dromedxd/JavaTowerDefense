package scenes;

import static main.GameStates.MENU;
import static main.GameStates.CREDITS;

import static main.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import helpz.LoadSave;
import main.Game;
import main.GameStates;
import ui.MyButton;

public class Settings extends GameScene implements SceneMethods {
	private BufferedImage[] images;
	private MyButton bMenu, bCredits;

	public Settings(Game game) {
		super(game);
		images = new BufferedImage[10];

		initButtons();
		renderImgs();
	}

	private void initButtons() {// 85, 70

		int w = 90;
		int h = w / 3;

		bMenu = new MyButton("Menu ", 450, 50, w, h);
		bCredits = new MyButton("Credits ", 275, 700, w, h);

	}

	public void render(Graphics g) {

		// Tamaño de la ventana(640, 800);

		// fondo/borde
		
		g.setColor(new Color(71, 45, 60));
		g.fillRect(0, 0, 640, 800);
		// div-contenido
		g.setColor(new Color(207, 198, 184));
		g.fillRoundRect(40, 35, 560, 730, 10, 10);
		drawButtons(g);

		// Primer cuadrado
		g.setColor(new Color(71, 45, 60));
		g.setFont(null);
		drawRectangle(80, 90, 480, 240, g);
		// DEFENSORES TEXTO
		g.setFont(new Font("LucidaSans", Font.BOLD, 20));
		g.drawString("Defenders", 85, 70);

		// segundo cuadrado
		g.drawString("Enemies", 85, 380);

		drawRectangle(80, 400, 480, 230, g);

		drawEnemyStuff(g);

		drawDefendersStuff(g);

	}

	public void drawRectangle(int x, int y, int width, int height, Graphics g) {

		g.setColor(new Color(71, 45, 60));

		g.fillRoundRect(x, y, width, height, 5, 5);

	}

	private void drawButtons(Graphics g) {

		bMenu.draw(g);
		bCredits.draw(g);

	}

	public void renderImgs() {
		BufferedImage atlas = LoadSave.getSpriteAtlas();

		// Azul
		images[0] = atlas.getSubimage(32 * 2, 32 * 22, 32, 32);

		// Verde
		images[1] = atlas.getSubimage(32 * 8, 32 * 23, 32, 32);

		// Amarilllo
		images[2] = atlas.getSubimage(32 * 8, 32 * 22, 32, 32);

		// Mago
		images[3] = atlas.getSubimage(24 * 32, 1 * 32, 32, 32);

		// campesino
		images[4] = atlas.getSubimage(25 * 32, 1 * 32, 32, 32);

		// caballero
		images[5] = atlas.getSubimage(28 * 32, 0 * 32, 32, 32);

		// speed
		images[6] = atlas.getSubimage(32 * 7, 32 * 23, 32, 32);

		// corazon
		images[7] = atlas.getSubimage(6 * 32, 32 * 23, 32, 32);

		// bow
		images[8] = atlas.getSubimage(5 * 32, 32 * 23, 32, 32);

		// sword

		images[9] = atlas.getSubimage(4 * 32, 32 * 23, 32, 32);
	}

	public void drawEnemyStuff(Graphics g) {

		int splitX = 180;
		int splitXStats = 24;

		g.setColor(new Color(207, 198, 184));
		g.setFont(new Font("LucidaSans", Font.BOLD, 18));

		// Blue

		g.drawString("Bluewy", 110, 430);
		g.drawImage(images[0], 100, 440, 96, 96, null);

		// speed
		g.drawImage(images[6], 110, 545, 21, 21, null);
		g.drawImage(images[6], 110 + splitXStats, 545, 21, 21, null);
		g.drawImage(images[6], 110 + splitXStats * 2, 545, 21, 21, null);

		// hp
		g.drawImage(images[7], 110, 586, 24, 24, null);

		// Green
		g.drawString("Greenly", 105 + splitX, 430);
		g.drawImage(images[1], 100 + splitX, 440, 96, 96, null);

		// sp
		g.drawImage(images[6], 110 + splitX, 545, 21, 21, null);
		g.drawImage(images[6], 110 + splitX + splitXStats, 545, 21, 21, null);
		// hp
		g.drawImage(images[7], 110 + splitX, 586, 24, 24, null);
		g.drawImage(images[7], 110 + splitX + splitXStats, 586, 24, 24, null);

		// Yellow
		g.drawString("Yellowy", 105 + splitX * 2, 430);
		g.drawImage(images[2], 100 + splitX * 2, 440, 96, 96, null);

		// speed
		g.drawImage(images[6], 110 + splitX * 2, 545, 21, 21, null);

		// hp
		g.drawImage(images[7], 110 + splitX * 2, 586, 24, 24, null);
		g.drawImage(images[7], 110 + splitX * 2 + splitXStats, 586, 24, 24, null);
		g.drawImage(images[7], 110 + splitX * 2 + splitXStats * 2, 586, 24, 24, null);

	}

	public void drawDefendersStuff(Graphics g) {
		int splitX = 180;
		int splitXStats = 24;

		g.setColor(new Color(207, 198, 184));
		g.setFont(new Font("LucidaSans", Font.BOLD, 18));

		// Farmer

		g.drawString("Farmer", 115, 120);
		g.drawImage(images[4], 100, 130, 96, 96, null);

		// Range
		g.drawImage(images[8], 110, 238, 21, 21, null);
		g.drawImage(images[8], 110 + splitXStats, 238, 21, 21, null);
		g.drawImage(images[8], 110 + splitXStats * 2, 238, 21, 21, null);

		// Dmg
		g.drawImage(images[9], 110, 279, 24, 24, null);

		// Mage
		g.drawString("Mage", 125 + splitX, 120);
		g.drawImage(images[3], 100 + splitX, 130, 96, 96, null);

		// range
		g.drawImage(images[8], 120 + splitX, 238, 21, 21, null);
		g.drawImage(images[8], 120 + splitX + splitXStats, 238, 21, 21, null);
		// dmg
		g.drawImage(images[9], 120 + splitX, 279, 24, 24, null);
		g.drawImage(images[9], 120 + splitX + splitXStats, 279, 24, 24, null);

		// Knight
		g.drawString("Knight", 105 + splitX * 2, 120);
		g.drawImage(images[5], 90 + splitX * 2, 130, 96, 96, null);

		// range
		g.drawImage(images[8], 100 + splitX * 2, 238, 21, 21, null);

		// dmg
		g.drawImage(images[9], 100 + splitX * 2, 279, 24, 24, null);
		g.drawImage(images[9], 100 + splitX * 2 + splitXStats, 279, 24, 24, null);
		g.drawImage(images[9], 100 + splitX * 2 + splitXStats * 2, 279, 24, 24, null);

	}

	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y)) {
			SetGameState(MENU);

		} else if (bCredits.getBounds().contains(x, y)) {
			SetGameState(CREDITS);

		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		bCredits.setMouseOver(false);

		if (bMenu.getBounds().contains(x, y)) {
			bMenu.setMouseOver(true);
		}else if (bCredits.getBounds().contains(x, y)) {
			bCredits.setMouseOver(true);}
		

	}

	@Override
	public void mousePressed(int x, int y) {

		if (bMenu.getBounds().contains(x, y)) {
			bMenu.setMousePressed(true);
		} else if (bCredits.getBounds().contains(x, y)) {
			bCredits.setMousePressed(true);
		}

	}

	@Override
	public void mouseReleased(int x, int y) {

		resetButtons();
	}

	private void resetButtons() {
		bMenu.resetBooleans();
		bCredits.resetBooleans();


	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub

	}

}
