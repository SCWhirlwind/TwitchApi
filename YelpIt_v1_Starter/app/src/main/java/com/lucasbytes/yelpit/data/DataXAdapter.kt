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
import com.lucasbytes.yelpit.model.DataX

class DataXAdapter(
    private var dataXs: MutableList<DataX>,
    private var onDataXClick: (dataX: DataX) -> Unit,
    private var context: Context
) : RecyclerView.Adapter<DataXAdapter.DataXViewHolder>() {

    fun appendDataXs(dataXs: List<DataX>){
        this.dataXs.addAll(dataXs)
        notifyItemRangeInserted(this.dataXs.size, dataXs.size)
    }

    inner class DataXViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val poster: ImageView = itemView.findViewById(R.id.imgStreamPhoto)
        private val dataXName: TextView = itemView.findViewById((R.id.StreamName))
        private val viewerCount: TextView = itemView.findViewById((R.id.ViewCount))

        fun bind(dataX: DataX) {
            val origImageUrl :String = dataX.thumbnailUrl
            val scaledImageUrl :String = origImageUrl.replace("o.jpg", "l.jpg")

            Glide.with(itemView)
                .load(scaledImageUrl)
                .transform(CenterCrop())
                .into(poster)
            dataXName.text = dataX.userName
            viewerCount.text = dataX.viewerCount.toString()
            itemView.setOnClickListener {
                onDataXClick.invoke(dataX)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataXViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_business, parent, false)
        return DataXViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataXs.size
    }

    override fun onBindViewHolder(holder: DataXViewHolder, position: Int) {
        holder.bind(dataXs[position])
    }
}