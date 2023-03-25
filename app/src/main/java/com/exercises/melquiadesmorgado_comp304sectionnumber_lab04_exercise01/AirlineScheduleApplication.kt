package com.exercises.melquiadesmorgado_comp304sectionnumber_lab04_exercise01

import android.app.Application
import com.exercises.melquiadesmorgado_comp304sectionnumber_lab04_exercise01.database.AppDatabase

class AirlineScheduleApplication: Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}