package com.icha.projectmobile_kel4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.admin_tambahmenu.*

class admin_tambahmenu : AppCompatActivity(),AdapterView.OnItemSelectedListener {
    var categori = arrayOf("Makanan", "Minuman", "Cemilan")
    var spinner:Spinner? = null
    var textView_msg:TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_tambahmenu)

        spinner = this.kategori
        spinner!!.setOnItemSelectedListener(this)

        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, categori)
        // Set layout to use when the list of choices appear
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        spinner!!.setAdapter(aa)

    }
    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
        textView_msg!!.text = "Selected : "+categori[position]
    }
    override fun onNothingSelected(arg0: AdapterView<*>) {
    }
}