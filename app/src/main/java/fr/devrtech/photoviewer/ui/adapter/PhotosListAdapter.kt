package fr.devrtech.photoviewer.ui.adapter

import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.devrtech.photoviewer.R
import fr.devrtech.photoviewer.core.entity.Photo
import kotlinx.android.synthetic.main.item_photo.view.*

/**
 * Adapter for displayiong a thumb af an item
 */
class PhotosListAdapter(private val photosList: List<Photo>) :
    RecyclerView.Adapter<PhotosListAdapter.PhotoViewHolder>() {

    // TAGs
    private val TAG = PhotosListAdapter::class.java.getSimpleName()


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return PhotoViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        // Get element from your dataset at this position
        val item: Photo = photosList.get(position)
        // Fill view with content
        holder.titleTextView.text = item.title
        Log.d(TAG, "loading thumb url : " + item.thumbnailUrl)
        if (!TextUtils.isEmpty(item.thumbnailUrl)) {
            // Image loading
            Picasso.with(holder.view.context).load(item.thumbnailUrl).into(holder.thumbImageView)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = photosList.size


    /**
     * View holder
     */
    inner class PhotoViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val thumbImageView: ImageView = view.pv_item_image_thumb

        val titleTextView: TextView = view.pv_item_text_title

    }

}
