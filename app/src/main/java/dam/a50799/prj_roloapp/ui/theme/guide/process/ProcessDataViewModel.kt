package dam.a50799.prj_roloapp.ui.theme.guide.process

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dam.a50799.prj_roloapp.data.local.entities.Chemical
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProcessDataViewModel: ViewModel() {
    var film by mutableStateOf("")
    var developer by mutableStateOf("")
    var fixer by mutableStateOf("")

    var developTime by mutableStateOf(0)
    var fixerTime by mutableStateOf(0)
    var temperature by mutableStateOf(20)

    private var chemicals: List<Chemical> = emptyList()

    var currentTime by mutableStateOf(developTime)
    private set

    private var timerJob: Job? = null
    fun setChemicals(chemicalList: List<Chemical>){
        chemicals = chemicalList
    }

    fun onFilmChange(newFilm: String) {
        film = newFilm
    }

    fun onDeveloperChange(newDeveloper: String) {
        developer = newDeveloper

        // atualizar tempos e temperatura
        chemicals.find { it.name.equals(newDeveloper, ignoreCase = true) }?.let { chemical ->
            developTime = parseTimeToSeconds(chemical.timeInMinutes)
            temperature = chemical.temperature
        }
    }

    fun onFixerChange(newFixer: String) {
        fixer = newFixer

        chemicals.find { it.name.equals(newFixer, ignoreCase = true)}?.let { chemical ->
            fixerTime = parseTimeToSeconds(chemical.timeInMinutes)
        }
    }

    fun startDevelopTimer() {
        timerJob?.cancel() // cancela anterior se existir
        currentTime = developTime
        timerJob = viewModelScope.launch {
            while (currentTime > 0) {
                delay(1000)
                currentTime -= 1
            }
        }
    }

    fun stopTimer() {
        timerJob?.cancel()
    }

    private fun parseTimeToSeconds(timeString: String): Int {
        return when {
            timeString.contains(":") -> {
                val parts = timeString.split(":")
                val minutes = parts[0].toIntOrNull() ?: 0
                val seconds = parts[1].toIntOrNull() ?: 0
                minutes * 60 + seconds
            }
            timeString.contains("-") -> {
                // intervalo tipo "2-5" → usar o médio
                val parts = timeString.split("-")
                val min = parts[0].toIntOrNull() ?: 0
                val max = parts[1].toIntOrNull() ?: 0
                ((min + max) / 2) * 60
            }
            else -> {
                (timeString.toIntOrNull() ?: 0) * 60
            }
        }
    }

    fun startFixerTimer() {
        timerJob?.cancel()
        currentTime = fixerTime
        timerJob = viewModelScope.launch {
            while (currentTime > 0) {
                delay(1000)
                currentTime -= 1
            }
        }
    }
}