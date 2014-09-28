//Name: 	GameScreen.java
//Purpose:	present the game to player and allow it to interact
//			we have 5 subscreens Ready, Running, Paused, GameOver, LevelIsPassed

package com.hungry.mouse.main;

import com.hungry.mouse.framework.Game;
import com.hungry.mouse.framework.Graphics;
import com.hungry.mouse.framework.Image;
import com.hungry.mouse.framework.Input.TouchEvent;
import com.hungry.mouse.framework.Screen;

//java libraries
import java.util.ArrayList;//implementation of list with additional operations add,remove,replace
import java.util.List;//a collection which maintain an ordering for its elements
import java.util.Scanner;//parser that parses a text string of primitive types and strings with the help of regular expressions

//android libraries stored in SDK platform
import android.graphics.Color;//defines method for creating and converting color ints
import android.graphics.Paint;//holds the style and color information about how to draw geometries, text and bitmaps


public class GameScreen extends Screen {
	enum GameState {
		Ready, Running, Paused, GameOver, LevelIsPassed
	}

	GameState state = GameState.Ready;

	//local variables
	private static Background bg1, bg2;
	private static Mouse mouse;
	public static Kamikazi hb, hb2, hb3, hb4, hb5, hb6, hb7, hb8, hb9;
	public static Cheese ch,ch2,ch3,ch4,ch5,ch6,ch7,ch8;
	public static Bomb bo, bo2, bo3, bo4, bo5, bo6, bo7, bo8, bo9;
	public static Sign si;
	
	float accelerometerValue;//get negative and positive value for left and right
	
	//members
	private Image currentSprite, character, character2, character3, Kamikazi,
			Kamikazi2, Kamikazi3, Kamikazi4, Kamikazi5;
	private Animation anim, banim, hanim, canim, sanim;
	private Image cheese, cheese2, cheese3;
	private Image bomb, bomb2;
	private Image sign, sign2;
	private ArrayList<Tile> tilearray = new ArrayList<Tile>();

	Paint paint, paint2;

