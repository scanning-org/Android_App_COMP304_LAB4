package com.exercises.melquiadesmorgado_comp304sectionnumber_lab04_exercise01.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.exercises.melquiadesmorgado_comp304sectionnumber_lab04_exercise01.database.schedule.Schedule
import com.exercises.melquiadesmorgado_comp304sectionnumber_lab04_exercise01.database.schedule.ScheduleDao

/**
 * Defines a database and specifies data tables that will be used.
 * Version is incremented as new tables/columns are added/removed/changed.
 * You can optionally use this class for one-time setup, such as pre-populating a database.
 */
@Database(entities = arrayOf(Schedule::class), version = 2, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database")
                    .createFromAsset("database/airline_schedule.db")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}