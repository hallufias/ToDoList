package com.example.todolist

import java.util.*

class TodoItem (var name:String){

    var isUrgent = false
    var date = Calendar.getInstance()
    var dateString : String = getDateAsString()

    constructor(name: String, isUrgent : Boolean) : this(name){
        this.isUrgent = isUrgent
    }

    fun getDateAsString(): String{
        val day = date.get(Calendar.DAY_OF_MONTH).toString()
        val month = date.get(Calendar.MONTH).toString()
        val year = date.get(Calendar.YEAR).toString()

        return "$year/$month/$day"
    }
}