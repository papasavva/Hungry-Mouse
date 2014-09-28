//Name: 	AndroidAudio.java
//Purpose:	responsible to create sound and music instances from asset files

package com.hungry.mouse.framework.implementation;

import com.hungry.mouse.framework.Audio;
import com.hungry.mouse.framework.Music;
import com.hungry.mouse.framework.Sound;

//java libraries
import java.io.IOException;//signal that i/o exception has occurred

//android libraries stored in SDK platform
import android.app.Activity;//activity create a window to place the ui
import android.content.res.AssetFileDescriptor;//file descriptor of an entry in the assetmanager
import android.content.res.AssetManager;//provides access to asset files
import android.media.AudioManager;//provides access to volume nad ringer mode control
import android.media.SoundPool;//manages and plays audio resources



public class AndroidAudio implements Audio {
    AssetManager assets;
    SoundPool soundPool;

    public AndroidAudio(Activity activity) {
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assets = activity.getAssets();
        this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
    }

    @Override
    public Music createMusic(String filename) {
        try {
            AssetFileDescriptor assetDescriptor = assets.openFd(filename);
            return new AndroidMusic(assetDescriptor);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load music '" + filename + "'");
        }
    }

    @Override
    public Sound createSound(String filename) {
        try {
            AssetFileDescriptor assetDescriptor = assets.openFd(filename);
            int soundId = soundPool.load(assetDescriptor, 0);
            return new AndroidSound(soundPool, soundId);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load sound '" + filename + "'");
        }
    }
}
