package nl.stephanmantel.starwars.characterlist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_character_list.*
import nl.stephanmantel.domain.Character
import nl.stephanmantel.starwars.R
import nl.stephanmantel.starwars.characterdetail.CharacterDetailFragment.Companion.BUNDLE_KEY_CHARACTER
import nl.stephanmantel.starwars.common.Resource
import nl.stephanmantel.starwars.common.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class CharacterListFragment: Fragment() {

    private val viewModel: CharacterListViewModel by viewModel()
    private val onFavouriteChanged = { character: Character, isFavourite: Boolean ->
        viewModel.setCharacterFavourite(character, isFavourite)
    }
    private val onCharacterClicked: (Character) -> Unit = {
        val bundle = Bundle().apply {
            putParcelable(BUNDLE_KEY_CHARACTER, it)
        }
        navController?.navigate(R.id.action_characterListFragment_to_characterDetailFragment, bundle)
    }
    private val characterAdapter = CharacterAdapter(onFavouriteChanged, onCharacterClicked)
    private val navController by lazy {
        view?.findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureViews()
        observeViewModelData()
    }

    private fun configureViews() {
        characterListRecyclerView.layoutManager = LinearLayoutManager(context)
        characterListRecyclerView.adapter = characterAdapter
    }

    private fun observeViewModelData() {
        viewModel.characterListLiveData.observe(viewLifecycleOwner, Observer {
            handleCharacterListChanged(it)
        })
    }

    private fun handleCharacterListChanged(resource: Resource<List<Character>>?) {
        when (resource?.status) {
            Status.LOADING -> {
                errorTextView.visibility = View.GONE
                loadingIndicator.visibility = View.VISIBLE
            }
            Status.ERROR -> {
                errorTextView.visibility = View.VISIBLE
                loadingIndicator.visibility = View.GONE
                errorTextView.text = resource.error?.message
            }
            Status.SUCCESS -> {
                characterAdapter.submitList(resource.data)
                errorTextView.visibility = View.GONE
                loadingIndicator.visibility = View.GONE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.character_list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.sortByName -> {
                viewModel.sortCharacters(byName)
            }
            R.id.sortByAge -> {
                viewModel.sortCharacters(byAge)
            }
            R.id.sortByFavourite -> {
                viewModel.sortCharacters(byFavouriteState)
            }
        }
        return true
    }
}