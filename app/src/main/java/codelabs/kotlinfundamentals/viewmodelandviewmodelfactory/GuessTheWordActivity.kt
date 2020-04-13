package codelabs.kotlinfundamentals.viewmodelandviewmodelfactory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kotlinbootcamp.R

class GuessTheWordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.guess_the_word_activity)
    }

}
