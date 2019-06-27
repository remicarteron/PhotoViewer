package fr.devrtech.photoviewer.core.interactor

import fr.devrtech.photoviewer.core.entity.Photo
import io.reactivex.Observable

/**
 * DAO for loading/reading photos
 */
interface PhotosLoader {


    fun loadAllPhotos(): Observable<List<Photo>>


}