	int livesLeft = 1;
	//constructor
	public GameScreen(Game game) {
		super(game);
		
		//initialize game objects
		//background
		bg1 = new Background(0, 0);
		bg2 = new Background(2160, 0);
		
		//need to initialize bg1 and bg2 before calling mouse
		mouse = new Mouse();
		
		if (Settings.currentLevel == 1)
		{
			si = new Sign(8000,347);
			
			ch = new Cheese(520,347);
			ch2 = new Cheese(760,347);
			ch3 = new Cheese(1080,347);
			ch4 = new Cheese(2960,347);
			ch5 = new Cheese(3640,347);
			ch6 = new Cheese(5200,347);
			ch7 = new Cheese(6080,347);
			ch8 = new Cheese(6600,347);	


			
			bo = new Bomb(1200,347);
			bo.setHealth(1);
			bo2 = new Bomb(3000,347);
			bo2.setHealth(1);
			bo3 = new Bomb(3160,347);
			bo3.setHealth(1);
			bo4 = new Bomb(4830,347);
			bo4.setHealth(1);
			bo5 = new Bomb(6120,347);
			bo5.setHealth(1);
			bo6 = new Bomb(7320,347);
			bo6.setHealth(1);
			bo7 = new Bomb(9380,347);
			bo7.setHealth(1);
			bo8 = new Bomb(9420,347);
			bo8.setHealth(1);
			bo9 = new Bomb(9500,347);	
			bo9.setHealth(1);
			
			hb = new Kamikazi(340, 297);
			hb.setHealth(5);
			hb2 = new Kamikazi(1600,297);
			hb2.setHealth(5);
			hb3 = new Kamikazi(1720,297);
			hb3.setHealth(5);
			hb4 = new Kamikazi(5200,297);	
			hb4.setHealth(5);
			hb5 = new Kamikazi(6360,297);
			hb5.setHealth(5);
			hb6 = new Kamikazi(6420,297);
			hb6.setHealth(4);
			hb7 = new Kamikazi(7440,297);
			hb7.setHealth(5);
			hb8 = new Kamikazi(7500,297);
			hb8.setHealth(6);
			hb9 = new Kamikazi(7560,297);
			hb9.setHealth(12);
		}
		else if(Settings.currentLevel == 2){
			si = new Sign(8000,347);
			
			ch = new Cheese(520,347);
			ch2 = new Cheese(760,347);
			ch3 = new Cheese(1080,347);
			ch4 = new Cheese(2960,347);
			ch5 = new Cheese(3640,347);
			ch6 = new Cheese(5200,347);
			ch7 = new Cheese(6080,347);
			ch8 = new Cheese(6600,347);	


			
			bo = new Bomb(1200,347);
			bo.setHealth(1);
			bo2 = new Bomb(3000,347);
			bo2.setHealth(1);
			bo3 = new Bomb(3160,347);
			bo3.setHealth(1);
			bo4 = new Bomb(4830,347);
			bo4.setHealth(1);
			bo5 = new Bomb(6120,347);
			bo5.setHealth(1);
			bo6 = new Bomb(7320,347);
			bo6.setHealth(1);
			bo7 = new Bomb(9380,347);
			bo7.setHealth(1);
			bo8 = new Bomb(9420,347);
			bo8.setHealth(1);
			bo9 = new Bomb(9500,347);	
			bo9.setHealth(1);	

			hb = new Kamikazi(340, 297);
			hb.setHealth(9);
			hb2 = new Kamikazi(1600,297);
			hb2.setHealth(10);
			hb3 = new Kamikazi(1720,297);
			hb3.setHealth(11);
			hb4 = new Kamikazi(5200,297);	
			hb4.setHealth(10);
			hb5 = new Kamikazi(6360,297);
			hb5.setHealth(7);
			hb6 = new Kamikazi(6420,297);
			hb6.setHealth(10);
			hb7 = new Kamikazi(7440,297);
			hb7.setHealth(6);
			hb8 = new Kamikazi(7500,297);
			hb8.setHealth(5);
			hb9 = new Kamikazi(7560,297);
			hb9.setHealth(25);
		}
		else if(Settings.currentLevel == 3){
			si = new Sign(7560,347);
			
			ch = new Cheese(520,347);
			ch2 = new Cheese(760,347);
			ch3 = new Cheese(1080,347);
			ch4 = new Cheese(2960,347);
			ch5 = new Cheese(3640,347);
			ch6 = new Cheese(5200,347);
			ch7 = new Cheese(6080,347);
			ch8 = new Cheese(6600,347);	


			
			bo = new Bomb(1200,347);
			bo.setHealth(1);
			bo2 = new Bomb(3000,347);
			bo2.setHealth(1);
			bo3 = new Bomb(3160,347);
			bo3.setHealth(1);
			bo4 = new Bomb(4830,347);
			bo4.setHealth(1);
			bo5 = new Bomb(6120,347);
			bo5.setHealth(1);
			bo6 = new Bomb(7320,347);
			bo6.setHealth(1);
			bo7 = new Bomb(9380,347);
			bo7.setHealth(1);
			bo8 = new Bomb(9420,347);
			bo8.setHealth(1);
			bo9 = new Bomb(9500,347);	
			bo9.setHealth(1);

			hb = new Kamikazi(340, 297);
			hb.setHealth(5);
			hb2 = new Kamikazi(1600,297);
			hb2.setHealth(10);
			hb3 = new Kamikazi(1720,297);
			hb3.setHealth(13);
			hb4 = new Kamikazi(5200,297);	
			hb4.setHealth(15);
			hb5 = new Kamikazi(6360,297);
			hb5.setHealth(9);
			hb6 = new Kamikazi(6420,297);
			hb6.setHealth(15);
			hb7 = new Kamikazi(7440,297);
			hb7.setHealth(8);
			hb8 = new Kamikazi(7500,297);
			hb8.setHealth(15);
			hb9 = new Kamikazi(7560,297);
			hb9.setHealth(33);
		}
					
		
		character = Assets.character;
		character2 = Assets.character2;
		character3 = Assets.character3;

		Kamikazi = Assets.Kamikazi;
		Kamikazi2 = Assets.Kamikazi2;
		Kamikazi3 = Assets.Kamikazi3;
		Kamikazi4 = Assets.Kamikazi4;
		Kamikazi5 = Assets.Kamikazi5;

		bomb = Assets.bomb;
		bomb2 = Assets.bomb;
		
		sign = Assets.sign;
		sign2 = Assets.sign;
		
		cheese = Assets.cheese_reward;
		cheese2 = Assets.cheese_reward;
		cheese3 = Assets.cheese_reward;
		
		//animations based on frames
		anim = new Animation();
		anim.addFrame(character, 1250);
		anim.addFrame(character2, 50);
		anim.addFrame(character3, 50);
		anim.addFrame(character2, 50);

		banim = new Animation();
		banim.addFrame(bomb, 100);		
		banim.addFrame(bomb2, 100);	
		
		hanim = new Animation();
		hanim.addFrame(Kamikazi, 100);
		hanim.addFrame(Kamikazi2, 100);
		hanim.addFrame(Kamikazi3, 100);
		hanim.addFrame(Kamikazi4, 100);
		hanim.addFrame(Kamikazi5, 100);
		hanim.addFrame(Kamikazi4, 100);
		hanim.addFrame(Kamikazi3, 100);
		hanim.addFrame(Kamikazi2, 100);

		canim = new Animation();
		canim.addFrame(cheese, 100);
		canim.addFrame(cheese2, 100);
		canim.addFrame(cheese3, 100);
		
		sanim = new Animation();
		sanim.addFrame(sign, 100);
		
		currentSprite = anim.getImage();

		loadMap();

		// Defining a paint object
		paint = new Paint();
		paint.setTextSize(30);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.RED);

