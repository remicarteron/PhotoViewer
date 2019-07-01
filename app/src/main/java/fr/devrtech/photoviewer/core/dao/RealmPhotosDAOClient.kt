package fr.devrtech.photoviewer.core.dao

import fr.devrtech.photoviewer.core.entity.Photo
import io.realm.Realm


/**
 * DAO based on Realm
 */
class RealmPhotosDAOClient : PhotosDAO {


    override fun getAllPhotos(): List<Photo> {
        // Obtain a Realm instance
        val realm = Realm.getDefaultInstance()
        // Obtain all photos
        val results = realm.where(Photo::class.java).findAll()
        return results
    }

    override fun storePhoto(photo: Photo) {
        // Obtain a Realm instance
        val realm = Realm.getDefaultInstance()
        // Insert
        realm.beginTransaction()
        realm.copyToRealm(photo)
        realm.commitTransaction()
    }

    override fun storeAllPhotos(photos: List<Photo>) {
        // Obtain a Realm instance
        val realm = Realm.getDefaultInstance()
        // Insert
        realm.beginTransaction()
        photos.forEach { realm.copyToRealm(it) }
        realm.commitTransaction()
    }

    override fun clearPhotos() {
        // Obtain a Realm instance
        val realm = Realm.getDefaultInstance()
        // Obtain the results of a query
        val results = realm.where(Photo::class.java).findAll()
        realm.beginTransaction()
        // Delete all matches
        results.deleteAllFromRealm()
        realm.commitTransaction()
    }

}
