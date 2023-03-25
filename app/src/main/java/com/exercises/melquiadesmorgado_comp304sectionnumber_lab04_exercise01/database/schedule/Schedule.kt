package com.exercises.melquiadesmorgado_comp304sectionnumber_lab04_exercise01.database.schedule

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a single table in the database. Each row is a separate instance of the Schedule class.
 * Each property corresponds to a column. Additionally, an ID is needed as a unique identifier for
 * each row in the database.
 */
@Entity
data class Schedule  (
    @PrimaryKey val id: Int,
    @NonNull @ColumnInfo(name = "airline_name") val airlineName: String,
    @NonNull @ColumnInfo(name = "arrival_time") val arrivalTime: Int,
    @NonNull @ColumnInfo(name = "terminal_number") val terminalNumber: String
)
