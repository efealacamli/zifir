package Package;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Polygon;

public class entity {

	public SpriteSheet EntitySpriteSheet;
	public SpriteSheet EntitySpriteSheetE;
	
	public Polygon EntityPolygon;
	
	public Animation EntityImage;
	
	public boolean isAlive;
	
	public int x, y;
	
	public entity(String SpriteSheetPath, int SpriteSheetWidth, int SpriteSheetHeight, int EntityPolygonPosition[], int x, int y, boolean isAlive) throws SlickException {
	
		this.x = x;
		
		this.y = y;
		
		this.isAlive = isAlive;
		
		this.EntitySpriteSheet = new SpriteSheet(SpriteSheetPath, SpriteSheetWidth, SpriteSheetHeight);
		this.EntitySpriteSheetE = new SpriteSheet("src/Data/Sprite/PlayerSpriteSheetR.png", SpriteSheetWidth, SpriteSheetHeight);
		
		this.EntityPolygon = new Polygon(new float[] {  x + EntityPolygonPosition[0], y + EntityPolygonPosition[1],
				
														x + EntityPolygonPosition[2], y + EntityPolygonPosition[3],
														
														x + EntityPolygonPosition[4], y + EntityPolygonPosition[5],
														
														x + EntityPolygonPosition[6], y + EntityPolygonPosition[7] 
																
		});

	}

	public boolean entityCollisionWithBlocks(blockmap map) throws SlickException {
		
		for (int i = 0; i < map.blocks.size(); i++) {
			
			block entity = (block) map.blocks.get(i);
			
			if (EntityPolygon.intersects(entity.poly)) {
				
				return true;
				
			}
			
		}
		
		return false;
		
	}

}
