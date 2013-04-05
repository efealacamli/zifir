package Package;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;

public class lightSource {

	public Image LightSourceImage;
	
	public Polygon EntityPolygon;

	public boolean isVisited;
	
	public float x, y;
	
	lightSource(int[] EntityPolygonPosition, float x, float y, boolean isAlive, boolean isVisited) throws SlickException {

		this.x = x;
		
		this.y = y;
		
		this.isVisited = isVisited;
		
		this.LightSourceImage = new Image("src/Data/Sprite/LightSourceImage.png");
		
		this.EntityPolygon = new Polygon(new float[] {  x + EntityPolygonPosition[0], y + EntityPolygonPosition[1],
				
														x + EntityPolygonPosition[2], y + EntityPolygonPosition[3],
														
														x + EntityPolygonPosition[4], y + EntityPolygonPosition[5],
														
														x + EntityPolygonPosition[6], y + EntityPolygonPosition[7] 
						
		});

	}
	
	public boolean isInRange(int playerX) {
		
		if( x - playerX <= 200 && playerX - x <= 50) {
	
			return true;
			
		}
		
		return false;
		
	}

}
