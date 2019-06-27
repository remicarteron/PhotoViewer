package fr.devrtech.photoviewer.core.dao

import fr.devrtech.photoviewer.core.entity.Photo



// Static data only for testing data
private val PHOTO_01 = Photo(1,1,"accusamus beatae ad facilis cum similique qui sunt", "https://via.placeholder.com/600/92c952", "https://via.placeholder.com/150/92c952")
private val PHOTO_02 = Photo(2,1, "reprehenderit est deserunt velit ipsam", "https://via.placeholder.com/600/771796", "https://via.placeholder.com/150/771796")


/**
 * Basic implementation of photo DAO
 */
class BasicPhotosDAOClient : PhotosDAO {



    // Storing content in memory
    val photos = ArrayList<Photo>()


    override fun getAllPhotos(): List<Photo> {
        return photos
    }

    override fun storePhoto(photo: Photo) {
        photos.add(photo)
    }

    override fun storeAllPhotos(photos: List<Photo>) {
        this.photos.toMutableList().addAll(photos)
    }

    override fun clearPhotos() {
        photos.clear()
    }

    override fun loadPhotos() {
        photos.add(PHOTO_01)
        photos.add(PHOTO_02)
    }

}
