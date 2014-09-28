//Name: 	AndroidFileIO.java
//Purpose:	read files like level maps, images and sounds

package com.hungry.mouse.framework.implementation;
import com.hungry.mouse.framework.FileIO;

//java libraries
import java.io.File;//abstract representation of a file system entity identified by a pathname
import java.io.FileInputStream;//input stream tha reads bytes from a file
import java.io.FileOutputStream;//output stream that writes bytes to file
import java.io.IOException;//signal that i/o exception has occured
import java.io.InputStream;//represent input stream of bytes
import java.io.OutputStream;//represent output stream of bytes

//android libraries stored in SDK platform
import android.content.Context;//allows access to application-specific resources and classes
import android.content.SharedPreferences;//interface for access and modify preference data
import android.content.res.AssetManager;//provide access to asset files
import android.os.Environment;//provide access to environment variables
import android.preference.PreferenceManager;//help to create preference hierarchies from activities and xml


public class AndroidFileIO implements FileIO {
	Context context;
    AssetManager assets;
    String externalStoragePath;

    //constructor
    public AndroidFileIO(Context context) {
        this.context = context;
        this.assets = context.getAssets();
        this.externalStoragePath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + File.separator;
    }

    //read asset else throw exception
    @Override
    public InputStream readAsset(String file) throws IOException {
        return assets.open(file);
    }

    //read stream else throw exception
    @Override
    public InputStream readFile(String file) throws IOException {
        return new FileInputStream(externalStoragePath + file);
    }

    //write file else throw exception
    @Override
    public OutputStream writeFile(String file) throws IOException {
        return new FileOutputStream(externalStoragePath + file);
    }
    
    //interface for access and modify preference data
    public SharedPreferences getSharedPref() {
    	return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
