package dam.a50799.prj_roloapp.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import dam.a50799.prj_roloapp.data.local.dao.ChemicalDao
import dam.a50799.prj_roloapp.data.local.dao.FilmDao
import dam.a50799.prj_roloapp.data.local.dao.UserProfileDao
import dam.a50799.prj_roloapp.data.local.entities.Chemical
import dam.a50799.prj_roloapp.data.local.entities.Film
import dam.a50799.prj_roloapp.data.local.entities.UserProfile
import dam.a50799.prj_roloapp.utils.Converters
import dam.a50799.prj_roloapp.utils.loadChemicalsJson
import dam.a50799.prj_roloapp.utils.loadFilmsJson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [Film::class, Chemical::class, UserProfile::class], version = 11)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
    abstract fun chemicalDao(): ChemicalDao
    abstract fun userProfileDao(): UserProfileDao

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
                    .addCallback(
                        object : RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                CoroutineScope(Dispatchers.IO).launch {
                                    val films = loadFilmsJson(context)
                                    getDatabase(context).filmDao().apply {
                                        films.forEach { insertFilm(it) }
                                    }
                                    val chemicals = loadChemicalsJson(context)
                                    getDatabase(context).chemicalDao().apply {
                                        chemicals.forEach { insertChemical(it) }
                                    }
                                }
                            }
                        })
                    .fallbackToDestructiveMigration() // recriar a bd em versões novas
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}