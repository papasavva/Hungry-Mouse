//Name: 	Sign.java
//Purpose:	the Sign reward with the coordinates, when player have collision with it,  the level is passed

package com.hungry.mouse.main;

//android libraries stored in SDK platform
import android.graphics.Rect;//hold 4 integer coordinates for rectangle

public class Sign extends Rewards {
	
	private Mouse Mouse = GameScreen.getMouse();
	
	//constructor
	public Sign(int centerX, int centerY) {

		setCenterX(centerX);//x axis coordinates
		setCenterY(centerY);//y axis coordinates

	}
	
	//check if mouse and reward had collision
	private void checkCollision() {
		if (Rect.intersects(r, Mouse.rect)|| Rect.intersects(r, Mouse.rect2) || Rect.intersects(r, Mouse.rect3) || Rect.intersects(r, Mouse.rect4)) {
			Mouse.setLevelPassed(1);
			Settings.theLevelPassed=1;
			this.collected=true;
			//this.setCenterX(-100);
		}
	}

}