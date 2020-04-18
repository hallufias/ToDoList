package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_add_item.*

class AddItemActivity : AppCompatActivity() {

    private lateinit var itemNameEditText: EditText
    private lateinit var isUrgentCheckBox: CheckBox
    private lateinit var titleTextView: TextView

    var isNewItem = true
    lateinit var oldItem: TodoItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        itemNameEditText = findViewById(R.id.etTodoItem)
        isUrgentCheckBox = findViewById(R.id.cbUrgent)
        titleTextView = findViewById(R.id.tvAddItemTitle)

        val itemName = intent.getStringExtra("EXTRA_ITEM_NAME")
        val itemUrgency = intent.getBooleanExtra("EXTRA_ITEM_URGENCY", false)

        if (itemName != null) {
            itemNameEditText.setText(itemName)
            titleTextView.setText(R.string.edit_item_message)
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

        val dbo = DatabaseOperations(this)
        if (isNewItem) {
            dbo.addItem(dbo, newTodoItem)
        } else {
            dbo.updateItem(dbo, this.oldItem, newTodoItem)
        }
    }
}
