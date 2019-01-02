package nl.stephanmantel.starwars.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import nl.stephanmantel.starwars.main.MainActivity

/**
 * Activity without any functionality. Its style will show a splash screen for as briefly as possible
 * and as soon as it is ready the main activity of the application will be started.
 */
class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appIntent = Intent(this, MainActivity::class.java)
        startActivity(appIntent)
        finish()
    }
}