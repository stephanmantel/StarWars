package nl.stephanmantel.starwars.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.stack_item_movie.view.*
import nl.stephanmantel.starwars.R

class MovieViewHolder(
    override val containerView: View
): RecyclerView.ViewHolder(containerView), LayoutContainer {

    companion object {
        fun newInstance(
            parent: ViewGroup
        ): MovieViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.stack_item_movie, parent, false)
            return MovieViewHolder(view)
        }
    }

    fun bind(string: String) {
        itemView.movieTitleTextView.text = string
    }


}