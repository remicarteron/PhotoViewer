package fr.devrtech.photoviewer.core.dao

import fr.devrtech.photoviewer.core.entity.Photo
import fr.devrtech.photoviewer.core.interactor.PhotosLoader
import io.reactivex.Observable


/**
 * Basic implementation of photo DAO
 */
class BasicPhotosDAOClient : PhotosDAO, PhotosLoader {


    // Storing content in memory
    val photos = ArrayList<Photo>()


    override fun getAllPhotos(): List<Photo> {
//        var end = 30
//        if (photos.size < 30) {
//            end = photos.size
//        }
//        return photos.subList(0, end)
        return photos
    }

    override fun storePhoto(photo: Photo) {
        photos.add(photo)
    }

    override fun storeAllPhotos(photos: List<Photo>) {
        this.photos.addAll(photos)
    }

    override fun clearPhotos() {
        photos.clear()
    }


    override fun loadAllPhotos(): Observable<List<Photo>> {
        loadPhotos()
        return Observable.just(photos)
    }


    private fun loadPhotos() {
        storePhoto(PHOTO_01)
        storePhoto(PHOTO_02)
    }


    companion object {

        // Static data only for testing data
        private val PHOTO_01 = Photo(
            1,
            1,
            "accusamus beatae ad facilis cum similique qui sunt",
            "https://via.placeholder.com/600/92c952",
            "https://via.placeholder.com/150/92c952"
        )
        private val PHOTO_02 = Photo(
            2,
            1,
            "reprehenderit est deserunt velit ipsam",
            "https://via.placeholder.com/600/771796",
            "https://via.placeholder.com/150/771796"
        )

    }

}
