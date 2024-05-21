package com.example.preapp.ui.catInformationFragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.preapp.App
import com.example.preapp.R
import com.example.preapp.databinding.FragmentCatInformationBinding
import com.example.preapp.ioc.ApplicationComponent
import com.squareup.picasso.Picasso


/**
 * A simple [Fragment] subclass.
 * Use the [CatInformationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CatInformationFragment : Fragment() {


    lateinit var binding: FragmentCatInformationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCatInformationBinding.inflate(inflater)

        Picasso.get()
            .load(arguments?.getString("imageUrl"))
            .resize(600, 400).into(binding.catImage)

        binding.catDescription.text = arguments?.getString("breedDesc")
        binding.catCountry.text = arguments?.getString("origin")
        binding.catMaxAge.text = arguments?.getString("lifeSpan")

        val wikiUrl = arguments?.getString("wikiUrl")

        binding.knowMoreInformationAboutCatButton.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(wikiUrl))
            startActivity(browserIntent)
        }
        binding.backToMainFragmentButton.setOnClickListener{
            findNavController().navigate(R.id.action_catInformationFragment_to_mainFragment)
        }

        return binding.root
    }
}