package nl.stephanmantel.starwars.characterlist

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_character_list.*
import nl.stephanmantel.domain.Character
import nl.stephanmantel.starwars.R
import nl.stephanmantel.starwars.common.Resource
import nl.stephanmantel.starwars.common.Status
import org.koin.android.viewmodel.ext.android.viewModel

internal class CharacterListFragment: Fragment() {

    private val viewModel: CharacterListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeViewModelData()
        viewModel.fetchCharacters()
    }

    private fun observeViewModelData() {
        viewModel.characterListLiveData.observe(viewLifecycleOwner, Observer {
            handleCharacterListChanged(it)
        })
    }

    private fun handleCharacterListChanged(resource: Resource<List<Character>>?) {
        when (resource?.status) {
            Status.LOADING -> {
                loadingIndicator.visibility = View.VISIBLE
            }
            Status.ERROR -> {
                loadingIndicator.visibility = View.GONE
            }
            Status.SUCCESS -> {
                loadingIndicator.visibility = View.GONE
            }
        }
    }
}