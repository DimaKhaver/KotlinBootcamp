package kotlinbootcamp.lifecycleandlogging

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

const val KEY_REVENUE = "revenue_key"
const val KEY_TIMER_SECONDS = "timer_seconds_key"

class ClickerActivity : AppCompatActivity() {

    private lateinit var dessertTimer : DessertTimer
    private var revenue: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dessertTimer = DessertTimer(this.lifecycle)

        if (savedInstanceState != null) {
            revenue = savedInstanceState.getInt(KEY_REVENUE, 0)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Timber.i("onSaveInstanceState Called")
        revenue?.let { outState.putInt(KEY_REVENUE, it) }
        outState.putInt(KEY_TIMER_SECONDS, dessertTimer.secondsCount)

    }

    override fun onStart() {
        super.onStart()
        dessertTimer.startTimer()
        Timber.i("onStart called")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume Called")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("onPause Called")
    }

    override fun onStop() {
        super.onStop()
        dessertTimer.stopTimer()
        Timber.i("onStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy Called")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.i("onRestart Called")
    }


}