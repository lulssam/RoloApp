package dam.a50799.prj_roloapp.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dam.a50799.prj_roloapp.data.local.dao.FilmDao
import dam.a50799.prj_roloapp.data.local.entities.Film
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [Film::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "roloapp_database"
                ).addCallback(
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