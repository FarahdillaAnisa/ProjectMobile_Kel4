package com.icha.projectmobile_kel4
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Guideline
import com.google.firebase.database.*
import org.w3c.dom.Text

class tambah_pemesananmenu : AppCompatActivity() {
    private lateinit var judul : Toolbar
    private lateinit var img1 : ImageView
    private lateinit var img2 : ImageView
    private lateinit var imgmenu : ImageView
    private lateinit var spinmenu : Spinner
    private lateinit var imgharga : ImageView
    private lateinit var edtharga : EditText
    private lateinit var imgjumlah : ImageView
    private lateinit var edtjumlah : EditText
    private lateinit var imgtotal : ImageView
    private lateinit var edtTotal : TextView
    private lateinit var btnPesan : Button
    private lateinit var guideline: Guideline
    private lateinit var lvTambahPesan : ListView
    private lateinit var pesanList : MutableList<Pesanmenu>
    private lateinit var ref : DatabaseReference

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_NAMA = "extra_nama"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tambah_pemesananmenu_pelanggan)

        judul = findViewById(R.id.toolbar_addmenu)
        img1 = findViewById(R.id.img_banner1)
        img2 = findViewById(R.id.img_banner)
        imgmenu = findViewById(R.id.img_menu)
        spinmenu = findViewById(R.id.spinnermenu)
        imgharga = findViewById(R.id.img_harga)
        edtharga = findViewById(R.id.edt_harga1)
        imgjumlah = findViewById(R.id.img_jumlah)
        edtjumlah = findViewById(R.id.edt_jumlah1)
        imgtotal = findViewById(R.id.img_total)
        edtTotal = findViewById(R.id.edt_Total)
        btnPesan = findViewById(R.id.btn_tambahPesan)
        guideline = findViewById(R.id.guideline2)
        lvTambahPesan = findViewById(R.id.lv_tambahPesan)

        val id = intent.getStringExtra(EXTRA_ID)
        val nama = intent.getStringExtra(EXTRA_NAMA)

        pesanList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("pemesanan menu")
            .child(id!!)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    pesanList.clear()
                    for (a in snapshot.children){
                        val pesan = a.getValue(Pesanmenu::class.java)
                        if (pesan != null){
                            pesanList.add(pesan)
                        }
                    }

                    val adapter = PemesananAdapter(this@tambah_pemesananmenu,
                        R.layout.listview_pemesananmenu_pelanggan, pesanList)
                    lvTambahPesan.adapter = adapter

                    println("output : " + pesanList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        btnPesan.setOnClickListener{
            simpanPesan()
        }
    }

    private fun simpanPesan() {
        val menu = spinmenu.selectedItem.toString()
        val edtharga = edtharga.text.toString()
        val harga = edtharga.toInt()
        val edtjumlah = edtjumlah.text.toString()
        val jumlah = edtjumlah.toInt()
        val edttotal: Int = harga * jumlah
        edtTotal.text = getString(edttotal)

        val pesanId = ref.push().key
        val pesan = Pesanmenu(pesanId!!, menu, harga, jumlah, edttotal)
        ref.child(pesanId).setValue(pesan).addOnCompleteListener {
            Toast.makeText(
                applicationContext, "Informasi Pemesanan berhasil ditambahkan",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}