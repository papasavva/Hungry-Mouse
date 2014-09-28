//Name: FileIO.java
//Purpose:	read files like level maps, images and sounds

package com.hungry.mouse.framework;

//java libraries
import java.io.IOException;//signal that i/o exception has occured
import java.io.InputStream;//represent input stream of bytes
import java.io.OutputStream;//represent output stream of bytes

//android libraries stored in SDK platform
import android.content.SharedPreferences;//access and modify preference data

public interface FileIO {
	
	//read file else throw exception
    public InputStream readFile(String file) throws IOException;

    //write file else throw exception
    public OutputStream writeFile(String file) throws IOException;
    
	//read file else throw exception
    public InputStream readAsset(String file) throws IOException;
    
    //interface for access and modify preference data
    public SharedPreferences getSharedPref();
}
