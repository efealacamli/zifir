package Package;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class play extends BasicGameState {

	private int caseF, stateID = 1, PlayerPolygonPosition[] = { 75, 0, 125, 0, 125, 225, 75, 225 };
	
	private Music GameMusic;
	
	public boolean isBound, isBound2;
	
	public blockmap tileMap;
	
	public player player;
	
	public Image smokeLayer;
	
	public float smokeX, smokeY;

	public play(int stateID) {
		
        this.stateID = stateID;
        
    }
	
	@Override
	public int getID() {
		
		return stateID;
		
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		GameMusic = new Music("src/Data/Sound/theme.wav", true);
	
		player = new player(PlayerPolygonPosition, 0, 50, true);
		
		tileMap = new blockmap("src/Data/Map/map.tmx");
		
		GameMusic.play();
		
		GameMusic.loop();
		
		isBound = false;
		
		
		
		
		isBound2 = false;
		
		smokeLayer = new Image("src/Data/Map/smoke.png");
		
		smokeX = 0f;
		
		smokeY = 0f;
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

		tileMap.tmap.render(tileMap.mapX, 0, 0);
		
		/*
		for (int i = 0; i < tileMap.blocks.size(); i++) {

			g.draw(tileMap.blocks.get(i).poly);
			
		}
		*/

		for(int i = 0; i < tileMap.lights.size(); i++) {
			
			if(tileMap.lights.get(i).isVisited) {
				
				tileMap.lights.get(i).LightSourceImage.draw((int)tileMap.lights.get(i).x - 55, (int)tileMap.lights.get(i).y - 55);
				
			}

			//g.draw(tileMap.lights.get(i).EntityPolygon);
			
		}

		for(int i = 0; i < tileMap.monsters.size(); i++) {
			
			if(tileMap.monsters.get(i).isAlive) {
			
				tileMap.monsters.get(i).EntityImage.draw((int)tileMap.monsters.get(i).x, (int)tileMap.monsters.get(i).y);
	
				g.draw(tileMap.monsters.get(i).EntityPolygon);
			
			} else {
				
				tileMap.monsters.remove(i);
				
			}
		
		}

		for(int i = 0; i < player.fires.size(); i++) {
			
			player.fires.get(i).EntityImage.draw((int)player.fires.get(i).x, (int)player.fires.get(i).y);

			//g.draw(player.fires.get(i).EntityPolygon);
		
		}

		//g.draw(player.EntityPolygon);

		player.EntityImage.draw(player.x, player.y);
				
		smokeLayer.draw(smokeX, smokeY);

		//g.drawString("LIGHT OF POWER: " + player.lightPower, 50, 25);
		
		

		
		
		for(int i = 0; i < tileMap.door.size(); i++) {
			
			System.out.println("sss");
			
			g.draw(tileMap.door.get(i).poly);
		
		}

		
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

		player.move(gc, delta, tileMap);

		smokeX = tileMap.mapX + 35;
		
		smokeY = 0f;
		
		for(int i = 0; i < player.fires.size(); i++) {

			if(player.fires.get(i).isAlive) {
				
				player.fires.get(i).move(player.x + 350, tileMap);
				
			} else {
				
				player.fires.remove(i);
				
			}
		
		}
		
		
		
		
		
		for(int i = 0; i < tileMap.door.size(); i++) {

			if (player.EntityPolygon.intersects(tileMap.door.get(i).poly)) {
					
				sbg.enterState(3);
					
			}

		}
		
		
		
		
		for(int i = 0; i < tileMap.monsters.size(); i++) {
			
			tileMap.monsters.get(i).move(gc, delta, player);
		
		}
		
		if(player.x >= 650) {
			
			isBound = true;
			
			changePositions(5);

		} else if(isBound) {
			
			changePositions(5);
			
		}
		
		if (player.x <= 0) {
			
			isBound2 = true;
			
			changePositions(-5);
			
		} else if (isBound2) {
			
			changePositions(-5);
			
		}
		
	}
	
	public void changePositions(int value) throws SlickException {

		tileMap.moveBlockMap(value, 0);
		
		tileMap.mapX = tileMap.mapX - value;
		
		for(int i = 0; i < tileMap.lights.size(); i++) {
			
			tileMap.lights.get(i).EntityPolygon.setX(tileMap.lights.get(i).EntityPolygon.getX() - value);
			
			tileMap.lights.get(i).x = tileMap.lights.get(i).x - value;
			
		}
		
		for(int i = 0; i < tileMap.monsters.size(); i++) {
			
			tileMap.monsters.get(i).EntityPolygon.setX(tileMap.monsters.get(i).EntityPolygon.getX() - value);
			
			tileMap.monsters.get(i).x = tileMap.monsters.get(i).x - value;
			
		}

		for(int i = 0; i < player.fires.size(); i++) {

			caseF = player.fires.get(i).fireCollisionWithMonsters(tileMap);
			
			if(caseF != -1) {

				tileMap.monsters.remove(caseF);
				
				player.fires.remove(i);
				
			}
		
		}
		
		player.x = player.x - value;
		
		player.EntityPolygon.setX(player.EntityPolygon.getX() - value);
		
		if(player.x <= 10) {
			
			isBound = false;
			
		}
		
		if(player.x >= 640) {
			
			isBound2 = false;
			
		}
		
	}

}
