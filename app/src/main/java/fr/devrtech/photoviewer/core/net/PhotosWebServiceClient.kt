package fr.devrtech.photoviewer.core.net

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import fr.devrtech.photoviewer.BuildConfig
import fr.devrtech.photoviewer.core.entity.Photo
import fr.devrtech.photoviewer.core.interactor.PhotosLoader
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * photo web service client
 */
class PhotosWebServiceClient : PhotosLoader {


    // Rest API client
    private var _restClient: PhotosAPIService

    // Gson
    private var gson: Gson? = null


    /**
     * Constructor
     */
    init {
        // Create
        gson = GsonBuilder().create()
        // OkHttp builder for certificates
        val builder = OkHttpClient.Builder()
        // Socket timeout and read timeout (30s for long calculation requests)
        builder.connectTimeout(10, TimeUnit.SECONDS)
        builder.readTimeout(30, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            // Logging Interceptor
            val interceptor = HttpLoggingInterceptor()
            //interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(interceptor)
        }
        // Create client
        val client = builder.build()
        // Retrofit client
        val retrofit = Retrofit.Builder()
            .baseUrl(PhotosAPIService.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
        _restClient = retrofit.create(PhotosAPIService::class.java)
    }


    /**
     * Gell all photos from the web service
     */
    override fun loadAllPhotos(): Observable<List<Photo>> {
        return _restClient.getAllPhotos()
    }

}
