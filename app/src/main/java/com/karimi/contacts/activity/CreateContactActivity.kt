package com.karimi.contacts.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.karimi.contacts.R
import com.karimi.contacts.adapter.AddPhoneBoxAdapter
import com.karimi.contacts.databinding.ActivityCreateContactBinding

class CreateContactActivity : AppCompatActivity() {
    lateinit var binding: ActivityCreateContactBinding

    lateinit var adapter: AddPhoneBoxAdapter
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_contact)


        binding.boxName.add.setOnClickListener {
            count++
            binding.boxName.recyclerAddUser.visibility = View.VISIBLE
            setRecycler()
        }

        binding.box1.imgClose.setOnClickListener { finish() }


    }

    private fun setRecycler() {
        adapter = AddPhoneBoxAdapter(count, this)
        binding.boxName.recyclerAddUser.adapter = adapter
    }

//    fun close(){
//        this.finish()
//    }


}