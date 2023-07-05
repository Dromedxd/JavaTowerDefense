package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JPanel;

public class Fase1GameScreen extends JPanel{
	
	private Random random;
	private BufferedImage img;
	
	// array con todas las imagenes
	private ArrayList<BufferedImage> sprites = new ArrayList<>();
	
	public Fase1GameScreen(BufferedImage img) {
		this.img=img;
		random = new Random();
		loadSprites();
	}
	
	private void loadSprites() {
		
		for(int y = 0; y < 22; y++ ) {
			for (int x = 0; x < 48; x++) {
				
				
				sprites.add(img.getSubimage( x*16 , y*16, 16, 16));
			}
		}
	}
	
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g); 
		
		for (int y = 0; y < 22; y++) {
		
		
			for (int x = 0; x <= 48; x++) {
				
				
				g.drawImage(sprites.get(getRndInt()), x * 16, y * 16, null);			
			}
			
	}
		
	
		// Sirve para llamar a la "super clase" para hacer todos los calculos que podriamos que tener que 
								 //lidiar, esto hace todas las movidas del background y capas
		
		//g.setColor(Color.blue); Colores por defecto
		
						// g.fillRect(0, 0, 32, 32);  Hemos hecho que la ventana sea 640*640, por lo que 32 * 20 llenariamos la ventana completamente
		
						// g.fillRect(32, 32, 32, 32); Los dos primeros parametros son para indicar las posiciones, lo suyo seria hacerlo con un bucle
		
	
		
//	g.drawImage(img.getSubimage( 20*16 , 16*16, 16, 16).getScaledInstance(300, 300, 0), 0, 0, null); // cogemos una parte de la imagen, y la redimensionamos
	
	
//		for (int y = 0; y < 20; y++) {
//			
//			
//			for (int x = 0; x <= 25; x++) {
//				g.setColor(getRndColor());
//				g.fillRect( x * 32,y* 32, 32, 32);
//			}
//		}
		
		
		
		
		
	}

	private int getRndInt() {
			return random.nextInt(1056);
	}
	
	
	
	private Color getRndColor() {
		int r = random.nextInt(256);
		int g = random.nextInt(256);
		int b = random.nextInt(256);
		
		return new Color(r,g,b);
	}
	
	
	
	
}
