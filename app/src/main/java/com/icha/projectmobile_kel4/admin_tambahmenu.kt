package com.icha.projectmobile_kel4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.admin_tambahmenu.*

class admin_tambahmenu : AppCompatActivity() {
    lateinit var ref : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_tambahmenu)

        ref = FirebaseDatabase.getInstance().getReference("Menu")

        tambahmenu_button.setOnClickListener {
            savedata()
        }

    }

    private fun savedata() {
        val enamaMenu = menu.text.toString()
        val ehargaMenu = harga.text.toString().toInt()
        val ekategori = kategori.text.toString()

        val menu2 = Menu(enamaMenu,ehargaMenu,ekategori)
        val menuId = ref.push().key.toString()

        ref.child(menuId).setValue(menu2).addOnCompleteListener {
            Toast.makeText(this, "Successs",Toast.LENGTH_SHORT).show()
            menu.setText("")
            harga.setText("")
            kategori.setText("")
        }
    }
}