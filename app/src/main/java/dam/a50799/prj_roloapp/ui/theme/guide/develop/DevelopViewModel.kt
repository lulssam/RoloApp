package dam.a50799.prj_roloapp.ui.theme.guide.develop

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dam.a50799.prj_roloapp.data.ai.GeminiRetrofitInstance
import dam.a50799.prj_roloapp.data.ai.models.Content
import dam.a50799.prj_roloapp.data.ai.models.GeminiRequest
import dam.a50799.prj_roloapp.data.ai.models.Part
import dam.a50799.prj_roloapp.data.ai.models.StepInstruction
import dam.a50799.prj_roloapp.utils.JsonUtils.parseInstructions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DevelopViewModel : ViewModel() {
    private val _steps = MutableStateFlow<List<StepInstruction>>(emptyList())
    val steps = _steps.asStateFlow()

    private val _currentStepIndex = MutableStateFlow(0)
    val currentStepIndex = _currentStepIndex.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()
    fun fetchInstructions(
        film: String,
        developer: String,
        fixer: String,
        temperature: String,
        iso: String,
    ) {

        Log.d("FetchInstructions", "Chamada com: $film, $developer, $fixer, $temperature, $iso")


        val apiKey = ""
        _isLoading.value = true

        val prompt = """
        Sou um amador em revelação de fotografia analógica.
        Dá-me um passo a passo detalhado e dividido em etapas para revelar o filme. Assume que o
        Stop Bath é somente água:
        - Filme: $film
        - Developer: $developer
        - Fixer: $fixer
        - Temperatura: $temperature ºC
        - ISO: $iso

        Para cada etapa (Developer, Stop Bath, Fixer, Wash), fornece:
        - Diluições
        - Tempos
        - Instruções claras

        Por favor, responde num formato JSON com a estrutura:

        [
          {
            "name": "Developer",
            "instruction": "..."
          },
          {
            "name": "Stop Bath",
            "instruction": "..."
          },
          {
            "name": "Fixer",
            "instruction": "..."
          },
          {
            "name": "Wash",
            "instruction": "..."
          }
        ]
        """.trimIndent()

        Log.d("PromptDebug", prompt)


        viewModelScope.launch {
            try {
                val request = GeminiRequest(
                    contents = listOf(Content(parts = listOf(Part(text = prompt))))
                )
                val response = GeminiRetrofitInstance.api.getDevelopRecipe(
                    request,
                    apiKey
                )

                val jsonResponse = response.candidates
                    .firstOrNull()?.content?.parts?.firstOrNull()?.text ?: "[]"

                Log.d("GeminiResponse", jsonResponse)

                //val parsedSteps = parseInstructions(jsonResponse)
                val parsedSteps = listOf(
                    StepInstruction("Developer", "Diluir 1+1, tempo: 9 minutos a 20ºC"),
                    StepInstruction("Stop Bath", "Ácido acético diluído, 1 minuto"),
                    StepInstruction("Fixer", "Diluir 1+4, tempo: 5 minutos"),
                    StepInstruction("Wash", "Lavar com água corrente por 10 minutos")
                )


                _steps.value = parsedSteps
                _currentStepIndex.value = 0
            } catch (e: Exception) {
                if (e is retrofit2.HttpException) {
                    when (e.code()) {
                        429 -> {
                            Log.e("GeminiError", "Limite de requisições atingido (HTTP 429).")
                            _steps.value = listOf(
                                StepInstruction(
                                    name = "Erro",
                                    instruction = "Atingiste o limite de requisições à API. Tenta novamente daqui a alguns minutos."
                                )
                            )
                        }

                        else -> {
                            Log.e("GeminiError", "Erro HTTP: ${e.code()}", e)
                            _steps.value = listOf(
                                StepInstruction(
                                    name = "Erro",
                                    instruction = "Erro HTTP ${e.code()}: ${e.message()}"
                                )
                            )
                        }
                    }
                } else {
                    Log.e("GeminiError", "Erro inesperado ao obter instruções", e)
                    _steps.value = listOf(
                        StepInstruction(
                            name = "Erro",
                            instruction = "Erro inesperado: ${e.localizedMessage}"
                        )
                    )
                }
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun nextStep() {
        if (_currentStepIndex.value < _steps.value.size - 1) {
            _currentStepIndex.value += 1
        }
    }

    fun previousStep() {
        if (_currentStepIndex.value > 0) {
            _currentStepIndex.value -= 1
        }
    }
}