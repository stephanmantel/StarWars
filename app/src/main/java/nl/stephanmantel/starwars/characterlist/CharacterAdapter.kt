package nl.stephanmantel.starwars.characterlist

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.view.ViewGroup
import nl.stephanmantel.domain.Character

private val characterDiffUtil = object : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean = oldItem.name == newItem.name
    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean = oldItem == newItem
}

class CharacterAdapter: ListAdapter<Character, CharacterViewHolder>(characterDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(viewHolder: CharacterViewHolder, position: Int) {
        viewHolder.bind(getItem(position))
    }

}