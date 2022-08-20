package com.example.multiviewsrecycler.common

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding


class BaseListAdapter<T> : ListAdapter<Any, BaseViewHolder<T>>(DiffUtils()) {

    var hViewHolderBinding: ((T, ViewBinding) -> Unit)? = null

    var hOnCreateViewHolder: ((ViewGroup) -> ViewBinding)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return hOnCreateViewHolder?.let { it(parent) }
            ?.let { BaseViewHolder(it, hViewHolderBinding!!) }!!
    }


    class DiffUtils<T : Any> : androidx.recyclerview.widget.DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem

        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        val item = getItem(position)
        holder.bind(item as T)
    }
}