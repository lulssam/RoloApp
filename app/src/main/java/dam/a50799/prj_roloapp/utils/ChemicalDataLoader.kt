package dam.a50799.prj_roloapp.utils

import android.content.Context
import com.google.gson.Gson
import dam.a50799.prj_roloapp.data.local.entities.Chemical


data class ChemicalJson(
    val name: String,
    val type: String,
    val dilution: String,
    val timeInMinutes: String,
    val temperature: Int,
    val description: String,
    val imageUri: String
)

fun loadChemicalsJson(context: Context): List<Chemical>{
    val inputStream = context.assets.open("chemicals.json")
    val jsonString = inputStream.bufferedReader().use { it.readText() }

    val gson = Gson()
    val chemicalJsonArray = gson.fromJson(jsonString, Array<ChemicalJson>::class.java)

    return chemicalJsonArray.map { chemicalJson ->
        // val converter thumbnail
        val thumbId = context.resources.getIdentifier(
            chemicalJson.imageUri, "drawable", context.packageName
        ).takeIf { it != 0 }

        Chemical(
            name = chemicalJson.name,
            type = chemicalJson.type,
            dilution = chemicalJson.dilution,
            timeInMinutes = chemicalJson.timeInMinutes,
            temperature = chemicalJson.temperature,
            description = chemicalJson.description,
            imageUri = thumbId.toString()
        )
    }


}