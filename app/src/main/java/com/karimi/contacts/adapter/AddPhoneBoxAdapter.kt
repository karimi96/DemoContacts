package com.karimi.contacts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import com.karimi.contacts.R
import com.karimi.contacts.activity.CreateContactActivity
import com.karimi.contacts.databinding.ListItemTextInputLayoutBinding
import com.karimi.contacts.model.Phone

class AddPhoneBoxAdapter(var i : Int , activity: CreateContactActivity ,var listPhone: ArrayList<Phone>) : RecyclerView.Adapter<AddPhoneBoxAdapter.MyViewHolder>(){

    private var activity1 : CreateContactActivity = activity
//    var listPhone : ArrayList<Phone> = ArrayList()


    inner class MyViewHolder(binding: ListItemTextInputLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var remove = binding.remove
        var phone = binding.etPhone1

        init { remove(remove , position) }

        }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemTextInputLayoutBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item_text_input_layout, parent, false)
        return MyViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.phone.hint = listPhone[position].phone
//        holder.phone.id =
    }


    override fun getItemCount(): Int {
        return i
    }


     fun remove(removeBtn: TextView , position: Int){
           removeBtn.setOnClickListener {
                notifyItemRemoved(position)
                i--
                activity1.count = i
            }
    }


}
