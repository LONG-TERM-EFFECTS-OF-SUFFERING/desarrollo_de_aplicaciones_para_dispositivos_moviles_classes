package com.example.classes.project_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.classes.databinding.Project1WebBinding


class WebFragment : Fragment() {
    private lateinit var binding: Project1WebBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Project1WebBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup_web_view()
    }

    private fun setup_web_view(){
        val url = "https://play.google.com/store/apps/details?id=com.nequi.MobileApp&hl=es-CO"
        binding.wvRateApp.apply {
            settings.apply {
                javaScriptEnabled = true
            }
            loadUrl(url)
        }
    }
}