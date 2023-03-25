package com.exercises.melquiadesmorgado_comp304sectionnumber_lab04_exercise01.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.exercises.melquiadesmorgado_comp304sectionnumber_lab04_exercise01.database.schedule.Schedule
import com.exercises.melquiadesmorgado_comp304sectionnumber_lab04_exercise01.database.schedule.ScheduleDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class AirlineListViewModel (private val scheduleDao: ScheduleDao): ViewModel(){
    fun fullSchedule(): Flow<List<Schedule>> {
        return scheduleDao.getAll()
            .onEach { schedules ->
                Log.d("AirlineListViewModel", "fullSchedule: Emitted ${schedules.size} schedules")
                schedules.forEachIndexed { index, schedule ->
                    Log.d("AirlineListViewModel", "fullSchedule: Schedule[$index] = $schedule")
                }
            }
    }

    fun scheduleForAirlineName(name: String): Flow<List<Schedule>> = scheduleDao.getByAirlineName(name)
}

class AirlineScheduleViewModelFactory(
    private val scheduleDao: ScheduleDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AirlineListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AirlineListViewModel(scheduleDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}