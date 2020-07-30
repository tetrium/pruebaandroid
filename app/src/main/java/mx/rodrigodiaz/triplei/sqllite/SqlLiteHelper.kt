package mx.rodrigodiaz.triplei.sqllite

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import mx.rodrigodiaz.triplei.entities.StoreData


@Database(entities = [ StoreData::class], version = 1, exportSchema = false)
abstract class SqlLiteHelper : RoomDatabase() {

    //abstract fun cardDAO(): CardDAO
    abstract fun storeDao(): StoreDAO


    companion object {

        @Volatile private var INSTANCE: SqlLiteHelper? = null

        fun getDatabase(context: Context): SqlLiteHelper =
            INSTANCE
                ?: synchronized(this) { INSTANCE
                    ?: buildDatabase(
                        context
                    )
                        .also { INSTANCE = it } }

        private fun buildDatabase(ctx: Context): SqlLiteHelper
        {
            ctx.deleteDatabase("stores.db")
            var ret= Room.databaseBuilder(
                ctx.applicationContext,
                SqlLiteHelper::class.java,
                "stores.db"
            )

                .fallbackToDestructiveMigration()
                .build()
            return ret!!
        }
    }
}