package javagame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.Log;

public class Camera {
	
	// size of tiles and sprite
	private static final int SIZE = 32;
	
	// display width in tiles
	private int widthInTiles;
	// display height in tiles
	private int heightInTiles;
	
	// offset from top in tiles
	private int topOffsetInTiles;
	// offset from left in tiles
	private int leftOffsetInTiles;
	
	// tiled map
	private TiledMap map;
	
	// map width in tiles
	private int mapWidth;
	// map height in tiles
	private int mapHeight;
	
	// game container
	private GameContainer gc;
	
	// log
	private Log log;
	
	// tile to start rendering at
	int tileX;
	// tile to start rendering at
	int tileY;
	
	public Camera(GameContainer gc, TiledMap map) {
		this.map = map;
		
		this.gc = gc;
		
		widthInTiles = gc.getWidth() / SIZE;
		heightInTiles = gc.getHeight() / SIZE;
		
		topOffsetInTiles = heightInTiles / 2;
		leftOffsetInTiles = widthInTiles / 2;
		
		mapWidth = map.getWidth();
		mapHeight = map.getHeight();
		
		log.info("topOffsetInTiles = " + topOffsetInTiles + " leftOffsetInTiles = " + leftOffsetInTiles);
	}
	
	public void drawMap(float playerX, float playerY) {
		int playerTileX = (int)playerX;
		int playerTileY = (int)playerY;
		
		int playerTileOffsetX = (int) ((playerTileX - playerX) * SIZE);
		int playerTileOffsetY = (int) ((playerTileY - playerY) * SIZE);
		
		tileX = playerTileX - leftOffsetInTiles;
		tileY = playerTileY - topOffsetInTiles;
		int oppositeTileX = 0;
		int oppositeTileY = 0;
		
		if(tileX < 0) {
			oppositeTileX = -tileX;
			playerTileOffsetX = 0;
		}
		if(tileX + widthInTiles + 1 > mapWidth) {
			oppositeTileX = -tileX + mapWidth - widthInTiles;
			playerTileOffsetX = 0;
		}
		if(tileY < 0) {
			oppositeTileY = -tileY;
			playerTileOffsetY = 0;
		}
		if(tileY + heightInTiles + 1 > mapHeight) {
			oppositeTileY = -tileY + mapHeight - heightInTiles;
			playerTileOffsetY = 0;
		}
		
		map.render(playerTileOffsetX, playerTileOffsetY, tileX + oppositeTileX, tileY + oppositeTileY, widthInTiles + 1, heightInTiles + 1);
	}
	
	public void translateGraphics(float playerX, float playerY) {
		if(tileX >= 0 && tileX < mapWidth - widthInTiles) {
			gc.getGraphics().translate((gc.getWidth() / 2) - (int) (playerX * SIZE), 0);
		}
		if(tileX >= mapWidth - widthInTiles) {
			gc.getGraphics().translate((widthInTiles * 32) - (mapWidth * 32), 0);
		}
		if(tileY >= 0 && tileY < mapHeight - heightInTiles) {
			gc.getGraphics().translate(0, (gc.getHeight() / 2) - (int) (playerY * SIZE));
		}
		if(tileY >= mapHeight - heightInTiles) {
			gc.getGraphics().translate(0, (heightInTiles * 32) - (mapHeight * 32));
		}
		//gc.getGraphics().translate((gc.getWidth() / 2) - (int) (playerX * SIZE), (gc.getHeight() / 2) - (int) (playerY * SIZE));
	}
	
	public void untranslateGraphics() {
		gc.getGraphics().resetTransform();
	}
	
}
