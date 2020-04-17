package com.example.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class TodoItemsAdapter(private val todoItemsList: ArrayList<TodoItem>) : RecyclerView.Adapter<TodoItemsAdapter.ToDoItemHolder>() {

    class ToDoItemHolder(val constraintLayout: ConstraintLayout) : RecyclerView.ViewHolder(constraintLayout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoItemHolder {
        //panggil layout to_do_item_layout masuk ke sini
        val constraintLayout = LayoutInflater.from(parent.context).inflate(R.layout.to_do_item_layout, parent, false) as ConstraintLayout
        return ToDoItemHolder(constraintLayout)
    }

    override fun onBindViewHolder(holder: ToDoItemHolder, position: Int) {
        //sambungkan layout to_do_item_layout
        val constraintLayout = holder.constraintLayout
        //
        val nameTextView = constraintLayout.getChildAt(0) as TextView
        val urgentTextView = constraintLayout.getChildAt(1) as TextView
        nameTextView.text = todoItemsList[position].name
        urgentTextView.text = if(todoItemsList[position].isUrgent) "!!" else ""
    }

    override fun getItemCount(): Int {
        return todoItemsList.size
    }

}