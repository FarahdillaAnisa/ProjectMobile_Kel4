package com.icha.projectmobile_kel4

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class ListMakanan : AppCompatActivity() {

    lateinit var ref : DatabaseReference
    lateinit var list : MutableList<Menu>
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        ref = FirebaseDatabase.getInstance().getReference("Menu")
        list = mutableListOf()
        listView = findViewById(R.id.listView)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()){

                    for (h in p0.children){
                        val menu = h.getValue(Menu::class.java)
                        list.add(menu!!)
                    }
                    val adapter = Adapter(applicationContext,R.layout.fragment_makanan,list)
                    listView.adapter = adapter
                }
            }
        })
    }
}