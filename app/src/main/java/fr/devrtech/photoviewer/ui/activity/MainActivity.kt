package fr.devrtech.photoviewer.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
    private var photos: List<Photo>? = null


    // Lock for loading only once a time
    private var loadingLock = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the nav_drawer; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onResume() {
        super.onResume()
        fillData()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        when (id) {
            R.id.action_refresh -> {
                onRefresh()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onInteractorStart() {
        loadingLock = true
    }

    override fun onRefresh() {
        refreshData()
    }

    override fun onSuccess() {
        photos = PhotoViewerApp.getPhotosDAO().getAllPhotos()
        fillData()
    }

    override fun onError(throwable: Throwable) {
        Toast.makeText(this, throwable.message.toString(), Toast.LENGTH_LONG).show()
        Snackbar.make(pv_main_fab, throwable.message.toString(), Snackbar.LENGTH_LONG).show()
    }

    override fun onFinally() {
        pv_main_swipe.isRefreshing = false
        loadingLock = false
    }


    fun initUI() {
        pv_main_toolbar.setTitle(R.string.title_activity_main)
        setSupportActionBar(pv_main_toolbar)
        pv_main_fab.setOnClickListener { view ->
            onRefresh()
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
        if (!loadingLock) {
            Snackbar.make(pv_main_fab, R.string.loading, Snackbar.LENGTH_SHORT).show()
            // Load photos
            PhotosLoaderInteractor(PhotoViewerApp.getPhotosLoader(), PhotoViewerApp.getPhotosDAO(), this).execute()
        } else {
            Snackbar.make(pv_main_fab, R.string.already_loading, Snackbar.LENGTH_SHORT).show()
        }
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
