package com.karimi.contacts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.karimi.contacts.R
import com.karimi.contacts.activity.CreateContactActivity
import com.karimi.contacts.activity.MainActivity
import com.karimi.contacts.databinding.ListItemTextInputLayoutBinding

class AddPhoneBoxAdapter(var i : Int , activity: CreateContactActivity) : RecyclerView.Adapter<AddPhoneBoxAdapter.MyViewHolder>(){

    private var activity1 : CreateContactActivity = activity



    inner class MyViewHolder(binding: ListItemTextInputLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
            var remove = binding.remove

        init {
            remove.setOnClickListener {
            notifyItemRemoved(position)
                i--
                activity1.count = i
            }
        }

        }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemTextInputLayoutBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item_text_input_layout, parent, false)
        return MyViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }


    override fun getItemCount(): Int {
        return i
    }

}
