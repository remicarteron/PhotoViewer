package fr.devrtech.photoviewer

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.room.Room
import fr.devrtech.photoviewer.core.dao.PhotosDAO
import fr.devrtech.photoviewer.core.dao.RoomPhotosDatabase
import fr.devrtech.photoviewer.core.interactor.PhotosLoader
import fr.devrtech.photoviewer.core.net.PhotosWebServiceClient
import io.realm.Realm

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
//        photosDAOClient = RealmPhotosDAOClient()
//        photosDAOClient = BasicPhotosDAOClient()

//        val db = Room.databaseBuilder(
//            applicationContext,
//            RoomPhotosDatabase::class.java, "photos_database"
//        ).build()

        photosDAOClient = RoomPhotosDatabase.getPhotoDAO(this)


        // TODO


//        photoLoaderClient = photosDAOClient as BasicPhotosDAOClient
        photoLoaderClient = PhotosWebServiceClient()
        // Initialize Realm (just once per application)
        Realm.init(this)
        // Phone infos (only for debug and control)
        val arch = System.getProperty("os.arch")
        val abi = System.getProperty("ro.product.cpu.abilist")
        Log.d(TAG, "Android SDK version " + Build.VERSION.SDK_INT)
        Log.d(TAG, "OS arch : " + arch + " " + abi)
    }

}
