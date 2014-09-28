//Name: 	AndroidImage.java
//Purpose:	basic informative methods for images

package com.hungry.mouse.framework.implementation;
import com.hungry.mouse.framework.Image;
import com.hungry.mouse.framework.Graphics.ImageFormat;

//android libraries stored in SDK platform
import android.graphics.Bitmap;//make use of bitmap operations

public class AndroidImage implements Image {
    Bitmap bitmap;
    ImageFormat format;
    
    //constructor
    public AndroidImage(Bitmap bitmap, ImageFormat format) {
        this.bitmap = bitmap;
        this.format = format;
    }

    //getters//
    @Override
    public int getWidth() {
        return bitmap.getWidth();
    }

    @Override
    public int getHeight() {
        return bitmap.getHeight();
    }

    
    @Override
    public ImageFormat getFormat() {
        return format;
    }

    @Override
    public void dispose() {
        bitmap.recycle();
    }      
}
