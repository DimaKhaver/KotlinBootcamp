package codelabs.kotlinfundamentals.imagerscsandcompatibility

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.kotlinbootcamp.R
import java.util.*

class ImageResourcesActivity : AppCompatActivity() {

    private lateinit var firstDiceImage : ImageView
    private lateinit var secondDiceImage : ImageView
    private lateinit var rollButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_resources_and_compatibility)

        firstDiceImage = findViewById(R.id.dice_image_1)
        secondDiceImage = findViewById(R.id.dice_image_2)
        rollButton = findViewById(R.id.roll_button)

        rollButton.setOnClickListener {
            firstDiceImage.setImageResource(rollDice())
            secondDiceImage.setImageResource(rollDice())
        }
    }

    private fun rollDice(): Int {
        return when (Random().nextInt(6) + 1) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }

}