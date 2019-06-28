package fr.devrtech.photoviewer.core.interactor

import fr.devrtech.photoviewer.core.dao.PhotosDAO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Interactor for photos loading
 */
class PhotosLoaderInteractor(
    private var loaderClient: PhotosLoader,
    private var photosDAO: PhotosDAO,
    private var listener: InteractorListener?
) {

    fun execute() {
        listener?.onInteractorStart()
        var disposable = loaderClient.loadAllPhotos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .unsubscribeOn(Schedulers.io())
            .doFinally({ listener?.onFinally() })
            .subscribe({ response ->
                photosDAO.storeAllPhotos(response)
                listener?.onSuccess()
            }, { error ->
                error.printStackTrace()
                listener?.onError(error)
            })
    }

}
