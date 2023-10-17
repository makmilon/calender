package com.example.myapplication



import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.GridView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar


class MainActivity : AppCompatActivity() {

    private var currentDate: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gridView = findViewById<GridView>(R.id.gridView)
        gridView.adapter = CalendarAdapter()

        val prevMonthButton = findViewById<Button>(R.id.prevMonthButton)
        val nextMonthButton = findViewById<Button>(R.id.nextMonthButton)

        prevMonthButton.setOnClickListener {
            currentDate.add(Calendar.MONTH, -1)
            gridView.adapter = CalendarAdapter()
        }

        nextMonthButton.setOnClickListener {
            currentDate.add(Calendar.MONTH, 1)
            gridView.adapter = CalendarAdapter()
        }
    }

    inner class CalendarAdapter : BaseAdapter() {
        override fun getCount(): Int {
            return currentDate.getActualMaximum(Calendar.DAY_OF_MONTH)
        }

        override fun getItem(position: Int): Any {
            return position + 1
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val inflater = LayoutInflater.from(this@MainActivity)
            val dateItemView = inflater.inflate(R.layout.grid_item_date, parent, false)

            val dayOfMonth = position + 1
            val dateTextView = dateItemView.findViewById<TextView>(R.id.dateTextView)
            dateTextView.text = dayOfMonth.toString()

            val isPresent = isDatePresent(dayOfMonth)

            val backgroundColor = if (isPresent) R.color.greenColor else R.color.redColor
            dateItemView.setBackgroundResource(backgroundColor)

            return dateItemView
        }
    }


    // Example function to determine if a date is present or absent
    private fun isDatePresent(dayOfMonth: Int): Boolean {
        // Customize this function to determine if a date is present or absent in your logic
        // For this example, we'll mark the dates 17, 3, 22, and 11 as absent (red)
        return dayOfMonth !in listOf(17, 3, 22, 11)
    }

}
