package com.example.multiviewsrecycler.common

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class BaseAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {
    var listOfItems: MutableList<T>? = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var hViewHolderBinding: ((T, ViewBinding) -> Unit)? = null

    var hOnCreateViewHolder: ((ViewGroup) -> ViewBinding)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return hOnCreateViewHolder?.let { it(parent) }
            ?.let { BaseViewHolder(it, hViewHolderBinding!!) }!!
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(listOfItems!![position])
    }

    override fun getItemCount(): Int {
        return listOfItems!!.size
    }

}