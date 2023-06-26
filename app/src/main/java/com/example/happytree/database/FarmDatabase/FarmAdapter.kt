package com.example.happytree.database.FarmDatabase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.happytree.R

class FarmAdapter : RecyclerView.Adapter<FarmAdapter.MyViewHolder>() {

    private var itemList = emptyList<Farm>()
    private var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(farm: Farm)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.recyclelayout, parent, false
        )
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = itemList[position]

        holder.itemView.findViewById<TextView>(R.id.txtDisease).text = currentItem.disease
        holder.itemView.findViewById<TextView>(R.id.txtDate).text = currentItem.dateTime
        holder.itemView.findViewById<TextView>(R.id.txtNumTree).text =
            currentItem.numberOfTrees.toString()
        holder.itemView.findViewById<CardView>(R.id.cv).setOnClickListener {
            onItemClickListener?.onItemClick(currentItem)
        }
    }

    override fun getItemCount() = itemList.size

    fun setData(item: List<Farm>) {
        this.itemList = item
        notifyDataSetChanged()
    }
}
