package com.myproject.vked2026.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.myproject.vked2026.data.datasource.local.dao.AppItemDao
import com.myproject.vked2026.data.datasource.local.entity.AppItemEntity
import com.myproject.vked2026.domain.models.AppItem

@Database(
    entities = [AppItemEntity::class ],
    version=1
)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun appItemDao(): AppItemDao

    companion object {
        @Volatile
        private var INSTANCE: LocalDatabase? = null

        fun getInstance(context: Context): LocalDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    LocalDatabase::class.java,
                    "Some_database"
                )
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}