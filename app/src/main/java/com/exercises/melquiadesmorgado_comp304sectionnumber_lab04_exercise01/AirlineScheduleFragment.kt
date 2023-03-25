package com.exercises.melquiadesmorgado_comp304sectionnumber_lab04_exercise01

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exercises.melquiadesmorgado_comp304sectionnumber_lab04_exercise01.databinding.FragmentAirlineScheduleBinding
import com.exercises.melquiadesmorgado_comp304sectionnumber_lab04_exercise01.viewmodels.AirlineListViewModel
import com.exercises.melquiadesmorgado_comp304sectionnumber_lab04_exercise01.viewmodels.AirlineScheduleViewModelFactory
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [AirlineScheduleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AirlineScheduleFragment : Fragment() {

    private var _binding: FragmentAirlineScheduleBinding?= null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView

    private val viewModel: AirlineListViewModel by activityViewModels {
        AirlineScheduleViewModelFactory(
            (activity?.application as AirlineScheduleApplication).database.scheduleDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAirlineScheduleBinding.inflate(inflater, container, false)
        val view = binding.root
        Log.d("DEBUG-FRAGMENT", "Inside onCreateView - AirlineScheduleFragment")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        Log.d("DEBUG-FRAGMENT", "Inside onViewCreateView - AirlineScheduleFragment- After binding RecyclerView")
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val airlineScheduleAdapter = AirlineScheduleAdapter({
            val action = AirlineScheduleFragmentDirections
                .actionAirlineScheduleFragmentToDetailedScheduleFragment(
                    airlineName = it.airlineName
                )
            view.findNavController().navigate(action)
        })
        recyclerView.adapter = airlineScheduleAdapter
        lifecycle.coroutineScope.launch {
            viewModel.fullSchedule().collect() {
                Log.d("DEBUG-FRAGMENT", "Inside Adapter - AirlineScheduleFragment")
                airlineScheduleAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}