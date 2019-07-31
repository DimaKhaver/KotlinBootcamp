package kotlinbootcamp.viewmodelandviewmodelfactory.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ScoreViewModelFactory(private val finalScore: Int) : ViewModelProvider.Factory {

    companion object {
        private const val EXCEPTION_MESSAGE = "unknown VM class"
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScoreViewModel::class.java))
            return ScoreViewModel(finalScore) as T
        throw IllegalArgumentException(EXCEPTION_MESSAGE)
    }
}