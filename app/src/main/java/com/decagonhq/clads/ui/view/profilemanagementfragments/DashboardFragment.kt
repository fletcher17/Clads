package com.decagonhq.clads.ui.view.profilemanagementfragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.decagonhq.clads.R
import com.decagonhq.clads.data.entity.ClientModel
import com.decagonhq.clads.databinding.FragmentDashboardBinding
import com.decagonhq.clads.ui.adapters.recyclerviewadapters.DashBoardClientAdapter
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var clientList: ArrayList<ClientModel>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createChatData()

        displayListOfClient()
        binding.fragmentDashboardClientListRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL, false
        )
        binding.fragmentDashboardClientListRecyclerView.adapter = DashBoardClientAdapter(clientList)

        // Populating the date spinner
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.fragment_dashboard_dates_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.fragmentDashboardDatesSpinner.adapter = adapter
        }
    }

    private fun displayListOfClient() {
        clientList = arrayListOf(
            ClientModel("Ola", "Machiaveli", "Lagos"),
            ClientModel("Jones", "Philips", "Jos"),
            ClientModel("Wilson", "Kent", "Owerri"),
            ClientModel("Mubarak", "Yang", "Yola"),
            ClientModel("Michael", "Jenkins", "Imo"),
        )
    }

    private fun createChatData() {
        val chart = binding.lineChart
        val entries = ArrayList<String>()
        entries.add("Jan")
        entries.add("Feb")
        entries.add("Mar")
        entries.add("Apr")
        entries.add("May")
        entries.add("Jun")
        entries.add("Jul")
        entries.add("Aug")
        entries.add("Sept")
        entries.add("Oct")
        entries.add("Nov")
        entries.add("Dec")

        val lineEntry = ArrayList<Entry>()
        lineEntry.add(Entry(30f, 0))
        lineEntry.add(Entry(50f, 1))
        lineEntry.add(Entry(40f, 2))
        lineEntry.add(Entry(35f, 3))
        lineEntry.add(Entry(45f, 4))
        lineEntry.add(Entry(59f, 5))
        lineEntry.add(Entry(40f, 6))
        lineEntry.add(Entry(52f, 7))
        lineEntry.add(Entry(49f, 8))
        lineEntry.add(Entry(60f, 9))
        lineEntry.add(Entry(55f, 10))
        lineEntry.add(Entry(65f, 11))

        // color to fill the chart
        val chartGraphColor = Color.argb(100, 116, 158, 218)

        val lineDataSet = LineDataSet(lineEntry, "Client")
        lineDataSet.color = resources.getColor(R.color.chart_blue)
        lineDataSet.setDrawValues(false)
        lineDataSet.setDrawFilled(true)
        lineDataSet.lineWidth = 2.5f
        lineDataSet.fillColor = chartGraphColor
        lineDataSet.setCircleColor(Color.BLUE)
        lineDataSet.color = R.color.red
        lineDataSet.setDrawFilled(true)
        lineDataSet.circleHoleColor
        lineDataSet.fillAlpha = 58

        val data = LineData(entries, lineDataSet)
        chart.data = data
        chart.setBackgroundColor(resources.getColor(R.color.white))
        chart.animateXY(3000, 3000)

        chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chart.xAxis.setDrawGridLines(false)
        chart.xAxis.textSize = 6f
        chart.xAxis.labelRotationAngle = -90f
        chart.xAxis.axisLineColor = R.color.deep_sky_blue

        chart.setDescription(null)
        chart.isDragEnabled = true
        chart.setGridBackgroundColor(R.color.deep_sky_blue)

        chart.axisRight.isEnabled = false
        chart.axisLeft.setDrawGridLines(false)
        chart.axisLeft.textSize = 6f
        chart.axisLeft.axisLineColor = R.color.deep_sky_blue

        chart.setTouchEnabled(true)
        chart.setDrawGridBackground(false)
        chart.setNoDataText("No data yet!")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
