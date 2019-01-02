package nl.stephanmantel.storage

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

private const val databaseName = "StarWarsRoomDatabase"

val StorageModule = module {
    single {
        Room.databaseBuilder(androidContext(), StarWarsRoomDatabase::class.java, databaseName)
            .fallbackToDestructiveMigration()
            .build()
    }
}