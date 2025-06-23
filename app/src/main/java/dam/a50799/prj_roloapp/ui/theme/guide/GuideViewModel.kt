package dam.a50799.prj_roloapp.ui.theme.guide

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dam.a50799.prj_roloapp.data.remote.GeminiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GuideViewModel : ViewModel(){

    private val _instructions = MutableStateFlow("")
    val instructions = _instructions.asStateFlow()

    fun fetchInstructions(film: String, developer: String, temperature: String, iso: String) {
        val prompt = """
            Sou um especialista em revelação de fotografia analógica.
            Dá-me um passo a passo claro e objetivo para revelar um filme:
            - Filme: $film
            - Developer: $developer
            - Temperatura: $temperature ºC
            - ISO: $iso

            Indica os tempos de revelação, diluições e tempos de stop bath e fixador.
        """.trimIndent()

        viewModelScope.launch {
            GeminiService.getFilmDevelopmentSteps(prompt,
                onResponse = { result -> _instructions.value = result },
                onError = { _instructions.value = "Erro ao obter instruções." }
            )
        }
    }
}