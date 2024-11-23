package com.example.classes.project_1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.classes.R
import com.example.classes.databinding.Project1HomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding: Project1HomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Project1HomeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controllers()
    }

    private fun controllers() {
        binding.btnRateApp.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_webFragment)
        }

        binding.btnShareApp.setOnClickListener {
            val send_intent = Intent().apply {
                action = Intent.ACTION_SEND

                val app_title = "Bottle Flip App"
                val slogan = "Only the brave play it!"
                val download_url = "https://play.google.com/store/apps/details?id=com.nequi.MobileApp&hl=es_419&gl=es"
                val message = "$app_title\n$slogan\n$download_url"

                putExtra(Intent.EXTRA_TEXT, message)
                type = "text/plain"
            }

            val share_intent = Intent.createChooser(send_intent, "Share app via")
            it.context.startActivity(share_intent)
        }
    }
}