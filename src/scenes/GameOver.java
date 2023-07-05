package scenes;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import main.Game;
import ui.MyButton;

public class GameOver extends GameScene implements SceneMethods {

	private MyButton bReplay, bMenu;

	
	
	public GameOver(Game game) {
		super(game);
		initButtons();
	}

	private void initButtons() {
		int w = 150;
		int h = w / 3;
		int x = 640 / 2 - w / 2;
		int y = 175;
		int yOffset = 70;

		bReplay = new MyButton("Replay", x, y, w, h);
		bMenu = new MyButton("Menu", x, y + yOffset * 2, w, h);
		
	}
	
	
	
	@Override
	public void render(Graphics g) {
		// game over

		// Botones
		bMenu.draw(g);
		bReplay.draw(g);
		// exit

		// replay

		// menu

	}

	@Override
	public void mouseClicked(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		bReplay.setMouseOver(false);
		
		if(bMenu.getBounds().contains(x,y))
			bMenu.setMouseOver(true);
		else if (bReplay.getBounds().contains(x,y))
			bReplay.setMouseOver(true);

	}

	@Override
	public void mousePressed(int x, int y) {
		
		if(bMenu.getBounds().contains(x,y))
			bMenu.setMousePressed(true);
		else if (bReplay.getBounds().contains(x,y))
			bReplay.setMousePressed(true);

	}

	@Override
	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
		bReplay.resetBooleans();
	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub

	}
	

}
