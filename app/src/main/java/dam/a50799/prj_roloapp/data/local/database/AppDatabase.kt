package dam.a50799.prj_roloapp.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dam.a50799.prj_roloapp.data.local.dao.ChemicalDao
import dam.a50799.prj_roloapp.data.local.dao.FilmDao
import dam.a50799.prj_roloapp.data.local.entities.Chemical
import dam.a50799.prj_roloapp.data.local.entities.Film
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [Film::class, Chemical::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
    abstract fun chemicalDao(): ChemicalDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "roloapp_database"
                )
                    .fallbackToDestructiveMigration() // recriar a bd em versões novas
                    .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            CoroutineScope(Dispatchers.IO).launch {
                                getDatabase(context).filmDao().apply {
                                    insertFilm(
                                        Film(
                                            name = "Kodak Gold 200",
                                            iso = 400,
                                            format = "35mm",
                                            type = "Cor",
                                            description = "lorem ",
                                            imageUri = null
                                        )
                                    )
                                    insertFilm(
                                        Film(
                                            name = "Ilford HP5 Plus",
                                            iso = 400,
                                            format = "35mm",
                                            type = "Preto e Branco",
                                            description = "lorem",
                                            imageUri = null
                                        )
                                    )
                                    // TODO adicionar mais filmes
                                }

                                getDatabase(context).chemicalDao().apply {
                                    insertChemical(
                                        Chemical(
                                            name = "Kodak D-76",
                                            type = "Developer",
                                            dilution = "1+1",
                                            timeInMinutes = 9,
                                            temperature = 20,
                                            notes = "Standard developer for BW",
                                            imageUri = null
                                        )
                                    )

                                    insertChemical(
                                        Chemical(
                                            name = "Kodak Fixer",
                                            type = "Fixer",
                                            dilution = "1+4",
                                            timeInMinutes = 5,
                                            temperature = 20,
                                            notes = "Standard fixer",
                                            imageUri = null
                                        )
                                    ) // TODO adicionar mais quimicos
                                }
                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}