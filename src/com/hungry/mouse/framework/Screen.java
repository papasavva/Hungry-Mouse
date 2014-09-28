//Name: 	Screen.java [abstract]
//Purpose:	create and keep track of low level methods

package com.hungry.mouse.framework;

public abstract class Screen {
    protected final Game game;

    //constructor receives game instance and store it in a
    //final member that is accessible to all subclasses
    public Screen(Game game) {
        this.game = game;
    }

    //deltatime takes into account how much time
    //it passed since the last time method was called
    public abstract void update(float deltaTime);
    public abstract void paint(float deltaTime);

    public abstract void pause();
    public abstract void resume();
    public abstract void dispose();
	public abstract void backButton();
}
