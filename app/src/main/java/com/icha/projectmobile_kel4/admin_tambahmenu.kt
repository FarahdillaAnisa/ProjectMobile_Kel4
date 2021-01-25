package com.icha.projectmobile_kel4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View
import android.widget.*
import android.content.Intent
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.admin_tambahmenu.*

class admin_tambahmenu : AppCompatActivity(),AdapterView.OnItemSelectedListener, View.OnClickListener {
    //baru tambahan
    private lateinit var edmenu: EditText
    private lateinit var edharga: EditText
    private lateinit var btnsimpanMenu: Button
    private lateinit var ref: DatabaseReference
    private lateinit var menuList: MutableList<Menu>

    var categori = arrayOf("Makanan", "Minuman", "Cemilan")
    var spinner:Spinner? = null
    var textView_msg:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_tambahmenu)

        //baru tambahan
        ref = FirebaseDatabase.getInstance().getReference("menu")
        edmenu = findViewById(R.id.menu)
        edharga = findViewById(R.id.harga)
        btnsimpanMenu = findViewById(R.id.tambahmenu_button)
        tambahmenu_button.setOnClickListener(this)
        menuList= mutableListOf()

        spinner = this.kategori
        spinner!!.setOnItemSelectedListener(this)

        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, categori)
        // Set layout to use when the list of choices appear
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        spinner!!.setAdapter(aa)

        //baru
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    menuList.clear()
                    for (a in snapshot.children){
                        val menu = a.getValue(Menu::class.java)
                        if (menu != null){
                            menuList.add(menu)
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    //baru
    override fun onClick(v: View?) {
        simpanData()
    }

    private fun simpanData(){
        val nama = edmenu.text.toString().trim()
        val editharga = edharga.text.toString()
        val harga :Int = editharga.toInt()
        val kategori = spinner?.selectedItem.toString()

        if (nama.isEmpty() or editharga.isEmpty() or kategori.isEmpty()){
            Toast.makeText(this, "Isi data secara lengkap tidak boleh kosong",
                Toast.LENGTH_SHORT).show()
            return
        }

        val menuId = ref.push().key
        val menu = Menu(menuId!!, nama, harga, kategori)

        ref.child(menuId).setValue(menu).addOnCompleteListener {
            Toast.makeText(applicationContext, "Data berhasil ditambahkan",
                Toast.LENGTH_SHORT).show()
        }
    }
    //baru
    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
        textView_msg!!.text = "Selected : "+categori[position]
    }
    override fun onNothingSelected(arg0: AdapterView<*>) {
    }
}