//Name: 	TouchHandler.java
//Purpose:	in order to use the two handler classes interchangeably we use this common interface

package com.hungry.mouse.framework.implementation;

import com.hungry.mouse.framework.Input.TouchEvent;

//java libraries
import java.util.List;//a collection which maintain an ordering for its elements

//android libraries stored in SDK platform
import android.view.View.OnTouchListener;//callback definition to be invoked from a touch event

public interface TouchHandler extends OnTouchListener {
	
	//check for touch
    public boolean isTouchDown(int pointer);
    
    //get x axis coordinates
    public int getTouchX(int pointer);
    
    //get y axis coordinates
    public int getTouchY(int pointer);
    
    //get the touch event
    public List<TouchEvent> getTouchEvents();
}
