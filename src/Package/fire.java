package Package;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

public class fire extends entity {
	
	public int walkspeed, bx, speed;
	
	public Animation FireSprite;

	fire(int[] EntityPolygonPosition, float x, float y, boolean isAlive) throws SlickException {
		
		super("src/Data/Sprite/FireSpriteSheet.png", 40, 40, EntityPolygonPosition, (int)x, (int)y, true);
		
		FireSprite = new Animation(EntitySpriteSheet, 0, 0, 0, 0, true, 150, true);

		EntityImage = FireSprite;
		
		speed = 4;
		
	}
	
	public void move(int limit, blockmap map) throws SlickException {

		if(bx <= limit) {
			
			bx = bx + speed;
			
			x = x + speed;
			
			EntityPolygon.setX(x);
			
			if(entityCollisionWithBlocks(map)) {
				
				isAlive = false;
				
			}
			
		} else {
			
			isAlive = false;
			
		}

	}
	
	public int fireCollisionWithMonsters(blockmap map) throws SlickException {
		
		for (int i = 0; i < map.monsters.size(); i++) {
			
			monster entity = (monster) map.monsters.get(i);
			
			if (EntityPolygon.intersects(entity.EntityPolygon)) {
				
				return i;
				
			}
			
		}
		return -1;
	}

}
