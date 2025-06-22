package dam.a50799.prj_roloapp.utils

import android.content.Context
import com.google.gson.Gson
import dam.a50799.prj_roloapp.data.local.entities.Film

data class FilmJson(
    val name: String,
    val iso: Int,
    val format: String,
    val type: String,
    val description: String,
    val imageUri: String,
    val exampleImages: List<String> = emptyList()
)

fun loadFilmsJson(context: Context): List<Film>{
    val inputStream = context.assets.open("films.json")
    val jsonString = inputStream.bufferedReader().use { it.readText() }

    val gson = Gson()
    val filmJsonArray = gson.fromJson(jsonString, Array<FilmJson>::class.java)

    return filmJsonArray.map { filmJson ->
        // converter thumbnail
        val thumbId = context.resources.getIdentifier(
            filmJson.imageUri, "drawable", context.packageName
        ).takeIf { it != 0 }

        // converter cada nome de exemplo para resource id
        val examples = filmJson.exampleImages.mapNotNull { name ->
            context.resources.getIdentifier(name.substringBeforeLast("."),
                "drawable",
                context.packageName)
                .takeIf { it != 0 }
        }

        Film(
            name = filmJson.name,
            iso = filmJson.iso,
            format = filmJson.format,
            type = filmJson.type,
            description = filmJson.description,
            imageUri = thumbId,
            exampleImages = examples
        )
    }
}