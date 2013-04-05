package Package;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class monster extends entity {
	
	public Animation playerWalkStrongR;

	public int walkspeed;

	monster(int[] EntityPolygonPosition, float x, float y, boolean isAlive) throws SlickException {
		
		super("src/Data/Sprite/MonsterSpriteSheet.png", 391, 140, EntityPolygonPosition, (int)x, (int)y, true);
		
		playerWalkStrongR = new Animation(EntitySpriteSheet, 0, 0, 17, 0, true, 150, true);

		EntityImage = playerWalkStrongR;
		
	}
	
	public void move(GameContainer GameContainer, int delta, player p) throws SlickException {

		if( x - p.x <= Math.abs(450)) {
			
			if(p.direction == 1) {
				
				x = x - 1;
				
			} else {
				
				x = x + 1;
				
			}
			
			EntityPolygon.setX(x);
			
		}

	}

}
