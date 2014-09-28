//Name: 	Audio.java
//Purpose:	create music and sound instances

package com.hungry.mouse.framework;

public interface Audio {
	
	//music represents streamed audio file
    public Music createMusic(String file);

    //sound represents short sound effects
    public Sound createSound(String file);
}
