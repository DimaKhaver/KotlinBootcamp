package kotlinbootcamp.viewmodelandviewmodelfactory.score


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.kotlinbootcamp.R
import com.kotlinbootcamp.databinding.ScoreFragmentBinding


class ScoreFragment : Fragment() {

    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: ScoreFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.score_fragment, container, false)
        setUpScoreVM(binding)
        return binding.root
    }

    private fun setUpScoreVM(binding: ScoreFragmentBinding) {
        viewModelFactory = ScoreViewModelFactory(ScoreFragmentArgs.fromBundle(arguments!!).score)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ScoreViewModel::class.java)
        binding.scoreViewModel = viewModel

        viewModel.eventPlayAgain.observe(this, Observer { playAgain ->
            if (playAgain) {
                findNavController().navigate(ScoreFragmentDirections.actionRestart())
                viewModel.onPlayAgainComplete()
            }
        })
    }

}
