package scenes;

import static main.GameStates.CREDITS;
import static main.GameStates.SETTINGS;
import static main.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import helpz.LoadSave;
import main.Game;
import main.GameStates;

public class Credits extends GameScene implements SceneMethods {
	
	private BufferedImage[] images;
	

	public Credits(Game game) {
		super(game);
		images = new BufferedImage[2];
		imgs();
	}

	public BufferedImage img() {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("credits.png");

		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;

	}
	
	public BufferedImage kaarin() {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("karin.png");

		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;

	}
	

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

	@Override
	public void render(Graphics g) {
		// fondo
		g.setColor(new Color(71, 45, 60));
		g.fillRect(0, 0, 640, 800);

		//letras 
		g.setFont(new Font("LucidaSans", Font.BOLD, 20));
		
		
		// borde-imagen
		g.setColor(new Color(207, 198, 184));
		g.fillRoundRect(10, 10, 620, 378, 5, 5);

		// imagen
		g.drawImage(img(), 20, 20, 600, 358, null, game);
		
		
		
		
		
		g.drawImage(title(), 245, 400, 140, 40, null, game);
		
		g.drawString("Credits", 280, 470);
		
		g.fillRoundRect(10, 490, 620, 300, 5, 5);
		
		g.setColor(new Color(71, 45, 60));
		
		g.setFont(new Font("LucidaSans", Font.BOLD, 16));
		
		g.drawString("Made by Alexander, expert in insurance for cyclists <3" , 30, 520);
		g.drawString("Especially thanks to : " , 30, 540);
		
		g.drawString("Kaarin Gaming who made a 10/10 Tower Defense tutorial.", 65, 582);
		g.drawImage(kaarin(), 30, 560, 32, 32, null, game);
		
		g.drawString("Pablo Escobeitor for the encouragement you gave me when i was giving ", 65, 610);
		g.drawString("up on studies last year.",65,625);
		
		g.drawString("Jose Filetes, for helping me out with the secret tricks of gimp and more.", 65, 660);
		
		
		g.drawString("Adrian the voice of the people, for giving me really good tips about", 65, 690);
		g.drawString("the colors.",65,705);
		
		g.drawString("UwU", 290, 750);
		
		g.drawImage(images[0], 450, 745, 32, 32, null, game);
		g.drawImage(images[1], 400, 740, 32, 32, null, game);
		
		
		
		
		
		
	}
	
	public void imgs() {
		BufferedImage atlas = LoadSave.getSpriteAtlas();

		// cafe
		images[0] = atlas.getSubimage(32 * 14, 32 * 22, 32, 32);

		// cadaver
		images[1] = atlas.getSubimage(32 * 7, 32 * 22, 32, 32);
		}

	@Override
	public void mouseClicked(int x, int y) {
		System.out.println("hola");
	}

	@Override
	public void mouseMoved(int x, int y) {
		// TODO Auto-generated method stub
		System.out.println("hola");
	}

	@Override
	public void mousePressed(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub

	}

	public void keyPressed(KeyEvent e) {
		
			
			GameStates.SetGameState(SETTINGS);
		
		
	}
		
		
	}
	
	
	


