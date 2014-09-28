//Name: 	Enemy.java
//Purpose:	all the attributes and functions of the enemy like health, death, collision and more

package com.hungry.mouse.main;

//android libraries stored in SDK platform
import android.graphics.Rect;//hold 4 integer coordinates for rectangle

public class Enemy {

	private int power;
	protected int centerX;
	protected int speedX;
	protected int centerY;
	protected Background bg = GameScreen.getBg1();
	private Mouse Mouse = GameScreen.getMouse();

	public Rect r = new Rect(0, 0, 0, 0);
	protected int health;//total health of enemy
	public boolean alive=true;
	
	protected int movementSpeed;//speed of enemy

	//Behavioral Method, update coordinates and intersects
	public void update() {
		follow();
		centerX += speedX;
		speedX = bg.getSpeedX() * 5 + movementSpeed;
		r.set(centerX - 25, centerY - 25, centerX + 25, centerY + 35);

		if (Rect.intersects(r, Mouse.yellowRed)) {
			checkCollision();
		}

	}
	
	//check if the enemy is killed by the bullets and update score
	public void checkKillsByBullets(){
		if ((this.health == 0) && (this.alive=true))
		{
			Mouse.addKamikaziKilled(1);
			alive=false;
			if (Settings.soundEnabled)
			{
				Assets.collision.play(1);
			}
		}
	}
	
	//check if mouse and enemy had collision
	protected void checkCollision() {
		if (Rect.intersects(r, Mouse.rect)|| Rect.intersects(r, Mouse.rect2) || Rect.intersects(r, Mouse.rect3) || Rect.intersects(r, Mouse.rect4)) {
			die();
		}
	}

	//the enemy follow the mouse
	public void follow() {
		
		if (centerX < -95 || centerX > 810){
			movementSpeed = 0;
		}
		else if (Math.abs(Mouse.getCenterX() - centerX) < 5) {
			movementSpeed = 0;
		}
		else {
			if (Mouse.getCenterX() >= centerX) {
				movementSpeed = 1;
			} else {
				movementSpeed = -1;
			}
		}

	}

	//enemy is die
	public void die() {
		this.setHealth(0);
		Mouse.subtractHealth(10);
		this.setCenterX(-100);
		
		if (Settings.soundEnabled)
		{
			Assets.collision.play(1);
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
	public int getPower() {
		return power;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}
	
	public int getHealth() {
		return health;
	}

	
	//setters//
	public void setHealth(int health) {
		this.health = health;
	}
	public void setPower(int power) {
		this.power = power;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}
	
	public void subtractHealth(int health){
		this.health-=health;
	}
}