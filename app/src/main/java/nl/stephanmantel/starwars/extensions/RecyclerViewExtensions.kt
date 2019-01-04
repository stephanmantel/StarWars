package nl.stephanmantel.starwars.extensions

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.onBottomReached (action: () -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if (!recyclerView.canScrollVertically(1)) {
                action()
            }
        }
    })
}