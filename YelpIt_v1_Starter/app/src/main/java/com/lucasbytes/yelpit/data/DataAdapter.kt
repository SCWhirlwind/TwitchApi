package com.lucasbytes.yelpit.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.lucasbytes.yelpit.R
import com.lucasbytes.yelpit.model.Data

class DataAdapter(
    private var datas: MutableList<Data>,
    private var onDataClick: (data: Data) -> Unit,
    private var context: Context
) : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    fun appendDatas(datas: List<Data>){
        this.datas.addAll(datas)
        notifyItemRangeInserted(this.datas.size, datas.size)
    }

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val poster: ImageView = itemView.findViewById(R.id.imgGamePhoto)
        private val dataName: TextView = itemView.findViewById((R.id.GameName))

        fun bind(data: Data) {
            val origImageUrl :String = data.boxArtUrl
            val scaledImageUrl :String = origImageUrl.replace("o.jpg", "l.jpg")

            Glide.with(itemView)
                .load(scaledImageUrl)
                .transform(CenterCrop())
                .into(poster)
            dataName.text = data.name
            itemView.setOnClickListener {
                onDataClick.invoke(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_business, parent, false)
        return DataViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(datas[position])
    }
}