package fr.devrtech.photoviewer.core.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import fr.devrtech.photoviewer.core.entity.Photo
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * DAO interface
 */
@Dao
interface PhotosDAO {

    @Query("SELECT * FROM Photo")
    fun getAllPhotos(): Flowable<List<Photo>>

    @Insert
    fun storePhoto(photo: Photo): Completable

    @Insert
    fun storeAllPhotos(photos: List<Photo>): Completable

    @Query("DELETE FROM Photo")
    fun clearPhotos(): Completable

}
