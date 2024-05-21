package com.example.preapp.ui.mainFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.preapp.App
import com.example.preapp.data.model.HttpResponceState
import com.example.preapp.databinding.FragmentMainBinding
import com.example.preapp.ioc.ApplicationComponent

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {

    val applicationComponent: ApplicationComponent
        get() = App.getApplicationInstance().applicationComponent

    lateinit var binding: FragmentMainBinding

    private val viewModel: MainFragmentViewModel by viewModels { applicationComponent.getMainViewModelFactory() }

    private val adapter = CatsListAdapter(
        goToCatInformationFragmentCallBack = ::goToCatInformationFragment,
        catInformationDiffUtil = CatInformationDiffUtil()
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)

        viewModel.updateCatsList()

        setUpCatsList()

        return binding.root
    }

    private fun setUpCatsList() {
        val recyclerView = binding.catsList
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter

        viewModel.cats.observe(viewLifecycleOwner) { httpResponceState ->
            when (httpResponceState) {
                is HttpResponceState.Success -> {
                    adapter.submitList(httpResponceState.value)
                }

                else -> {
                    Toast.makeText(
                        requireContext(),
                        "Проверьте своё подключение к интернету",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }


    private fun goToCatInformationFragment(
        breedDesc: String,
        imageUrl: String,
        origin: String,
        lifeSpan: String,
        wikiUrl: String
    ) {
        val action = MainFragmentDirections.actionMainFragmentToCatInformationFragment(
            breedDesc,
            imageUrl,
            origin,
            lifeSpan,
            wikiUrl
        )
        findNavController().navigate(action)
    }
}