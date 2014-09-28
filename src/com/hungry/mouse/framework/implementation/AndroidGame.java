//Name: 	AndroidGame.java
//Purpose:	the most important class that holds everything together

package com.hungry.mouse.framework.implementation;
import com.hungry.mouse.framework.Audio;
import com.hungry.mouse.framework.FileIO;
import com.hungry.mouse.framework.Game;
import com.hungry.mouse.framework.Graphics;
import com.hungry.mouse.framework.Input;
import com.hungry.mouse.framework.Screen;

//android libraries stored in SDK platform
import android.app.Activity;//important part of an application's overall lifecycle
import android.content.Context;// allows access to application-specific resources and classes
import android.content.res.Configuration;//all device configuration information
import android.graphics.Bitmap;//make use of bitmap operations
import android.graphics.Bitmap.Config;//describes how pixels are stored
import android.os.Bundle;//mapping from String values to various Parcelable types
import android.os.PowerManager;//control the power state of device
import android.os.PowerManager.WakeLock;//indicate that application needs to have the device stay on. Request in manifest
import android.view.Window;//window look and behavior policy
import android.view.WindowManager;//interface that apps use to talk to the window manager



public abstract class AndroidGame extends Activity implements Game {
    AndroidFastRenderView renderView;
    Graphics graphics;
    Audio audio;
    Input input;
    FileIO fileIO;
    Screen screen;
    WakeLock wakeLock;

    //
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        boolean isPortrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
        
        //check current orientation of device and set width and height dimensions
        int frameBufferWidth = isPortrait ? 480: 800;
        int frameBufferHeight = isPortrait ? 800: 480;
        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,
                frameBufferHeight, Config.RGB_565);//rgb565 no waste to much memory, drawing is faster
        
        //adjust everything to device`s aspect ratio
        float scaleX = (float) frameBufferWidth
                / getWindowManager().getDefaultDisplay().getWidth();
        float scaleY = (float) frameBufferHeight
                / getWindowManager().getDefaultDisplay().getHeight();
        
        //instantiate the helper classes that will run in the background
        renderView = new AndroidFastRenderView(this, frameBuffer);
        graphics = new AndroidGraphics(getAssets(), frameBuffer);
        fileIO = new AndroidFileIO(this);
        audio = new AndroidAudio(this);
        input = new AndroidInput(this, renderView, scaleX, scaleY);
        screen = getInitScreen();
        setContentView(renderView);
        
        //keep screen from turn off, to save battery
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "MyGame");
    }

    @Override
    public void onResume() {
  
        super.onResume();
        
        //current screen is informed that the game/activity resumed
        wakeLock.acquire();
        screen.resume();
        
        //resume the rendering thread
        renderView.resume();
    }

    @Override
    public void onPause() {
    	
        super.onPause();
        
        //release the wakelock
        wakeLock.release();
        
        //rendering thread is terminated
        renderView.pause();
        
        //after the rendering thread is terminated,is allowed to pause screen
        screen.pause();
        
        //clean what is is necessary
        if (isFinishing())
            screen.dispose();
    }
    
    //the following 4 methods just return the respective instances to the caller
    @Override
    public Input getInput() {
        return input;
    }

    @Override
    public FileIO getFileIO() {
        return fileIO;
    }

    @Override
    public Graphics getGraphics() {
        return graphics;
    }

    @Override
    public Audio getAudio() {
        return audio;
    }

    //setter//
    @Override
    public void setScreen(Screen screen) {
    	
    	//do null checking, null-screen is not allowed
        if (screen == null)
            throw new IllegalArgumentException("Screen must not be null");
        
        //pause the current screen and dispose to make space for the new screen
        this.screen.pause();
        this.screen.dispose();
        
        //resume screen and update when the delta time is ZERO
        screen.resume();
        screen.update(0);
        
        //set screen member to the new screen
        this.screen = screen;
    }
    //getter//
    
    //simply return the current active screen
    public Screen getCurrentScreen() {

    	return screen;
    }
}