package scenes;

import static main.GameStates.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import helpz.LoadSave;
import main.Game;
import ui.MyButton;

public class Menu extends GameScene implements SceneMethods {

	private Random random;
	private BufferedImage[] images;

	private MyButton bPlaying, bEditing, bSettings, bQuit;

	private int timer = 60 * 5;
	private int timerTick = 0;
	private int proyectileX = 120;

	public Menu(Game game) {
		super(game);
		random = new Random();
		images = new BufferedImage[6];

		initButtons();
		imgs();

	}

	private void initButtons() {
		int w = 150;
		int h = w / 3;
		int x = 640 / 2 - w / 2;
		int y = 175;
		int yOffset = 70;

		bPlaying = new MyButton("Play", x, y + yOffset, w, h);
		bEditing = new MyButton("Create map", x, y + yOffset * 2, w, h);
		bSettings = new MyButton("Game info", x, y + yOffset * 3, w, h);
		bQuit = new MyButton("Quit", x, y + yOffset * 4, w, h);
	}

	@Override
	public void render(Graphics g) {
		// Content
		g.setColor(new Color(71, 45, 60));
		g.fillRect(0, 0, 640, 800);
		// Fill
		g.setColor(new Color(207, 198, 184));
		g.fillRect(40, 30, 560, 560);
		g.drawImage(title(), 155, 100, 335, 86, null, game);
		g.drawImage(images[3], 50, 660, 96, 96, null, game);
		// size = new Dimension(640, 800);
		drawButtons(g);

		// Alexio el chapuzas

		// dibujamos

		timerTick++;

		if (timer < timerTick) {
			
			//dibujamos el bichito vivo y disparamos
			g.drawImage(images[0], 500, 660, 96, 96, null, game);
			drawShot(g);

		}

		if (proyectileX == 120)
			
			//Dibujamos el proytectil
			g.drawImage(images[1], 500, 660, 96, 96, null, game);

	}

	private void drawShot(Graphics g) {

		proyectileX++;
		
		//si el proyectil llega a la posicion de la x 440 se reiniciara todo el proceso
		if (proyectileX == 440) {
			proyectileX = 120;
			timerTick = 0;

		} else
			//dibujamos el proyectil
			g.drawImage(images[2], proyectileX, 660, 96, 96, null, game);
	}

	private void drawButtons(Graphics g) {

		bPlaying.draw(g);
		bEditing.draw(g);
		bSettings.draw(g);
		bQuit.draw(g);
	}

	private int getRndInt() {
		return random.nextInt(1056);
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (bPlaying.getBounds().contains(x, y)) {
			SetGameState(PLAYING);

		} else if (bEditing.getBounds().contains(x, y)) {
			SetGameState(EDITING);
		} else if (bSettings.getBounds().contains(x, y)) {
			SetGameState(SETTINGS);
		} else if (bQuit.getBounds().contains(x, y)) {
			System.exit(0);
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		bPlaying.setMouseOver(false);
		bEditing.setMouseOver(false);
		bSettings.setMouseOver(false);
		bQuit.setMouseOver(false);

		if (bPlaying.getBounds().contains(x, y)) {
			bPlaying.setMouseOver(true);
		} else if (bEditing.getBounds().contains(x, y)) {
			bEditing.setMouseOver(true);
		} else if (bSettings.getBounds().contains(x, y)) {
			bSettings.setMouseOver(true);
		} else if (bQuit.getBounds().contains(x, y)) {
			bQuit.setMouseOver(true);
		}

	}

	@Override
	public void mousePressed(int x, int y) {

		if (bPlaying.getBounds().contains(x, y)) {
			bPlaying.setMousePressed(true);
		} else if (bSettings.getBounds().contains(x, y)) {
			bSettings.setMousePressed(true);
		} else if (bQuit.getBounds().contains(x, y)) {
			bQuit.setMousePressed(true);
		}

	}

	@Override
	public void mouseReleased(int x, int y) {
		resetButtons();

	}

	public static boolean isMenu() {

		return true;
	}

	private void resetButtons() {
		bPlaying.resetBooleans();
		bSettings.resetBooleans();
		bQuit.resetBooleans();
	}
//  el antiguo menu
//	public void render(Graphics g) {
//		for (int y = 0; y < 24; y++) {
//
//			for (int x = 0; x <= 48; x++) {
//
//				g.drawImage(sprites.get(getRndInt()), x * 32, y * 32, null);
//			}
//		}
//		
//	}

	public BufferedImage title() {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("title.png");

		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;

	}

	public void imgs() {
		BufferedImage atlas = LoadSave.getSpriteAtlas();

		// Azul
		images[0] = atlas.getSubimage(32 * 2, 32 * 22, 32, 32);

		// Azul-cadaver
		images[1] = atlas.getSubimage(32 * 7, 32 * 22, 32, 32);

		// Disparo
		images[2] = atlas.getSubimage(32 * 15, 32 * 22, 32, 32);

		// Mago
		images[3] = atlas.getSubimage(24 * 32, 1 * 32, 32, 32);

		// campesino
		images[4] = atlas.getSubimage(25 * 32, 1 * 32, 32, 32);

		// caballero
		images[5] = atlas.getSubimage(28 * 32, 0 * 32, 32, 32);

	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub

	}

}
