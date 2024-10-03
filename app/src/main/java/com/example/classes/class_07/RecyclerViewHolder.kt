package com.example.classes.class_07

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.classes.databinding.Class07ItemAnimeGirlBinding


class RecyclerViewHolder(binding: Class07ItemAnimeGirlBinding): RecyclerView.ViewHolder(binding.root) {
    val binding_ = binding

    fun set_anime_girl_item(anime_girl: AnimeGirl) {
        binding_.ivAnimeGirl.setImageResource(anime_girl.imagen_id)
        binding_.tvName.text = anime_girl.name
        binding_.tvDescription.text = anime_girl.description

        binding_.cvAnimeGirls.setOnClickListener {
            Toast.makeText(it.context, anime_girl.name, Toast.LENGTH_SHORT).show()
        }
    }
}