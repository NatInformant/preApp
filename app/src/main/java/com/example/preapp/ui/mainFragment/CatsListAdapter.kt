package com.example.preapp.ui.mainFragment

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.preapp.R
import com.example.preapp.data.model.CatInformation
import com.example.preapp.databinding.CatElementBinding
import com.squareup.picasso.Picasso

class CatsListAdapter(
    private val goToCatInformationFragmentCallBack: (CatInformation) -> Unit,
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
        private val goToCatInformationFragmentCallBack: (CatInformation) -> Unit,
        private val catElementBinding: CatElementBinding
    ) :
        RecyclerView.ViewHolder(catElementBinding.root) {

        fun onBind(catInformation: CatInformation) {
            catElementBinding.catBreed.text = catInformation.breedName

            catElementBinding.root.setOnClickListener {
                goToCatInformationFragmentCallBack(
                    catInformation
                )
            }


            Picasso.get()
                .load(catInformation.imageUrl)
                .resize(
                    catElementBinding.root.context.resources.getInteger(R.integer.cat_image_width),
                    catElementBinding.root.context.resources.getInteger(R.integer.cat_image_height)
                ).into(catElementBinding.catImage)

        }
    }
}