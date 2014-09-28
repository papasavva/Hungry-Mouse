//Name: 	AndroidInput.java
//Purpose:	get input of user

package com.hungry.mouse.framework.implementation;
import com.hungry.mouse.framework.Input;

//java libraries
import java.util.List;//a collection which maintain an ordering for its elements

//android libraries stored in SDK platform
import android.content.Context;//interface to global information for app environment
import android.os.Build.VERSION;//various version strings
import android.view.View;//represents basic building block for user interface components



public class AndroidInput implements Input { 
	AccelerometerHandler accelHandler;
    TouchHandler touchHandler;


    //constructor
    public AndroidInput(Context context, View view, float scaleX, float scaleY) {
    	accelHandler = new AccelerometerHandler(context);//for accelerometer
        //in android 2.0 (API 5), multi-touch was introduced
        //check the api and implement the appropriate touch handler
        if(Integer.parseInt(VERSION.SDK) < 5) 
            touchHandler = new SingleTouchHandler(view, scaleX, scaleY);
        else
            touchHandler = new MultiTouchHandler(view, scaleX, scaleY);        
    }

    
    @Override
    public boolean isTouchDown(int pointer) {
        return touchHandler.isTouchDown(pointer);
    }

    @Override
    public int getTouchX(int pointer) {
        return touchHandler.getTouchX(pointer);
    }

    @Override
    public int getTouchY(int pointer) {
        return touchHandler.getTouchY(pointer);
    }

    
    //accelerometer

    @Override
    public float getAccelX() {
        return accelHandler.getAccelX();
    }

    @Override
    public float getAccelY() {
        return accelHandler.getAccelY();
    }

    @Override
    public float getAccelZ() {
        return accelHandler.getAccelZ();
    }
    
    
    @Override
    public List<TouchEvent> getTouchEvents() {
        return touchHandler.getTouchEvents();
    }
    
}
