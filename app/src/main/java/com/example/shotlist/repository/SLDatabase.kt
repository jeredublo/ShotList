package com.example.shotlist.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shotlist.w_project.data_structs.Project

// TODO: For each entity, need @PrimaryKey + @Entity on the data classes
@Database(entities = [Project::class], version = 1)
// @TypeConverters(ExampleTypeConverter::class) TODO: create one from template in case objects contain non-primitive values
abstract class SLDatabase : RoomDatabase() {
    abstract fun slDao(): SLDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: SLDatabase? = null

        fun getDatabase(context: Context): SLDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SLDatabase::class.java,
                    "sl_database.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}