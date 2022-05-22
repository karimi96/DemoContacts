package com.karimi.contacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.karimi.contacts.adapter.ContactAdapter
import com.karimi.contacts.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var listContacts : ArrayList<String>
    lateinit var listAlfabet : ArrayList<String>
    lateinit var position : ArrayList<Int>
    lateinit var contactAdapter : ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        fullArrayList()
        listContacts.sort()
        initRecycler()
        getFirstIndex()

        Log.e("222", "onCreate: $listAlfabet " )
        Log.e("222", "onCreate: $position" )


    }

    fun fullArrayList(){
        listContacts = ArrayList()
        listContacts.add("Ali")
        listContacts.add("Mostafa")
        listContacts.add("Mostafa")
        listContacts.add("Mostafa")
        listContacts.add("Mostafa")
        listContacts.add("Mostafa")
        listContacts.add("Milad")
        listContacts.add("Mohsen")
        listContacts.add("Mohsen")
        listContacts.add("AliReza")
        listContacts.add("AliReza")
        listContacts.add("Mahdi")
        listContacts.add("Hadi")
        listContacts.add("Hadi")
        listContacts.add("Mohammad")
        listContacts.add("Mohammad")
        listContacts.add("Basir")
    }


    fun initRecycler(){
        contactAdapter = ContactAdapter(listContacts,this)
        binding.recycler.adapter = contactAdapter
    }


    fun getFirstIndex(){
        position = ArrayList();
        position.add(0)

        listAlfabet = ArrayList()
        listAlfabet.add(0 ,listContacts[0][0].uppercase() )

        var i = 1
        while (i < listContacts.size){
            if(listContacts[i][0].uppercase() in listAlfabet){
                i++
                continue
            } else {
                listAlfabet.add(listContacts[i][0].uppercase() + "")
                position.add(i)
                i++
            }
        }
    }









}