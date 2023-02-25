package com.example.pix

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.pix.databinding.ItemImageBinding

class PixAdapter(private var list:ArrayList<Hit>) : Adapter<PixAdapter.PixViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixViewHolder {
        return PixViewHolder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PixViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class PixViewHolder(private var binding: ItemImageBinding) : ViewHolder(binding.root) {
        fun onBind(hit: Hit){
            binding.image.load(hit.largeImageURL)
        }
    }
}