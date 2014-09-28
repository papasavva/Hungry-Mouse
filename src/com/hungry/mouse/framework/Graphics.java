//Name: Graphics.java
//Purpose:	contains many methods that will be used to draw 
//			text and images to the screen.

package com.hungry.mouse.framework;

//android libraries stored in SDK platform
import android.graphics.Paint;//hold style and color information to draw

public interface Graphics {
	public static enum ImageFormat {
		ARGB8888, ARGB4444, RGB565
	}

	//used to load assets
	public Image newImage(String fileName, ImageFormat format);

	//clear complete framebuffer with given color
	public void clearScreen(int color);

	//draw an a line from a start point to end point with given color
	public void drawLine(int x, int y, int x2, int y2, int color);

	//draw an a rectangle from a start point to end point with given color
	public void drawRect(int x, int y, int width, int height, int color);

	//load an image with jpeg/png format that is located to assets
	//it used to draw a part of an image
	public void drawImage(Image image, int x, int y, int srcX, int srcY,
			int srcWidth, int srcHeight);
	
	//load an image with jpeg/png format that is located to assets
	//it used to draw the whole image
	public void drawImage(Image Image, int x, int y);

	
	//draw a string at given coordinates 
	void drawString(String text, int x, int y, Paint paint);

	//getters//
	//return image width
	public int getWidth();

	//return image height
	public int getHeight();
	
	//draw based on colors Red, Green & Blue.A for alpha 
	public void drawARGB(int i, int j, int k, int l);
}
