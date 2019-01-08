package nl.stephanmantel.starwars.characterdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import kotlinx.android.synthetic.main.fragment_character_detail.*
import nl.stephanmantel.domain.Character
import nl.stephanmantel.starwars.R
import nl.stephanmantel.starwars.common.Resource
import nl.stephanmantel.starwars.common.Status
import nl.stephanmantel.starwars.movies.CardVoteListener
import nl.stephanmantel.starwars.movies.MovieStackViewAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CharacterDetailFragment: Fragment() {

    companion object {
        const val BUNDLE_KEY_CHARACTER = "CHARACTER"
    }

    private val viewModel: CharacterDetailViewModel by viewModel {
        parametersOf(arguments?.getParcelable<Character>(BUNDLE_KEY_CHARACTER)!!)
    }
    private val adapter by lazy { MovieStackViewAdapter() }

    private val votedListener by lazy { CardVoteListener(viewModel::like, viewModel::dislike) }

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
        moviesCardStackView.layoutManager = CardStackLayoutManager(context, votedListener)
        moviesCardStackView.adapter = adapter
    }

    private fun observeViewModelData() {
        viewModel.characterLiveData.observe(viewLifecycleOwner, Observer { handleCharacterChanged(it) })
    }

    private fun handleCharacterChanged(resource: Resource<Character>?) {
        when (resource?.status) {
            Status.LOADING -> {
                showLoading()
            }
            Status.ERROR -> {
                showError(resource.error?.message)
            }
            Status.SUCCESS -> {
                showSuccess()
                characterNameTextView.text = resource.data?.name
            }
        }
    }

    private fun showLoading() {
        loadingIndicator.visibility = View.VISIBLE
        errorTextView.visibility = View.GONE
    }

    private fun showError(message: String?) {
        loadingIndicator.visibility = View.GONE
        errorTextView.visibility = View.VISIBLE
        errorTextView.text = message
    }

    private fun showSuccess() {
        loadingIndicator.visibility = View.GONE
        errorTextView.visibility = View.GONE
    }
}