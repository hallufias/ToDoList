package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var todoItemRecyclerView : RecyclerView
//    private lateinit var recyclerAdapter: TodoItemsAdapter
    private lateinit var recyclerLayoutManager : RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        todoItemsList.add(TodoItem("Buy Groceries"))
//        todoItemsList.add(TodoItem("Do Laundry", true))
//        todoItemsList.add(TodoItem("Play Guiter", false))

//        todoItemRecyclerView = findViewById(R.id.rvTodoList)
//
//        recyclerLayoutManager = LinearLayoutManager(this) //view from top to bottom layout
//        recyclerAdapter = TodoItemsAdapter(todoItemsList, this)
//
//        todoItemRecyclerView.apply {
//            setHasFixedSize(true)
//            layoutManager = recyclerLayoutManager
//            adapter = recyclerAdapter
//        }
    }

    fun navToAddItemPage(view : View){
        val intent = Intent(this, AddItemActivity::class.java)
        startActivity(intent)
    }
}
