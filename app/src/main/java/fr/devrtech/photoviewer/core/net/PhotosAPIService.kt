package fr.devrtech.photoviewer.core.net

import fr.devrtech.photoviewer.BuildConfig
import fr.devrtech.photoviewer.core.entity.Photo
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * API for photo web service
 */
interface PhotosAPIService {


    companion object {

        // Base URL
        val API_BASE_URL = BuildConfig.CONF_API_BASE_URL

    }


    @GET("photos")
    fun getAllPhotos(): Observable<List<Photo>>


}
