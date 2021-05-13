package com.example.diffutiltest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.select_item.view.*

class RadioAdapter(private val callback: Call) : RecyclerView.Adapter<RadioViewHolder>() {
    private val diffUtil = AsyncListDiffer(this, DiffUtilCallBack)

    interface Call {
        fun callBack(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioViewHolder {
        return RadioViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: RadioViewHolder, position: Int) {
        holder.bindView((diffUtil.currentList[position] as RadioDataModel), callback)
    }

    fun setData(item: List<RadioDataModel>) {
        DataManager.data = item
        diffUtil.submitList(item)
    }
}

class RadioViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.select_item, parent, false)
) {

    private val radioBtn = itemView.radioBtn
    private val radioText = itemView.radioText
    fun bindView(item: RadioDataModel, callback: RadioAdapter.Call) {
        radioBtn.isChecked = item.selected
        radioText.text = item.itemVal

        radioBtn.setOnClickListener {
            callback.callBack(item.key)
        }
    }
}

object DiffUtilCallBack : DiffUtil.ItemCallback<DiffUtilCallbackSelectorInterface>() {
    override fun areItemsTheSame(
        oldItem: DiffUtilCallbackSelectorInterface,
        newItem: DiffUtilCallbackSelectorInterface
    ): Boolean {
        return oldItem.keyValue() == newItem.keyValue()
    }

    override fun areContentsTheSame(
        oldItem: DiffUtilCallbackSelectorInterface,
        newItem: DiffUtilCallbackSelectorInterface
    ): Boolean {
        return oldItem.contentValue() == newItem.contentValue()
    }
}