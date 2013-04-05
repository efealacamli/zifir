package Package;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.gui.MouseOverArea;

public class extras extends BasicGameState {
	
	private ArrayList<MouseOverArea> MouseOverAreaList = new  ArrayList<MouseOverArea>();
	
	private ArrayList<Image> ImageList = new  ArrayList<Image>();

	private Image background, back, next, prev, light;
	
	private int stateID = 1, flag;
	
	public extras(int stateID) {
		
        this.stateID = stateID;
        
    }

	@Override
	public int getID() {
		
		return stateID;
		
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		background 	= new Image("src/Data/Menu/extrasBackground.png");
	
		back 		= new Image("src/Data/Menu/back.png");
		
		next 		= new Image("src/Data/Menu/next.png");
		
		prev 		= new Image("src/Data/Menu/prev.png");

		ImageList.add(new Image("src/Data/Menu/extras/0.png"));
		ImageList.add(new Image("src/Data/Menu/extras/1.png"));
		ImageList.add(new Image("src/Data/Menu/extras/2.png"));
		ImageList.add(new Image("src/Data/Menu/extras/3.png"));
		ImageList.add(new Image("src/Data/Menu/extras/4.png"));
		ImageList.add(new Image("src/Data/Menu/extras/5.png"));
		ImageList.add(new Image("src/Data/Menu/extras/6.png"));
		ImageList.add(new Image("src/Data/Menu/extras/7.png"));
		ImageList.add(new Image("src/Data/Menu/extras/8.png"));
		ImageList.add(new Image("src/Data/Menu/extras/9.png"));
		ImageList.add(new Image("src/Data/Menu/extras/10.png"));
		ImageList.add(new Image("src/Data/Menu/extras/11.png"));
		ImageList.add(new Image("src/Data/Menu/extras/12.png"));

		MouseOverAreaList.add(new MouseOverArea(gc, back, 725, 523));
		
		MouseOverAreaList.add(new MouseOverArea(gc, next, 648, 225));
		
		MouseOverAreaList.add(new MouseOverArea(gc, prev, 252, 225));
		
		light 		= new Image("src/Data/Menu/cursor.png");
	
		flag = 0;

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

		background.draw(0, 0, 800, 600);

		for (int i = 0; i < MouseOverAreaList.size(); ++i) {
			
			MouseOverAreaList.get(i).render(gc, g);
			
		}

		ImageList.get(flag).draw(320, 72, 291, 377);
		
		light.drawCentered(gc.getInput().getMouseX(), gc.getInput().getMouseY());
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

		Input input = gc.getInput();

		if (MouseOverAreaList.get(0).isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			
			sbg.enterState(0);
			
		}

		if (MouseOverAreaList.get(1).isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			
			if(flag == ImageList.size() - 1) {
				
				flag = 0;
				
			} else {
				
				++flag;
				
			}
			
		}

		if (MouseOverAreaList.get(2).isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			
			if(flag == 0) {
				
				flag = ImageList.size() - 1;
				
			} else {
				
				--flag;
				
			}

		}
		
	}

}
