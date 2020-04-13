package codelabs.kotlinfundamentals.viewmodelandviewmodelfactory.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    companion object {
        private const val DONE = 0L
        private const val ONE_SECOND = 1000L
        private const val COUNT_DOWN_TIME = 60000L
    }

    private val _currentTime = MutableLiveData<Long>()
    private val _word = MutableLiveData<String>()
    private val _score = MutableLiveData<Int>()
    private val _eventGameFinished = MutableLiveData<Boolean>()
    private var timer: CountDownTimer? = null
    private lateinit var wordList: MutableList<String>

    val currentTime: LiveData<Long>
        get() = _currentTime

    val eventGameFinish : LiveData<Boolean>
        get() = _eventGameFinished

    val score: LiveData<Int>
        get() = _score

    val word: LiveData<String>
        get() = _word

    val currentTimeString = Transformations.map(currentTime) {time ->
        DateUtils.formatElapsedTime(time)
    }

    val wordHint = Transformations.map(word) { word ->
        val randomPosition = (1..word.length).random()
        "Current word has " + word.length + " letters" + "\nThe letter at position " +
        randomPosition + " is " + word[randomPosition - 1].toUpperCase()
    }

    init {
        Log.i("GameViewModel", "GameViewModel created!")
        _word.value = ""
        _score.value = 0
        resetList()
        nextWord()
        setUpTimer()
    }

    private fun setUpTimer() {
        timer = object : CountDownTimer(COUNT_DOWN_TIME, ONE_SECOND) {
            override fun onFinish() {
                _currentTime.value = DONE
                onGameFinish()
            }

            override fun onTick(p0: Long) {
                _currentTime.value = p0 / ONE_SECOND
            }
        }
        timer?.start()
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
            resetList()
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
        timer?.cancel()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }
}