//Name: 	Mouse.java
//Purpose:	the main character that player will control with attributes and its functions

package com.hungry.mouse.main;

//java libraries
import java.util.ArrayList;

//android libraries stored in SDK platform
import android.graphics.Rect;//hold 4 integer coordinates for rectangle

public class Mouse {

	//constant
	final int JUMPSPEED = -19;
	final int MOVESPEED = 5;

	//local variables
	private int centerX = 100;//start x axis coordinates
	private int centerY = 297;//start y axis coordinates
	private boolean jumped = false;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean ducked = false;//sit
	private boolean readyToFire = true;
	
	private int health=100;//health of mouse
	private int kamikaziKilled;//how many kamikazi the mouse killed
	private int collectedCheeses;//how many cheeses the mouse collected
	private int levelPassed;
	
	private int speedX = 0;//start speed of x axis
	private int speedY = 0;//start speed of y axis
	
	
	
	//rectangles for collision
	public static Rect rect = new Rect(0, 0, 0, 0);
	public static Rect rect2 = new Rect(0, 0, 0, 0);
	public static Rect rect3 = new Rect(0, 0, 0, 0);
	public static Rect rect4 = new Rect(0, 0, 0, 0);
	public static Rect yellowRed = new Rect(0, 0, 0, 0);
	
	public static Rect footleft = new Rect(0,0,0,0);
	public static Rect footright = new Rect(0,0,0,0);
	
	
	private Background bg1 = GameScreen.getBg1();
	private Background bg2 = GameScreen.getBg2();

	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	//update position and speed of mouse
	public void update() {
		
		// move mouse and scroll background
		if (speedX < 0) {
			centerX += speedX;
		}
		if (speedX == 0 || speedX < 0) {
			if (bg1 != null)
			bg1.setSpeedX(0);
			if (bg2 != null)
			bg2.setSpeedX(0);

		}
		if (centerX <= 200 && speedX > 0) {
			centerX += speedX;
		}
		if (speedX > 0 && centerX > 200) {
			bg1.setSpeedX(-MOVESPEED / 5);
			bg2.setSpeedX(-MOVESPEED / 5);
		}

		//update y axis
		centerY += speedY;

		//handles jumping

			speedY += 1;

		//if the speed is bigger than 3, flag jumped
		if (speedY > 3){// jump height
			jumped = true;
		}

		// Prevents going beyond X coordinate of 0
		if (centerX + speedX <= 60) {
			centerX = 61;
		}

		rect.set(centerX - 34, centerY - 63, centerX + 34, centerY);
		rect2.set(rect.left, rect.top + 63, rect.left+68, rect.top + 128);
		rect3.set(rect.left - 26, rect.top+32, rect.left, rect.top+52);
		rect4.set(rect.left + 68, rect.top+32, rect.left+94, rect.top+52);
		yellowRed.set(centerX - 110, centerY - 110, centerX + 70, centerY + 70);
		footleft.set(centerX - 50, centerY + 20, centerX, centerY + 35);
		footright.set(centerX, centerY + 20, centerX+50, centerY+35);


	}

	//set horizontal speed of mouse
	public void moveRight() {
		if (ducked == false) {
			speedX = MOVESPEED;
		}
	}

	//set horizontal speed of mouse
	public void moveLeft() {
		if (ducked == false) {
			speedX = -MOVESPEED;
		}
	}
	
	//stop moving right
	public void stopRight() {
		setMovingRight(false);
		stop();
	}

	//stop moving left
	public void stopLeft() {
		setMovingLeft(false);
		stop();
	}

	//where to move check
	private void stop() {
		if (isMovingRight() == false && isMovingLeft() == false) {
			speedX = 0;
		}

		if (isMovingRight() == false && isMovingLeft() == true) {
			moveLeft();
		}

		if (isMovingRight() == true && isMovingLeft() == false) {
			moveRight();
		}

	}

	//set vertical speed of mouse
	public void jump() {
		if (jumped == false) {
			//if  sound settings are on
			if (Settings.soundEnabled)
			Assets.jump.play(1);
			speedY = JUMPSPEED;
			jumped = true;
		}

	}

	//create new bullet and add to the list
	public void shoot() {
		if (readyToFire) {
			//if  sound settings are on
			if (Settings.soundEnabled)
				Assets.fire.play(1);
			Projectile p = new Projectile(centerX, centerY+34);//make the bullets appear from the gun
			projectiles.add(p);
		}
	}
	
	public boolean isJumped() {
		return jumped;
	}
	
	public boolean isDucked() {
		return ducked;
	}

	public boolean isReadyToFire() {
		return readyToFire;
	}
	
	public void addCollectedCheeses(int collectedCheeses){
		this.collectedCheeses+=collectedCheeses;
	}
	
	public void addKamikaziKilled(int kamikaziKilled)
	{
		this.kamikaziKilled+=kamikaziKilled;
	}
	public void subtractHealth(int health){
		this.health-=health;
	}
	

	
	//getters//
	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}
	public int getHealth(){
		return health;
	}
	
	public int getKamikaziKilled()
	{
		return kamikaziKilled;
	}
	
	public int getCollectedCheeses()
	{
		return collectedCheeses;
	}
	
	public int  getLevelPassed()
	{
		return levelPassed;
	}
	//reference to the bullets
	public ArrayList getProjectiles() {
		return projectiles;
	}
	
	//setters//
	public void setDucked(boolean ducked) {
		this.ducked = ducked;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public void setReadyToFire(boolean readyToFire) {
		this.readyToFire = readyToFire;
	}
	
	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setJumped(boolean jumped) {
		this.jumped = jumped;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
	public void setLevelPassed(int levelPassed) {
		this.levelPassed = levelPassed;
	}

}
