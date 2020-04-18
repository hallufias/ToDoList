package com.example.todolist

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class TodoItemsAdapter(private val todoItemsList: ArrayList<TodoItem>, val activity: MainActivity) : RecyclerView.Adapter<TodoItemsAdapter.ToDoItemHolder>() {

    class ToDoItemHolder(val constraintLayout: ConstraintLayout) : RecyclerView.ViewHolder(constraintLayout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoItemHolder {
        //panggil layout to_do_item_layout masuk ke sini
        val constraintLayout = LayoutInflater.from(parent.context).inflate(R.layout.to_do_item_layout, parent, false) as ConstraintLayout

        constraintLayout.setOnClickListener(View.OnClickListener {
            val nameTextView = constraintLayout.getChildAt(0) as TextView
            val urgencyTextView = constraintLayout.getChildAt(1) as TextView

            val nameText = nameTextView.text
            val urgencyText = urgencyTextView.text
            val isItemUrgent = if(urgencyText == "!!") true else false

            //val activity : MainActivity
            val intent = Intent(parent.context, AddItemActivity::class.java)
            intent.putExtra("EXTRA_ITEM_NAME", nameText)
            intent.putExtra("EXTRA_ITEM_URGENCY", isItemUrgent)
            activity.startActivity(intent)

        })

        constraintLayout.setOnLongClickListener(View.OnLongClickListener {
            val position = parent.indexOfChild(it)

            //delete from database
            val todoItemToRemove = activity.todoItemsList[position]
            val dbo = DatabaseOperations(parent.context)
            dbo.deleteItem(dbo,todoItemToRemove)

            activity.todoItemsList.removeAt(position)
            notifyItemRemoved(position)// the adapter that we remove this item
            true
        })

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