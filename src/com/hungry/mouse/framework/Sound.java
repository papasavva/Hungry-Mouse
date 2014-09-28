//Name: 	Sound.java
//Purpose:	provide the interface to play and release sounds

package com.hungry.mouse.framework;

public interface Sound {
	
    public void play(float volume);

    //close system resource when is no more needed
    public void dispose();
}
