package com.example.preapp.ui.mainFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.preapp.data.model.CatInformation
import com.example.preapp.databinding.CatElementBinding
import com.squareup.picasso.Picasso

class CatsListAdapter(
    private val goToCatInformationFragmentCallBack: (String, String, String, String, String) -> Unit,
    catInformationDiffUtil: CatInformationDiffUtil
) : ListAdapter<CatInformation, CatsListAdapter.CatElementViewHolder>(catInformationDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatElementViewHolder {
        val catElementBinding =
            CatElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatElementViewHolder(goToCatInformationFragmentCallBack, catElementBinding)
    }

    override fun onBindViewHolder(holder: CatElementViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }


    class CatElementViewHolder(
        private val goToCatInformationFragmentCallBack: (String, String, String, String, String) -> Unit,
        private val catElementBinding: CatElementBinding
    ) :
        RecyclerView.ViewHolder(catElementBinding.root) {

        fun onBind(catInformation: CatInformation) {
            catElementBinding.catBreed.text = catInformation.breedName
            catElementBinding.root.setOnClickListener {
                goToCatInformationFragmentCallBack(
                    catInformation.breedDesc,
                    catInformation.imageUrl,
                    catInformation.origin,
                    catInformation.lifeSpan,
                    catInformation.wikiUrl
                )
            }

            Picasso.get()
                .load(catInformation.imageUrl)
                .resize(600, 400).into(catElementBinding.catImage)

        }
    }
}