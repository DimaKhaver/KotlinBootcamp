package codelabs.kotlinfundamentals.basicappanatomy

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.kotlinbootcamp.R
import java.util.*

class BasicAppAnatomyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.basic_app_anatomy)
        val rollButton: Button = findViewById(R.id.roll_button)
        val countUpButton: Button = findViewById(R.id.count_up_button)
        val text: TextView = findViewById(R.id.roll_text)

        rollButton.setOnClickListener { setUpRollButtonClick(text) }
        countUpButton.setOnClickListener { setUpCountButtonClick(text) }
    }

    private fun setUpRollButtonClick(text: TextView) {
        val randomInt = Random().nextInt(6) + 1
        text.text = randomInt.toString()
    }

    private fun setUpCountButtonClick(textView: TextView) {
        if (textView.text.equals(getString(R.string.dice_rolled)))
            textView.text = "1"
        else if (textView.text != "6") {
            var tempInt = textView.text.toString().toInt()
            tempInt += 1
            textView.text = tempInt.toString()
        }
    }
}