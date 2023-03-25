package com.exercises.melquiadesmorgado_comp304sectionnumber_lab04_exercise01

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exercises.melquiadesmorgado_comp304sectionnumber_lab04_exercise01.databinding.FragmentAirlineScheduleBinding
import com.exercises.melquiadesmorgado_comp304sectionnumber_lab04_exercise01.databinding.FragmentDetailedScheduleBinding
import com.exercises.melquiadesmorgado_comp304sectionnumber_lab04_exercise01.viewmodels.AirlineListViewModel
import com.exercises.melquiadesmorgado_comp304sectionnumber_lab04_exercise01.viewmodels.AirlineScheduleViewModelFactory
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailedScheduleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailedScheduleFragment : Fragment() {

    companion object {
        var AIRLINE_NAME = "airlineName"
    }

    private var _binding: FragmentDetailedScheduleBinding?= null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var airlineName: String

    private val viewModel: AirlineListViewModel by activityViewModels {
        AirlineScheduleViewModelFactory(
            (activity?.application as AirlineScheduleApplication).database.scheduleDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            airlineName = it.getString(AIRLINE_NAME).toString()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailedScheduleBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val detailedScheduleAdapter = DetailedScheduleAdapter({})
        // by passing in the airline name, filtered results are returned,
        // and tapping rows won't trigger navigation
        recyclerView.adapter = detailedScheduleAdapter
        lifecycle.coroutineScope.launch {
            viewModel.scheduleForAirlineName(airlineName).collect() {
                detailedScheduleAdapter.submitList(it)
            }
        }
    }

}