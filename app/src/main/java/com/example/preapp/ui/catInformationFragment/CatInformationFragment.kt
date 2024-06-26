package com.example.preapp.ui.catInformationFragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import coil.size.Scale
import com.example.preapp.App
import com.example.preapp.R
import com.example.preapp.data.model.CatInformation
import com.example.preapp.databinding.FragmentCatInformationBinding
import com.example.preapp.ioc.ApplicationComponent
import com.example.preapp.ui.catInformationFragment.CatInformationFragmentArgs.Companion.fromBundle


/**
 * A simple [Fragment] subclass.
 * Use the [CatInformationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CatInformationFragment : Fragment() {


    lateinit var binding: FragmentCatInformationBinding
    private val catInformationFragmentArgs by navArgs<CatInformationFragmentArgs>()
    private val catInformation by lazy { catInformationFragmentArgs.catInformation }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCatInformationBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.catImage.load(catInformation.imageUrl)


        binding.catDescription.text = catInformation.breedDesc
        binding.catCountry.text = catInformation.origin
        binding.catMaxAge.text = catInformation.lifeSpan

        val wikiUrl = catInformation.wikiUrl

        binding.knowMoreInformationAboutCatButton.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(wikiUrl))
            startActivity(browserIntent)
        }
        binding.backToMainFragmentButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}