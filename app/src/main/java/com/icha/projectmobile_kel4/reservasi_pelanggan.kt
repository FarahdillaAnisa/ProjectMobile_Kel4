package com.icha.projectmobile_kel4
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.view.View
import android.widget.Button
import android.widget.*
import java.util.*
import android.content.Intent
import android.app.DatePickerDialog
import android.os.PersistableBundle
import android.widget.DatePicker
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import kotlinx.android.synthetic.main.halaman_reservasi_pelanggan.*

class reservasi_pelanggan : AppCompatActivity(), View.OnClickListener{
    //baru tambahan
    private lateinit var edNama: EditText
    private lateinit var edHP: EditText
    private lateinit var edMeja: EditText
    private lateinit var btnreservasi: Button
    private lateinit var listreservasi: ListView
    private lateinit var ref: DatabaseReference
    private lateinit var reservasiList: MutableList<Reservasi>
    private lateinit var button_date : Button
    private lateinit var textView_date: TextView
    var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.halaman_reservasi_pelanggan)

        //baru tambahan
        ref = FirebaseDatabase.getInstance().getReference("reservasi")
        edNama = findViewById(R.id.edtNama)
        edHP = findViewById(R.id.edtNoHp)
        edMeja = findViewById(R.id.edtMeja)
        btnreservasi = findViewById(R.id.btnReservasi)
        listreservasi = findViewById(R.id.lv_reservasi)
        btnreservasi.setOnClickListener(this)
        reservasiList= mutableListOf()

        //--------------elemen tanggal -------------------------
        //ambil elemen dari layout
        textView_date = findViewById(R.id.edtTgl)
        button_date = findViewById(R.id.btnedttgl)
        textView_date!!.text = "--/--/----"

        //buat onDateSetListener
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }

        // pada saat klik akan show DatePickerDialog - OnDateSetListener
        button_date!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(this@reservasi_pelanggan,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }
        })

        //bagian tambah reservasi
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    reservasiList.clear()
                    for (a in snapshot.children){
                        val reservasi = a.getValue(Reservasi::class.java)
                        if (reservasi != null){
                            reservasiList.add(reservasi)
                        }
                    }

                    val adapter = ReservasiAdapter(this@reservasi_pelanggan,
                        R.layout.listview_reservasi_pelanggan, reservasiList)
                    listreservasi.adapter = adapter
                    println("Output : " + reservasiList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        listreservasi.setOnItemClickListener { parent, view, position, id ->
            val reservasi = reservasiList.get(position)
            val intent = Intent(this@reservasi_pelanggan, tambah_pemesananmenu::class.java)
            intent.putExtra(tambah_pemesananmenu.EXTRA_ID, reservasi.id)
            intent.putExtra(tambah_pemesananmenu.EXTRA_NAMA, reservasi.namapemesan)
            startActivity(intent)
        }
    }
    //tambah reservasi

    override fun onClick(v: View?) {
        simpanData()
    }

    private fun simpanData(){
        val nama = edNama.text.toString().trim()
        val hp = edHP.text.toString()
        val meja = edMeja.text.toString()
        val jumlahmeja = meja.toInt()

        if (nama.isEmpty() or hp.isEmpty() or meja.isEmpty()){
            Toast.makeText(this, "Isi data secara lengkap tidak boleh kosong",
                Toast.LENGTH_SHORT).show()
            return
        }

        val reservasiId = ref.push().key
        val reservasi = Reservasi(reservasiId!!, nama, hp, jumlahmeja)

        ref.child(reservasiId).setValue(reservasi).addOnCompleteListener {
            Toast.makeText(applicationContext, "Data berhasil ditambahkan",
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy" // format tanggal
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        textView_date!!.text = sdf.format(cal.getTime())
    }
    //--------------elemen tanggal -------------------------
}
