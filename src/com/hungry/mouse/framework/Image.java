//Name:		Image.java
//Purpose:	make use of imageformat

package com.hungry.mouse.framework;

import com.hungry.mouse.framework.Graphics.ImageFormat;

public interface Image {
    public int getWidth();
    public int getHeight();
    public ImageFormat getFormat();
    public void dispose();
}
