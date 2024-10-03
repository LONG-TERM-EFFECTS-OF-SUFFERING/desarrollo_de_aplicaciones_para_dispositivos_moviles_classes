package com.example.classes.class_07.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.classes.R
import com.example.classes.class_07.AnimeGirl
import com.example.classes.class_07.RecyclerAdapter
import com.example.classes.databinding.Class07RecyclerFragmentBinding


class RecyclerFragment : Fragment() {
    private lateinit var binding: Class07RecyclerFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Class07RecyclerFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler()
    }

    fun recycler() {
        var anime_girl_list = mutableListOf(
            AnimeGirl(R.drawable.anime_girl, "Anime girl", "anime girl description"),
            AnimeGirl(R.drawable.anime_girl, "Usagi Tsukino", "The main protagonist of Sailor Moon, known for her magical abilities as Sailor Moon."),
            AnimeGirl(R.drawable.anime_girl, "Asuka Langley", "A main character from Neon Genesis Evangelion, known for piloting the Evangelion Unit-02."),
            AnimeGirl(R.drawable.anime_girl, "Mikasa Ackerman", "A main character from Attack on Titan, known for her exceptional combat skills."),
            AnimeGirl(R.drawable.anime_girl, "Rem", "A character from Re:Zero, known for her loyalty and combat abilities."),
            AnimeGirl(R.drawable.anime_girl, "Lucy Heartfilia", "A main character from Fairy Tail, known for her celestial spirit magic."),
            AnimeGirl(R.drawable.anime_girl, "Sakura Haruno", "A main character from Naruto, known for her medical ninjutsu and superhuman strength."),
            AnimeGirl(R.drawable.anime_girl, "Hinata Hyuga", "A character from Naruto, known for her Byakugan and gentle fist fighting style."),
        )

        val recycler = binding.recyclerview
        recycler.layoutManager = LinearLayoutManager(context)// todo: lista vertical
        // recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false) // Horizontal list
        // val columns = 2
        // recycler.layoutManager = GridLayoutManager(context, columns) // Table
        val adapter = RecyclerAdapter(anime_girl_list)
        recycler.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}
