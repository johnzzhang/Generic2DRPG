package javagame;

import javagame.entities.Dragon;
import javagame.entities.Player;
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

public class SlickBasicGame extends BasicGame {
	
	private Camera camera;
	
	private Player player;
	
	private Dragon dragon;
	
	private Dragon dragon1;
	
	// tiled map
	private TiledMap map;
	
	// constructor
	public SlickBasicGame() {
		super("Test");
	}
	
	public void init(GameContainer gc) throws SlickException {
		// set frame rate
		gc.setTargetFrameRate(60);
		
		// load tiled map
		map = new TiledMap("res/map.tmx");
		
		// load camera object
		camera = new Camera(gc, map);
		
		// load player entity
		player = new Player();
		
		// load dragon entity
		dragon = new Dragon();
		
		dragon1 = new Dragon();
		
		player.loadEntity(map);
		
		dragon.loadEntity(map);
		
		dragon1.loadEntity(map);
		
	}
	
	public void update(GameContainer gc, int delta) throws SlickException {
		player.updateEntity(gc, delta);
		
		dragon.updateEntity(gc, delta);
		
		dragon1.updateEntity(gc, delta);
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		camera.drawMap(player.getEntityX(), player.getEntityY());
		
		camera.translateGraphics(player.getEntityX(), player.getEntityY());
		
		player.drawEntity(gc, g);
		
		dragon.drawEntity(gc, g);
		
		dragon1.drawEntity(gc, g);
		
		camera.untranslateGraphics();
		
		//g.drawString("playerX = " + playerX + " playerY = " + playerY, 100, 20);
		//g.drawString("onGridX = " + onGridX() + " onGridY = " + onGridY(), 100, 40);
		//g.drawString("dir = " + dir + " moving = " + moving, 100, 60);
		//g.drawString("playerTileX = " + camera.playerTileX + " playerTileY = " + camera.playerTileY, 100, 80);
		//g.drawString("playerTileOffsetX = " + camera.playerTileOffsetX + " playerTileOffsetY = " + camera.playerTileOffsetY, 100, 100);
		//g.drawString("tileX = " + camera.tileX + " tileY = " + camera.tileY, 100, 120);
	}
	
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new SlickBasicGame());
		
		app.setDisplayMode(640, 640, false);
		app.start();
	}
	
}