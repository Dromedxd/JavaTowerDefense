package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import main.GameStates;

public class MyButton {

	public int x, y, width, height, id;
	public Color background;
	private String text;
	private Rectangle bounds;
	private boolean mouseOver, mousePressed;

	// For normal Buttons
	public MyButton(String text, int x, int y, int width, int height) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = -1;

		initBounds();
	}

	// For tile buttons
	public MyButton(String text, int x, int y, int width, int height, int id) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;

		initBounds();
	}
	// botones del menu
	public MyButton(String text, int x, int y, int width, int height, Color background) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = -1;
		this.background=background;

		initBounds();
	}

	private void initBounds() {
		this.bounds = new Rectangle(x, y, width, height);
	}

	public void draw(Graphics g) {
		// Body
		drawBody(g);

		// Text
		drawText(g);
		
		// Border
		drawBorder(g);

		
	}

	private void drawBorder(Graphics g) {

		g.setColor(Color.black);
		g.drawRect(x, y, width, height);
		if (mousePressed) {
			g.drawRect(x + 1, y + 1, width - 2, height - 2);
			g.drawRect(x + 2, y + 2, width - 4, height - 4);
		}

	}

	private void drawBody(Graphics g) {
		if (mouseOver)
			g.setColor(new Color(207, 198, 184));
		else
			g.setColor(new Color(71, 45, 60));
		g.fillRect(x, y, width, height);

	}

	private void drawText(Graphics g) {
		
		
		
		int estilo = Font.BOLD;
		String nEstilo = g.getFont()+"";
		int size = 12;
		Font pordfecto = new Font(nEstilo, estilo, size);
		
		int w = g.getFontMetrics().stringWidth(text);
		int h = g.getFontMetrics().getHeight();
		
		if(mouseOver) {
		//igual que hicimos en clase
		
		 
		 Font nuevo = new Font(nEstilo, estilo, size);
		 
		 g.setColor(new Color(71, 45, 60));
		 g.setFont(nuevo);
		 g.drawString(text, x - w / 2 + width / 2, y + h / 2 + height / 2);
		

		}
		else {
			g.setColor(new Color(207, 198, 184));
			g.setFont(pordfecto);
			g.drawString(text, x - w / 2 + width / 2, y + h / 2 + height / 2);
			
		}
			

	}
	
	

	public void resetBooleans() {
		this.mouseOver = false;
		this.mousePressed = false;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public int getId() {
		return id;
	}

}
