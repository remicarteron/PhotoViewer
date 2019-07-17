package fr.devrtech.photoviewer.core.dao

import fr.devrtech.photoviewer.core.entity.Photo
import io.reactivex.Completable
import io.reactivex.Flowable
import io.realm.Realm


/**
 * DAO based on Realm
 */
class RealmPhotosDAOClient : PhotosDAO {


    override fun getAllPhotos(): Flowable<List<Photo>> {
        // Obtain a Realm instance
        val realm = Realm.getDefaultInstance()
        // Obtain all photos
        val results = realm.where(Photo::class.java).findAll()
        return Flowable.just(results)
    }

    override fun storePhoto(photo: Photo): Completable {
        // Obtain a Realm instance
        val realm = Realm.getDefaultInstance()
        // Insert
        realm.beginTransaction()
        realm.copyToRealm(photo)
        realm.commitTransaction()
        return Completable.complete()
    }

    override fun storeAllPhotos(photos: List<Photo>): Completable {
        // Obtain a Realm instance
        val realm = Realm.getDefaultInstance()
        // Insert
        realm.beginTransaction()
        photos.forEach { realm.copyToRealm(it) }
        realm.commitTransaction()
        return Completable.complete()
    }

    override fun clearPhotos(): Completable {
        // Obtain a Realm instance
        val realm = Realm.getDefaultInstance()
        // Obtain the results of a query
        val results = realm.where(Photo::class.java).findAll()
        realm.beginTransaction()
        // Delete all matches
        results.deleteAllFromRealm()
        realm.commitTransaction()
        return Completable.complete()
    }

}
