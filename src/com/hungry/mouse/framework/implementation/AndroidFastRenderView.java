//Name: 	AndroidFastRenderView.java
//Purpose:	the most important class that holds everything together and render images

package com.hungry.mouse.framework.implementation;

//android libraries stored in SDK platform
import android.graphics.Bitmap;//make use of bitmap operations
import android.graphics.Canvas;//hold the draw calls
import android.graphics.Rect;//hold 4 integer coordinates for rectangle
import android.view.SurfaceHolder;//control the surface/format
import android.view.SurfaceView;//provide dedicated drawing surface

public class AndroidFastRenderView extends SurfaceView implements Runnable {
    AndroidGame game;
    Bitmap framebuffer;
    Thread renderThread = null;
    SurfaceHolder holder;
    volatile boolean running = false;
    
    public AndroidFastRenderView(AndroidGame game, Bitmap framebuffer) {
        super(game);
        this.game = game;
        this.framebuffer = framebuffer;
        this.holder = getHolder();

    }

    public void resume() { 
        running = true;
        renderThread = new Thread(this);
        renderThread.start();   

    }      
    
    public void run() {
        Rect dstRect = new Rect();
        long startTime = System.nanoTime();
        
        //keep track of deltatime between each frame
        //prevent movement speed from depending on frame rate,
        //No matter what the frame rate is, objects will move the same amount
        //given the same time period.
        while(running) {  
            if(!holder.getSurface().isValid())
                continue;           
            
            float deltaTime = (System.nanoTime() - startTime) / 10000000.000f;
            startTime = System.nanoTime();
            
            if (deltaTime > 3.15){
            	deltaTime = (float) 3.15;
           }
     
            //update game logic and render things to framebuffer
            game.getCurrentScreen().update(deltaTime);
            game.getCurrentScreen().paint(deltaTime);
            
            //draw with the correct dimensions
            Canvas canvas = holder.lockCanvas();
            canvas.getClipBounds(dstRect);
            canvas.drawBitmap(framebuffer, null, dstRect, null);                           
            holder.unlockCanvasAndPost(canvas);
          }
    }

    public void pause() {                        
        running = false;                        
        while(true) {
            try {
                renderThread.join();
                break;
            } catch (InterruptedException e) {
                // retry
            }
            
        }
    }     
    
  
}