//Name: 	MultiTouchHandler.java
//Purpose:	used to get multiple touches on the touch screen

package com.hungry.mouse.framework.implementation;

import com.hungry.mouse.framework.Pool;
import com.hungry.mouse.framework.Input.TouchEvent;
import com.hungry.mouse.framework.Pool.PoolObjectFactory;

//java libraries
import java.util.ArrayList;//implementation of list with additional operations add,remove,replace
import java.util.List;//a collection which maintain an ordering for its elements

//android libraries stored in SDK platform
import android.view.MotionEvent;//report movement events
import android.view.View;//represents basic building block for user interface components



public class MultiTouchHandler implements TouchHandler {
	private static final int MAX_TOUCHPOINTS = 10;
	
	boolean[] isTouched = new boolean[MAX_TOUCHPOINTS];
	int[] touchX = new int[MAX_TOUCHPOINTS];
	int[] touchY = new int[MAX_TOUCHPOINTS];
	int[] id = new int[MAX_TOUCHPOINTS];
	Pool<TouchEvent> touchEventPool;
	List<TouchEvent> touchEvents = new ArrayList<TouchEvent>();
	List<TouchEvent> touchEventsBuffer = new ArrayList<TouchEvent>();
	float scaleX;
	float scaleY;

	//constructor
	public MultiTouchHandler(View view, float scaleX, float scaleY) {
		PoolObjectFactory<TouchEvent> factory = new PoolObjectFactory<TouchEvent>() {
			@Override
			public TouchEvent createObject() {
				return new TouchEvent();
			}
		};
		touchEventPool = new Pool<TouchEvent>(factory, 100);
		view.setOnTouchListener(this);

		this.scaleX = scaleX;
		this.scaleY = scaleY;
	}

	//handle multiple touches
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		synchronized (this) {
			int action = event.getAction() & MotionEvent.ACTION_MASK;
			int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
			int pointerCount = event.getPointerCount();
			TouchEvent touchEvent;
			for (int i = 0; i < MAX_TOUCHPOINTS; i++) {
				if (i >= pointerCount) {
					isTouched[i] = false;
					id[i] = -1;
					continue;
				}
				int pointerId = event.getPointerId(i);
				if (event.getAction() != MotionEvent.ACTION_MOVE && i != pointerIndex) {
					// if it's an up/down/cancel/out event, mask the id to see if we should process it for this touch
					// point
					continue;
				}
				switch (action) {
				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_POINTER_DOWN:
					touchEvent = touchEventPool.newObject();
					touchEvent.type = TouchEvent.TOUCH_DOWN;
					touchEvent.pointer = pointerId;
					touchEvent.x = touchX[i] = (int) (event.getX(i) * scaleX);
					touchEvent.y = touchY[i] = (int) (event.getY(i) * scaleY);
					isTouched[i] = true;
					id[i] = pointerId;
					touchEventsBuffer.add(touchEvent);
					break;

				case MotionEvent.ACTION_UP:
				case MotionEvent.ACTION_POINTER_UP:
				case MotionEvent.ACTION_CANCEL:
					touchEvent = touchEventPool.newObject();
					touchEvent.type = TouchEvent.TOUCH_UP;
					touchEvent.pointer = pointerId;
					touchEvent.x = touchX[i] = (int) (event.getX(i) * scaleX);
					touchEvent.y = touchY[i] = (int) (event.getY(i) * scaleY);
					isTouched[i] = false;
					id[i] = -1;
					touchEventsBuffer.add(touchEvent);
					break;

				case MotionEvent.ACTION_MOVE:
					touchEvent = touchEventPool.newObject();
					touchEvent.type = TouchEvent.TOUCH_DRAGGED;
					touchEvent.pointer = pointerId;
					touchEvent.x = touchX[i] = (int) (event.getX(i) * scaleX);
					touchEvent.y = touchY[i] = (int) (event.getY(i) * scaleY);
					isTouched[i] = true;
					id[i] = pointerId;
					touchEventsBuffer.add(touchEvent);
					break;
				}
			}
			return true;
		}
	}

	//check for touch and do error check
	@Override
	public boolean isTouchDown(int pointer) {
		synchronized (this) {
			int index = getIndex(pointer);
			if (index < 0 || index >= MAX_TOUCHPOINTS)
				return false;
			else
				return isTouched[index];
		}
	}

	//get x axis coordinates and do error check
	@Override
	public int getTouchX(int pointer) {
		synchronized (this) {
			int index = getIndex(pointer);
			if (index < 0 || index >= MAX_TOUCHPOINTS)
				return 0;
			else
				return touchX[index];
		}
	}
	
	//get y axis coordinates and do error check
	@Override
	public int getTouchY(int pointer) {
		synchronized (this) {
			int index = getIndex(pointer);
			if (index < 0 || index >= MAX_TOUCHPOINTS)
				return 0;
			else
				return touchY[index];
		}
	}

	//get multiple touch events
	@Override
	public List<TouchEvent> getTouchEvents() {
		synchronized (this) {
			int len = touchEvents.size();
			for (int i = 0; i < len; i++)
				touchEventPool.free(touchEvents.get(i));
			touchEvents.clear();
			touchEvents.addAll(touchEventsBuffer);
			touchEventsBuffer.clear();
			return touchEvents;
		}
	}
	
	// returns the index for a given pointerId or -1 if no index.
	private int getIndex(int pointerId) {
		for (int i = 0; i < MAX_TOUCHPOINTS; i++) {
			if (id[i] == pointerId) {
				return i;
			}
		}
		return -1;
	}
}
