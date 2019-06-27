package fr.devrtech.photoviewer

import android.app.Application
import fr.devrtech.photoviewer.core.dao.BasicPhotosDAOClient
import fr.devrtech.photoviewer.core.dao.PhotosDAO
import fr.devrtech.photoviewer.core.interactor.PhotosLoader
import fr.devrtech.photoviewer.core.net.PhotosWebServiceClient

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
            return APP_INSTANCE.photosDAOClient
        }

        fun getPhotosLoader(): PhotosLoader {
            return APP_INSTANCE.photoLoaderClient
        }

    }


    // PhotoDAO (unique in app)
    private lateinit var photosDAOClient: PhotosDAO

    // Web client
    private lateinit var photoLoaderClient: PhotosLoader


    override fun onCreate() {
        super.onCreate()
        APP_INSTANCE = this;
        photosDAOClient = BasicPhotosDAOClient()
//        photoLoaderClient = photosDAOClient as BasicPhotosDAOClient
        photoLoaderClient = PhotosWebServiceClient()
    }

}
