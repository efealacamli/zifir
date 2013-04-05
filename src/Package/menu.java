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

public class menu extends BasicGameState {

	private ArrayList<MouseOverArea> MouseOverAreaList = new  ArrayList<MouseOverArea>();

	private Image background, play, extras, exit, play_hover, exit_hover, light;

	private MouseOverArea MouseOverArea;
	
	private int stateID = 1;

	public menu(int stateID) {

        this.stateID = stateID;
        
    }

	@Override
	public int getID() {
		
		return stateID;
		
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		background 		= new Image("src/Data/Menu/background.png");
		
		play 			= new Image("src/Data/Menu/play.png");
		
		extras 			= new Image("src/Data/Menu/extras.png");
		
		exit 			= new Image("src/Data/Menu/exit.png");
		
		play_hover 		= new Image("src/Data/Menu/play_hover.png");
		
		exit_hover 		= new Image("src/Data/Menu/exit.png");
	
		MouseOverArea = new MouseOverArea(gc, play, 61, 242, 344, 106);
		
		MouseOverArea.setMouseOverImage(play_hover);
		
		MouseOverAreaList.add(MouseOverArea);
		
		MouseOverArea = new MouseOverArea(gc, extras, 78, 381);
		
		MouseOverAreaList.add(MouseOverArea);

		// These lines initialize the object of the menu
		MouseOverArea = new MouseOverArea(gc, exit, 643, 502);
		
		MouseOverArea.setMouseOverImage(exit_hover);
		
		MouseOverAreaList.add(MouseOverArea);
		
		light 		= new Image("src/Data/Menu/cursor.png");

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

		background.draw(0, 0, 800, 600);

		for (int i = 0; i < MouseOverAreaList.size(); ++i) {
			
			MouseOverAreaList.get(i).render(gc, g);
			
		}
		
		light.drawCentered(gc.getInput().getMouseX(), gc.getInput().getMouseY());
	
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

		Input input = gc.getInput();

		// PLAY
		if (MouseOverAreaList.get(0).isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			
			sbg.enterState(1);
			
		}

		// CREDITS
		if (MouseOverAreaList.get(1).isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			
			sbg.enterState(2);
			
		}
		
		// EXIT
		if (MouseOverAreaList.get(2).isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			
			gc.exit();
			
		}
		
	}

}
