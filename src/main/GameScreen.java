package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JPanel;

import inputs.KeyboardListener;
import inputs.MyMouseListener;

public class GameScreen extends JPanel {

	private Game game;

	private Dimension size;

	private long lastTime;// para controlar las movidas de los fps, esta servira para comprar con el
							// tiempo del sistema
	private int frames;

	private MyMouseListener myMouseListener;
	private KeyboardListener keyboardListener;
	

	public GameScreen(Game game) {
		this.game = game;
		

		SetPanelSize();

	}
	
	public void initInputs() {
		myMouseListener = new MyMouseListener(game);
		keyboardListener = new KeyboardListener(game);

		addMouseListener(myMouseListener);
		addMouseMotionListener(myMouseListener);
		addKeyListener(keyboardListener);

		requestFocus(); // para evitar bugs
		
	}

	private void SetPanelSize() {
		size = new Dimension(640, 800);
		//size = new Dimension(1568, 704);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.getRender().render(g);

	}

}