		paint2 = new Paint();
		paint2.setTextSize(100);
		paint2.setTextAlign(Paint.Align.CENTER);
		paint2.setAntiAlias(true);
		paint2.setColor(Color.WHITE);

	}

	//load the map to create the level environment
	private void loadMap() {
		ArrayList lines = new ArrayList();
		int width = 0;
		int height = 0;

		Scanner scanner = new Scanner(SampleGame.map);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();

			//no more lines to read
			if (line == null) {
				break;
			}

			//ignore comments
			if (!line.startsWith("!")) {
				lines.add(line);
				width = Math.max(width, line.length());

			}
		}
		height = lines.size();

		//loop until the boarders of the screen
		for (int j = 0; j < 12; j++) {
			String line = (String) lines.get(j);
			for (int i = 0; i < width; i++) {

				if (i < line.length()) {
					char ch = line.charAt(i);
					Tile t = new Tile(i, j, Character.getNumericValue(ch));
					tilearray.add(t);
				}

			}
		}

	}

	//update and present different screens based on events
	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		// We have four separate update methods in this example.
		// Depending on the state of the game, we call different update methods.
		// Refer to Unit 3's code. We did a similar thing without separating the
		// update methods.

		if (state == GameState.Ready)
			updateReady(touchEvents);
		if (state == GameState.Running)
			updateRunning(touchEvents, deltaTime);
		if (state == GameState.Paused)
			updatePaused(touchEvents);
		if (state == GameState.GameOver)
			updateGameOver(touchEvents);
		if (state == GameState.LevelIsPassed)
			updateLevelIsPassed(touchEvents);
	}

	//when user touch screen, the game begin
	private void updateReady(List<TouchEvent> touchEvents) {
		if (touchEvents.size() > 0)
			state = GameState.Running;
	}

	//game is running and we look for events to control the player
	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
		
		if (si.collected)
			state = GameState.LevelIsPassed;
		
		if (Settings.gyroscopeEnabled){
		//go right or left using accelerometer
		accelerometerValue=game.getInput().getAccelY();
		
		if (accelerometerValue>1.8) {
			mouse.moveRight();
			mouse.setMovingLeft(false);

		} else if (accelerometerValue<-1.8) {
			mouse.moveLeft();
			mouse.setMovingRight(false);

		} else 
		{
			mouse.stopLeft();
			mouse.stopRight();
		}
			
		}
		
		//handle the input
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) {//when pressed down the key
				
				//jump
				if (inBounds(event, 592, 368, 96, 96)) {
					mouse.jump();
					currentSprite = anim.getImage();
					mouse.setDucked(false);
					
			
				}

				else if (inBounds(event, 688, 368, 96, 96)) {
					//fire
					if (mouse.isDucked() == false && mouse.isJumped() == false
							&& mouse.isReadyToFire()) {
							mouse.shoot();
											}
				}

				else if (inBounds(event, 592, 368, 96, 96)
						&& mouse.isJumped() == false) {
					currentSprite = Assets.characterDown;
					mouse.setDucked(true);
					mouse.setSpeedX(0);

				}

				if (!Settings.gyroscopeEnabled){//if gyroscope is on, we dont need the control arrows
					if (inBounds(event, 112, 368, 96, 96)) {
						mouse.moveRight();
						mouse.setMovingLeft(false);
	
					}
					else if (inBounds(event, 16, 368, 96, 96)) {
						mouse.moveLeft();
						mouse.setMovingRight(false);
					}  
					
				}

			}

			if (event.type == TouchEvent.TOUCH_UP) {//when release the key

				if (inBounds(event, 0, 415, 65, 65)) {
					currentSprite = anim.getImage();
					mouse.setDucked(false);

				}

				if (inBounds(event, 0, 0, 130, 130)) {
					pause();

				}
				if (!Settings.gyroscopeEnabled){//if gyroscope is on, we dont need the control arrows
					if (inBounds(event, 112, 368, 96, 96)) {
						mouse.stopLeft();
	
					}
					if (inBounds(event, 16, 368, 96, 96)) {
						mouse.stopRight();
	
					}
				}
			}

		}


		//check for death and show gameover screen
		if (livesLeft == 0) {//when fall to gap
			state = GameState.GameOver;
		}
		
		if (mouse.getHealth() <1)//when died from kamikazis and bombs
		{
			state = GameState.GameOver;			
		}
		
		//update mouse,kamikazi,bullets,background and everything that is included in game
		if (mouse != null) {
			mouse.update();
		 } else{
		       System.out.println("mouse is null, do not attempt to call update");
		}
		if (mouse.isJumped()) {
			currentSprite = Assets.characterJump;
		} else if (mouse.isJumped() == false && mouse.isDucked() == false) {
			currentSprite = anim.getImage();
		}

		ArrayList projectiles = mouse.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = (Projectile) projectiles.get(i);
			if (p.isVisible() == true) {
				p.update();
			} else {
				projectiles.remove(i);
			}
		}

		updateTiles();
		hb.update();
		hb2.update();
		hb3.update();
		hb4.update();
		hb5.update();
		hb6.update();		
		hb7.update();
		hb8.update();
		hb9.update();		
		
		
		bg1.update();
		bg2.update();
		
		//cheese
		ch.update();
		ch2.update();
		ch3.update();
		ch4.update();
		ch5.update();
		ch6.update();
		ch7.update();
		ch8.update();

		
		//bomb
		bo.update();
		bo2.update();
		bo3.update();
		bo4.update();
		bo5.update();
		bo6.update();
		bo7.update();
		bo8.update();
		bo9.update();
		
		//sign
		si.update();
		
		animate();

		if (mouse.getCenterY() > 500) {
			state = GameState.GameOver;
		}
	}

	//check if user touch inside the rectangle
	private boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}
	
	//g.drawImage(Assets.menu_buttons,  224, 128, 0, 128, 352, 256);//play button (show the 2/3 of image)
	//show pause screen
	private void updatePaused(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if (inBounds(event, 0, 0, 800, 240)) {

					if (!inBounds(event, 0, 0, 96, 96)) {
						resume();
					}
				}

				if (inBounds(event, 0, 240, 800, 240)) {
					nullify();
					goToMenu();
				}
			}
		}
	}

	//show game over screen
	private void updateGameOver(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if (inBounds(event, 0, 0, 800, 480)) {
					nullify();
					game.setScreen(new LevelSelectorScreen(game));
					return;
				}
			}
		}

	}
	
	//show level passed screen
	private void updateLevelIsPassed(List<TouchEvent> touchEvents) {
		
		//change score only if its better
		if (Settings.currentLevel==1)
		{
			if (Settings.level1CollectedCheeses < mouse.getCollectedCheeses())
				Settings.level1CollectedCheeses=mouse.getCollectedCheeses();
			
			if (Settings.level1KilledKamikazis < mouse.getKamikaziKilled())	
				Settings.level1KilledKamikazis=mouse.getKamikaziKilled();
				
			
		}
		else if (Settings.currentLevel==2)
		{
			if (Settings.level2CollectedCheeses < mouse.getCollectedCheeses())
				Settings.level2CollectedCheeses=mouse.getCollectedCheeses();
			if (Settings.level2KilledKamikazis < mouse.getKamikaziKilled())	
				Settings.level2KilledKamikazis=mouse.getKamikaziKilled();
		}	
		else
		{
			if (Settings.level3CollectedCheeses < mouse.getCollectedCheeses())			
				Settings.level3CollectedCheeses=mouse.getCollectedCheeses();
			if (Settings.level3KilledKamikazis < mouse.getKamikaziKilled())	
				Settings.level3KilledKamikazis=mouse.getKamikaziKilled();
		}			
		
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if (inBounds(event, 0, 0, 800, 480)) {
					nullify();
					game.setScreen(new LevelSelectorScreen(game));
					return;
				}
			}
		}

	}
	//create the level
	private void updateTiles() {

		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			t.update();
		}

	}

	//show the images to the screen
	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();

		g.drawImage(Assets.background, bg1.getBgX(), bg1.getBgY());
		g.drawImage(Assets.background, bg2.getBgX(), bg2.getBgY());
		paintTiles(g);

		ArrayList projectiles = mouse.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = (Projectile) projectiles.get(i);
			g.drawRect(p.getX(), p.getY(), 10, 5, Color.YELLOW);
		}
		// First draw the game elements based on their center x,y

		g.drawImage(currentSprite, mouse.getCenterX() - 48,
				mouse.getCenterY() - 64);
		
		//sign
		if (si.getColleceted()==false){
		g.drawImage(sanim.getImage(), si.getCenterX() - 16,
				si.getCenterY() - 16);
		}
		//cheeses
		if (ch.getColleceted()==false){
		g.drawImage(canim.getImage(), ch.getCenterX() - 16,
				ch.getCenterY() - 16);
		}
		if (ch2.getColleceted()==false){
		g.drawImage(canim.getImage(), ch2.getCenterX() - 16,
				ch2.getCenterY() - 16);
		}
		if (ch3.getColleceted()==false){
		g.drawImage(canim.getImage(), ch3.getCenterX() - 16,
				ch3.getCenterY() - 16);
		}
		if (ch4.getColleceted()==false){
		g.drawImage(canim.getImage(), ch4.getCenterX() - 16,
				ch4.getCenterY() - 16);
		}
		if (ch5.getColleceted()==false){
		g.drawImage(canim.getImage(), ch5.getCenterX() - 16,
				ch5.getCenterY() - 16);
		}
		if (ch6.getColleceted()==false){
		g.drawImage(canim.getImage(), ch6.getCenterX() - 16,
				ch6.getCenterY() - 16);
		}
		if (ch7.getColleceted()==false){
		g.drawImage(canim.getImage(), ch7.getCenterX() - 16,
				ch7.getCenterY() - 16);
		}
		if (ch8.getColleceted()==false){
		g.drawImage(canim.getImage(), ch8.getCenterX() - 16,
				ch8.getCenterY() - 16);
		}
		
		//bombs
		if (bo.getHealth()!=0){
		g.drawImage(banim.getImage(), bo.getCenterX() - 16,
				bo.getCenterY() - 16);
		}
		if (bo2.getHealth()!=0){
		g.drawImage(banim.getImage(), bo2.getCenterX() - 16,
				bo2.getCenterY() - 16);
		}
		if (bo3.getHealth()!=0){
		g.drawImage(banim.getImage(), bo3.getCenterX() - 16,
				bo3.getCenterY() - 16);
		}
		if (bo4.getHealth()!=0){
		g.drawImage(banim.getImage(), bo4.getCenterX() - 16,
				bo4.getCenterY() - 16);
		}
		if (bo5.getHealth()!=0){
		g.drawImage(banim.getImage(), bo5.getCenterX() - 16,
				bo5.getCenterY() - 16);
		}
		if (bo6.getHealth()!=0){
		g.drawImage(banim.getImage(), bo6.getCenterX() - 16,
				bo6.getCenterY() - 16);
		}
		if (bo7.getHealth()!=0){
		g.drawImage(banim.getImage(), bo7.getCenterX() - 16,
				bo7.getCenterY() - 16);
		}
		if (bo8.getHealth()!=0){
		g.drawImage(banim.getImage(), bo8.getCenterX() - 16,
				bo8.getCenterY() - 16);
		}
		if (bo9.getHealth()!=0){
		g.drawImage(banim.getImage(), bo9.getCenterX() - 16,
				bo9.getCenterY() - 16);
		}
		
		//kamikazi
		if (hb.getHealth()!=0){
			g.drawString(String.valueOf(hb.health), hb.getCenterX(), hb.getCenterY()-48, paint);
			g.drawImage(hanim.getImage(), hb.getCenterX() - 48,
					hb.getCenterY() - 64);
		}
		if (hb2.getHealth()!=0){
			g.drawString(String.valueOf(hb2.health), hb2.getCenterX(), hb2.getCenterY()-48, paint);		
			g.drawImage(hanim.getImage(), hb2.getCenterX() - 48,
					hb2.getCenterY() - 64);
		}
		if (hb3.getHealth()!=0){
			g.drawString(String.valueOf(hb3.health), hb3.getCenterX(), hb3.getCenterY()-48, paint);		
			g.drawImage(hanim.getImage(), hb3.getCenterX() - 48,
					hb3.getCenterY() - 64);
		}
		if (hb4.getHealth()!=0){
			g.drawString(String.valueOf(hb4.health), hb4.getCenterX(), hb4.getCenterY()-48, paint);
			g.drawImage(hanim.getImage(), hb4.getCenterX() - 48,
					hb4.getCenterY() - 64);
		}
		if (hb5.getHealth()!=0){
			g.drawString(String.valueOf(hb5.health), hb5.getCenterX(), hb5.getCenterY()-48, paint);		
			g.drawImage(hanim.getImage(), hb5.getCenterX() - 48,
					hb5.getCenterY() - 64);
		}
		if (hb6.getHealth()!=0){
			g.drawString(String.valueOf(hb6.health), hb6.getCenterX(), hb6.getCenterY()-48, paint);		
			g.drawImage(hanim.getImage(), hb6.getCenterX() - 48,
					hb6.getCenterY() - 64);
		}
		if (hb7.getHealth()!=0){
			g.drawString(String.valueOf(hb7.health), hb7.getCenterX(), hb7.getCenterY()-48, paint);
			g.drawImage(hanim.getImage(), hb7.getCenterX() - 48,
					hb7.getCenterY() - 64);
		}
		if (hb8.getHealth()!=0){
			g.drawString(String.valueOf(hb8.health), hb8.getCenterX(), hb8.getCenterY()-48, paint);		
			g.drawImage(hanim.getImage(), hb8.getCenterX() - 48,
					hb8.getCenterY() - 64);
		}
		if (hb9.getHealth()!=0){
			g.drawString(String.valueOf(hb9.health), hb9.getCenterX(), hb9.getCenterY()-48, paint);		
			g.drawImage(hanim.getImage(), hb9.getCenterX() - 48,
					hb9.getCenterY() - 64);
		}
		//ui elements
		if (state == GameState.Ready)
			drawReadyUI();
		if (state == GameState.Running)
			drawRunningUI();
		if (state == GameState.Paused)
			drawPausedUI();
		if (state == GameState.GameOver)
			drawGameOverUI();
		if (state == GameState.LevelIsPassed)		
			drawLevelIsPassedUI();

	}

	//show images
	private void paintTiles(Graphics g) {
		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			if (t.type != 0) {
				g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY());
			}
		}
	}

	//make the animations
	public void animate() {
		anim.update(10);
		hanim.update(50);
		banim.update(100);
	}

	// 	set all variables to be null and recreate them in the constructor
	//	it used when we quit from the level to free up memory
	private void nullify() {

		paint = null;
		bg1 = null;
		bg2 = null;
		mouse = null;
		
		hb = null;
		hb2 = null;
		hb3 = null;
		hb4 = null;
		hb5 = null;
		hb6 = null;
		hb7 = null;
		hb8 = null;
		hb9 = null;
	
		ch = null;
		ch2 = null;
		ch3 = null;
		ch4 = null;
		ch5 = null;
		ch6 = null;
		ch7 = null;
		ch8 = null;

		
		currentSprite = null;
		character = null;
		character2 = null;
		character3 = null;
		Kamikazi = null;
		Kamikazi2 = null;
		Kamikazi3 = null;
		Kamikazi4 = null;
		Kamikazi5 = null;
		anim = null;
		hanim = null;
		banim = null;

		//call the garbage collector, we need to free up the memory
		System.gc();

	}

	//show read to play screen
	private void drawReadyUI() {
		Graphics g = game.getGraphics();

		g.drawARGB(155, 0, 0, 0);
		g.drawString("Touch to Start !", 400, 240, paint);

	}

	//show some important information while in running state
	private void drawRunningUI() {
		Graphics g = game.getGraphics();
		
		
		//draw health of mob1
		g.drawString("Health: "+String.valueOf(mouse.getHealth()), 704, 64, paint);	
		g.drawString("Killed: "+String.valueOf(mouse.getKamikaziKilled()), 704, 96, paint);	
		g.drawString("Cheeses: "+String.valueOf(mouse.getCollectedCheeses()), 704, 128, paint);	
		
		//info at the screen
		g.drawString("Level: "+String.valueOf(Settings.currentLevel), 196, 64, paint);
		
		g.drawImage(Assets.pause_button, 16, 16, 0, 0, 96, 96);
		
		if (!Settings.gyroscopeEnabled){//if gyroscope is on, we dont need the control arrows
		g.drawImage(Assets.arrow_buttons,  16, 368, 0, 0, 96, 96);
		g.drawImage(Assets.arrow_buttons,  112, 368, 0, 96, 96, 96);
		}
		
		g.drawImage(Assets.control_buttons,  592, 368, 0, 0, 96, 96);	
		g.drawImage(Assets.control_buttons,  688, 368, 0, 96, 96, 96);	
	}

	//show paused screen
	private void drawPausedUI() {
		Graphics g = game.getGraphics();
		//make more dark the screen
		g.drawARGB(155, 0, 0, 0);
		g.drawImage(Assets.menu_buttons,  224, 128, 0, 128, 352, 256);//play button (show the 2/3 of image)


	}
	int gameOverCounter=0;
	//show game over user interface/screen
	private void drawGameOverUI() {
		Graphics g = game.getGraphics();
		g.drawRect(0, 0, 1281, 801, Color.BLACK);
		
		if ((Settings.soundEnabled) && (gameOverCounter==0))
		{
			Assets.gameover.play(1);
			gameOverCounter++;
		}
		g.drawString("[Game Over]", 400, 240, paint2);
		g.drawString("Touch to return to game", 400, 290, paint);

	}
	
	//show game is passed user interface/screen
	int levelPassedCounter=0;
	private void drawLevelIsPassedUI() {
		Graphics g = game.getGraphics();
		g.drawRect(0, 0, 1281, 801, Color.BLACK);
		
		if ((Settings.soundEnabled) && (levelPassedCounter==0))
		{
			Assets.passed.play(1);
			levelPassedCounter++;
		}
		g.drawString("Level "+Settings.currentLevel+" Passed!", 400, 240, paint2);
		g.drawString("Touch to select next Level", 400, 290, paint);

	}
	@Override
	public void pause() {
		if (state == GameState.Running)
			state = GameState.Paused;

	}

	@Override
	public void resume() {
		if (state == GameState.Paused)
			state = GameState.Running;
	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {
		pause();
	}

	private void goToMenu() {
		// TODO Auto-generated method stub
		game.setScreen(new MainMenuScreen(game));

	}

	public static Background getBg1() {
		// TODO Auto-generated method stub
		return bg1;
	}

	public static Background getBg2() {
		// TODO Auto-generated method stub
		return bg2;
	}

	public static Mouse getMouse() {
		// TODO Auto-generated method stub
		return mouse;
	}
	
}