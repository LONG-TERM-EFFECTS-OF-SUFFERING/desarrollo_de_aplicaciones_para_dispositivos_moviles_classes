package com.example.classes.class_07

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.classes.databinding.Class07ItemAnimeGirlBinding


class RecyclerAdapter(private val anime_girl_list:MutableList<AnimeGirl>):
    RecyclerView.Adapter <RecyclerViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding = Class07ItemAnimeGirlBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return RecyclerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return anime_girl_list.size
    }

    override fun onBindViewHolder(recyclerViewHolder:RecyclerViewHolder, position: Int) {
        val girl = anime_girl_list[position]
        recyclerViewHolder.set_anime_girl_item(girl)
    }
}