package com.studio.common.ui.utils

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import timber.log.Timber
import javax.inject.Inject

class CompassSensor @Inject constructor(val context: Context) : SensorEventListener {

    private var listener: CompassListener? = null

    private val sensorManager: SensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private var gsensor: Sensor? = null
    private var msensor: Sensor? = null

    private val mGravity = FloatArray(3)
    private val mGeomagnetic = FloatArray(3)
    private val R = FloatArray(9)
    private val I = FloatArray(9)

    private var azimuth: Float = 0.toFloat()
    private var azimuthFix: Float = 0.toFloat()

    interface CompassListener {
        fun onNewAzimuth(azimuth: Float)
    }

    private var isStarted = false

    fun start() {
        isStarted = true
        gsensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        Timber.d("SENSOR ACC: $gsensor")
        msensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        Timber.d("SENSOR MAG: $msensor")
        Timber.d("LISTENER: $listener")
        Timber.d("Register ACC: $gsensor")
        if (gsensor != null) {
            val success = sensorManager.registerListener(
                this,
                gsensor,
                SensorManager.SENSOR_DELAY_GAME
            )
            Timber.d("Success ACC: $success")
            if (!success) {
                Timber.e("--- Accelerometer sensor not found!")
            }
        }

        if (msensor != null) {
            Timber.d("Register MAG: $msensor")
            val sucess = sensorManager.registerListener(
                this,
                msensor,
                SensorManager.SENSOR_DELAY_GAME
            )
            Timber.d("Success MAG: $sucess")
            if (!sucess) {
                Timber.e("--- Magnetic field sensor not found!")
            }
        }
    }

    fun stop() {
        isStarted = false
        sensorManager.unregisterListener(this)
    }

    fun isCompassStarted(): Boolean {
        return isStarted
    }

    fun setAzimuthFix(fix: Float) {
        azimuthFix = fix
    }

    fun resetAzimuthFix() {
        setAzimuthFix(0f)
    }

    fun setListener(l: CompassListener) {
        listener = l
    }

    override fun onSensorChanged(event: SensorEvent) {
        val alpha = 0.97f

        synchronized(this) {
            if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
                mGravity[0] = alpha * mGravity[0] + (1 - alpha) * event.values[0]
                mGravity[1] = alpha * mGravity[1] + (1 - alpha) * event.values[1]
                mGravity[2] = alpha * mGravity[2] + (1 - alpha) * event.values[2]
            }

            if (event.sensor.type == Sensor.TYPE_MAGNETIC_FIELD) {
                mGeomagnetic[0] = alpha * mGeomagnetic[0] + (1 - alpha) * event.values[0]
                mGeomagnetic[1] = alpha * mGeomagnetic[1] + (1 - alpha) * event.values[1]
                mGeomagnetic[2] = alpha * mGeomagnetic[2] + (1 - alpha) * event.values[2]
            }

            val success = SensorManager.getRotationMatrix(
                R,
                I,
                mGravity,
                mGeomagnetic
            )
            if (success) {
                val orientation = FloatArray(3)
                SensorManager.getOrientation(R, orientation)
                azimuth = Math.toDegrees(orientation[0].toDouble()).toFloat()
                azimuth = (azimuth + azimuthFix + 360f) % 360
                if (listener != null) {
                    listener?.onNewAzimuth(azimuth)
                }
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
}
