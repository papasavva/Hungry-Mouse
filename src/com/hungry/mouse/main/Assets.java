//Name: 	Assets.java
//Purpose:	assign reference variables for all the assets,images sound and music
//			loadingscreen.java will load all the assets for the game during the splash screen

package com.hungry.mouse.main;

import com.hungry.mouse.framework.Image;
import com.hungry.mouse.framework.Music;
import com.hungry.mouse.framework.Sound;

public class Assets {
	
	//use static so there is no need to create object to access them
	//also static is 15%-20% faster since there is no need of an object
	
	//rectangle buttons
	public static Image menu_buttons;
	
	//small buttons
	public static Image pause_button;
		
	public static Image sound_on_buttons,music_on_buttons,info_buttons,help,logo,play_buttons;
	public static Image about,help1,help2,help3,help4,arrow_buttons,control_buttons,gyroscope_buttons,level_buttons,level1_button,level2_button,level3_button;
	public static Image cheese_reward,bomb,bomb2,sign;
	public static Image menu, splash, background, character, character2, character3, Kamikazi, Kamikazi2, Kamikazi3, Kamikazi4, Kamikazi5;
	public static Image tiledirt, tilegrassTop, tilegrassBot, tilegrassLeft, tilegrassRight, characterJump, characterDown;
	public static Sound click,fire,jump,collision,gameover,passed,collect;
	public static Music theme;
	
	public static void load(SampleGame sampleGame) {
		// TODO Auto-generated method stub
		theme = sampleGame.getAudio().createMusic("menutheme.mp3");//get the asset
		theme.setLooping(true);//repeat background music
		theme.setVolume(0.80f);//volume set to 80%
		theme.play();//play
	}
	
}
