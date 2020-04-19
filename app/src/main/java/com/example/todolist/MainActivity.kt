package com.example.todolist

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var todoItemRecyclerView : RecyclerView
    private lateinit var recyclerAdapter: TodoItemsAdapter //assign var jd object todoitemsadapter
    private lateinit var recyclerLayoutManager : RecyclerView.LayoutManager

    private lateinit var todaysItemButton : Button
    private lateinit var pastItemButton : Button

    var todoItemsList = ArrayList<TodoItem>()

    var todaysItemsList = ArrayList<TodoItem>()
    var pastItemsList = ArrayList<TodoItem>()

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
                val itemDate = getString(getColumnIndex(DatabaseInfo.TableInfo.COLUMN_ITEM_DATE))
//                todoItemsList.add(TodoItem(itemName,isUrgent))

                val newTodoItems = TodoItem(itemName, isUrgent)
                newTodoItems.dateString = itemDate //
                todoItemsList.add(newTodoItems)// Display semua list

                if(itemDate == getDateAsString()){
                    todaysItemsList.add(newTodoItems)// display date hari ini
                }else{
                    pastItemsList.add(newTodoItems)// display date semalam
                }
            }
        }

//        todoItemsList.add(TodoItem("Buy Groceries"))
//        todoItemsList.add(TodoItem("Do Laundry", true))
//        todoItemsList.add(TodoItem("Play Guitar", false))

        todoItemRecyclerView = findViewById(R.id.rvTodoList)
        todaysItemButton = findViewById(R.id.btnTodayItem)
        pastItemButton = findViewById(R.id.btnPastItem)

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

    fun displayTodayItem(view :View){
        recyclerAdapter = TodoItemsAdapter(todaysItemsList, this)
        todoItemRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = recyclerLayoutManager
            adapter = recyclerAdapter
        }

        todaysItemButton.setBackgroundColor(Color.BLACK)
        todaysItemButton.setTextColor(Color.WHITE)
        pastItemButton.setBackgroundColor(Color.WHITE)
        pastItemButton.setTextColor(Color.BLACK)
    }

    fun displayPastItem(view :View){
        recyclerAdapter = TodoItemsAdapter(pastItemsList, this)
        todoItemRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = recyclerLayoutManager
            adapter = recyclerAdapter
        }
        pastItemButton.setBackgroundColor(Color.BLACK)
        pastItemButton.setTextColor(Color.WHITE)
        todaysItemButton.setBackgroundColor(Color.WHITE)
        todaysItemButton.setTextColor(Color.BLACK)
    }

    fun getDateAsString(): String{
        val date = Calendar.getInstance()
        val day = date.get(Calendar.DAY_OF_MONTH).toString()
        val month = date.get(Calendar.MONTH).toString()
        val year = date.get(Calendar.YEAR).toString()

        return "$year/$month/$day"
    }
}
