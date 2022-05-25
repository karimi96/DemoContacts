package com.karimi.contacts.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.karimi.contacts.R
import com.karimi.contacts.adapter.AddPhoneBoxAdapter
import com.karimi.contacts.databinding.ActivityCreateContactBinding
import com.karimi.contacts.model.Phone
import java.util.ArrayList as ArrayList1

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

        close()



    }

    private fun setRecycler() {
        var list : ArrayList<Phone> = ArrayList()
        list.add(0, Phone("home"))
        list.add(1, Phone("work"))
        var i = 0
        while (i<count){
            list.add(Phone("other"))
            i++
        }
//        list.add("home")
//        list.add("work")
//        list.add("other")
        adapter = AddPhoneBoxAdapter(count, this , list)
        binding.boxName.recyclerAddUser.adapter = adapter
    }

    private fun close(){
        binding.box1.imgClose.setOnClickListener { finish() }
    }


    private fun save(){
        binding.box1.save
        var firstName = binding.boxName.etFirstName
        var lastName = binding.boxName.etLastName
        var phone = binding.boxName.etPhone
        var email = binding.boxName.etEmail
    }


    private fun addPicture(){
        binding.boxAddPicture.fabAddPic
    }






}