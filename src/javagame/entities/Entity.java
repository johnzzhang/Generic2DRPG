package javagame.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public interface Entity {
	void drawEntity(GameContainer gc, Graphics g) throws SlickException;
	
	void updateEntity(GameContainer gc, int delta) throws SlickException;
	
	void loadEntity(TiledMap map) throws SlickException;
	
	float distanceTo(Entity sprite);
	
	boolean isBlocked(float x, float y);
	
	boolean onGridX();
	
	boolean onGridY();
	
	void updateMoveX(float speed);
	
	void updateMoveY(float speed);
	
	float getEntityX();
	
	float getEntityY();
}
