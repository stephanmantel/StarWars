package nl.stephanmantel.starwars.movies

import android.util.Log
import android.view.View
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction

data class CardVoteListener(
    private val onLike: () -> Unit,
    private val onDislike: () -> Unit
): CardStackListener {
    override fun onCardDisappeared(view: View?, position: Int) {

    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {

    }

    override fun onCardSwiped(direction: Direction?) {
        when (direction) {
            Direction.Left -> {
                onDislike()
            }
            Direction.Right -> {
                onLike()
            }
            else -> {
                /* no-op */
            }
        }
    }

    override fun onCardCanceled() {

    }

    override fun onCardAppeared(view: View?, position: Int) {

    }

    override fun onCardRewound() {

    }

}