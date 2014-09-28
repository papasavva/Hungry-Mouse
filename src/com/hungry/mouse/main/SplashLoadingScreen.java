//Name: 	SplashScreen.java
//Purpose:	show this screen when loading assets, before the main menu screen

package com.hungry.mouse.main;

import com.hungry.mouse.framework.Game;
import com.hungry.mouse.framework.Graphics;
import com.hungry.mouse.framework.Screen;
import com.hungry.mouse.framework.Graphics.ImageFormat;

public class SplashLoadingScreen extends Screen {
	
	//constructor
	public SplashLoadingScreen(Game game) {
		super(game);
	}

	//draw splash image
	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		Assets.splash= g.newImage("splash.png", ImageFormat.RGB565);

		game.setScreen(new LoadingScreen(game));

	}

	@Override
	public void paint(float deltaTime) {

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

	}
}