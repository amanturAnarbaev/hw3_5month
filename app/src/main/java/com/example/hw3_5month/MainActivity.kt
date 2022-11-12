package com.example.hw3_5month

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw3_5month.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var page = 1
    private var per_page=10
    private var adapter = ImageAdapter(mutableListOf())
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ------------------------------------------------------------  onScroll listener
//        val layoutManager = LinearLayoutManager(this)
//
//        binding.recycleImages.layoutManager = layoutManager
//
//        binding.recycleImages.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//
//                val visibleItemCount: Int = layoutManager.itemCount
//                val pastVisbleItem: Int = layoutManager.findFirstCompletelyVisibleItemPosition()
//                val total: Int =per_page
//                if (visibleItemCount + pastVisbleItem >= total) {
//
//                    getImagesPages()
//
//                }
//                super.onScrolled(recyclerView, dx, dy)
//            }
//        })
        //----------------------------------------------------------


        binding.buttomToNextPage.setOnClickListener {
            page++
            getImagesPages()
        }
        binding.buttom.setOnClickListener { getImages() }


    }

    private fun getImages() {
        App.api.getImages(q = binding.editText.text.toString(), per_page = per_page, page = page).enqueue(
            object : Callback<PixModel> {
                override fun onResponse(call: Call<PixModel>, response: Response<PixModel>) {
                    if (response.isSuccessful) {
                        adapter = ImageAdapter(response.body()!!.hits as MutableList<ImageModel>)
                    }
                    binding.recycleImages.adapter = adapter

                }

                override fun onFailure(call: Call<PixModel>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            }
        )
    }

    private fun getImagesPages() {
        App.api.getImages(q = binding.editText.text.toString(), per_page = per_page, page = page).enqueue(
            object : Callback<PixModel> {
                override fun onResponse(call: Call<PixModel>, response: Response<PixModel>) {
                    adapter.getNewElemets(response.body()!!.hits as MutableList<ImageModel>)
//                    per_page++

                }

                override fun onFailure(call: Call<PixModel>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            }
        )
    }

}