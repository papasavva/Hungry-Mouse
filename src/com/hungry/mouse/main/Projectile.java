//Name: 	Projectile.java
//Purpose:	the functions of the bullets

package com.hungry.mouse.main;

//android libraries stored in SDK platform
import android.graphics.Rect;//hold 4 integer coordinates for rectangle

public class Projectile {

	//local variables
	private int x, y, speedX;//coordinates and speed of bullet
	private boolean visible;//until bullet hit or move out of screen is visible
	
	private Rect r;
	
	//constructor
	public Projectile(int startX, int startY){
		x = startX;
		y = startY;
		speedX = 7;
		visible = true;
		
		r = new Rect(0, 0, 0, 0);
	}
	
	//change position of bullet and check for collision
	public void update(){
		x += speedX;
		r.set(x, y, x+10, y+5);
		
		//check bullet if there is inside of the screen
		if (x > 800){
			visible = false;
			r = null;
		}
		//so if there is inside of the screen, we can check for collision
		if (visible){
			checkCollision();
			
		}
		
	}

	//check for collision of enemies and the bullets
	private void checkCollision() {
		
		//check if the bullet hit and enemy and subtract from health
		
		//enemy 1
		if (Rect.intersects(r, GameScreen.hb.r)){
			visible = false;

			if (GameScreen.hb.getHealth() > 0) {
				GameScreen.hb.subtractHealth(1);
			}
			if (GameScreen.hb.getHealth() == 0) {
				GameScreen.hb.setCenterX(-100);
				GameScreen.hb.checkKillsByBullets();
			}

		}
		
		//enemy2
		if (Rect.intersects(r, GameScreen.hb2.r)){
			visible = false;

			if (GameScreen.hb2.getHealth() > 0) {
				GameScreen.hb2.subtractHealth(1);
			}
			if (GameScreen.hb2.getHealth() == 0) {
				GameScreen.hb2.setCenterX(-100);
				GameScreen.hb2.checkKillsByBullets();

			}
		}
		
		//enemy3
		if (Rect.intersects(r, GameScreen.hb3.r)){
			visible = false;

			if (GameScreen.hb3.getHealth() > 0) {
				GameScreen.hb3.subtractHealth(1);
			}
			if (GameScreen.hb3.getHealth() == 0) {
				GameScreen.hb3.setCenterX(-100);
				GameScreen.hb3.checkKillsByBullets();

			}
		}
		//enemy4
		if (Rect.intersects(r, GameScreen.hb4.r)){
			visible = false;

			if (GameScreen.hb4.getHealth() > 0) {
				GameScreen.hb4.subtractHealth(1);
			}
			if (GameScreen.hb4.getHealth() == 0) {
				GameScreen.hb4.setCenterX(-100);
				GameScreen.hb4.checkKillsByBullets();

			}
		}
		//enemy5
		if (Rect.intersects(r, GameScreen.hb5.r)){
			visible = false;

			if (GameScreen.hb5.getHealth() > 0) {
				GameScreen.hb5.subtractHealth(1);
			}
			if (GameScreen.hb5.getHealth() == 0) {
				GameScreen.hb5.setCenterX(-100);
				GameScreen.hb5.checkKillsByBullets();

			}
		}
		//enemy6
		if (Rect.intersects(r, GameScreen.hb6.r)){
			visible = false;

			if (GameScreen.hb6.getHealth() > 0) {
				GameScreen.hb6.subtractHealth(1);
			}
			if (GameScreen.hb6.getHealth() == 0) {
				GameScreen.hb6.setCenterX(-100);
				GameScreen.hb6.checkKillsByBullets();

			}
		}
		//enemy7
		if (Rect.intersects(r, GameScreen.hb7.r)){
			visible = false;

			if (GameScreen.hb7.getHealth() > 0) {
				GameScreen.hb7.subtractHealth(1);
			}
			if (GameScreen.hb7.getHealth() == 0) {
				GameScreen.hb7.setCenterX(-100);
				GameScreen.hb7.checkKillsByBullets();

			}
		}
		//enemy8
		if (Rect.intersects(r, GameScreen.hb8.r)){
			visible = false;

			if (GameScreen.hb8.getHealth() > 0) {
				GameScreen.hb8.subtractHealth(1);
			}
			if (GameScreen.hb8.getHealth() == 0) {
				GameScreen.hb8.setCenterX(-100);
				GameScreen.hb8.checkKillsByBullets();

			}
		}
		//enemy9
		if (Rect.intersects(r, GameScreen.hb9.r)){
			visible = false;

			if (GameScreen.hb9.getHealth() > 0) {
				GameScreen.hb9.subtractHealth(1);
			}
			if (GameScreen.hb9.getHealth() == 0) {
				GameScreen.hb9.setCenterX(-100);
				GameScreen.hb9.checkKillsByBullets();

			}
		}
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	//getters//
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSpeedX() {
		return speedX;
	}



	//setters//
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	
}
