package Package;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class player extends entity {
	
	public int square[] = {0, 0, 40, 0, 40, 40, 0, 40};
	
	public float playerYVelocity = 0.0f, playerYAcceleration = 1000.0f;
	
	public int walkspeed, direction, lightPower, dist, sourceIndex;
	
	public ArrayList<fire> fires = new ArrayList<fire>();
	
	boolean isItGoingtoJump, playerYOnTheGround;
	
	public Animation playerWalkStrongR, playerWalkStrongL;
	public Animation playerWalkWeakR, playerWalkWeakL;
	
	public Animation playerLightStrongR, playerLightStrongL;
	public Animation playerLightWeakR, playerLightWeakL;
	
	public Animation playerJumpStrongR, playerJumpStrongL;
	public Animation playerJumpWeakR, playerJumpWeakL;
	
	public Animation playerFireR, playerFireL;
	
	public Animation playerRestStrongR, playerRestStrongL;
	public Animation playerRestWeakR, playerRestWeakL;
	
	boolean canFire;

	float lastEnemyTime = 0f;
	
	float enemyEveryT = 1000f;
	
	player(int[] EntityPolygonPosition, float x, float y, boolean isAlive) throws SlickException {
		
		super("src/Data/Sprite/PlayerSpriteSheetL.png", 250, 250, EntityPolygonPosition, 10, 10, true);

		playerWalkStrongR = new Animation(EntitySpriteSheetE, 0, 3, 11, 3, true, 150, true);
		playerWalkStrongL = new Animation(EntitySpriteSheet, 0, 3, 11, 3, true, 150, true);

		playerWalkWeakR = new Animation(EntitySpriteSheetE, 0, 9, 11, 9, true, 150, true);
		playerWalkWeakL = new Animation(EntitySpriteSheet, 0, 9, 11, 9, true, 150, true);

		playerLightStrongR = new Animation(EntitySpriteSheetE, 0, 2, 11, 2, true, 150, true);
		playerLightStrongL = new Animation(EntitySpriteSheet, 0, 2, 11, 2, true, 150, true);

		playerLightWeakR = new Animation(EntitySpriteSheetE, 0, 1, 11, 1, true, 150, true);
		playerLightWeakL = new Animation(EntitySpriteSheet, 0, 1, 11, 1, true, 150, true);

		playerJumpStrongR = new Animation(EntitySpriteSheetE, 0, 9, 11, 9, true, 150, true);
		playerJumpStrongL = new Animation(EntitySpriteSheet, 0, 9, 11, 9, true, 150, true);
		
		playerJumpWeakR = new Animation(EntitySpriteSheetE, 0, 10, 8, 10, true, 150, true);
		playerJumpWeakL = new Animation(EntitySpriteSheet, 0, 10, 8, 10, true, 150, true);

		playerRestStrongR = new Animation(EntitySpriteSheetE, 0, 0, 11, 0, true, 150, true);
		playerRestStrongL = new Animation(EntitySpriteSheet, 0, 0, 11, 0, true, 150, true);
		
		playerRestWeakR = new Animation(EntitySpriteSheetE, 0, 7, 7, 7, true, 150, true);
		playerRestWeakL = new Animation(EntitySpriteSheet, 0, 7, 7, 7, true, 150, true);

		playerFireR = new Animation(EntitySpriteSheetE, 0, 4, 17, 4, true, 150, true);
		playerFireL = new Animation(EntitySpriteSheet, 0, 4, 17, 4, true, 150, true);

		EntityImage = playerRestWeakR;
		
		isItGoingtoJump = false;
		
		sourceIndex = -1;

		lightPower = 0;
		
		walkspeed = 2;
		
		direction = 1;
		
		canFire = false;
		
	}
	
	public void move(GameContainer GameContainer, int delta, blockmap map) throws SlickException {
		
		Input input = GameContainer.getInput();

        if(input.isKeyDown(Input.KEY_RIGHT)) {
        	
        	direction = 1;
        	
        	if(lightPower == 0) {
        		
        		EntityImage = playerWalkWeakR;
        		
        	} else {
        		
        		EntityImage = playerWalkStrongR;
        	}

        	if(x <= 700) {
        		
	        	x = x + walkspeed;
	        	
	        	EntityPolygon.setX(x + 75);
	        	
				if (entityCollisionWithBlocks(map) || entityCollisionWithMonsters(map)) {
					
					x = x - walkspeed;
					
					EntityPolygon.setX(x + 75);
					
				}
				
        	}
        	
        }
		
        if(input.isKeyDown(Input.KEY_LEFT)) {
        	
        	direction = -1;
        	
        	if(lightPower == 0) {
        		
        		EntityImage = playerWalkWeakL;
        		
        	} else {
        		
        		EntityImage = playerWalkStrongL;
        	}
        	
        	if(x >= 0) {
        	
	        	x = x - walkspeed;
	        	
	        	EntityPolygon.setX(x + 75);
	        	
				if (entityCollisionWithBlocks(map) || entityCollisionWithMonsters(map)) {
					
					x = x + walkspeed;
					
					EntityPolygon.setX(x + 75);
					
				}
			
        	}
        	
        }
		
        if(input.isKeyPressed(Input.KEY_DOWN)) {
        	
        	
        	if(lightPower == 0) {
        		
	        	// LIGHT RIGHT AND LEFT
	        	if(direction == 1) {
	        		
	        		EntityImage = playerLightWeakR;
	        		
	        	} else {
	        		
	        		EntityImage = playerLightWeakL;
	        		
	        	}
        	
        	} else {
        	
	        	// LIGHT RIGHT AND LEFT
	        	if(direction == 1) {
	        		
	        		EntityImage = playerLightStrongR;
	        		
	        	} else {
	        		
	        		EntityImage = playerLightStrongL;
	        		
	        	}
        	
        	}
        	
    		for (int i = 0; i < map.lights.size(); i++) {
    			
    			if(map.lights.get(i).isInRange(x)) {
    				
    				sourceIndex = i;
    				
    	    		break;
    				
    			} else {
    				
    				sourceIndex = -1;
    				
    			}
   			
    		}
    		
    		if(sourceIndex != -1) {
			
				if (lightPower == 0 && map.lights.get(sourceIndex).isVisited) {

					sourceIndex = -1;
					
					walkspeed = 3;
					
					lightPower = 1;
					
				} else if (lightPower == 1 && !map.lights.get(sourceIndex).isVisited)  {

					map.lights.get(sourceIndex).isVisited = true;
					
					sourceIndex = -1;
					
					walkspeed = 2;
					
					lightPower = 0;
					
				} 
			
    		}

        }

		if(input.isKeyDown(Input.KEY_UP)) {
        	
        	
        	if(lightPower == 0) {
        		
	        	if(direction == 1) {
	        		
	        		EntityImage = playerJumpWeakR;
	        		
	        	} else {
	        		
	        		EntityImage = playerJumpWeakL;
	        		
	        	}
	        	
        	} else {
        		
	        	if(direction == 1) {
	        		
	        		EntityImage = playerJumpStrongR;
	        		
	        	} else {
	        		
	        		EntityImage = playerJumpStrongL;
	        		
	        	}
        		
        	}
        	
			if(playerYOnTheGround){
				
				playerYVelocity = -600;
				
			}
			
		}
		
		

		
		lastEnemyTime += delta;
		
		if (lastEnemyTime > enemyEveryT) {
			
			lastEnemyTime = 0f;
		
			if(input.isKeyDown(Input.KEY_SPACE) && lightPower == 1) {
				
				lightPower = 0;
	
				if(direction == 1) {
					
					fires.add(new fire(square, x + 125, y + 25, true));
					
				} else {
					
					fires.add(new fire(square, x + 125, y + 25, true));
					
				}
	
			}
			
		}
	
		
		if( !input.isKeyDown(Input.KEY_SPACE) && !input.isKeyDown(Input.KEY_RIGHT) && !input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_UP) ) {

			if(lightPower == 0) {
				
				if(direction == 1) {
					
		    		EntityImage = playerRestWeakR;
		    		
		    	} else {
		    		
		    		EntityImage = playerRestWeakL;
		    		
		    	}
			
			} else {
				
				if(direction == 1) {
					
		    		EntityImage = playerRestStrongR;
		    		
		    	} else {
		    		
		    		EntityImage = playerRestStrongL;
		    		
		    	}
				
			}
			
		}
		

		boolean wasColliding = entityCollisionWithBlocks(map);
		
		float deltaSecs = delta * .001f;
		
		playerYVelocity += playerYAcceleration * deltaSecs;
		
		float verticalDisplacement = playerYVelocity * deltaSecs;
		
		playerYOnTheGround = false;
		
		y += verticalDisplacement;
		
		EntityPolygon.setY(y);
		
		boolean movedDown = verticalDisplacement > 0;
		
		boolean collided = entityCollisionWithBlocks(map);
		
		//if collided when moving down, then on the ground.		
		if(!wasColliding && collided || entityCollisionWithMonsters(map)) {
			
			if(movedDown) {
				
				y -= verticalDisplacement;
				
				EntityPolygon.setY(y);
				
				playerYVelocity = 0f;
				
				playerYOnTheGround = true;
				
			} else {
				
				y -= verticalDisplacement;
				
				EntityPolygon.setY(y);
				
				playerYVelocity = 0f;
				
			}
			
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean entityCollisionWithMonsters(blockmap map) throws SlickException {
		
		for (int i = 0; i < map.monsters.size(); i++) {
			
			monster entity = (monster) map.monsters.get(i);
			
			if (EntityPolygon.intersects(entity.EntityPolygon)) {
				
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
}
