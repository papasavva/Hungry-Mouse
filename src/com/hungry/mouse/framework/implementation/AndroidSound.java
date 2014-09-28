//Name: 	AndroidSound.java
//Purpose:	provide the interface to play and release sounds

package com.hungry.mouse.framework.implementation;

import com.hungry.mouse.framework.Sound;

//android libraries stored in SDK platform
import android.media.SoundPool;//manages and plays audio resources

public class AndroidSound implements Sound {
    int soundId;
    SoundPool soundPool;

    //constructor
    public AndroidSound(SoundPool soundPool, int soundId) {
        this.soundId = soundId;
        this.soundPool = soundPool;
    }

    @Override
    public void play(float volume) {
        soundPool.play(soundId, volume, volume, 0, 0, 1);
    }
    
    //close system resource when is no more needed
    @Override
    public void dispose() {
        soundPool.unload(soundId);
    }

}
