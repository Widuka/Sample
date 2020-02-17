package com.sample.utils.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

abstract class BaseViewHolder<T>(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {
    abstract fun bind(item: T, onClick: ((T) -> Unit)?)
}