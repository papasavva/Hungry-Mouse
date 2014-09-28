//Name: 	Rewards.java
//Purpose:	all the attributes and functions of the rewards like collision and rewards collected

package com.hungry.mouse.main;

import java.util.ArrayList;
import android.graphics.Rect;//hold 4 integer coordinates for rectangle

public class Rewards {

	//local variables
	private int power, centerX, speedX, centerY;
	private int movementSpeed;
	
	private Background bg = GameScreen.getBg1();
	private Mouse Mouse = GameScreen.getMouse();

	public Rect r = new Rect(0, 0, 0, 0);
	public boolean collected=false;

	//Behavioral Method, update coordinates and intersects
	public void update() {

		centerX += speedX;
		speedX = bg.getSpeedX() * 5 + movementSpeed;
		r.set(centerX - 25, centerY - 25, centerX + 25, centerY + 35);

		if (Rect.intersects(r, Mouse.yellowRed)) {
			checkCollision();
		}
		

	}
	
	//check if mouse and reward had collision
	private void checkCollision() {
		if (Rect.intersects(r, Mouse.rect)|| Rect.intersects(r, Mouse.rect2) || Rect.intersects(r, Mouse.rect3) || Rect.intersects(r, Mouse.rect4)) {
			Mouse.addCollectedCheeses(1);
			this.collected=true;
			this.setCenterX(-100);
			
			if (Settings.soundEnabled)
				Assets.collect.play(1);
		}
	}
	
	//when background moves, the enemy must move to the same direction
	public Background getBg() {
		return bg;
	}
	public void setBg(Background bg) {
		this.bg = bg;
	}
	
	//getters//
	public boolean getColleceted(){
		return collected;
	}
	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}


	//setters//
	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}
}