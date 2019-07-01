package fr.devrtech.photoviewer.core.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

/**
 * Model for photo item
 */
open class Photo() : Serializable, RealmObject() {


    constructor(id: Long?, albumId: Long?, title: String?, url: String?, thumbnailUrl: String?) : this() {
        this.id = id
        this.albumId = albumId
        this.title = title
        this.url = url
        this.thumbnailUrl = thumbnailUrl
    }


    @PrimaryKey
    var id: Long? = null

    var albumId: Long? = null

    var title: String? = null

    var url: String? = null

    var thumbnailUrl: String? = null

}
