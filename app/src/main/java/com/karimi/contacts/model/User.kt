package com.karimi.contacts.model

import kotlin.properties.Delegates

class User {
    lateinit var firstName : String
    lateinit var lastName : String
    lateinit var phone : String
    lateinit var email : String
    var picture by Delegates.notNull<Int>()



    constructor(firstName: String, lastName: String, phone: String, email: String, picture: Int) {
        this.firstName = firstName
        this.lastName = lastName
        this.phone = phone
        this.email = email
        this.picture = picture
    }


    constructor(firstName: String, lastName: String, phone: String, email: String) {
        this.firstName = firstName
        this.lastName = lastName
        this.phone = phone
        this.email = email
    }


    constructor(firstName: String, lastName: String, phone: String) {
        this.firstName = firstName
        this.lastName = lastName
        this.phone = phone
    }



}