package Package;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class game extends StateBasedGame {

	public static final int MENU 		= 0;
	
	public static final int PLAY 		= 1;
	
	public static final int CREDITS 	= 2;

	public static final int FINISH	 	= 3;

	public game() throws SlickException {
		
		super("Slick2DPath2Glory - SimpleGame");

		this.addState(new menu(MENU));
		
		this.addState(new play(PLAY));
		
		this.addState(new extras(CREDITS));

		this.addState(new finalscene(FINISH));

		this.enterState(MENU);

	}
	
	public void initStatesList(GameContainer GameContainer) throws SlickException {

		this.getState(MENU).init(GameContainer, this);
	}
	
	public static void main(String[] args) throws SlickException {
		
		AppGameContainer AppGameContainer = new AppGameContainer(new game());
		
		AppGameContainer.setDisplayMode(800, 600, false);
		
		AppGameContainer.setTargetFrameRate(75);
		
		AppGameContainer.setShowFPS(false);
		
		AppGameContainer.start();
		
	}
	
}
