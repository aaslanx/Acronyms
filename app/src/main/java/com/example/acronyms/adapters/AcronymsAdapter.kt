package com.example.acronyms.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.acronyms.R
import com.example.acronyms.model.Lf
import com.example.acronyms.utils.capitalized

class AcronymsAdapter(private val onClick: (lf: Lf) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val aList = mutableListOf<Lf>()

    fun submitData(data: List<Lf>) {
        aList.clear()
        aList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.row_acronym, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val context = holder.itemView.context

        if (holder is MyViewHolder) {
            val acronym = getItem(position)

            holder.apply {
                longForm.text = acronym.lf.capitalized()
                freq.text = "${context.getString(R.string.frequency)} ${acronym.freq}"
                since.text = "${context.getString(R.string.since)} ${acronym.since}"

                if (!acronym.vars.isNullOrEmpty()) {
                    vars.text = "${context.getString(R.string.variation)} ${acronym.vars.size}"
                    showButton.setOnClickListener {
                        onClick(acronym)
                    }
                } else {
                    vars.isVisible = false
                    showButton.isVisible = false
                }

            }
        }
    }

    override fun getItemCount(): Int = aList.size


    private fun getItem(position: Int): Lf {
        return aList[position]
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val longForm: TextView = view.findViewById(R.id.longForm)
        val freq: TextView = view.findViewById(R.id.freq)
        val since: TextView = view.findViewById(R.id.since)
        val vars: TextView = view.findViewById(R.id.vars)
        val showButton: TextView = view.findViewById(R.id.showMoreButton)
    }
}