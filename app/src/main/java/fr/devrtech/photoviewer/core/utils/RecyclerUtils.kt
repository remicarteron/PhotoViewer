package fr.devrtech.photoviewer.core.utils

import android.content.Context

/**
 * Utils functions used with recyclerview
 */
class RecyclerUtils {


    companion object {

        fun calculateNoOfColumns(context: Context, columnWidthDp: Float): Int {
            val displayMetrics = context.getResources().getDisplayMetrics()
            val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
            return (screenWidthDp / columnWidthDp).toInt()
        }

    }

}
