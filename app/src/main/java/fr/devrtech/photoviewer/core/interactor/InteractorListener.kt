package fr.devrtech.photoviewer.core.interactor


/**
 * Default callbacks for interactors
 */
interface InteractorListener {

    /**
     * Called when interactor starts
     */
    fun onInteractorStart()

    /**
     * Called when all is success
     */
    fun onSuccess()


    /**
     * Called if an exception occured
     */
    fun onError(throwable: Throwable)


    /**
     * Called when all done
     */
    fun onFinally()

}