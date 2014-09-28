//Name: 	Tile.java
//Purpose:	create a 2-dimensional array with values that from text files.
//			based on those values we will present specific images to the screen
//			the text files and therefore the 2-dimensional array represents the level environment

package com.hungry.mouse.main;

import com.hungry.mouse.framework.Image;

//android libraries stored in SDK platform
import android.graphics.Rect;//hold 4 integer coordinates for rectangle

public class Tile {

	private int tileX, tileY, speedX;
	public int type;
	public Image tileImage;

	private Mouse Mouse = GameScreen.getMouse();
	private Background bg = GameScreen.getBg1();

	private Rect r;

	//initialize tiles
	public Tile(int x, int y, int typeInt) {
		tileX = x * 40;
		tileY = y * 40;

		type = typeInt;

		r = new Rect();

		//load suitable image based on numerical value
		if (type == 5) {
			tileImage = Assets.tiledirt;
		} else if (type == 8) {
			tileImage = Assets.tilegrassTop;
		} else if (type == 4) {
			tileImage = Assets.tilegrassLeft;

		} else if (type == 6) {
			tileImage = Assets.tilegrassRight;

		} else if (type == 2) {
			tileImage = Assets.tilegrassBot;
		} else {
			type = 0;
		}

	}

	//update and check for collisions
	public void update() {
		speedX = bg.getSpeedX() * 5;
		tileX += speedX;
		r.set(tileX, tileY, tileX+40, tileY+40);
			
		if (Rect.intersects(r, Mouse.yellowRed) && type != 0) {
			checkVerticalCollision(Mouse.rect, Mouse.rect2);
			checkSideCollision(Mouse.rect3, Mouse.rect4, Mouse.footleft, Mouse.footright);
		}
	
	}

	//check the vertical collision with the environment
	public void checkVerticalCollision(Rect rtop, Rect rbot) {
		if (Rect.intersects(rtop, r)) {
		}

		if (Rect.intersects(rbot, r) && type == 8) {
			Mouse.setJumped(false);
			Mouse.setSpeedY(0);
			Mouse.setCenterY(tileY - 63);
		}
	}

	//check the side collision with the environment
	public void checkSideCollision(Rect rleft, Rect rright, Rect leftfoot, Rect rightfoot) {
		if (type != 5 && type != 2 && type != 0){
			if (Rect.intersects(rleft, r)) {
				Mouse.setCenterX(tileX + 102);
				Mouse.setSpeedX(0);
				}else if (Rect.intersects(leftfoot, r)) {
					Mouse.setCenterX(tileX + 85);
					Mouse.setSpeedX(0);
				}
				
			if (Rect.intersects(rright, r)) {
				Mouse.setCenterX(tileX - 62);
				Mouse.setSpeedX(0);
			}
			else if (Rect.intersects(rightfoot, r)) {
				Mouse.setCenterX(tileX - 45);
				Mouse.setSpeedX(0);
			}
		}
	}
		
	//getters//
	public int getTileX() {
		return tileX;
	}


	public int getTileY() {
		return tileY;
	}
	public Image getTileImage() {
		return tileImage;
	}
	//setters//
	public void setTileX(int tileX) {
		this.tileX = tileX;
	}
	
	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public void setTileImage(Image tileImage) {
		this.tileImage = tileImage;
	}

}