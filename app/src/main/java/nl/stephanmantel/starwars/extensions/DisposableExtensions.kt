package nl.stephanmantel.starwars.extensions

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

internal operator fun CompositeDisposable.plusAssign(subscribe: Disposable) {
    this.add(subscribe)
}