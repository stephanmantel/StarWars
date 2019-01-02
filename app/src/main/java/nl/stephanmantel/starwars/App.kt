package nl.stephanmantel.starwars

import android.app.Application
import nl.stephanmantel.network.NetworkModule
import nl.stephanmantel.starwars.characterlist.CharacterListModule
import org.koin.android.ext.android.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(
            CharacterListModule,
            NetworkModule
        ))
    }
}