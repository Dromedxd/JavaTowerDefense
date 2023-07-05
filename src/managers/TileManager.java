package managers;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import helpz.ImgFix;
import helpz.LoadSave;
import objects.Tile;

import static helpz.Constants.Tiles.*;

public class TileManager {

	public Tile RELLENO1, AGUA, CAMINOH, CAMINOV, GIRO1, GIRO2, GIRO3, GIRO4, water1, water2, water3, water4, mar1,
			mar2, mar3, mar4;
	public BufferedImage atlas;
	public ArrayList<Tile> tiles = new ArrayList<>();

	public ArrayList<Tile> roadS = new ArrayList<>();
	public ArrayList<Tile> roadC = new ArrayList<>();
	public ArrayList<Tile> water = new ArrayList<>();

	public ArrayList<Tile> mar = new ArrayList<>();

	public TileManager() {

		loadAtalas();
		createTiles();
	}

	private void createTiles() {
		int id = 0;

		tiles.add(RELLENO1 = new Tile(getSprite(2, 0), id++, FILL_TILE)); // id 0
		tiles.add(AGUA = new Tile(getSprite(8, 5), id++, WATER_TILE));// id 4


		roadS.add(CAMINOV = new Tile(getSprite(8, 0), id++, ROAD_TILE));// id 5
		roadS.add(CAMINOH = new Tile(ImgFix.getRotImg(getSprite(8, 0), 90), id++, ROAD_TILE));


		roadC.add(GIRO1 = new Tile(getSprite(9, 0), id++, ROAD_TILE)); // id 7
		roadC.add(GIRO2 = new Tile(ImgFix.getRotImg(getSprite(9, 0), 90), id++, ROAD_TILE));
		roadC.add(GIRO3 = new Tile(ImgFix.getRotImg(getSprite(9, 0), 180), id++, ROAD_TILE));
		roadC.add(GIRO4 = new Tile(ImgFix.getRotImg(getSprite(9, 0), 270), id++, ROAD_TILE));

		water.add(water1 = new Tile(getSprite(10, 5), id++, WATER_TILE));// id 5
		water.add(water2 = new Tile(ImgFix.getRotImg(getSprite(10, 5), 90), id++, WATER_TILE));
		water.add(water3 = new Tile(ImgFix.getRotImg(getSprite(10, 5), 180), id++, WATER_TILE));
		water.add(water4 = new Tile(ImgFix.getRotImg(getSprite(10, 5), 270), id++, WATER_TILE));

		mar.add(mar1 = new Tile(getSprite(9, 5), id++, WATER_TILE));// id 5
		mar.add(mar2 = new Tile(ImgFix.getRotImg(getSprite(9, 5), 90), id++, WATER_TILE));
		mar.add(mar3 = new Tile(ImgFix.getRotImg(getSprite(9, 5), 180), id++, WATER_TILE));
		mar.add(mar4 = new Tile(ImgFix.getRotImg(getSprite(9, 5), 270), id++, WATER_TILE));

		tiles.addAll(roadS);
		tiles.addAll(roadC);
		tiles.addAll(water);
		tiles.addAll(mar);

	}

	public ArrayList<Tile> getMar() {
		return mar;
	}

	private void loadAtalas() {
		atlas = LoadSave.getSpriteAtlas();

	}

	public BufferedImage getSprite(int id) {

		return tiles.get(id).getSprite();
	}

	public Tile getTile(int id) {
		return tiles.get(id);
	}

	private BufferedImage getSprite(int xCord, int yCord) { // cordenadas

		return atlas.getSubimage(xCord * 32, yCord * 32, 32, 32);
	}

	public ArrayList<Tile> getRoadS() {
		return roadS;
	}

	public ArrayList<Tile> getRoadC() {
		return roadC;
	}

	public ArrayList<Tile> getWater() {
		return water;
	}

}
