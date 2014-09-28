//Name: 	Kamikazi.java
//Purpose:	the bomb enemy with the coordinates

package com.hungry.mouse.main;

//android libraries stored in SDK platform
import android.graphics.Rect;//hold 4 integer coordinates for rectangle

public class Bomb extends Enemy {

	//constructor
	public Bomb(int centerX, int centerY) {

		setCenterX(centerX);//x axis coordinates
		setCenterY(centerY);//y axis coordinates

	}
	
	@Override
	public void update() {
		centerX += speedX;
		speedX = bg.getSpeedX() * 5 + movementSpeed;
		r.set(centerX - 25, centerY - 25, centerX + 25, centerY + 35);

		if (Rect.intersects(r, Mouse.yellowRed)) {
			checkCollision();
		}

	}
	public void subtractHealth(int health){
		this.health-=health;
	}

}
