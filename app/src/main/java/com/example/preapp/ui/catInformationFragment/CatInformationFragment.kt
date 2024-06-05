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
import com.example.preapp.data.model.CatInformation
import com.example.preapp.databinding.FragmentCatInformationBinding
import com.example.preapp.ioc.ApplicationComponent
import com.example.preapp.ui.catInformationFragment.CatInformationFragmentArgs.Companion.fromBundle
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
        binding = FragmentCatInformationBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Это нормально или есть способ сделать по лучше,
        //при этом оставшись на том же уровне минимальной версии ОС?
        val catInformation = fromBundle(requireArguments()).catInformation as? CatInformation

        Picasso.get()
            .load(catInformation?.imageUrl)
            .resize(
                resources.getInteger(R.integer.cat_image_width),
                resources.getInteger(R.integer.cat_image_height)
            ).into(binding.catImage)


        binding.catDescription.text = catInformation?.breedDesc
        binding.catCountry.text = catInformation?.origin
        binding.catMaxAge.text = catInformation?.lifeSpan

        val wikiUrl = catInformation?.wikiUrl

        binding.knowMoreInformationAboutCatButton.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(wikiUrl))
            startActivity(browserIntent)
        }
        binding.backToMainFragmentButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}