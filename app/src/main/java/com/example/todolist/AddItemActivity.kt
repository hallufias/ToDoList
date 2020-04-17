package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_add_item.*

class AddItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)


    }

    fun cancelItemAction (view: View){
        val cancelIntent = Intent(this, MainActivity::class.java)
        startActivity(cancelIntent)
    }

    fun saveItemAction (view: View){

    }
}
