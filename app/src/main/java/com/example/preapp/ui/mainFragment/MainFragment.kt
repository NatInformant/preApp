package com.example.preapp.ui.mainFragment

import android.content.Context
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
import com.example.preapp.data.model.CatInformation
import com.example.preapp.data.model.HttpResponceState
import com.example.preapp.databinding.FragmentMainBinding
import com.example.preapp.ioc.ApplicationComponent
import com.example.preapp.ioc.MainViewModelFactory
import com.example.preapp.ioc.applicationComponent
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment () : Fragment() {


    //А есть более элегантный способ?
    @Inject
    lateinit var viewModelFactory: MainViewModelFactory
    private val viewModel: MainFragmentViewModel by viewModels { viewModelFactory }

    lateinit var binding: FragmentMainBinding
    private val adapter = CatsListAdapter(
        goToCatInformationFragmentCallBack = ::goToCatInformationFragment,
        catInformationDiffUtil = CatInformationDiffUtil()
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext().applicationComponent.injectMainFragment(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.updateCatsList()
        setUpCatsList()
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
                is HttpResponceState.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        "Проверьте своё подключение к интернету",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {

                }
            }
        }
    }


    private fun goToCatInformationFragment(
        catInformation: CatInformation
    ) {
        val action = MainFragmentDirections.actionMainFragmentToCatInformationFragment(
            catInformation
        )
        findNavController().navigate(action)
    }
}