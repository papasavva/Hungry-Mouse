//Name: 	AndroidGraphics.java
//Purpose:	draw pixels, lines, rectangles and pixmaps to the framebuffer
//			also create pixmap instances from asset files

package com.hungry.mouse.framework.implementation;
import com.hungry.mouse.framework.Graphics;
import com.hungry.mouse.framework.Image;

//java libraries
import java.io.IOException;//signal that i/o exception has occurred
import java.io.InputStream;//represent input stream of bytes

//android libraries stored in SDK platform
import android.content.res.AssetManager;//provide access to application`s asset files
import android.graphics.Bitmap;//make use of bitmap operations
import android.graphics.Bitmap.Config;//describes how pixels are stored
import android.graphics.BitmapFactory;//create bitmap objects from various sources
import android.graphics.BitmapFactory.Options;//extended capabilities of BitmapFactory
import android.graphics.Canvas;//hold the draw calls
import android.graphics.Paint;//hold style and color information to draw
import android.graphics.Paint.Style;//specifies if the primitive being drawn, stroked or both
import android.graphics.Rect;//hold 4 integer coordinates for rectangle



public class AndroidGraphics implements Graphics {
	
	//use to load bitmap instances
    AssetManager assets;
    
    //represents artificial framebuffer
    Bitmap frameBuffer;
    
    //use to draw to artificial framebuffer
    Canvas canvas;
    
    //use to draw
    Paint paint;
    
    //need to implement drawpixmap() methods
    Rect srcRect = new Rect();
    Rect dstRect = new Rect();

    //constructor
    public AndroidGraphics(AssetManager assets, Bitmap frameBuffer) {
        this.assets = assets;
        this.frameBuffer = frameBuffer;
        this.canvas = new Canvas(frameBuffer);
        this.paint = new Paint();
    }

    //try to load a bitmap from an asset file using the imageformat
    @Override
    public Image newImage(String fileName, ImageFormat format) {
        Config config = null;
        if (format == ImageFormat.RGB565)
            config = Config.RGB_565;
        else if (format == ImageFormat.ARGB4444)
            config = Config.ARGB_4444;
        else
            config = Config.ARGB_8888;

        Options options = new Options();
        options.inPreferredConfig = config;
        
        
        //try to load bitmap from asset via bitmap factory
        InputStream in = null;
        Bitmap bitmap = null;
        try {
            in = assets.open(fileName);
            bitmap = BitmapFactory.decodeStream(in, null, options);
            if (bitmap == null)
                throw new RuntimeException("Couldn't load bitmap from asset '"
                        + fileName + "'");
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load bitmap from asset '"
                    + fileName + "'");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
        
        //construct a new bitmap instance based on the loaded bitmap and its imageformat 
        if (bitmap.getConfig() == Config.RGB_565)
            format = ImageFormat.RGB565;
        else if (bitmap.getConfig() == Config.ARGB_4444)
            format = ImageFormat.ARGB4444;
        else
            format = ImageFormat.ARGB8888;

        //return it to the caller
        return new AndroidImage(bitmap, format);
    }

    //extract the red, green and blue components
    //alpha value is ignore, because is transaparent
    @Override
    public void clearScreen(int color) {
        canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8,
                (color & 0xff));
    }

    //draw a line to the artificial framebuffer
    @Override
    public void drawLine(int x, int y, int x2, int y2, int color) {
        paint.setColor(color);
        canvas.drawLine(x, y, x2, y2, paint);
    }

    //draw a rectangle to the artificial framebuffer
    @Override
    public void drawRect(int x, int y, int width, int height, int color) {
        paint.setColor(color);
        paint.setStyle(Style.FILL);
        canvas.drawRect(x, y, x + width - 1, y + height - 1, paint);
    }
    
    //draw based on ARGB values
    @Override
    public void drawARGB(int a, int r, int g, int b) {
        paint.setStyle(Style.FILL);
       canvas.drawARGB(a, r, g, b);
    }
    
    //draw a string
    @Override
    public void drawString(String text, int x, int y, Paint paint){
    	canvas.drawText(text, x, y, paint);
	
    }
    
    //show a part of an image/or the whole image
    @Override
    public void drawImage(Image Image, int x, int y, int srcX, int srcY,
            int srcWidth, int srcHeight) {
        srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX + srcWidth;
        srcRect.bottom = srcY + srcHeight;
        
        
        dstRect.left = x;
        dstRect.top = y;
        dstRect.right = x + srcWidth;
        dstRect.bottom = y + srcHeight;

        canvas.drawBitmap(((AndroidImage) Image).bitmap, srcRect, dstRect,
                null);
    }
    
    //show the whole image
    @Override
    public void drawImage(Image Image, int x, int y) {
        canvas.drawBitmap(((AndroidImage)Image).bitmap, x, y, null);
    }
    
    //show a part of an image/or the whole image at the specified scale
    public void drawScaledImage(Image Image, int x, int y, int width, int height, int srcX, int srcY, int srcWidth, int srcHeight){
    	
    	
   	 srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX + srcWidth;
        srcRect.bottom = srcY + srcHeight;
        
        
        dstRect.left = x;
        dstRect.top = y;
        dstRect.right = x + width;
        dstRect.bottom = y + height;
        
   
        
        canvas.drawBitmap(((AndroidImage) Image).bitmap, srcRect, dstRect, null);
        
    }
    
    //getters//
    @Override
    public int getWidth() {
        return frameBuffer.getWidth();
    }

    @Override
    public int getHeight() {
        return frameBuffer.getHeight();
    }
}
