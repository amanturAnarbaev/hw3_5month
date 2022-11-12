package com.example.hw3_5month

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.hw3_5month.databinding.ItemBinding

class ImageAdapter(private val list : MutableList<ImageModel>):RecyclerView.Adapter<ImageAdapter.ViewHolder>(){
    class ViewHolder(private val binding: ItemBinding ) : RecyclerView.ViewHolder (binding.root){
        fun onBind(item : ImageModel){
            binding.imagemItem.load(item.largeImageURL)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
return ViewHolder(
    ItemBinding.inflate(LayoutInflater.from(parent.context),parent , false)
)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getNewElemets(list : MutableList<ImageModel>){
        this.list.addAll(list)
        notifyDataSetChanged()
    }

}