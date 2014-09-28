//Name: 	Background.java
//Purpose:	update and scroll the background image when player moves

package com.hungry.mouse.main;

public class Background {
	
	//local variables
	private int bgX, bgY, speedX;
	
	//constructor
	public Background(int x, int y){
		bgX = x;
		bgY = y;
		speedX = 0;//at the beggining the background must not move
	}
	
	//update and move the background when palyer moves
	public void update() {
		bgX += speedX;

		//based on image of dimension 2160x480
		if (bgX <= -2160){
			bgX += 4320;
		}
	}

	//getters//
	public int getBgX() {
		return bgX;
	}

	public int getBgY() {
		return bgY;
	}

	public int getSpeedX() {
		return speedX;
	}

	//setters//
	public void setBgX(int bgX) {
		this.bgX = bgX;
	}

	public void setBgY(int bgY) {
		this.bgY = bgY;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

}
