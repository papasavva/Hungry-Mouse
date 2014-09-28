//Name: 	Music.java
//Purpose:	provide the interface to control audio files

package com.hungry.mouse.framework;

public interface Music {
	
    public void play();
    public void stop();
    public void pause();

    public boolean isPlaying();
    public boolean isStopped();
    public boolean isLooping();

    //setters//
    public void setLooping(boolean looping);
    public void setVolume(float volume);
    
    //close system resource when is no more needed
    public void dispose();

	void seekBegin();
}
