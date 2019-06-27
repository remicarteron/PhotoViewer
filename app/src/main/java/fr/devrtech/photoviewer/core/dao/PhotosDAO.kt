package fr.devrtech.photoviewer.core.dao

import fr.devrtech.photoviewer.core.entity.Photo

/**
 * DAO interface
 */
interface PhotosDAO {

    fun getAllPhotos(): List<Photo>

    fun storePhoto(photo: Photo)

    fun storeAllPhotos(photos: List<Photo>)

    fun clearPhotos()

}
