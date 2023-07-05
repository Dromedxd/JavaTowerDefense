package main;

import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import managers.TileManager;

import helpz.LoadSave;
import inputs.KeyboardListener;
import inputs.MyMouseListener;
import scenes.Credits;
import scenes.Editing;
import scenes.GameOver;
import scenes.Menu;
import scenes.Playing;
import scenes.Settings;

public class Game extends JFrame implements Runnable {

	private GameScreen gameScreen;

	private final double FPS_SET = 120.0;
	private final double UPS_SET = 60.0;

	

	private Thread gameThread;

	// Classes
	private Render render;
	private Menu menu;
	private Playing playing;
	private Settings settings;
	private Editing editing;
	private GameOver gameOver;
	private TileManager tileManager;
	private Credits credits;
	

	public Game() {

//		setSize(768, 352); // Recordar que el puñetero windows añade 16 de ancho y 39 de altura, asi que
//							// hacemos la movida de abajo para q no haya problema al hacer los bichitos y
//							// los mapas
//
//		setSize(getWidth() + 16, getHeight() + 39);

		
		setAlwaysOnTop(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		setResizable(false); // JOSE TIENE FILETES
		
		initClasses();
		createDefaultLevel();

		
		add(gameScreen);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		
		
	}
	
	private void createDefaultLevel() {
		int[] arr = new int[1000];
		for(int i = 0; i < arr.length; i++) {
			arr[i]=0;
		}
		LoadSave.CreateLevel("new_level", arr);
	}
	
	private void initClasses() {//tener cuidado con el orden en que se inician las clases
		tileManager = new TileManager();
		render = new Render(this);
		gameScreen = new GameScreen(this);
		menu = new Menu(this);
		playing = new Playing(this);
		settings = new Settings(this);
		editing = new Editing(this);
		gameOver = new GameOver(this);
		credits = new Credits(this);
}



	private void start() {
		gameThread = new Thread(this) {
		};

		gameThread.start();
	}
	
	private void updateGame() {
		switch(GameStates.gameState) {
		case EDITING:
			break;
		case MENU:
			break;
		case PLAYING:
			playing.update();
			break;
		case SETTINGS:
			break;
		default:
			break;
		
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Game game = new Game();
		game.gameScreen.initInputs();
		game.start();

	}

	@Override
	public void run() { // para el hilo (Thread)
		double timePerFrame = 1000000000.0 / FPS_SET; // para limitar los fps a 60 por segundo esto equivale a un
														// segundo en nanosegundos
		double timePerUpdate = 1000000000.0 / UPS_SET;

		long lastFrame = System.nanoTime();
		long lastUpdate = System.nanoTime();

		long lastTimeCheck = System.currentTimeMillis();

		int frames = 0;
		int updates = 0;

		long now;

		while (true) {

			now = System.nanoTime();
			// Renderizado
			if (now - lastFrame >= timePerFrame) {

				repaint();
				lastFrame = now;
				frames++;
			}

			// actualizacion
			if (now - lastUpdate >= timePerUpdate) {
				
				updateGame();
				lastUpdate = now;
				updates++;
			}

			if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
				System.out.println("FPS " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;
				lastTimeCheck = System.currentTimeMillis();

			}
		}

	}

	public Render getRender() {
		return render;
	}

	public Menu getMenu() {
		return menu;
	}

	public Playing getPlaying() {
		return playing;
	}

	public Settings getSettings() {
		return settings;
	}
	
	public Editing getEditor() {
		return editing;
	}
	public TileManager getTileManager() {
		return tileManager;
	}
	public GameOver getGameOver() {
		return gameOver;
	}
	public Credits getCredits() {
		return credits;
	}

}
