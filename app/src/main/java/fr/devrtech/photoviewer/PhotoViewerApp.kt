package fr.devrtech.photoviewer

import android.app.Application
import fr.devrtech.photoviewer.core.dao.BasicPhotosDAOClient
import fr.devrtech.photoviewer.core.dao.PhotosDAO

/**
 * Application class for global data / configurations
 */
class PhotoViewerApp() : Application() {


    // TAGs
    private val TAG = PhotoViewerApp::class.java.getSimpleName()


    companion object {

        // Singleton
        lateinit var APP_INSTANCE: PhotoViewerApp

        fun getInstance(): PhotoViewerApp {
            return APP_INSTANCE
        }

        fun getPhotosDAO(): PhotosDAO {
            return APP_INSTANCE.photosDAO
        }

    }


    // PhotoDAO (unique in app)
    lateinit var photosDAO: PhotosDAO


    override fun onCreate() {
        super.onCreate()
        APP_INSTANCE = this;
        photosDAO = BasicPhotosDAOClient()
    }

}
