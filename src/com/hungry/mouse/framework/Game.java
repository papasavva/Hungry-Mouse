//Name: 	Game.java
//Purpose:	create and keep track of low level methods

package com.hungry.mouse.framework;

public interface Game {

	//getters//
    public Audio getAudio();
    public Input getInput();
    public FileIO getFileIO();
    public Graphics getGraphics();
    public Screen getCurrentScreen();
    public Screen getInitScreen();
    
    //setters//
    public void setScreen(Screen screen);
    
}
