package dam.a50799.prj_roloapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films") // entidade
/**
 * Classe que define o modelo de dados do filme*/
data class Film(
    val name: String, //ex: kodak gold
    val iso: Int, // ex: 200
    val format: String, // ex: 35mm
    val type: String, // ex: cor
    val description: String, // descrição do rolo
    val imageUri: Int?, // para guardar a imagem
    val exampleImages: List<Int> = emptyList(),

    @PrimaryKey(autoGenerate =  true) val id: Int = 0 // id unico
)