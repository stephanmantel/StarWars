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
    override val containerView: View,
    private val onFavouriteChanged: (Character, isFavourite: Boolean) -> Unit,
    private val onCharacterClicked: (Character) -> Unit
): RecyclerView.ViewHolder(containerView), LayoutContainer {

    companion object {
        fun newInstance(
            parent: ViewGroup,
            onFavouriteChanged: (Character, isFavourite: Boolean) -> Unit,
            onCharacterClicked: (Character) -> Unit
        ): CharacterViewHolder {
            val context = parent.context
            val itemView = LayoutInflater.from(context).inflate(R.layout.list_item_character, parent, false)
            return CharacterViewHolder(itemView, onFavouriteChanged, onCharacterClicked)
        }
    }

    private lateinit var character: Character

    init {
        itemView.favouriteToggle.setOnCheckedChangeListener { _, isChecked ->
            onFavouriteChanged(character, isChecked)
        }
        itemView.setOnClickListener {
            onCharacterClicked(character)
        }
    }

    fun bind(character: Character) {
        this.character = character
        itemView.nameTextView.text = character.name
        itemView.favouriteToggle.isChecked = character.isFavourite
    }

}