package com.lucasbytes.yelpit.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lucasbytes.yelpit.R
import com.lucasbytes.yelpit.data.DataAdapter
import com.lucasbytes.yelpit.data.DataRepository
import com.lucasbytes.yelpit.model.Data
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var dataResults: RecyclerView
    private lateinit var dataAdapter: DataAdapter
    private lateinit var dataResultsLayoutManager: LinearLayoutManager

    private var dataResultsOffset = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataResults = findViewById(R.id.recyclerBusinessesList)
        dataResultsLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        dataResults.layoutManager = dataResultsLayoutManager
        dataAdapter = DataAdapter(mutableListOf(),  { data -> showDataDetails(data)}, this)
        dataResults.adapter = dataAdapter

        btnSubmitSearch.setOnClickListener {
            hideKeyboard()
            getDataResults()
        }
    }

    private fun AppCompatActivity.hideKeyboard(){
        val imm : InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    private fun showDataDetails (data : Data) {
        val intent = Intent(this, BusinessDetailsActivity::class.java)
        intent.putExtra("extra_business_backdrop", data.boxArtUrl)


        startActivity(intent)
    }

    private fun getDataResults() {
        DataRepository.getGameResults(
            ::onSuccess,
            ::onError
        )
    }

    private fun onSuccess(datas: List<Data>) {
        dataAdapter.appendDatas(datas)
        attachDataOnScrollListener()
    }

    private fun onError() {
        Toast.makeText(this, "Check Internet", Toast.LENGTH_SHORT).show()
    }

    private fun attachDataOnScrollListener() {
        dataResults.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val totalItemCount = dataResultsLayoutManager.itemCount

                val visibleItemCount =  dataResultsLayoutManager.findLastVisibleItemPosition() - dataResultsLayoutManager.findFirstVisibleItemPosition()

                val firstVisibleItem = dataResultsLayoutManager.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    Log.d("MainActivity:vitemCount", "${visibleItemCount}")
                    Log.d("MainActivity:itemCount", "${totalItemCount}")

                    dataResults.removeOnScrollListener(this)
                    dataResultsOffset += 20
                    getDataResults()
                }
            }
        })
    }
}
