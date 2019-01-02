package nl.stephanmantel.starwars.characterlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_character.view.*
import nl.stephanmantel.domain.Character
import nl.stephanmantel.starwars.R

class CharacterViewHolder(
    override val containerView: View
): RecyclerView.ViewHolder(containerView), LayoutContainer {

    companion object {
        fun newInstance(parent: ViewGroup): CharacterViewHolder {
            val context = parent.context
            val itemView = LayoutInflater.from(context).inflate(R.layout.list_item_character, parent, false)
            return CharacterViewHolder(itemView)
        }
    }

    fun bind(character: Character) {
        itemView.nameTextView.text = character.name
    }

}