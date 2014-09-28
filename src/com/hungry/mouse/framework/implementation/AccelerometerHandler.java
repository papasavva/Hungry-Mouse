//Name: 	AccelerometerHandler.java
//Purpose:	provides the interface to use the accelerometer 
//			we need to know which side is up to control the player

package com.hungry.mouse.framework.implementation;

//android libraries stored in SDK platform
import android.content.Context;//allows access to application-specific resources and classes
import android.hardware.Sensor;//represent a sensor
import android.hardware.SensorEvent;//represents a Sensor event and holds informations such as the sensor's type, the time-stamp, accuracy and of course the sensor's data
import android.hardware.SensorEventListener;//receiving notifications from the SensorManager
import android.hardware.SensorManager;//access the device's sensors


public class AccelerometerHandler implements SensorEventListener {
    float accelX;
    float accelY;
    float accelZ;

    public AccelerometerHandler(Context context) {
        SensorManager manager = (SensorManager) context
                .getSystemService(Context.SENSOR_SERVICE);
        if (manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
            Sensor accelerometer = manager.getSensorList(
                    Sensor.TYPE_ACCELEROMETER).get(0);
            manager.registerListener(this, accelerometer,
                    SensorManager.SENSOR_DELAY_GAME);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // nothing to do here
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        accelX = event.values[0];
        accelY = event.values[1];
        accelZ = event.values[2];
    }

    public float getAccelX() {
        return accelX;
    }

    public float getAccelY() {
        return accelY;
    }

    public float getAccelZ() {
        return accelZ;
    }
}