package javagame.entities;

import java.util.Random;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

public class Dragon implements Entity {
	
	// size of tiles and sprite
	private static final int SIZE = 32;
	
	// player x position in tiles
	private float playerX = 5;
	// player y position in tiles
	private float playerY = 5;
	
	// animation of sprite
	private Animation sprite, up, down, left, right;
	
	// collision map of blocked tiles
	private boolean blocked[][];
	
	// is player moving
	boolean moving;
	
	// direction player is moving
	String dir;
	
	Random random = new Random();
	
	public Dragon() {
		
	}
	
	public void loadEntity(TiledMap map) throws SlickException {
		// build collision map with property of tiles
		blocked = new boolean[map.getWidth()][map.getHeight()];
		for(int x = 0; x < map.getWidth(); x++) {
			for(int y = 0; y < map.getHeight(); y++) {
				int tileID = map.getTileId(x, y, 0);
				String value = map.getTileProperty(tileID, "blocked", "false");
				if("true".equals(value)) {
					blocked[x][y] = true;
				}
			}
		}
		
		// load sprite sheet
		SpriteSheet sheet = new SpriteSheet("res/dragon.png", 32, 32);
		
		// add frames to animation
		sprite = new Animation();
		up = new Animation();
		down = new Animation();
		left = new Animation();
		right = new Animation();
		for(int xFrame = 0; xFrame < 3; xFrame++) {
			for(int yFrame = 0; yFrame < 4; yFrame++) {
				switch(yFrame) {
					case 0:
						down.addFrame(sheet.getSprite(xFrame, yFrame), 150);
						break;
					case 1:
						left.addFrame(sheet.getSprite(xFrame, yFrame), 150);
						break;
					case 2:
						right.addFrame(sheet.getSprite(xFrame, yFrame), 150);
						break;
					case 3:
						up.addFrame(sheet.getSprite(xFrame, yFrame), 150);
						break;
				}
			}
		}
		sprite.setAutoUpdate(false);
		up.setAutoUpdate(false);
		down.setAutoUpdate(false);
		left.setAutoUpdate(false);
		right.setAutoUpdate(false);
		
		sprite = down;
		dir = "";
		moving = false;
	}
	
	public void updateEntity(GameContainer gc, int delta) throws SlickException {
		float fDelta = delta * 0.1f;
		float speed = 1 / 32f;
		
		if(!moving) {
			switch(random.nextInt(4)) {
				case 0:
					dir = "up";
					moving = true;
					break;
				case 1:
					dir = "down";
					moving = true;
					break;
				case 2:
					dir = "left";
					moving = true;
					break;
				case 3:
					dir = "right";
					moving = true;
					break;
				default:
					break;
			}
		}
		
		
		switch(dir) {
			case "up":
				sprite.update(delta);
				sprite = up;
				if(isBlocked((playerX * 32), (playerY * 32) - fDelta) || isBlocked((playerX * 32) + SIZE - 1, (playerY * 32) - fDelta)) {
					moving = false;
					dir = "";
				}else{
					updateMoveY(-speed);
				}
				break;
			case "down":
				sprite.update(delta);
				sprite = down;
				if(isBlocked((playerX * 32), (playerY * 32) + SIZE) || isBlocked((playerX * 32) + SIZE - 1, (playerY * 32) + SIZE)) {
					moving = false;
					dir = "";
				}else{
					updateMoveY(speed);
				}
				break;
			case "left":
				sprite.update(delta);
				sprite = left;
				if(isBlocked((playerX * 32) - fDelta, (playerY * 32)) || isBlocked((playerX * 32) - fDelta, (playerY * 32) + SIZE - 1)) {
					moving = false;
					dir = "";
				}else{
					updateMoveX(-speed);
				}
				break;
			case "right":
				sprite.update(delta);
				sprite = right;
				if(isBlocked((playerX * 32) + SIZE, (playerY * 32)) || isBlocked((playerX * 32) + SIZE, (playerY * 32) + SIZE - 1)) {
					moving = false;
					dir = "";
				}else{
					updateMoveX(speed);
				}
				break;
			default:
				break;
		}
	}
	
	public void drawEntity(GameContainer gc, Graphics g) throws SlickException {
		sprite.draw((playerX * 32), (playerY * 32));
	}
	
	public float distanceTo(Entity sprite) {
		// change later
		return 0;
	}
	
	public boolean isBlocked(float x, float y) {
		int xBlock = (int)x / SIZE;
		int yBlock = (int)y / SIZE;
		return blocked[xBlock][yBlock];
	}
	
	public boolean onGridX() {
		return playerX - (int)playerX == 0;
	}
	
	public boolean onGridY() {
		return playerY - (int)playerY == 0;
	}
	
	public void updateMoveX(float speed) {
		playerX += speed;
		if(onGridX()) {
			moving = false;
			dir = "";
		}
	}
	
	public void updateMoveY(float speed) {
		playerY += speed;
		if(onGridY()) {
			moving = false;
			dir = "";
		}
	}
	
	public float getEntityX() {
		return playerX;
	}
	
	public float getEntityY() {
		return playerY;
	}
}
