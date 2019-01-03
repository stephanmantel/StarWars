package nl.stephanmantel.starwars.characterlist

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import nl.stephanmantel.domain.Character

private val characterDiffUtil = object : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean = oldItem.name == newItem.name
    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean = oldItem == newItem
}

class CharacterAdapter (
    private val onFavouriteChanged: (Character, isFavourite: Boolean) -> Unit,
    private val onCharacterClicked: (Character) -> Unit
): ListAdapter<Character, CharacterViewHolder>(characterDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder.newInstance(parent, onFavouriteChanged, onCharacterClicked)
    }

    override fun onBindViewHolder(viewHolder: CharacterViewHolder, position: Int) {
        viewHolder.bind(getItem(position))
    }

}