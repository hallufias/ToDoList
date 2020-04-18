package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var todoItemRecyclerView : RecyclerView
    private lateinit var recyclerAdapter: TodoItemsAdapter //assign var jd object todoitemsadapter
    private lateinit var recyclerLayoutManager : RecyclerView.LayoutManager

    var todoItemsList = ArrayList<TodoItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //call database
        val dbo = DatabaseOperations(this)
        val cursor = dbo.getAllItems(dbo)
        with(cursor){
            while (moveToNext()){
                val itemName = getString(getColumnIndex(DatabaseInfo.TableInfo.COLUMN_ITEM_NAME))
                val itemUrgency = getInt(getColumnIndex(DatabaseInfo.TableInfo.COLUMN_ITEM_URGENCY))
                val isUrgent = if(itemUrgency == 0) false else true
                todoItemsList.add(TodoItem(itemName,isUrgent))
            }
        }

//        todoItemsList.add(TodoItem("Buy Groceries"))
//        todoItemsList.add(TodoItem("Do Laundry", true))
//        todoItemsList.add(TodoItem("Play Guitar", false))

        todoItemRecyclerView = findViewById(R.id.rvTodoList)

        recyclerLayoutManager = LinearLayoutManager(this) //view from top to bottom layout
        recyclerAdapter = TodoItemsAdapter(todoItemsList, this)

        //cara pertama
        //todoItemRecyclerView.setHasFixedSize(true)
        //todoItemRecyclerView.layoutManager = recyclerLayoutManager
        //todoItemRecyclerView.adapter = recyclerAdapter

        //cara kedua
        todoItemRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = recyclerLayoutManager
            adapter = recyclerAdapter
        }
    }

    fun navToAddItemPage(view : View){
        val intent = Intent(this, AddItemActivity::class.java)
        startActivity(intent)
    }
}
