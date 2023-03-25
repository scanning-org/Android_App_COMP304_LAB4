package com.exercises.melquiadesmorgado_comp304sectionnumber_lab04_exercise01

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.exercises.melquiadesmorgado_comp304sectionnumber_lab04_exercise01.database.schedule.Schedule
import com.exercises.melquiadesmorgado_comp304sectionnumber_lab04_exercise01.databinding.DetailedScheduleItemBinding
import java.util.*

class DetailedScheduleAdapter(
    private val onItemClicked: (Schedule) -> Unit
) : ListAdapter<Schedule, DetailedScheduleAdapter.DetailedScheduleViewHolder>(DiffCallback) {
    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Schedule>() {
            override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailedScheduleViewHolder {
        val viewHolder = DetailedScheduleViewHolder(
            DetailedScheduleItemBinding.inflate(
                LayoutInflater.from( parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: DetailedScheduleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class DetailedScheduleViewHolder(
        private var binding: DetailedScheduleItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(schedule: Schedule) {
//            var airlineName = binding.airlineNameTextView.text
//            Log.d("DEBUG-ADAPTER: ", "Airline Name $airlineName")
            binding.airlineNameTextView.text = schedule.airlineName
            binding.terminalNumberTextView.text = schedule.terminalNumber
            binding.statusTextView.text = schedule.status

        }
    }
}