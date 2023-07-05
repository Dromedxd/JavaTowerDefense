package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static main.GameStates.*;

import main.Game;
import main.GameStates;
import scenes.Credits;
import scenes.GameScene;

public class KeyboardListener implements KeyListener {
	private Game game;

	public KeyboardListener(Game game) {
		this.game = game;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (GameStates.gameState == EDITING)
			game.getEditor().keyPressed(e);
		else if (GameStates.gameState == PLAYING)
			game.getPlaying().keyPressed(e);
		else if (GameStates.gameState == CREDITS)
				game.getCredits().keyPressed(e);
			
			
			
			
		

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
