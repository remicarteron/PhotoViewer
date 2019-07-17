package fr.devrtech.photoviewer.core.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

/**
 * Model for photo item
 */
@Entity
open class Photo() : Serializable, RealmObject() {


    constructor(id: Long?, albumId: Long?, title: String?, url: String?, thumbnailUrl: String?) : this() {
        this.id = id
        this.albumId = albumId
        this.title = title
        this.url = url
        this.thumbnailUrl = thumbnailUrl
    }


    @androidx.room.PrimaryKey
    @PrimaryKey
    var id: Long? = null

    @ColumnInfo(name = "album_id")
    var albumId: Long? = null

    @ColumnInfo(name = "title")
    var title: String? = null

    @ColumnInfo(name = "url")
    var url: String? = null

    @ColumnInfo(name = "thumbnail_url")
    var thumbnailUrl: String? = null

}
