package com.studio.common.ui.utils

import android.content.Context
import android.media.MediaPlayer
import timber.log.Timber

class CSoundAssets(val context: Context) {

    private var mediaPlayer = MediaPlayer()

    fun playLooping(pathAudio: String) {
        try {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
                mediaPlayer.release()
                mediaPlayer = MediaPlayer()
            }
            mediaPlayer.reset()
            val descriptor = context.assets.openFd(pathAudio)
            mediaPlayer.setDataSource(
                descriptor.fileDescriptor,
                descriptor.startOffset,
                descriptor.length
            )
            descriptor.close()
            mediaPlayer.prepare()
            mediaPlayer.setVolume(100f, 100f)
            mediaPlayer.isLooping = true
            mediaPlayer.start()
        } catch (e: java.lang.Exception) {
            Timber.e("Error: ${e.message}")
        }
    }

    fun play(pathAudio: String) {
        try {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
                mediaPlayer.release()
                mediaPlayer = MediaPlayer()
            }
            mediaPlayer.reset()
            val descriptor = context.assets.openFd(pathAudio)
            mediaPlayer.setDataSource(
                descriptor.fileDescriptor,
                descriptor.startOffset,
                descriptor.length
            )
            descriptor.close()
            mediaPlayer.prepare()
            mediaPlayer.setVolume(100f, 100f)
            mediaPlayer.isLooping = false
            mediaPlayer.start()
        } catch (e: Exception) {
            Timber.e("Error: ${e.message}")
        }
    }

    fun stop() {
        mediaPlayer.stop()
    }
}
