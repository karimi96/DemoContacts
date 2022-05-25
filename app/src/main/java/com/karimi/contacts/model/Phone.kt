package com.karimi.contacts.model

class Phone {
    var phone: String

    constructor(phone: String) {
        this.phone = phone
    }

    @JvmName("setPhone1")
    fun setPhone(phone: String){
        this.phone = phone
    }

    @JvmName("getPhone1")
    fun getPhone() : String{
        return phone
    }



}