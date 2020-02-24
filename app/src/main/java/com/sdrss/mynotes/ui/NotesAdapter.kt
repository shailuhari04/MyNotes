package com.sdrss.mynotes.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sdrss.mynotes.R
import com.sdrss.mynotes.pojo.User
import com.sdrss.mynotes.utils.gone
import kotlinx.android.synthetic.main.record_item_layout.view.*

class NotesAdapter : ListAdapter<User, NotesAdapter.NoteViewHolder>(NoteDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.record_item_layout,
                parent,
                false
            )
        )
    }

    fun swapData(data: List<User>) {
        submitList(data.toMutableList())
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.apply {
            with(item) {
                //name
                this@with.name?.let {
                    this@apply.tvName.text = it
                }

                //email
                this@with.email?.let {
                    this@apply.tvEmail.text = it
                }

                //phone
                this@with.phone?.let {
                    this@apply.tvPhone.text = it
                }

                //age
                this@with.age?.let {
                    this@apply.tvAge.text = "$it yr"
                }
            }

            //handle last item
            if (currentList.size - 1 == position) this@apply.viewDivider.gone()
        }
    }

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

private class NoteDiff : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(
        oldItem: User,
        newItem: User
    ) = oldItem.name == newItem.name

    override fun areContentsTheSame(
        oldItem: User,
        newItem: User
    ) = oldItem == newItem
}