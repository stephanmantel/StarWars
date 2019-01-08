package nl.stephanmantel.starwars

import android.app.Application
import nl.stephanmantel.data.DataModule
import nl.stephanmantel.network.NetworkModule
import nl.stephanmantel.starwars.characterdetail.CharacterDetailModule
import nl.stephanmantel.starwars.characterlist.CharacterListModule
import nl.stephanmantel.storage.StorageModule
import org.koin.android.ext.android.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(
            CharacterListModule,
            CharacterDetailModule,
            NetworkModule,
            StorageModule,
            DataModule
        ))
    }
}