//Name: 	Settings.java
//Purpose:	save and load settings like sounds on/off gyroscope on/off and save high scores
package com.hungry.mouse.main;

import com.hungry.mouse.framework.FileIO;

//java libraries
import java.io.BufferedReader;//wraps and existing reader and buffers the input
import java.io.BufferedWriter;//wraps an existing Writer and buffers the output
import java.io.IOException;//signal that i/o exception has occurred
import java.io.InputStreamReader;//turn a byte stream to character stream
import java.io.OutputStreamWriter;



public class Settings {
   
    // Create variables that will hold the values you want to save in your game.
    // Default values:
   
    public static boolean soundEnabled = true;
    public static boolean gyroscopeEnabled = false;
    public static int currentLevel = 0;
    public static int theLevelPassed = 0;
    public static int level1CollectedCheeses, level2CollectedCheeses, level3CollectedCheeses;
    public static int level1KilledKamikazis, level2KilledKamikazis, level3KilledKamikazis;
   
    public static void save(FileIO files) {
        BufferedWriter out = null;
        try {
           
            // Writes a file called .savedata to the SD Card
            out = new BufferedWriter(new OutputStreamWriter(
                    files.writeFile(".savethedataHungryMouse")));
           
            // Line by line ("\n" creates a new line), write the value of each variable to the file.
            out.write(Boolean.toString(soundEnabled));
            out.write("\n");
            out.write(Boolean.toString(gyroscopeEnabled));
            out.write("\n");            
            out.write(Integer.toString(currentLevel));
            out.write("\n");
            out.write(Integer.toString(theLevelPassed));
            out.write("\n");
            
            //cheeses
            out.write(Integer.toString(level1CollectedCheeses));
            out.write("\n");
            out.write(Integer.toString(level2CollectedCheeses));
            out.write("\n");
            out.write(Integer.toString(level3CollectedCheeses));
            out.write("\n");
            
            //kamikazis
            out.write(Integer.toString(level1KilledKamikazis));
            out.write("\n");
            out.write(Integer.toString(level2KilledKamikazis));
            out.write("\n");
            out.write(Integer.toString(level3KilledKamikazis));
            out.write("\n");
       
           
           // This section handles errors in file management!
           
        } catch (IOException e) {
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
            }
        }
    }
   
    public static void load(FileIO files) {
        BufferedReader in = null;
        try {
            // Reads file called Save Data
            in = new BufferedReader(new InputStreamReader(
                    files.readFile(".savethedataHungryMouse")));

            // Loads values from the file and replaces default values.
            soundEnabled = Boolean.parseBoolean(in.readLine());
            gyroscopeEnabled = Boolean.parseBoolean(in.readLine());
            currentLevel = Integer.parseInt(in.readLine());
            theLevelPassed = Integer.parseInt(in.readLine());
            
            level1CollectedCheeses = Integer.parseInt(in.readLine());
            level2CollectedCheeses = Integer.parseInt(in.readLine());
            level3CollectedCheeses = Integer.parseInt(in.readLine());            
            
            level1KilledKamikazis = Integer.parseInt(in.readLine());
            level2KilledKamikazis = Integer.parseInt(in.readLine());
            level3KilledKamikazis = Integer.parseInt(in.readLine());
           
        } catch (IOException e) {
            // Catches errors. Default values are used.
        } catch (NumberFormatException e) {
            // Catches errors. Default values are used.
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
            }
        }
    }
}