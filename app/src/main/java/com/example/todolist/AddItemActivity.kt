package com.example.todolist

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_add_item.*

class AddItemActivity : AppCompatActivity() {

    private lateinit var itemNameEditText: EditText
    private lateinit var isUrgentCheckBox: CheckBox
    private lateinit var titleTextView: TextView
    private lateinit var constraintLayout: ConstraintLayout

    var isNewItem = true
    lateinit var oldItem: TodoItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        itemNameEditText = findViewById(R.id.etTodoItem)
        isUrgentCheckBox = findViewById(R.id.cbUrgent)
        titleTextView = findViewById(R.id.tvAddItemTitle)
        constraintLayout = findViewById(R.id.clAddItem)

        constraintLayout.setBackgroundColor(Color.MAGENTA)

        val itemName = intent.getStringExtra("EXTRA_ITEM_NAME")
        val itemUrgency = intent.getBooleanExtra("EXTRA_ITEM_URGENCY", false)

        if (itemName != null) {
            itemNameEditText.setText(itemName)
            titleTextView.setText(R.string.edit_item_message)
            constraintLayout.setBackgroundColor(Color.CYAN)
        }

        if (itemUrgency) {
            isUrgentCheckBox.isChecked = true
        }
    }

    fun cancelItemAction(view: View) {
        val cancelIntent = Intent(this, MainActivity::class.java)
        startActivity(cancelIntent)
    }

    fun saveItemAction(view: View) {
        val itemName = etTodoItem.text.toString()
        val isItemUrgent = cbUrgent.isChecked
        val newTodoItem = TodoItem(itemName, isItemUrgent)

        if(itemName.isEmpty()){
            Toast.makeText(this, "Please insert a todo task", Toast.LENGTH_SHORT).show()
        }else{
            val dbo = DatabaseOperations(this)
            if (isNewItem) {
                dbo.addItem(dbo, newTodoItem)
            } else {
                dbo.updateItem(dbo, this.oldItem, newTodoItem)
            }

            val saveIntent = Intent(this, MainActivity::class.java)
            Toast.makeText(this, "Item Added to Todo List", Toast.LENGTH_SHORT).show()
            startActivity(saveIntent)
        }
    }
}