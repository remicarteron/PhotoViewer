package fr.devrtech.photoviewer

import android.app.Application

/**
 * Application class for global data / configurations
 */
class PhotoViewerApp : Application() {

    // TAGs
    private val TAG = PhotoViewerApp::class.java.getSimpleName()


    companion object {

        // Singleton
        lateinit var APP_INSTANCE: PhotoViewerApp

        fun getInstance(): PhotoViewerApp {
            return APP_INSTANCE
        }

    }

    override fun onCreate() {
        super.onCreate()
        APP_INSTANCE = this;
    }

}