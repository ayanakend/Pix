package com.example.pix

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.pix.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var adapter = PixAdapter(arrayListOf())
    var page: Int = 1
    var images = arrayListOf<Hit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickers()

    }

    private fun initClickers() {
        with(binding) {
            imageRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1)) {
                        ++page
                        moreRequest()
                    }
                }
            })
            btnMore.setOnClickListener {
                ++page
                moreRequest()
            }
            btnEnter.setOnClickListener {
                page = 1
                doRequest()
            }
        }
    }

    private fun ActivityMainBinding.doRequest() {
        PixService().api.getImages(
            pictureWord = etSearch.text.toString(), page = page
        ).enqueue(object : Callback<PixModel> {
            override fun onResponse(call: Call<PixModel>, response: Response<PixModel>) {
                if (response.isSuccessful) {
                    adapter = PixAdapter(response.body()?.hits!!)
                    imageRecycler.adapter = adapter
                    images = response.body()?.hits!!
                }
            }

            override fun onFailure(call: Call<PixModel>, t: Throwable) {
                Log.e("ololo", t.message.toString())
            }

        })
    }

    fun ActivityMainBinding.moreRequest() {
        PixService().api.getImages(
            pictureWord = etSearch.text.toString(), page = page
        ).enqueue(object : Callback<PixModel> {
            override fun onResponse(call: Call<PixModel>, response: Response<PixModel>) {
                if (response.isSuccessful) {
                    val moreImages = response.body()?.hits!!
                    images.addAll(moreImages)
                    adapter = PixAdapter(images)
                    imageRecycler.adapter = adapter
                }
            }

            override fun onFailure(call: Call<PixModel>, t: Throwable) {
                Log.e("ololo", t.message.toString())
            }

        })
    }

}