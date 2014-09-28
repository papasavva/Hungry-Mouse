//Name: 	Animation.java
//Purpose:	display different images in sequence based on the duration specified

package com.hungry.mouse.main;

import com.hungry.mouse.framework.Image;

//java libraries
import java.util.ArrayList;//implementation of list with additional operations add,remove,replace

public class Animation {

	//local variables
	private ArrayList frames;//contain animframe objects
	private int currentFrame;//numerical location in the list
	//long is more accurate than int
	private long animTime;//keep track of time elapsed since last image
	private long totalDuration;//how long each image will be displayed

	//constructor
	public Animation() {
		frames = new ArrayList();
		totalDuration = 0;
		
		//threads are called together
		synchronized (this) {
			animTime = 0;
			currentFrame = 0;
		}
	}

	//add animframe object to animation list
	//threads are called together
	public synchronized void addFrame(Image image, long duration) {
		totalDuration += duration;
		frames.add(new AnimFrame(image, totalDuration));
	}

	//update current frame with its appropriate image
	public synchronized void update(long elapsedTime) {
		if (frames.size() > 1) {
			
			//add elapsed time to anime time
			animTime += elapsedTime;
			
			//if animation time exceeds total duration we change frame
			if (animTime >= totalDuration) {
				animTime = animTime % totalDuration;
				currentFrame = 0;

			}

			while (animTime > getFrame(currentFrame).endTime) {
				currentFrame++;

			}
		}
	}
	
	//get current frame`s image to paint(present) it
	//threads are called together	
	public synchronized Image getImage() {
		if (frames.size() == 0) {
			return null;
		} else {
			return getFrame(currentFrame).image;
		}
	}

	//get the frame`s location from the list
	private AnimFrame getFrame(int i) {
		return (AnimFrame) frames.get(i);
	}

	//create objects to contain current images and duration to be displayed
	private class AnimFrame {

		Image image;
		long endTime;
		
		//nested class
		public AnimFrame(Image image, long endTime) {
			this.image = image;
			this.endTime = endTime;//the end time to be displayed
		}
	}
}
