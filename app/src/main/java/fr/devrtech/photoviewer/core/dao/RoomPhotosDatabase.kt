package fr.devrtech.photoviewer.core.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fr.devrtech.photoviewer.core.entity.Photo

/**
 * Database containing photos
 */
@Database(entities = [Photo::class], version = 1)
abstract class RoomPhotosDatabase : RoomDatabase() {


    abstract fun photosDAO(): PhotosDAO


    companion object {

        @Volatile
        private var INSTANCE: RoomPhotosDatabase? = null

        fun getDatabase(context: Context): RoomPhotosDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomPhotosDatabase::class.java,
                    "photos_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

        fun getPhotoDAO(context: Context): PhotosDAO {
            return getDatabase(context).photosDAO()
        }

    }

}
