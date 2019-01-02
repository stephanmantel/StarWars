package nl.stephanmantel.starwars.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import nl.stephanmantel.starwars.R

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}