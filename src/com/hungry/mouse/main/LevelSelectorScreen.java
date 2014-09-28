//Name: 	LevelSelectorScreen.java
//Purpose:	create the level selector screen to choose level to play

package com.hungry.mouse.main;

import com.hungry.mouse.framework.Game;
import com.hungry.mouse.framework.Graphics;
import com.hungry.mouse.framework.Input.TouchEvent;
import com.hungry.mouse.framework.Screen;

//java libraries
import java.util.List;//a collection which maintain an ordering for its elements

//android libraries stored in SDK platform
import android.graphics.Color;//defines methods for creating and converting color ints
import android.graphics.Paint;//olds the style and color information about how to draw geometries, text and bitmaps


public class LevelSelectorScreen extends Screen {
	
	//variables
	public static int currentLevel;
	
	Paint paint, paint2;
	
	//constructor
	public LevelSelectorScreen(Game game) {
		super(game);
		
		// Defining a paint object
		paint = new Paint();
		paint.setTextSize(32);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);

		paint2 = new Paint();
		paint2.setTextSize(100);
		paint2.setTextAlign(Paint.Align.CENTER);
		paint2.setAntiAlias(true);
		paint2.setColor(Color.WHITE);
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
				
				//level 1
				if (inBounds(event, 40, 160, 240, 170)) {
					Settings.currentLevel=1;
                 game.setScreen(new GameScreen(game));//change screen when button pressed
                 if (Settings.soundEnabled)
                 Assets.click.play(1);
				}
				
				//level 2
				if (inBounds(event, 280, 160, 240, 170)) {
					Settings.currentLevel=2;
	                 game.setScreen(new GameScreen(game));//change screen when button pressed
	                 if (Settings.soundEnabled)
	                 Assets.click.play(1);
					}
				
				//level 3
				if (inBounds(event, 520, 160, 240, 170)) {
					Settings.currentLevel=3;
	                 game.setScreen(new GameScreen(game));//change screen when button pressed
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
		
		g.drawImage(Assets.level1_button,  40, 160);
		g.drawString(String.valueOf(Settings.level1KilledKamikazis)+"/9",138,300,paint);
		g.drawString(String.valueOf(Settings.level1CollectedCheeses)+"/9",238,300,paint);
		
		g.drawImage(Assets.level2_button,  280, 160);
		g.drawString(String.valueOf(Settings.level2KilledKamikazis)+"/9",378,300,paint);
		g.drawString(String.valueOf(Settings.level2CollectedCheeses)+"/9",478,300,paint);
		
		g.drawImage(Assets.level3_button,  520, 160);
		g.drawString(String.valueOf(Settings.level3KilledKamikazis)+"/9",618,300,paint);
		g.drawString(String.valueOf(Settings.level3CollectedCheeses)+"/9",718,300,paint);
		
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
		game.setScreen(new MainMenuScreen(game));

	}
	
	public static int getCurrentLevel()
	{
		return currentLevel;
	}
	
	public static void setCurrentLevel(int currentLevel){
		currentLevel = currentLevel;
	}
	
}
