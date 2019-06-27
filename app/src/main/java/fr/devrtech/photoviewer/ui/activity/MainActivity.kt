package fr.devrtech.photoviewer.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import fr.devrtech.photoviewer.PhotoViewerApp
import fr.devrtech.photoviewer.R
import fr.devrtech.photoviewer.core.entity.Photo
import fr.devrtech.photoviewer.core.interactor.InteractorListener
import fr.devrtech.photoviewer.core.interactor.PhotosLoaderInteractor
import fr.devrtech.photoviewer.core.utils.RecyclerUtils
import fr.devrtech.photoviewer.ui.adapter.PhotosListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

/**
 * Main activty of the app (viewing all photo in a recyclerview)
 */
class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener, InteractorListener {


    // Current photos
    var photos: List<Photo>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
    }

    override fun onResume() {
        super.onResume()
        fillData()
    }

    override fun onRefresh() {
        refreshData()
    }

    override fun onSuccess() {
        photos = PhotoViewerApp.getPhotosDAO().getAllPhotos()
        fillData()
    }

    override fun onError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_LONG).show()
    }

    override fun onFinally() {
        pv_main_swipe.isRefreshing = false
    }


    fun initUI() {
        pv_main_toolbar.setTitle(R.string.title_activity_main)
        setSupportActionBar(pv_main_toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        // RecyclerView setup
        val thumbWidthDp = getResources().getDimension(R.dimen.thumb_size) / getResources().getDisplayMetrics().density
        val nbColumns = RecyclerUtils.calculateNoOfColumns(this, thumbWidthDp)
        pv_main_recycler.layoutManager = GridLayoutManager(this, nbColumns)
        // Swipe setup
        pv_main_swipe.setOnRefreshListener(this)
    }

    fun refreshData() {
        if (!pv_main_swipe.isRefreshing) {
            pv_main_swipe.isRefreshing = true;
        }
        // Load photos
        PhotosLoaderInteractor(PhotoViewerApp.getPhotosLoader(), PhotoViewerApp.getPhotosDAO(), this).execute()
    }

    fun fillData() {
        if (photos != null && photos!!.size > 0) {
            pv_main_empty.visibility = View.GONE
            pv_main_recycler.visibility = View.VISIBLE
            pv_main_recycler.adapter = PhotosListAdapter(photos!!)
        } else {
            pv_main_empty.visibility = View.VISIBLE
            pv_main_recycler.visibility = View.GONE
        }
    }

}
