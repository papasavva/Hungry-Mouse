//Name: 	MainMenuScreen.java
//Purpose:	create the menu screen with many options

package com.hungry.mouse.main;

import com.hungry.mouse.framework.Game;
import com.hungry.mouse.framework.Graphics;
import com.hungry.mouse.framework.Screen;
import com.hungry.mouse.framework.Input.TouchEvent;
import com.hungry.mouse.main.Assets;
import com.hungry.mouse.main.AboutScreen;

//java libraries
import java.util.List;//a collection which maintain an ordering for its elements

public class MainMenuScreen extends Screen {
	
	//constructor
	public MainMenuScreen(Game game) {
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
				
				//sound options
				if (inBounds(event, 16, 368, 96, 96)) {
	                  Settings.soundEnabled = !Settings.soundEnabled;
	                   if (Settings.soundEnabled)
	                   Assets.click.play(1);
					}				
				//gyroscope options
				
				if (inBounds(event, 112, 368, 96, 96)) {
	                  Settings.gyroscopeEnabled = !Settings.gyroscopeEnabled;
	                   if (Settings.soundEnabled)
	                   Assets.click.play(1);
	                   }
				
				if (inBounds(event, 224, 176, 352, 128)) {
                   game.setScreen(new LevelSelectorScreen(game));//change screen when button pressed
                   if (Settings.soundEnabled)
                   Assets.click.play(1);
				}
				if (inBounds(event, 688, 368, 96, 96)) {
					game.setScreen(new AboutScreen(game));//change screen when button pressed
					if (Settings.soundEnabled)
					Assets.click.play(1);
				}
				if (inBounds(event, 592, 368, 96, 96)) {
					game.setScreen(new HelpScreen1(game));//change screen when button pressed
					if (Settings.soundEnabled)
					Assets.click.play(1);
				}				

			}
			
		}
		Settings.save(game.getFileIO());//save the changes
	}

	//create rectangles with coordinates that will be used to track touches
	private boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}

	//draw graphics
	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.menu, 0, 0);

		g.drawImage(Assets.logo,  224, 16);		
		g.drawImage(Assets.menu_buttons,  224, 176, 0, 0, 352, 128);//play button (show the 1/3 of image)
		
		if (Settings.soundEnabled){
		g.drawImage(Assets.sound_on_buttons,  16, 368, 0, 0, 96, 96);
		} else {
			g.drawImage(Assets.sound_on_buttons,  16, 368, 0, 96, 96, 96);			
		}
		
		
		if (Settings.gyroscopeEnabled){
		g.drawImage(Assets.gyroscope_buttons,  112, 368, 0, 96, 96, 96);
		} else
		{
			g.drawImage(Assets.gyroscope_buttons,  112, 368, 0, 0, 96, 96);		
		}
		
		g.drawImage(Assets.info_buttons,  592, 368, 0, 0, 96, 96);
		g.drawImage(Assets.info_buttons,  688, 368, 0, 96, 96, 96);		

	}

	@Override
	public void pause() {
		Settings.save(game.getFileIO());//save the changes
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

	//kill all process including all activities
	//prefered than finish() to free up memory
	@Override
	public void backButton() {
		
		//kill the process with the given PID and free up memory
        android.os.Process.killProcess(android.os.Process.myPid());

	}
}
