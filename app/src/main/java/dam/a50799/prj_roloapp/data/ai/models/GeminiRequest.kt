package dam.a50799.prj_roloapp.data.ai.models

data class GeminiRequest(
    val contents: List<Content>
)

data class Content(
    val parts: List<Part>
)

data class Part(
    val text: String
)

data class StepInstruction(
    val name: String, // developer, stop bath, etc...
    val instruction: String
)