//Name: 	AndroidMusic.java
//Purpose:	provide the interface to control music files

package com.hungry.mouse.framework.implementation;
import com.hungry.mouse.framework.Music;
import com.hungry.mouse.main.Settings;

//java libraries
import java.io.IOException;//signals a general i-o related error

//android libraries stored in SDK platform
import android.content.res.AssetFileDescriptor;//file descriptor of an entry in the assetmanager
import android.media.MediaPlayer;//control playback of audio/video files and streams
import android.media.MediaPlayer.OnCompletionListener;//callback to be invoked when playback is completed
import android.media.MediaPlayer.OnPreparedListener;//callback to be invoked when media is ready for playback
import android.media.MediaPlayer.OnSeekCompleteListener;//callback to be invoked when seek is completed
import android.media.MediaPlayer.OnVideoSizeChangedListener;//callback to be invoked when video size is known or updated


public class AndroidMusic implements Music, OnCompletionListener, OnSeekCompleteListener, OnPreparedListener, OnVideoSizeChangedListener {
    MediaPlayer mediaPlayer;
    
    //variable is needed so call mediaplayer start,stop, pause when is prepared
    boolean isPrepared = false;

    //constructor
    public AndroidMusic(AssetFileDescriptor assetDescriptor) {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(assetDescriptor.getFileDescriptor(),
                    assetDescriptor.getStartOffset(),
                    assetDescriptor.getLength());
            
            //first prepate mediaplayer and after set prepare flag to true
            mediaPlayer.prepare();
            isPrepared = true;
            
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnSeekCompleteListener(this);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnVideoSizeChangedListener(this);
            
        } catch (Exception e) {
            throw new RuntimeException("Couldn't load music");
        }
    }

    //check if mediaplayer is still playing and stop it, else throw runtime exception
    @Override
    public void dispose() {
    
    	 if (this.mediaPlayer.isPlaying()){
    	       this.mediaPlayer.stop();
    	        }
        this.mediaPlayer.release();
    }

    @Override
    public boolean isLooping() {
        return mediaPlayer.isLooping();
    }

    @Override
    public boolean isPlaying() {
        return this.mediaPlayer.isPlaying();
    }
    
    //indicate that mediaplayer stopped
    @Override
    public boolean isStopped() {
        return !isPrepared;
    }

    @Override
    public void pause() {
        if (this.mediaPlayer.isPlaying())
            mediaPlayer.pause();
    }

    
    
    @Override
    public void play() {

        if (this.mediaPlayer.isPlaying())
            return;
        
        //check if mediaplayer is prepared, else prepare it
        try {
            synchronized (this) {
                if (!isPrepared)
                    mediaPlayer.prepare();
                mediaPlayer.start();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setLooping(boolean isLooping) {
        mediaPlayer.setLooping(isLooping);
    }

    @Override
    public void setVolume(float volume) {
        mediaPlayer.setVolume(volume, volume);
    }

    @Override
    public void stop() {
    	 if (this.mediaPlayer.isPlaying() == true){
        this.mediaPlayer.stop();
        
       synchronized (this) {
           isPrepared = false;
        }
       }
    	 
    }

    @Override
    public void onCompletion(MediaPlayer player) {
        synchronized (this) {
            isPrepared = false;
        }
    }

	@Override
	public void seekBegin() {
		mediaPlayer.seekTo(0);
		
	}


	@Override
	public void onPrepared(MediaPlayer player) {
		// TODO Auto-generated method stub
		 synchronized (this) {
	           isPrepared = true;
	        }
		
	}

	@Override
	public void onSeekComplete(MediaPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onVideoSizeChanged(MediaPlayer player, int width, int height) {
		// TODO Auto-generated method stub
		
	}
}
