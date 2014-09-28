//Name: 	Kamikazi.java
//Purpose:	the kamikazi enemy with the coordinates

package com.hungry.mouse.main;

public class Kamikazi extends Enemy {

	//constructor
	public Kamikazi(int centerX, int centerY) {

		setCenterX(centerX);//x axis coordinates
		setCenterY(centerY);//y axis coordinates

	}
	public void subtractHealth(int health){
		this.health-=health;
	}
}
