package dam.a50799.prj_roloapp.data.ai

import dam.a50799.prj_roloapp.data.ai.models.GeminiRequest
import dam.a50799.prj_roloapp.data.ai.models.GeminiResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface GeminiApiService{
    @POST("models/gemini-1.5-pro:generateContent")
    suspend fun getDevelopRecipe(
        @Body request: GeminiRequest,
        @Query("key") apiKey: String
    ): GeminiResponse

}