package nl.stephanmantel.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import nl.stephanmantel.network.rawdomain.character.CharacterMapper
import okhttp3.OkHttpClient
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val NetworkModule = module {

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .build()
    }

    single<Gson> {
        GsonBuilder()
            .create()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
            .build()
    }

    single<StarWarsService> {
        val retrofit: Retrofit = get()
        retrofit.create(StarWarsService::class.java)
    }

    factory {
        CharacterMapper()
    }
}