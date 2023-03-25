package com.exercises.melquiadesmorgado_comp304sectionnumber_lab04_exercise01.database.schedule

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Provides access to read/write operations on the schedule table.
 * Used by the view models to format the query results for use in the UI.
 */
@Dao
interface ScheduleDao {

    @Query("SELECT * FROM schedule ORDER BY arrival_time ASC")
    fun getAll(): Flow<List<Schedule>>

    @Query("SELECT * FROM schedule WHERE airline_name = :airlineName ORDER BY arrival_time ASC")
    fun getByAirlineName(airlineName: String): Flow<List<Schedule>>
}
