package com.tigerit.androidhackathonproject.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tigerit.androidhackathonproject.R
import com.tigerit.androidhackathonproject.databinding.ItemTrendingBinding
import com.tigerit.androidhackathonproject.models.SingleTrendingContent


class TrendingContentAdapter(val context: Context, val clickListener: (SingleTrendingContent) -> Unit) :
        RecyclerView.Adapter<TrendingContentAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemTrendingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(content: SingleTrendingContent, position: Int) {
            binding.content = content
            //binding.showDivider = !isLastItem(position)
            binding.executePendingBindings()
        }
    }

    private var contents = ArrayList<SingleTrendingContent>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: ItemTrendingBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.item_trending, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val series = contents[position]

        holder.bind(series, position)
        holder.binding.root.setOnClickListener { clickListener(series) }
    }

    override fun getItemCount(): Int {
        return contents.size
    }

    fun setSeries(series: List<SingleTrendingContent>) {
        this.contents = series as ArrayList<SingleTrendingContent>
        notifyDataSetChanged()
    }


}