//Name: 	LoadingScreen.java
//Purpose:	loads the assets during presentation of splashscreen

package com.hungry.mouse.main;

import com.hungry.mouse.main.Settings;
import com.hungry.mouse.framework.Game;
import com.hungry.mouse.framework.Graphics;
import com.hungry.mouse.framework.Screen;
import com.hungry.mouse.framework.Graphics.ImageFormat;

public class LoadingScreen extends Screen {
	
	//constructor
	public LoadingScreen(Game game) {
		
		super(game);
	}

	//load the assets and the saved settings
	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		
		//rectangle buttons
		Assets.menu_buttons = g.newImage("menu_buttons.png", ImageFormat.RGB565);		

		//small buttons
		Assets.pause_button = g.newImage("pause_button.png", ImageFormat.RGB565);
		
		Assets.cheese_reward = g.newImage("cheese_reward.png", ImageFormat.RGB565);
		Assets.bomb = g.newImage("bomb.png", ImageFormat.RGB565);		
		Assets.bomb2 = g.newImage("bomb2.png", ImageFormat.RGB565);	
		Assets.sign = g.newImage("sign.png", ImageFormat.RGB565);	
		
		
		Assets.play_buttons = g.newImage("play_buttons.png", ImageFormat.RGB565);		
		Assets.sound_on_buttons = g.newImage("sound_on_buttons.png", ImageFormat.RGB565);
		Assets.music_on_buttons = g.newImage("music_on_buttons.png", ImageFormat.RGB565);	
					
		Assets.info_buttons = g.newImage("info_buttons.png", ImageFormat.RGB565);	
		Assets.gyroscope_buttons = g.newImage("gyroscope_buttons.png", ImageFormat.RGB565);	
		Assets.level_buttons = g.newImage("level_buttons.png", ImageFormat.RGB565);	
		Assets.level1_button = g.newImage("level1_button.png", ImageFormat.RGB565);	
		Assets.level2_button = g.newImage("level2_button.png", ImageFormat.RGB565);	
		Assets.level3_button = g.newImage("level3_button.png", ImageFormat.RGB565);	
		Assets.arrow_buttons = g.newImage("arrow_buttons.png", ImageFormat.RGB565);		
		
		Assets.logo = g.newImage("logo.png", ImageFormat.RGB565);
		Assets.help = g.newImage("help-buttons.png", ImageFormat.RGB565);
		Assets.control_buttons = g.newImage("control_buttons.png", ImageFormat.RGB565);		
		
		Assets.help1 = g.newImage("help1.png", ImageFormat.RGB565);
		Assets.help2 = g.newImage("help2.png", ImageFormat.RGB565);
		Assets.help3 = g.newImage("help3.png", ImageFormat.RGB565);
		Assets.help4 = g.newImage("help4.png", ImageFormat.RGB565);
		
		Assets.about = g.newImage("about.png", ImageFormat.RGB565);	
		
		Assets.menu = g.newImage("menu.png", ImageFormat.RGB565);
		Assets.background = g.newImage("background.png", ImageFormat.RGB565);
		Assets.character = g.newImage("character.png", ImageFormat.RGB565);
		Assets.character2 = g.newImage("character2.png", ImageFormat.RGB565);
		Assets.character3  = g.newImage("character3.png", ImageFormat.RGB565);
		Assets.characterJump = g.newImage("jumped.png", ImageFormat.RGB565);
		Assets.characterDown = g.newImage("down.png", ImageFormat.RGB565);

		
		Assets.Kamikazi = g.newImage("kamikazi.png", ImageFormat.RGB565);
		Assets.Kamikazi2 = g.newImage("kamikazi2.png", ImageFormat.RGB565);
		Assets.Kamikazi3  = g.newImage("kamikazi3.png", ImageFormat.RGB565);
		Assets.Kamikazi4  = g.newImage("kamikazi4.png", ImageFormat.RGB565);
		Assets.Kamikazi5  = g.newImage("kamikazi5.png", ImageFormat.RGB565);


		
		Assets.tiledirt = g.newImage("tiledirt.png", ImageFormat.RGB565);
		Assets.tilegrassTop = g.newImage("tilegrasstop.png", ImageFormat.RGB565);
		Assets.tilegrassBot = g.newImage("tilegrassbot.png", ImageFormat.RGB565);
		Assets.tilegrassLeft = g.newImage("tilegrassleft.png", ImageFormat.RGB565);
		Assets.tilegrassRight = g.newImage("tilegrassright.png", ImageFormat.RGB565);
		

		Assets.fire = game.getAudio().createSound("fire.wav");
		Assets.jump = game.getAudio().createSound("jump.wav");
		Assets.click = game.getAudio().createSound("click.wav");
		Assets.collision = game.getAudio().createSound("collision.wav");
		Assets.gameover = game.getAudio().createSound("gameover.wav");
		Assets.passed = game.getAudio().createSound("passed.wav");
		Assets.collect = game.getAudio().createSound("collect.wav");
		
		Settings.load(game.getFileIO());//lest load the settings
		game.setScreen(new MainMenuScreen(game));

	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.splash, 0, 0);
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