//Name: 	SampleGame.java
//Purpose:	the first class that is used to begin the game activity.
//			its called from manifest.xml


package com.hungry.mouse.main;

import com.hungry.mouse.framework.Screen;
import com.hungry.mouse.framework.implementation.AndroidGame;
import com.hungry.mouse.main.R;

//java libraries
import java.io.BufferedReader;//wraps and existing reader and buffers the input
import java.io.IOException;//signal that i/o exception has occurred
import java.io.InputStream;//represent input stream of bytes
import java.io.InputStreamReader;//turn a byte stream to character stream

//android libraries stored in SDK platform
import android.util.Log;//API for sending log output

public class SampleGame extends AndroidGame {

	public static String map;
	boolean firstTimeCreate = true;

	//check if its the first time that class is opened.
	@Override
	public Screen getInitScreen() {

		if (firstTimeCreate) {
			Assets.load(this);//load assets 
			firstTimeCreate = false;
		}

		//load map-level
		InputStream is = getResources().openRawResource(R.raw.map1);
		
		//create the level
		map = convertStreamToString(is);

		//we return to the splash screen
		return new SplashLoadingScreen(this);

	}

	//go back at every screen when key is pressed
	@Override
	public void onBackPressed() {
		getCurrentScreen().backButton();
	}

	//get txt file and return string
	private static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append((line + "\n"));
			}
		} catch (IOException e) {
			Log.w("LOG", e.getMessage());
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				Log.w("LOG", e.getMessage());
			}
		}
		return sb.toString();
	}

	@Override
	public void onResume() {
		super.onResume();
		
	}

	@Override
	public void onPause() {
		super.onPause();
		Assets.theme.pause();

	}
	
	
}