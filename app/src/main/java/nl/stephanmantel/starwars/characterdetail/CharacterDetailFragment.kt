package nl.stephanmantel.starwars.characterdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import nl.stephanmantel.starwars.R

class CharacterDetailFragment: Fragment() {

    companion object {
        const val BUNDLE_KEY_CHARACTER = "CHARACTER"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureViews()
        observeViewModelData()
    }

    private fun configureViews() {

    }

    private fun observeViewModelData() {

    }
}