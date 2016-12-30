package com.github.okwrtdsh.roulette

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import butterknife.bindView
import com.github.okwrtdsh.roulette.model.Pocket

class PocketRecyclerAdapter(val pockets: List<Pocket> = emptyList(), val runScroll: () -> Unit) :
    RecyclerView.Adapter<PocketRecyclerAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pocket = pockets[position % pockets.size]
        var count = 0
        holder.numberTextView.text = pocket.number.toString()
        holder.commentTextView.text = pocket.comment
        holder.itemView.setOnClickListener { view ->
            when (count++ % 2) {
                0    -> Toast.makeText(view.context, "toast_number: ${pocket.toast}", Toast.LENGTH_SHORT).show()
                else -> runScroll()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.pocket_layout, parent, false))

    override fun getItemCount(): Int = Int.MAX_VALUE

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val numberTextView: TextView by bindView(R.id.number)
        val commentTextView: TextView by bindView(R.id.comment)
    }
}