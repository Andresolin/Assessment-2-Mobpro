package org.d3if3002.miniproject2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.d3if3002.miniproject2.model.Pemesanan

@Database(entities = [Pemesanan::class], version = 1, exportSchema = false)
abstract class PemesananDb : RoomDatabase() {

    abstract val dao: PemesananDao

    companion object {

        @Volatile
        private var INSTANCE: PemesananDb? = null

        fun getInstance(context: Context): PemesananDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PemesananDb::class.java,
                        "pemesanan.db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }

}