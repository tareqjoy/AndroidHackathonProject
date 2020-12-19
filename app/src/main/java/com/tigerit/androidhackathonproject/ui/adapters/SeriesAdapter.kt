package com.tigerit.androidhackathonproject.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tigerit.androidhackathonproject.R
import com.tigerit.androidhackathonproject.databinding.ItemSeriesBinding
import com.tigerit.androidhackathonproject.models.SingleSeries

class SeriesAdapter(val context: Context, val clickListener: (SingleSeries) -> Unit) :
    RecyclerView.Adapter<SeriesAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemSeriesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(series: SingleSeries, position: Int) {
            binding.series = series
            //binding.showDivider = !isLastItem(position)
            binding.executePendingBindings()
        }
    }

    private var series = ArrayList<SingleSeries>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: ItemSeriesBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.item_series, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val series = series[position]
        holder.bind(series, position)
        holder.binding.root.setOnClickListener { clickListener(series) }
    }

    override fun getItemCount(): Int {
        return series.size
    }

    fun setSeries(series: List<SingleSeries>) {
        this.series = series as ArrayList<SingleSeries>
        notifyDataSetChanged()
    }


}