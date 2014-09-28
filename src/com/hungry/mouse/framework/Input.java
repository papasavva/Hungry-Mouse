//Name: Input.java
//Purpose:	give access to touchscreen, keyboard and gyroscope.
//			Also keep track of touch events

package com.hungry.mouse.framework;

//java libraries
import java.util.List;//a collection which maintain an ordering for its elements

public interface Input {
    
	//keep track of touch events
    public static class TouchEvent {
        public static final int TOUCH_DOWN = 0;
        public static final int TOUCH_UP = 1;
        public static final int TOUCH_DRAGGED = 2;
        public static final int TOUCH_HOLD = 3;

        public int type;
        public int x, y;
        public int pointer;
    }
    
    public boolean isTouchDown(int pointer);
    
    //getters//
    public int getTouchX(int pointer);
    public int getTouchY(int pointer);
    
    public float getAccelX();
    public float getAccelY();
    public float getAccelZ();

    public List<TouchEvent> getTouchEvents();
}
 