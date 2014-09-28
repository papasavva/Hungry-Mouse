//Name: 	AboutScreen.java
//Purpose:	create the about screen that include project information

package com.hungry.mouse.main;

import com.hungry.mouse.framework.Game;
import com.hungry.mouse.framework.Graphics;
import com.hungry.mouse.framework.Input.TouchEvent;
import com.hungry.mouse.framework.Screen;
import com.hungry.mouse.main.Assets;

//java libraries
import java.util.List;//a collection which maintain an ordering for its elements

public class AboutScreen extends Screen{
	
	//constructor
	public AboutScreen(Game game) {
		super(game);
	}
	
	//touch event checking
	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			
			if (event.type == TouchEvent.TOUCH_UP) {	
				if (inBounds(event, 16, 368, 96, 96)) {
					game.setScreen(new MainMenuScreen(game));//change screen when button pressed
	                   if (Settings.soundEnabled)
	                       Assets.click.play(1);
						return;
				}
				
			}
		}
	}
	
	//create rectangles with coordinates that will be used to track touches
	private boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
		if (event.x > x && event.x < x + width -1 && event.y > y && event.y < y + height -1)
			return true;
		else
			return false;
		
	}
	
	//draw graphics
	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		
		g.drawImage(Assets.about, 0, 0);
		g.drawImage(Assets.logo,  224, 16);		
		g.drawImage(Assets.arrow_buttons,  16, 368, 0, 0, 96, 96);

	}
	
	@Override
	public void pause() {
		
	}
	
	@Override
	public void resume() {
		
	}
	
	@Override
	public void dispose() {
		
	}

	@Override
	public void backButton() {
		// TODO Auto-generated method stub
		
	}
	
	
}