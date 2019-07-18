package kotlinbootcamp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.kotlinbootcamp.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.math.absoluteValue

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
