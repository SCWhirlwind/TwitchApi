package com.lucasbytes.yelpit.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.lucasbytes.yelpit.R
import com.lucasbytes.yelpit.data.DataAdapter
import com.lucasbytes.yelpit.data.DataRepository
import com.lucasbytes.yelpit.data.DataXAdapter
import com.lucasbytes.yelpit.model.Data
import com.lucasbytes.yelpit.model.DataX

const val BUSINESS_BACKDROP = "extra_business_backdrop"

class BusinessDetailsActivity : AppCompatActivity() {
    private lateinit var dataXResults: RecyclerView
    private lateinit var dataXAdapter: DataXAdapter
    private lateinit var dataXResultsLayoutManager: GridLayoutManager
    private lateinit var backdrop: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_business_details)

        backdrop = findViewById(R.id.business_backdrop)
        dataXResults = findViewById(R.id.recyclerStreamList)
        dataXResultsLayoutManager = GridLayoutManager(this, 4)

        dataXResults.layoutManager = dataXResultsLayoutManager
        dataXAdapter = DataXAdapter(mutableListOf(), { dataX -> showDataXDetails(dataX)}, this)
        dataXResults.adapter = dataXAdapter

        val extras = intent.extras

        if(extras != null) {
            businessDetails(extras)
        }
        else {
            finish()
        }
    }

    private fun showDataXDetails (dataX : DataX) {
    }

    private fun getDataXResults() {
        DataRepository.getStreamResults(
            ::onSuccess,
            ::onError
        )
    }

    private fun onSuccess(dataXs: List<DataX>) {
        dataXAdapter.appendDataXs(dataXs)
        attachDataXOnScrollListener()
    }

    private fun onError() {
        Toast.makeText(this, "Check Internet", Toast.LENGTH_SHORT).show()
    }

    private fun attachDataXOnScrollListener() {
        dataXResults.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val totalItemCount = dataXResultsLayoutManager.itemCount

                val visibleItemCount =  dataXResultsLayoutManager.findLastVisibleItemPosition() - dataXResultsLayoutManager.findFirstVisibleItemPosition()

                val firstVisibleItem = dataXResultsLayoutManager.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    Log.d("MainActivity:vitemCount", "${visibleItemCount}")
                    Log.d("MainActivity:itemCount", "${totalItemCount}")

                    dataXResults.removeOnScrollListener(this)
                    getDataXResults()
                }
            }
        })
    }

    private fun businessDetails(extras: Bundle) {
        extras.getString(BUSINESS_BACKDROP)?.let { imageURL ->
            Glide.with(this)
                .load(imageURL)
                .transform(CenterCrop())
                .into(backdrop)
        }

    }
}
