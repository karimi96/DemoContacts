package com.karimi.contacts.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.text.TextUtils
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.karimi.contacts.adapter.ContactAdapter
import com.karimi.contacts.databinding.ActivityMainBinding
import com.karimi.contacts.model.User
import kotlin.collections.ArrayList

import com.karimi.contacts.R


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var listContacts : ArrayList<User>


    lateinit var listAlfabet : ArrayList<String>
    lateinit var position : ArrayList<Int>
    lateinit var contactAdapter : ContactAdapter

    private val requestCall = 1




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        fullArrayList()
        initRecycler()
        getFirstIndex()


        binding.searchBar.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                contactAdapter.filter.filter(newText)
                contactAdapter.filter?.filter(newText)
                return false
            }

        })




//        binding.eee.addTextChangedListener(object : TextWatcher {
//            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//
//                // TODO Auto-generated method stub
//            }
//
//            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
//
//                // TODO Auto-generated method stub
//            }
//
//            override fun afterTextChanged(s: Editable) {
//
//                // filter your list from your input
//                filter(s.toString())
//                //you can use runnable postDelayed like 500 ms to delay search text
//            }
//        })

       










//        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
//            androidx.appcompat.widget.SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: User): Boolean {
//                if (listContacts.contains(query)) {
//                    contactAdapter.filter.filter(query.firstName)
//                } else {
//                    Toast.makeText(this@MainActivity, "No Match found", Toast.LENGTH_LONG).show()
//                }
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String): Boolean {
//                contactAdapter.filter.filter(newText)
//                return false
//            }
//        })




       /* fun filter(var text :String){
            var temp = ArrayList<User>()
            if(DataHolder d: displayedList){
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if(d.getEnglish().contains(text)){
                temp.add(d);
            }
        }
            //update recyclerview
            disp_adapter.updateList(temp);
        }*/
        



    }

    fun fullArrayList(){
        listContacts = ArrayList()
        listContacts.add(User("Ali","karimi" ,"" , ""))
        listContacts.add(User("Mostafa","karimi" ,"" , ""))
        listContacts.add(User("Mostafa","karimi" ,"" , ""))
        listContacts.add(User("Mostafa","karimi" ,"" , ""))
        listContacts.add(User("Milad","karimi" ,"" , ""))
        listContacts.add(User("Mohsen","karimi" ,"" , ""))
        listContacts.add(User("Mohsen","karimi" ,"" , ""))
        listContacts.add(User("AliReza","karimi" ,"" , ""))
        listContacts.add(User("AliReza","karimi" ,"" , ""))
        listContacts.add(User("Mahdi","karimi" ,"" , ""))
        listContacts.add(User("Hadi","karimi" ,"" , ""))
        listContacts.add(User("Bsir","karimi" ,"" , ""))
        listContacts.add(User("Roghaye","karimi" ,"" , ""))
        listContacts.add(User("Payaam","karimi" ,"" , ""))

        listContacts.sortBy { it.firstName }
    }


    fun initRecycler(){
        contactAdapter = ContactAdapter(listContacts,this , this)
        binding.recycler.adapter = contactAdapter
    }



    fun getFirstIndex(){
        position = ArrayList();
        position.add(0)

        listAlfabet = ArrayList()
        listAlfabet.add(0 ,listContacts[0].firstName[0].uppercase() )

        var i = 1
        while (i < listContacts.size){
            if(listContacts[i].firstName[0].uppercase() in listAlfabet){
                i++
                continue
            } else {
                listAlfabet.add(listContacts[i].firstName[0].uppercase() + "")
                position.add(i)
                i++
            }
        }
    }


    fun Call(view: View) {
        // Use format with "tel:" and phoneNumber created is
        // stored in u.
        val u = Uri.parse("tel:" + "")
        // Create the intent and set the data for the
        // intent as the phone number.
        val i = Intent(Intent.ACTION_DIAL, u)
        try {
            // Launch the Phone app's dialer with a phone
            // number to dial a call.
            startActivity(i)
        } catch (s: SecurityException) {
            // show() method display the toast with
            // exception message.
            Toast.makeText(this, "An error occurred", Toast.LENGTH_LONG)
                .show()
        }


    }



    fun addContact(view: View) {
        val intent = Intent(this,CreateContactActivity::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }


     fun makePhoneCall() {
        val number: String = "12222"
        if (number.trim { it <= ' ' }.isNotEmpty()) {
            if (ContextCompat.checkSelfPermission(
                    this@MainActivity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.CALL_PHONE),
                    requestCall
                )
            } else {
                val dial = "tel:$number"
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse(dial)))
            }
        } else {
            Toast.makeText(this@MainActivity, "Enter Phone Number", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == requestCall) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall()
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show()
            }
        }
    }





    fun myMessage() {
        val myNumber = "11"
        val myMsg = "hello"
        if (myNumber == "" || myMsg == "") {
            Toast.makeText(this, "Field cannot be empty", Toast.LENGTH_SHORT).show()
        } else {
            if (TextUtils.isDigitsOnly(myNumber)) {
                var phoneNumber="22222222"
                val uri = Uri.parse("smsto:$phoneNumber")
                startActivity(Intent(Intent.ACTION_SENDTO, uri))

            } else {
                Toast.makeText(this, "Please enter the correct number", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }





}