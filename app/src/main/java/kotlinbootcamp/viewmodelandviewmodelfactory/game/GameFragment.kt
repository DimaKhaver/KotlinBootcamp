package kotlinbootcamp.viewmodelandviewmodelfactory.game


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.kotlinbootcamp.R
import com.kotlinbootcamp.databinding.GameFragmentBinding


/**
 * Fragment where the game is played
 */
class GameFragment : Fragment() {
    private lateinit var binding: GameFragmentBinding
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i("GameFragment", "Called ViewModelProviders.of")
        setUpUIBinding(inflater, container)
        setUpGameVM()
        return binding.root
    }

    private fun setUpGameVM() {
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        viewModel.score.observe(this, Observer { newScore ->
            binding.scoreText.text = newScore.toString()
        })

        viewModel.word.observe(this, Observer { newWord ->
            binding.wordText.text = newWord
        })

        viewModel.eventGameFinish.observe(this, Observer<Boolean> { hasFinished ->
            if (hasFinished) gameFinished()
        })
    }

    private fun setUpUIBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.game_fragment, container, false)
        binding.correctButton.setOnClickListener { onCorrect() }
        binding.skipButton.setOnClickListener { onSkip() }
        binding.endGameButton.setOnClickListener { onEndGame() }
    }

    private fun onSkip() {
        viewModel.onSkip()
    }

    private fun onCorrect() {
        viewModel.onCorrect()
    }

    private fun onEndGame() {
        gameFinished()
    }

    private fun gameFinished() {
        Toast.makeText(activity, "Game has just finished", Toast.LENGTH_LONG).show()
        viewModel.onGameFinishComplete()
        val action = GameFragmentDirections.actionGameToScore()
        action.score = viewModel.score.value ?: 0
        NavHostFragment.findNavController(this).navigate(action)
    }
}
