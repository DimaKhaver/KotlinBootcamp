package codelabs.kotlinfundamentals.viewmodelandviewmodelfactory.game


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


class GameFragment : Fragment() {
    private lateinit var binding: GameFragmentBinding
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i("GameFragment", "Called ViewModelProviders.of")
        setUpGameVM()
        setUpUIBinding(inflater, container)
        return binding.root
    }

    private fun setUpGameVM() {
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        viewModel.eventGameFinish.observe(this, Observer<Boolean> { hasFinished ->
            if (hasFinished) gameFinished()
        })
    }

    private fun setUpUIBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.game_fragment, container, false)
        binding.gameViewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun gameFinished() {
        Toast.makeText(activity, "Game has just finished", Toast.LENGTH_LONG).show()
        viewModel.onGameFinishComplete()
        val action = GameFragmentDirections.actionGameToScore()
        action.score = viewModel.score.value ?: 0
        NavHostFragment.findNavController(this).navigate(action)
    }
}
