package com.example.preapp.ui.mainFragment

import androidx.recyclerview.widget.DiffUtil
import com.example.preapp.data.model.CatInformation

class CatInformationDiffUtil : DiffUtil.ItemCallback<CatInformation>() {
    override fun areItemsTheSame(oldItem: CatInformation, newItem: CatInformation): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CatInformation, newItem: CatInformation): Boolean {
        return oldItem==newItem
    }
}