package Package;

import org.newdawn.slick.geom.Polygon;
 
public class block  {
	
	public Polygon poly;
	
	public block(int x, int y, int test[]) {
		
        poly = new Polygon(new float[]{ x + test[0], y + test[1],
        		
										x + test[2], y + test[3],
										
										x + test[4], y + test[5],
										
										x + test[6], y + test[7],
        });   
        
	}
 
	public void update(int delta) {
		
	}

}