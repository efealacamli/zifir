package Package;

import java.util.ArrayList;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.tiled.TiledMap;
 
public class blockmap {

	public int square[] = {0, 0, 40, 0, 40, 40, 0, 40}, monster[] = {0, 0, 198, 0, 198, 140, 0, 140}, mapX, mapWidth, mapHeight;
	
	public ArrayList<lightSource> lights = new ArrayList<lightSource>();
	
	public ArrayList<monster> monsters = new ArrayList<monster>();
	
	public ArrayList<block> blocks = new ArrayList<block>();
	
	public ArrayList<block> door = new ArrayList<block>();
	
	public block FinishDoor;
	
	public TiledMap tmap;
	
	public blockmap(String ref) throws SlickException {

		tmap = new TiledMap(ref, "src/Data/Map");

		for (int x = 0; x < tmap.getWidth(); x++) {
			
			for (int y = 0; y < tmap.getHeight(); y++) {
				
				int tileID = tmap.getTileId(x, y, 1);

				if (tileID == 1) {
					
					blocks.add(new block(x * 40, y * 40, square));
					
				} else if (tileID == 2) {
					
					lights.add(new lightSource(square, x * 40, y * 40, false, false));
					
				} else if (tileID == 3) {
					
					monsters.add(new monster(monster, x * 40, y * 40 - 100, true));
					
				} else if(tileID == 1204) {
					
					System.out.println("BURDAAAAAAAAAA");
					
					door.add(new block(x * 40, y * 40, square));
					
				}	
				
			}
			
		}
		
		lights.get(0).isVisited = true;
		
		mapX = 0;

	}
	
	public void moveBlockMap(float backgroundSpeedX, float backgroundSpeedY) {
		
		Polygon polygonObject;

		for (int i = 0; i < blocks.size(); i++) {
			
			polygonObject = (Polygon) blocks.get(i).poly;		

	    	polygonObject.setX(polygonObject.getX() - backgroundSpeedX);
		
		}
		
	}
	
}