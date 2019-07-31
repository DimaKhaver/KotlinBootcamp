package kotlinbootcamp.viewmodelandviewmodelfactory.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private val _word = MutableLiveData<String>()
    private val _score = MutableLiveData<Int>()
    private val _eventGameFinished = MutableLiveData<Boolean>()

    val eventGameFinish : LiveData<Boolean>
        get() = _eventGameFinished

    val score: LiveData<Int>
        get() = _score

    val word: LiveData<String>
        get() = _word

    // The list of words - the front of the list is the next _word to guess
    private lateinit var wordList: MutableList<String>

    init {
        _word.value = ""
        _score.value = 0
        resetList()
        nextWord()
        Log.i("GameViewModel", "GameViewModel created!")
    }

    fun onGameFinish() {
        _eventGameFinished.value = true
    }

    private fun resetList() {
        wordList = mutableListOf(
            "queen", "hospital", "basketball", "cat", "change", "snail", "soup", "calendar", "sad", "desk",
            "guitar", "home", "railway", "zebra", "jelly", "car", "crow", "trade", "bag", "roll", "bubble")
        wordList.shuffle()
    }

    private fun nextWord() {
        if (wordList.isNotEmpty())
            _word.value = wordList.removeAt(0)
        else
            onGameFinish()
    }

    fun onSkip() {
        if (wordList.isNotEmpty()) {
            _score.value = (score.value)?.minus(1)
        }
        nextWord()
    }

    fun onCorrect() {
        if (wordList.isNotEmpty()) {
            _score.value = (score.value)?.plus(1)
        }
        nextWord()
    }

    fun onGameFinishComplete() {
        _eventGameFinished.value = false
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }
}